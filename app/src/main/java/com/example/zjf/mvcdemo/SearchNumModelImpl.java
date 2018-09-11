package com.example.zjf.mvcdemo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;

/**
*@description  SearchNumModelImpl就进行了联系人的查询操作，并返回查询到的号码；
 *              如果查询成功调用接口 listener.onSuccess返回number，
 *              如果查询失败listener.onError通知view查询失败。
*
*@author zjf
*@date 2018/9/11 20:38
*/
public class SearchNumModelImpl implements SearchNumInterface {
    private Context mContext;
    public SearchNumModelImpl(Context context){ mContext = context;}
    @Override
    public void getNum(String name, SearchNumberListener listener) {
        if (isEmptyString(name) || isEmptyString(number(name))){
            listener.onError();
        }else {
            listener.onSuccess(number(name));
        }
    }

    public String number(String name){
        //获取联系人信息的Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //获取ContentResolver;查询数据，返回Cursor
        Cursor cursor = mContext.getContentResolver().query(uri, null,null,null,null);
        while (cursor.moveToNext()){
            //获取联系人的ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获取联系人的姓名
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            if (name.equals(contactName)){
                Cursor phone = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                if (phone.moveToNext()){
                    String phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    return phoneNumber;
                }
            }
        }
        return null;
    }

    public static boolean isEmptyString (String str){
        return str == null || str.trim().length() == 0;
    }
}
