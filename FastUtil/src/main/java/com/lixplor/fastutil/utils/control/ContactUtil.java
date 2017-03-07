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

package com.lixplor.fastutil.utils.control;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-11
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ContactUtil {

    /*
    todo
    联系人增删改查

     */

    /*
    NEED PERMISSIONS:
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
    <uses-permission android:name="android.permission.WRITE_CONTACTS"/>
     */


    private static final String SPLIT_NUMBER = "NO";
    private static final String SPLIT_EMAIL = ";";

    private static final String WHERE_DATA_CONTACT_ID = Data.CONTACT_ID + " = %s";
    private static final String WHERE_RAW_CONTACT_ID = RawContacts.CONTACT_ID + " = %s";
    private static final String SELECTION_CONTACT_ICON = Photo.CONTACT_ID + " = %s and " + Data.MIMETYPE + " = '" + Photo.CONTENT_ITEM_TYPE + "'";
    private static final String SELECTION_CONTACT_NAME = StructuredName.CONTACT_ID + " = %s and " + Data.MIMETYPE + " = '" + StructuredName.CONTENT_ITEM_TYPE + "'";
    private static final String SELECTION_CONTACT_PHONE = Phone.CONTACT_ID + " = %s";
    private static final String SELECTION_CONTACT_EMAIL = Email.CONTACT_ID + " = %s";
    private static final String SELECTION_CONTACT_IM = Im.CONTACT_ID + " = %s and " + Data.MIMETYPE + " = '" + Im.CONTENT_ITEM_TYPE + "'";
    private static final String SELECTION_CONTACT_ADDR = StructuredPostal.CONTACT_ID + " = %s";
    private static final String SELECTION_CONTACT_ORG = Data.CONTACT_ID + " = %s AND " + Data.MIMETYPE + " = '" + Organization.CONTENT_ITEM_TYPE + "'";
    private static final String SELECTION_CONTACT_NOTE = Data.CONTACT_ID + " = %s AND " + Data.MIMETYPE + " = '" + Note.CONTENT_ITEM_TYPE + "'";
    private static final String SELECTION_CONTACT_NICK = Data.CONTACT_ID + " = %s AND " + Data.MIMETYPE + " = '" + Nickname.CONTENT_ITEM_TYPE + "'";
    private static final String SELECTION_CONTACT_WEB = Data.CONTACT_ID + " = %s AND " + Data.MIMETYPE + " = '" + Website.CONTENT_ITEM_TYPE + "'";

    private static Context sContext = FastUtil.getContext();
    private static ContentResolver sContentResolver = sContext.getContentResolver();

    //增

    /**
     *
     * @param contactBean
     */
    public static void insert(ContactBean contactBean) {
        ContentValues contentValues = new ContentValues();
        Uri uri = sContentResolver.insert(RawContacts.CONTENT_URI, contentValues);
        // 向raw_contact表中插入一条空数据,并获得一个id
        long id = ContentUris.parseId(uri);

//        // 向data表插入邮箱
//        sContentResolver.insert(DATA, getDataValues(id, MIMETYPE[TYPE_EMAIL], email));
//        // 向data表插入手机号码
//        sContentResolver.insert(DATA, getDataValues(id, MIMETYPE[TYPE_PHONE], phone));
//        // 向data表插入姓名
//        sContentResolver.insert(DATA, getDataValues(id, MIMETYPE[TYPE_NAME], name));
    }

    public static boolean insert(List<ContactBean> contactBeanList) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<>();
        ContentProviderOperation option = ContentProviderOperation.newInsert(Data.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_NAME, null)
                .build();

        sContentResolver.applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        return false;
    }

    /**
     * Delete contact by id
     *
     * @param contactId contact id
     */
    public static void deleteById(String contactId) {
        sContentResolver.delete(Data.CONTENT_URI, String.format(WHERE_DATA_CONTACT_ID, contactId), null);
        sContentResolver.delete(RawContacts.CONTENT_URI, String.format(WHERE_RAW_CONTACT_ID, contactId), null);
    }

    /**
     * Delete all contacts
     */
    public static void deleteAll() {
        sContentResolver.delete(Data.CONTENT_URI, null, null);
        sContentResolver.delete(RawContacts.CONTENT_URI, null, null);
    }

    //改

    /**
     * Get all contacts
     *
     * @return contact bean list
     */
    public static List<ContactBean> getAllContact() {
        List<ContactBean> contactList = new ArrayList<>();
        ArrayList<String> idList = getContactIdList();
        ContactBean newContact;
        for (String contactId : idList) {
            newContact = new ContactBean(
                    contactId,
                    getContactIcon(contactId),
                    getName(contactId),
                    getPhoneNumber(contactId),
                    getEmail(contactId),
                    getIM(contactId),
                    getAddressDetail(contactId),
                    getOrganizations(contactId),
                    getNotes(contactId),
                    getNick(contactId),
                    getWebsite(contactId)
            );
            contactList.add(newContact);
        }
        return contactList;
    }

    /**
     * Get contact icon by id
     *
     * @param contactId contact id
     * @return icon bytes
     */
    public static byte[] getContactIcon(String contactId) {
        byte[] image = null;
        String selection = String.format(SELECTION_CONTACT_ICON, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null && cursor.moveToNext()) {
            image = cursor.getBlob(cursor.getColumnIndex(Photo.PHOTO));
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return image;
    }

    /**
     * Get name by contact id. Result is an arr which 0 index is given name and 1 index is family name
     */
    public static String[] getName(String contactId) {
        String name[] = new String[2];
        String selection = String.format(SELECTION_CONTACT_NAME, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                name[1] = cursor.getString(cursor.getColumnIndex(StructuredName.FAMILY_NAME));
                name[0] = cursor.getString(cursor.getColumnIndex(StructuredName.GIVEN_NAME));
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        System.out.println("give name :" + name[0] + " famliy name :" + name[1]);
        return name;
    }

    /**
     * Get all contact ids
     */
    public static ArrayList<String> getContactIdList() {
        ArrayList<String> contactIdList = new ArrayList<>();
        Cursor cursor = sContentResolver.query(Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            String id;
            while (cursor.moveToNext()) {
                id = cursor.getString(cursor.getColumnIndex(Contacts._ID));
                contactIdList.add(id);
                System.out.println("contractId is :" + id);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return contactIdList;
    }

    /**
     * Get phone number by id
     *
     * @param contactId contact id
     * @return phone number map; key{1,2,3,7} ==={home,mobile,work,other}
     */
    public static Map<String, String> getPhoneNumber(String contactId) {
        Map<String, String> phoneNumberMap = new HashMap<>();
        String value;
        String key;
        String selection = String.format(SELECTION_CONTACT_PHONE, contactId);
        Cursor cursor = sContentResolver.query(Phone.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                key = cursor.getString(cursor.getColumnIndex(Phone.DATA2));
                value = cursor.getString(cursor.getColumnIndex(Phone.DATA1));
                if ("7".equals(key)) {
                    String v = phoneNumberMap.get(key);
                    if (v != null && !"".equals(v)) {
                        value = v + SPLIT_NUMBER + value;
                    }
                }
                phoneNumberMap.put(key, value);
                System.out.println("number type is :" + key + " number is :" + value);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return phoneNumberMap;
    }

    /**
     * Get emails by id
     *
     * @param contactId contact id
     * @return emails map; key {1,2,3,4} =={home,work,other, mobile }
     */
    public static Map<String, String> getEmail(String contactId) {
        Map<String, String> emailMap = new HashMap<>();
        String key;
        String value;
        String selection = String.format(SELECTION_CONTACT_EMAIL, contactId);
        Cursor cursor = sContentResolver.query(Email.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                key = cursor.getString(cursor.getColumnIndex(Email.DATA2));
                value = cursor.getString(cursor.getColumnIndex(Email.DATA1));
                if ("4".equals(key)) {
                    String v = emailMap.get(key);
                    if (v != null && !"".equals(v)) {
                        value = v + SPLIT_EMAIL + value;
                    }
                }
                emailMap.put(key, value);
                System.out.println("email type is :" + key + " email is :" + value);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return emailMap;
    }

    /**
     * Get IM by id
     *
     * @param contactId contact id
     * @return IM map; key {0,1,2,3,4,5,6,7} == {aim,live,yahoo,skyp,qq,gtalk,icq,jabber}
     */
    public static Map<String, String> getIM(String contactId) {
        Map<String, String> im = new HashMap<>();
        String key;
        String value;
        String selection = String.format(SELECTION_CONTACT_IM, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                key = cursor.getString(cursor.getColumnIndex(Im.DATA5));//判断im 不同种类
                value = cursor.getString(cursor.getColumnIndex(Im.DATA1));
                if ("7".equals(key)) {//jabber类型
                    String v = im.get(key);
                    if (v != null && !"".equals(v)) {
                        value = v + ";" + value;
                    }
                }
                im.put(key, value);
                System.out.println("aimType :" + key + " ; aim : " + value);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return im;
    }

    /**
     * Get address by id
     *
     * @param contactId contact id
     * @return address map; data4-data10 : {street pobox neigborbood city state zip country}==str[0-6]
     * key {1,2,3} =={home,work,other}
     */
    public static Map<String, List<String[]>> getAddressDetail(String contactId) {
        Map<String, List<String[]>> postalMap = new HashMap<>();
        String key;
        List<String[]> addrList;
        String addr[];
        String selection = String.format(SELECTION_CONTACT_ADDR, contactId);
        Cursor cursor = sContentResolver.query(StructuredPostal.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                key = cursor.getString(cursor.getColumnIndex(StructuredPostal.DATA2));
                addr = new String[7];
                addr[0] = cursor.getString(cursor.getColumnIndex(StructuredPostal.STREET));
                addr[1] = cursor.getString(cursor.getColumnIndex(StructuredPostal.POBOX));
                addr[2] = cursor.getString(cursor.getColumnIndex(StructuredPostal.NEIGHBORHOOD));
                addr[3] = cursor.getString(cursor.getColumnIndex(StructuredPostal.CITY));
                addr[4] = cursor.getString(cursor.getColumnIndex(StructuredPostal.REGION));
                addr[5] = cursor.getString(cursor.getColumnIndex(StructuredPostal.POSTCODE));
                addr[6] = cursor.getString(cursor.getColumnIndex(StructuredPostal.COUNTRY));
                addrList = postalMap.get(key);
                if (addrList != null && !addrList.isEmpty()) {
                    addrList.add(addr);
                    postalMap.put(key, addrList);
                } else {
                    addrList = new ArrayList<>();
                    addrList.add(addr);
                    postalMap.put(key, addrList);
                }
                System.out.println("address's type is :" + key + " -- address: " + addr[0] + ":" + addr[1] + ":" + addr[2] + ":" + addr[3] + ":" + addr[4] + ":" + addr[5] + ":" + addr[6]);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return postalMap;
    }

    /**
     * Get orgnization by id
     *
     * @param contactId contact id
     * @return orgnization map; organization: Map<String,List> String 是数据类型  List 是一个数组：String [data1(company),data4(position)]
     * key {1,2} ==={work ,other}
     */
    public static Map<String, List<String[]>> getOrganizations(String contactId) {
        Map<String, List<String[]>> companyMap = new HashMap<>();
        String key;
        List<String[]> positionList;
        String pos[];
        String selection = String.format(SELECTION_CONTACT_ORG, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                pos = new String[2];
                key = cursor.getString(cursor.getColumnIndex(Organization.DATA2));
                pos[0] = cursor.getString(cursor.getColumnIndex(Organization.COMPANY));
                pos[1] = cursor.getString(cursor.getColumnIndex(Organization.TITLE));
                positionList = companyMap.get(key);
                if (positionList != null && !positionList.isEmpty()) {
                    positionList.add(pos);
                    companyMap.put(key, positionList);
                } else {
                    positionList = new ArrayList<>();
                    positionList.add(pos);
                    companyMap.put(key, positionList);
                }
//          positionList.add(pos);
                System.out.println("--------------------------------");
                System.out.println("type:" + key);
                System.out.println("company:" + pos[0]);
                System.out.println("position:" + pos[1]);

            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return companyMap;

    }

    /**
     * Get note by id
     *
     * @param contactId contact id
     * @return note list
     */
    public static List<String> getNotes(String contactId) {
        List<String> notesList = new ArrayList<>();
        String note;
        String selection = String.format(SELECTION_CONTACT_NOTE, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                note = cursor.getString(cursor.getColumnIndex(Note.NOTE));
                System.out.println("note :" + note);
                notesList.add(note);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return notesList;
    }

    /**
     * Get nickname by id
     *
     * @param contactId contact id
     * @return nickname list
     */
    public static List<String> getNick(String contactId) {
        List<String> nickList = new ArrayList<>();
        String nick;
        String selection = String.format(SELECTION_CONTACT_NICK, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                nick = cursor.getString(cursor.getColumnIndex(Nickname.DATA1));
                System.out.println("nick :" + nick);
                nickList.add(nick);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return nickList;
    }

    /**
     * Get website by id
     *
     * @param contactId contact id
     * @return website list
     */
    public static List<String> getWebsite(String contactId) {
        List<String> webSiteList = new ArrayList<>();
        String webSite;
        String selection = String.format(SELECTION_CONTACT_WEB, contactId);
        Cursor cursor = sContentResolver.query(Data.CONTENT_URI, null, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                webSite = cursor.getString(cursor
                        .getColumnIndex(Website.DATA1));
                webSiteList.add(webSite);
                System.out.println("webSite : " + webSite);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return webSiteList;
    }

    public static class ContactBean {
        private String id;
        private byte[] contactIcon;
        private String[] nameArr;
        private Map<String, String> phoneNumberMap;
        private Map<String, String> emailMap;
        private Map<String, String> ImMap;
        private Map<String, List<String[]>> addressMap;
        private Map<String, List<String[]>> organizationMap;
        private List<String> noteList;
        private List<String> nickNameList;
        private List<String> websiteList;

        public ContactBean() {

        }

        public ContactBean(String id, byte[] contactIcon, String[] nameArr, Map<String, String> phoneNumberMap, Map<String, String> emailMap, Map<String, String> imMap, Map<String, List<String[]>> addressMap, Map<String, List<String[]>> organizationMap, List<String> noteList, List<String> nickNameList, List<String> websiteList) {
            this.id = id;
            this.contactIcon = contactIcon;
            this.nameArr = nameArr;
            this.phoneNumberMap = phoneNumberMap;
            this.emailMap = emailMap;
            ImMap = imMap;
            this.addressMap = addressMap;
            this.organizationMap = organizationMap;
            this.noteList = noteList;
            this.nickNameList = nickNameList;
            this.websiteList = websiteList;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public byte[] getContactIcon() {
            return contactIcon;
        }

        public void setContactIcon(byte[] contactIcon) {
            this.contactIcon = contactIcon;
        }

        public String[] getNameArr() {
            return nameArr;
        }

        public void setNameArr(String[] nameArr) {
            this.nameArr = nameArr;
        }

        public Map<String, String> getPhoneNumberMap() {
            return phoneNumberMap;
        }

        public void setPhoneNumberMap(Map<String, String> phoneNumberMap) {
            this.phoneNumberMap = phoneNumberMap;
        }

        public Map<String, String> getEmailMap() {
            return emailMap;
        }

        public void setEmailMap(Map<String, String> emailMap) {
            this.emailMap = emailMap;
        }

        public Map<String, String> getImMap() {
            return ImMap;
        }

        public void setImMap(Map<String, String> imMap) {
            ImMap = imMap;
        }

        public Map<String, List<String[]>> getAddressMap() {
            return addressMap;
        }

        public void setAddressMap(Map<String, List<String[]>> addressMap) {
            this.addressMap = addressMap;
        }

        public Map<String, List<String[]>> getOrganizationMap() {
            return organizationMap;
        }

        public void setOrganizationMap(Map<String, List<String[]>> organizationMap) {
            this.organizationMap = organizationMap;
        }

        public List<String> getNoteList() {
            return noteList;
        }

        public void setNoteList(List<String> noteList) {
            this.noteList = noteList;
        }

        public List<String> getNickNameList() {
            return nickNameList;
        }

        public void setNickNameList(List<String> nickNameList) {
            this.nickNameList = nickNameList;
        }

        public List<String> getWebsiteList() {
            return websiteList;
        }

        public void setWebsiteList(List<String> websiteList) {
            this.websiteList = websiteList;
        }
    }
}
