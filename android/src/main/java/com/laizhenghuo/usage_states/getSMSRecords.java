package com.laizhenghuo.usage_states;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class getSMSRecords {
    final static String SMS_URI_ALL = "content://sms/"; // 所有短信
    final static String SMS_URI_INBOX = "content://sms/inbox"; // 收件箱
    final static String SMS_URI_SEND = "content://sms/sent"; // 已发送
    final static String SMS_URI_DRAFT = "content://sms/draft"; // 草稿
    final static String SMS_URI_OUTBOX = "content://sms/outbox"; // 发件箱
    final static String SMS_URI_FAILED = "content://sms/failed"; // 发送失败
    final static String SMS_URI_QUEUED = "content://sms/queued"; // 待发送列表


    public static List<Map<String, String>> getSMSRecordsList(Context context){
        Uri uri = Uri.parse(SMS_URI_ALL);
        List<Map<String,String>> list=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED) return list;
        String[] projection = new String[] { "_id", "address", "person", "body", "date", "type", };
        @SuppressLint("Recycle") Cursor cur = context.getContentResolver().query(uri, projection, null,
                null, "date desc");
        if (null == cur) {
            list.add(Map.of("Result","NULL"));
            return list;
        }
        while (cur.moveToNext()) {
            @SuppressLint("Range") String number = cur.getString(cur.getColumnIndex("address"));//手机号
            @SuppressLint("Range") String body = cur.getString(cur.getColumnIndex("body"));//短信内容
            @SuppressLint("Range") String Date = formatDate(cur.getLong(cur.getColumnIndex("date")));
            @SuppressLint("Range") String Type = functionOfSmsType(cur.getInt(cur.getColumnIndex("type")));

            Map<String, String> map = new HashMap<>();
            map.put("DATE",Date);
            map.put("NUM", number);
            map.put("MESSAGE", body);
            map.put("TYPE",Type);
            if(Type.equals("from")||Type.equals("to"))list.add(map);
        }
        return list;
    }

    static String formatDate(long longDate){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date(longDate);
        String strDate = dateFormat.format(d);
        return  strDate;
    }

    static String functionOfSmsType(int intType){
        String strType;
        if (intType == 1) {
            strType = "from";
        } else if (intType == 2) {
            strType = "to";
        } else if (intType == 3) {
            strType = "draft";
        } else if (intType == 4) {
            strType = "Outbox";
        } else if (intType == 5) {
            strType = "fail";
        } else if (intType == 6) {
            strType = "wait";
        } else if (intType == 0) {
            strType = "all";
        } else {
            strType = "null";
        }
        return strType;
    }
}
