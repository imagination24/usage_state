package com.laizhenghuo.usage_states;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import androidx.core.content.ContextCompat;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class getCallRecords {
    @SuppressLint("Recycle")
    public static List<Map<String,String>> getCallHistoryList(Context context) {
        List<Map<String,String>> records=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED) return records;
        ContentResolver cr = context.getContentResolver();
        Date date = Calendar.getInstance().getTime();
        Cursor cs;
        cs = cr.query(CallLog.Calls.CONTENT_URI, //系统方式获取通讯录存储地址
                new String[]{
                        CallLog.Calls.CACHED_NAME,  //姓名
                        CallLog.Calls.NUMBER,    //号码
                        CallLog.Calls.TYPE,  //呼入/呼出(2)/未接
                        CallLog.Calls.DATE,  //拨打时间
                        CallLog.Calls.DURATION   //通话时长
                }, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        int i = 0;
        if (cs != null && cs.getCount() > 0) {
            for (cs.moveToFirst(); !cs.isAfterLast() & i < 50; cs.moveToNext()) {
                String callName = cs.getString(0);
                String callNumber = cs.getString(1);
                //通话类型
                int callType = Integer.parseInt(cs.getString(2));
                String callTypeStr = "";
                switch (callType) {
                    case CallLog.Calls.INCOMING_TYPE:
                    case CallLog.Calls.MISSED_TYPE:
                    case CallLog.Calls.REJECTED_TYPE:
                    case CallLog.Calls.VOICEMAIL_TYPE:
                    case CallLog.Calls.ANSWERED_EXTERNALLY_TYPE:
                        callTypeStr = "from";
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        callTypeStr = "to";
                        break;
                    case  CallLog.Calls.BLOCKED_TYPE:
                        callTypeStr = "Block";
                        break;
                }
                //拨打时间
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date callDate = new Date(Long.parseLong(cs.getString(3)));
                String callDateStr = sdf.format(callDate);
                //通话时长
                int callDuration = Integer.parseInt(cs.getString(4));
                if(callName==null)callName="null";
                if(callDate.getDate()==date.getDate()&&callDate.getYear()==date.getYear()&&callDate.getMonth()==date.getMonth()) {
                    Map<String, String> map= Map.of("CACHED_NAME",callName,"NUMBER",callNumber,"TYPE",callTypeStr,"DATE",callDateStr,"DURATION",callDuration+"");
                    records.add(map);
                }
                i++;
            }
        }
        return records;
    }
}
