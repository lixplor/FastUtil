package cn.fantasymaker.fmutils.utils.calculate;

import android.util.Base64;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created :  2016-11-05
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class EncryptUtilTest {
    @Test
    public void base64EncodeToString() throws Exception {

    }

    @Test
    public void hmacSha1Encrypt() throws Exception {
        String src = "a=200001&b=newbucket&k=AKIDUfLUEUigQiXqm7CVSspKJnuaiIKtxqAv&e=1438669115&t=1436077115&r=11162&f=";
        String key = "bLcPnl88WU30VY57ipRhSePfPdOfSruK";
        byte[] enc = EncryptUtil.hmacSha1Encrypt(key.getBytes(), src.getBytes());
        byte[] srcB = src.getBytes();
        byte[] total = new byte[enc.length + srcB.length];
        for(int i = 0; i < total.length; i++){
            if(i < enc.length){
                total[i] = enc[i];
            }else{
                total[i] = srcB[i - enc.length];
            }
        }
        String base = EncryptUtil.base64EncodeToString(total, Base64.DEFAULT);
        System.out.println(base);
    }

}