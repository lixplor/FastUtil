/*
 *  Copyright 2016 Lixplor
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.fantasymaker.fmutils.utils.calculate;

import android.util.Base64;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.fantasymaker.fmutils.utils.develop.LogUtil;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class EncryptUtil {

    /*
     todo
      各种加密算法
      xbase64
      xurl
      x对称加密: des, 3des, aes, pbe
      非对称加密: dh, rsa, elgamal
      x数字签名: rsa, dsa, ecdsa
      消息摘要: md5, sha1, sha2, mac
      x摩斯电码
      x自定义加密
      crc校验
     */

    private static final int SALT = 888;
    private static final int LENGTH_512 = 512;
    private static final int LENGTH_1024 = 1024;
    private static final int LENGTH_2048 = 2048;

    public static final String TYPE_DES = "DES";
    public static final String TYPE_3DES = "DESede";
    public static final String TYPE_AES = "AES";
    public static final String TYPE_PBE_MD5_DES = "PBEWITHMD5andDES";
    public static final String TYPE_PBE_MD5_RC2 = "PBEWITHMD5andRC2";
    public static final String TYPE_PBE_SHA1_DES = "PBEWITHSHA1andDES";
    public static final String TYPE_PBE_SHA1_RC2 = "PBEWITHSHA1andRC2";
    public static final String TYPE_RSA = "RSA";
    public static final String TYPE_DSA = "DSA";
    public static final String TYPE_ECDSA = "EC";
    public static final String TYPE_MD2 = "MD2";
    public static final String TYPE_MD5 = "MD5";
    public static final String TYPE_SHA1 = "SHA-1";
    public static final String TYPE_SHA256 = "SHA-256";
    public static final String TYPE_SHA384 = "SHA-384";
    public static final String TYPE_SHA512 = "SHA-512";
    public static final String TYPE_HMAC_MD5 = "HmacMD5";
    public static final String TYPE_HMAC_SHA1 = "HmacSHA1";
    public static final String TYPE_HMAC_SHA256 = "HmacSHA256";
    public static final String TYPE_HMAC_SHA384 = "HmacSHA384";
    public static final String TYPE_HMAC_SHA512 = "HmacSHA512";
    public static final String CIPHER_DES_ECB_PKCS5 = "DES/ECB/PKCS5Padding";
    public static final String CIPHER_3DES_ECB_PKCS5 = "DESede/ECB/PKCS5Padding";
    public static final String CIPHER_AES_ECB_PKCS5 = "AES/ECB/PKCS5Padding";
    public static final String SIGN_MD2_RSA = "MD2withRSA";
    public static final String SIGN_MD5_RSA = "MD5withRSA";
    public static final String SIGN_SHA1_RSA = "SHA1withRSA";
    public static final String SIGN_SHA1_DSA = "SHA1withDSA";
    public static final String SIGN_NONE_ECDSA = "NONEwithECDSA";
    public static final String SIGN_SHA1_ECDSA = "SHA1withECDSA";
    public static final String SIGN_SHA256_ECDSA = "SHA256withECDSA";
    public static final String SIGN_SHA384_ECDSA = "SHA384withECDSA";
    public static final String SIGN_SHA512_ECDSA = "SHA512withECDSA";

    private EncryptUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /*BASE64*/

    /**
     * base64 encode
     *
     * @param src string to be encoded
     * @return encoded string
     */
    public static byte[] base64Encode(byte[] src) {
        return src == null ? null : Base64.encode(src, Base64.URL_SAFE);
    }

    /**
     * base64 encode
     *
     * @param src string to be encoded
     * @return encoded string
     */
    public static byte[] base64Encode(String src) {
        return src == null ? null : Base64.encode(src.getBytes(), Base64.URL_SAFE);
    }

    /**
     * base64 encode
     *
     * @param src byte array to be encoded
     * @return encoded string
     */
    public static String base64EncodeToString(byte[] src) {
        return src == null ? null : new String(Base64.encode(src, Base64.URL_SAFE));
    }

    /**
     * base64 encode
     *
     * @param src string to be encoded
     * @return encoded string
     */
    public static String base64EncodeToString(String src) {
        return src == null ? null : new String(Base64.encode(src.getBytes(), Base64.URL_SAFE));
    }

    /**
     * base64 encode
     *
     * @param src   byte array to be encoded
     * @param flags encode type
     * @return encoded string
     */
    public static String base64EncodeToString(byte[] src, int flags) {
        return src == null ? null : new String(Base64.encode(src, flags));
    }

    /**
     * base64 encode
     *
     * @param src   string to be encoded
     * @param flags encode type
     * @return encoded string
     */
    public static String base64EncodeToString(String src, int flags) {
        return src == null ? null : new String(Base64.encode(src.getBytes(), flags));
    }

    /**
     * base64 decode
     *
     * @param src encoded byte array to be decoded
     * @return decoded string
     */
    public static byte[] base64Decode(byte[] src) {
        return src == null ? null : Base64.decode(src, Base64.URL_SAFE);
    }

    /**
     * base64 decode
     *
     * @param src encoded byte array to be decoded
     * @return decoded string
     */
    public static byte[] base64Decode(String src) {
        return src == null ? null : Base64.decode(src, Base64.URL_SAFE);
    }

    /**
     * base64 decode
     *
     * @param src encoded byte array to be decoded
     * @return decoded string
     */
    public static String base64DecodeToString(byte[] src) {
        return src == null ? null : new String(Base64.decode(src, Base64.URL_SAFE));
    }

    /**
     * base64 decode
     *
     * @param src encoded string to be decoded
     * @return decoded string
     */
    public static String base64DecodeToString(String src) {
        return src == null ? null : new String(Base64.decode(src.getBytes(), Base64.URL_SAFE));
    }

    /**
     * base64 decode
     *
     * @param src   encoded byte array to be decoded
     * @param flags decode type
     * @return decoded string
     */
    public static String base64DecodeToString(byte[] src, int flags) {
        return src == null ? null : new String(Base64.decode(src, flags));
    }

    /**
     * base64 decode
     *
     * @param src   encoded string to be decoded
     * @param flags decode type
     * @return decoded string
     */
    public static String base64DecodeToString(String src, int flags) {
        return src == null ? null : new String(Base64.decode(src.getBytes(), flags));
    }

    /*URL*/

    /**
     * URL encode
     *
     * @param src         string to be encoded
     * @param charsetName charset name for encoding
     * @return encoded url string
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String src, String charsetName) throws UnsupportedEncodingException {
        return src == null ? null : URLEncoder.encode(src, charsetName);
    }

    /**
     * URL decode
     *
     * @param src         encoded string to be decoded
     * @param charsetName charset name for decoding
     * @return decoded url string
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(String src, String charsetName) throws UnsupportedEncodingException {
        return src == null ? null : URLDecoder.decode(src, charsetName);
    }

    /*对称加密 - DES / 3DES / AES / PBE*/

    private static byte[] xesCrypt(int mode, String encryptType, String cipherParam, byte[] keyBytes, byte[] srcBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = new SecretKeySpec(keyBytes, encryptType);
        Cipher c = Cipher.getInstance(cipherParam);
        c.init(mode, key);
        return c.doFinal(srcBytes);
    }

    public static byte[] genKey(String encryptType) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(encryptType);
        kg.init(new SecureRandom());
        SecretKey sk = kg.generateKey();
        return sk.getEncoded();
    }

    public static byte[] desEncrypt(String cipherParam, byte[] keyBytes, byte[] textBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return xesCrypt(Cipher.ENCRYPT_MODE, TYPE_DES, cipherParam, keyBytes, textBytes);
    }

    public static byte[] desDecrypt(String cipherParam, byte[] keyBytes, byte[] textBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return xesCrypt(Cipher.DECRYPT_MODE, TYPE_DES, cipherParam, keyBytes, textBytes);
    }

    public static byte[] tripleDesEncrypt(String cipherParam, byte[] keyBytes, byte[] textBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return xesCrypt(Cipher.ENCRYPT_MODE, TYPE_3DES, cipherParam, keyBytes, textBytes);
    }

    public static byte[] tripleDesDecrypt(String cipherParam, byte[] keyBytes, byte[] textBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return xesCrypt(Cipher.DECRYPT_MODE, TYPE_3DES, cipherParam, keyBytes, textBytes);
    }

    public static byte[] aesEncrypt(String cipherParam, byte[] keyBytes, byte[] textBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return xesCrypt(Cipher.ENCRYPT_MODE, TYPE_AES, cipherParam, keyBytes, textBytes);
    }

    public static byte[] aesDecrypt(String cipherParam, byte[] keyBytes, byte[] textBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return xesCrypt(Cipher.DECRYPT_MODE, TYPE_AES, cipherParam, keyBytes, textBytes);
    }

    public static byte[] genSalt(int numBytes) {
        return new SecureRandom().generateSeed(numBytes);
    }

    private static byte[] pbeCrypt(int mode, byte[] saltBytes, String encryptType, char[] pwdBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PBEKeySpec pks = new PBEKeySpec(pwdBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(encryptType);
        Key k = skf.generateSecret(pks);
        PBEParameterSpec pps = new PBEParameterSpec(saltBytes, 100);
        Cipher c = Cipher.getInstance(encryptType);
        c.init(mode, k, pps);
        return c.doFinal(textBytes);

    }

    public static byte[] pbeEncrypt(String encryptType, byte[] saltBytes, char[] pwdBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return pbeCrypt(Cipher.ENCRYPT_MODE, saltBytes, encryptType, pwdBytes, textBytes);
    }

    public static byte[] pbeDecrypt(String encryptType, byte[] saltBytes, char[] pwdBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return pbeCrypt(Cipher.DECRYPT_MODE, saltBytes, encryptType, pwdBytes, textBytes);
    }

    /*非对称加密 - DH / RSA / ElGamal*/

    public static XsaKeyPair genRsaKeypair(int length) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(TYPE_RSA);
        keyPairGenerator.initialize(length);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new XsaKeyPair(rsaPrivateKey.getEncoded(), rsaPublicKey.getEncoded());
    }

    public static byte[] rsaPrivateEncrypt(byte[] privateKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //私钥加密, 公钥解密 -- 加密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(TYPE_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(textBytes);
    }

    public static byte[] rsaPublicDecrypt(byte[] publicKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //私钥加密, 公钥解密 -- 解密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(TYPE_RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(textBytes);
    }

    public static byte[] rsaPublicEncrypt(byte[] publicKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //公钥加密, 私钥解密 -- 加密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(TYPE_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(textBytes);
    }

    public static byte[] rsaPrivateDecrypt(byte[] privateKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //公钥加密, 私钥解密 -- 解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(TYPE_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(textBytes);
    }


    public static class XsaKeyPair {
        private byte[] privateKeyBytes;
        private byte[] publicKeyBytes;

        public XsaKeyPair(byte[] privateKeyBytes, byte[] publicKeyBytes) {
            setPrivateKeyBytes(privateKeyBytes);
            setPublicKeyBytes(publicKeyBytes);
        }

        public byte[] getPrivateKeyBytes() {
            return privateKeyBytes;
        }

        public void setPrivateKeyBytes(byte[] privateKeyBytes) {
            this.privateKeyBytes = privateKeyBytes;
        }

        public byte[] getPublicKeyBytes() {
            return publicKeyBytes;
        }

        public void setPublicKeyBytes(byte[] publicKeyBytes) {
            this.publicKeyBytes = publicKeyBytes;
        }
    }


    /*数字签名 - RSA / DSA / ECDSA*/

    public static byte[] rsaSign(byte[] privateKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_MD5_RSA);
        signature.initSign(privateKey);
        signature.update(textBytes);
        return signature.sign();
    }

    public static boolean rsaVerify(byte[] publicKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_MD5_RSA);
        signature.initVerify(publicKey);
        signature.update(textBytes);
        return signature.verify(textBytes);
    }

    public static XsaKeyPair genDsaKeypair(int length) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(TYPE_DSA);
        keyPairGenerator.initialize(length);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();
        return new XsaKeyPair(dsaPrivateKey.getEncoded(), dsaPublicKey.getEncoded());
    }

    public static byte[] dsaSign(byte[] privateKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_DSA);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_SHA1_DSA);
        signature.initSign(privateKey);
        signature.update(textBytes);
        return signature.sign();
    }

    public static boolean dsaVerify(byte[] publicKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_DSA);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_SHA1_DSA);
        signature.initVerify(publicKey);
        signature.update(textBytes);
        return signature.verify(textBytes);
    }

    public static XsaKeyPair genEcdsaKeypair(int length) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(TYPE_ECDSA);
        keyPairGenerator.initialize(length);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        ECPublicKey dsaPublicKey = (ECPublicKey) keyPair.getPublic();
        ECPrivateKey dsaPrivateKey = (ECPrivateKey) keyPair.getPrivate();
        return new XsaKeyPair(dsaPrivateKey.getEncoded(), dsaPublicKey.getEncoded());
    }

    public static byte[] ecdsaSign(byte[] privateKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_ECDSA);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_SHA1_ECDSA);
        signature.initSign(privateKey);
        signature.update(textBytes);
        return signature.sign();
    }

    public static boolean ecdsaVerify(byte[] publicKeyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_ECDSA);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_SHA1_ECDSA);
        signature.initVerify(publicKey);
        signature.update(textBytes);
        return signature.verify(textBytes);
    }

    /*消息摘要 - MD / SHA / MAC*/

    public static byte[] mdEncrypt(String type, byte[] textBytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(type);
        return messageDigest.digest(textBytes);
    }

    public static byte[] md2Encrypt(byte[] textBytes) throws NoSuchAlgorithmException {
        return mdEncrypt(TYPE_MD2, textBytes);
    }

    public static byte[] md5Encrypt(byte[] textBytes) throws NoSuchAlgorithmException {
        return mdEncrypt(TYPE_MD5, textBytes);
    }

    public static byte[] shaEncrypt(String type, byte[] textBytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(type);
        return messageDigest.digest(textBytes);
    }

    public static byte[] sha1Encrypt(byte[] textBytes) throws NoSuchAlgorithmException {
        return shaEncrypt(TYPE_SHA1, textBytes);
    }

    public static byte[] sha256Encrypt(byte[] textBytes) throws NoSuchAlgorithmException {
        return shaEncrypt(TYPE_SHA256, textBytes);
    }

    public static byte[] sha384Encrypt(byte[] textBytes) throws NoSuchAlgorithmException {
        return shaEncrypt(TYPE_SHA384, textBytes);
    }

    public static byte[] sha512Encrypt(byte[] textBytes) throws NoSuchAlgorithmException {
        return shaEncrypt(TYPE_SHA512, textBytes);
    }

    public static byte[] genHmacKey(String type) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(type);
        SecretKey sk = kg.generateKey();
        return sk.getEncoded();
    }

    public static byte[] hmacEncrypt(String type, byte[] keyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(keyBytes, type);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(textBytes);
    }

    public static byte[] hmacMD5Encrypt(byte[] keyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        return hmacEncrypt(TYPE_HMAC_MD5, keyBytes, textBytes);
    }

    public static byte[] hmacSha1Encrypt(byte[] keyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        return hmacEncrypt(TYPE_HMAC_SHA1, keyBytes, textBytes);
    }

    public static byte[] hmacSha256Encrypt(byte[] keyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        return hmacEncrypt(TYPE_HMAC_SHA256, keyBytes, textBytes);
    }

    public static byte[] hmacSha384Encrypt(byte[] keyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        return hmacEncrypt(TYPE_HMAC_SHA384, keyBytes, textBytes);
    }

    public static byte[] hmacSha512Encrypt(byte[] keyBytes, byte[] textBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        return hmacEncrypt(TYPE_HMAC_SHA512, keyBytes, textBytes);
    }

    /**
     * SHA-1 encryption
     *
     * @param plainString String to be encrypted
     * @param saltMode    true: add salt; false: standard
     * @return encrypted string
     */
    public static String sha1Encrypt(String plainString, boolean saltMode) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] buffer = digest.digest(plainString.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : buffer) {
                int result = b & 0xff;
                String hex = "";
                if (saltMode) {
                    // not standard sha-1, add salt to prevent decryption
                    hex = Integer.toHexString(result) + LENGTH_512;
                } else {
                    // standard sha-1 encryption mode, can be decrypted
                    hex = Integer.toHexString(result);
                }
                // if hex contains only 1 charactor, add an '0' in front of it
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                //concat hex
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5 encryption
     *
     * @param plainString String to be encrypted
     * @param saltMode    true: add salt; false: standard
     * @return encrypted string
     */
    public static String md5Encrypt(String plainString, boolean saltMode) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buffer = digest.digest(plainString.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : buffer) {
                int result = b & 0xff;
                String hex = "";
                if (saltMode) {
                    // not standard MD5, add salt to prevent decryption
                    hex = Integer.toHexString(result) + LENGTH_512;
                } else {
                    // standard MD5 encryption mode, can be decrypted
                    hex = Integer.toHexString(result);
                }
                // if hex contains only 1 charactor, add an '0' in front of it
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                //concat hex
                stringBuffer.append(hex);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get MD5 string of an input stream. Normally use for checking file
     *
     * @param in       InputStream
     * @param saltMode true: add salt; false: standard
     * @return MD5 string
     */
    public static String getMD5(InputStream in, boolean saltMode) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];  //8Mb buffer
            int byteCount;
            while ((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }
            byte[] digest = digester.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                int result = b & 0xff;
                String hex = "";
                if (saltMode) {
                    hex = Integer.toHexString(result) + LENGTH_512;
                } else {
                    hex = Integer.toHexString(result);
                }
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*Morse*/

    public static HashMap<Character, String> sMorseMap = new HashMap<>();

    static {
        sMorseMap.clear();
        sMorseMap.put('A', ".-");
        sMorseMap.put('B', "-...");
        sMorseMap.put('C', "-.-.");
        sMorseMap.put('D', "-..");
        sMorseMap.put('E', ".");
        sMorseMap.put('F', "..-.");
        sMorseMap.put('G', "--.");
        sMorseMap.put('H', "....");
        sMorseMap.put('I', "..");
        sMorseMap.put('J', ".---");
        sMorseMap.put('K', "-.-");
        sMorseMap.put('L', ".-..");
        sMorseMap.put('M', "--");
        sMorseMap.put('N', "-.");
        sMorseMap.put('O', "---");
        sMorseMap.put('P', ".--.");
        sMorseMap.put('Q', "--.-");
        sMorseMap.put('R', ".-.");
        sMorseMap.put('S', "...");
        sMorseMap.put('T', "-");
        sMorseMap.put('U', "..-");
        sMorseMap.put('V', "...-");
        sMorseMap.put('W', ".--");
        sMorseMap.put('X', "-..-");
        sMorseMap.put('Y', "-.--");
        sMorseMap.put('Z', "--..");
        sMorseMap.put('0', "-----");
        sMorseMap.put('1', ".----");
        sMorseMap.put('2', "..---");
        sMorseMap.put('3', "...--");
        sMorseMap.put('4', "....-");
        sMorseMap.put('5', ".....");
        sMorseMap.put('6', "-....");
        sMorseMap.put('7', "--...");
        sMorseMap.put('8', "---..");
        sMorseMap.put('9', "----.");
        sMorseMap.put('.', ".-.-.-");
        sMorseMap.put(',', "--..--");
        sMorseMap.put('?', "..--..");
        sMorseMap.put('\'', ".----.");
        sMorseMap.put('!', "-.-.--");
        sMorseMap.put('/', "-..-.");
        sMorseMap.put('(', "-.--.");
        sMorseMap.put(')', "-.--.-");
        sMorseMap.put('&', ".-...");
        sMorseMap.put(':', "---...");
        sMorseMap.put(';', "-.-.-.");
        sMorseMap.put('=', "-...-");
        sMorseMap.put('+', ".-.-.");
        sMorseMap.put('-', "-....-");
        sMorseMap.put('_', "..--.-");
        sMorseMap.put('"', ".-..-.");
        sMorseMap.put('$', "...-..-");
        sMorseMap.put('@', ".--.-.");
    }

    public static String morseEncode(String text, String splitSymbol) {
        StringBuffer stringBuffer = new StringBuffer();
        text = text.toUpperCase();
        char[] textChars = text.toCharArray();
        for (char c : textChars) {
            stringBuffer.append(sMorseMap.get(c));
            stringBuffer.append(splitSymbol);
        }
        stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(splitSymbol));
        return stringBuffer.toString();
    }

    public static String morseDecode(String morse, String splitSymbol) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] morses = morse.split(splitSymbol);
        LogUtil.i(Arrays.toString(morses));
        Set<Map.Entry<Character, String>> keySet = sMorseMap.entrySet();
        for (String code : morses) {
            for (Map.Entry<Character, String> map : keySet) {
                if (map.getValue().equalsIgnoreCase(code)) {
                    stringBuffer.append(map.getKey());
                }
            }

        }
        return stringBuffer.toString();
    }

    /*Custom*/

    /**
     * simple encrytion
     *
     * @param textBytes bytes to be encryted
     * @param key       int value to be a key for encryption
     * @return encryted bytes
     */
    public static byte[] simpleEncrypt(byte[] textBytes, int key) {
        for (int i = 0; i < textBytes.length; i++) {
            textBytes[i] ^= key;
        }
        return textBytes;
    }

    /**
     * decrypt from simple encryption
     *
     * @param textBytes encrypted bytes to be decrypted
     * @param key       int value to be a key for decryption
     * @return decrypted bytes
     */
    public static byte[] simpleDecrypt(byte[] textBytes, int key) {
        //process is same, re-do once
        return simpleEncrypt(textBytes, key);
    }


}
