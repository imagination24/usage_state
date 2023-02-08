package com.laizhenghuo.usage_states;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventUtils {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static List<Map<String, Object>> queryUsageTime(Context context, long beginTime, long endTime){
        HashMap<String,Integer> packageMap = new HashMap<>();
        UsageEvents.Event currentEvent;
        List<UsageEvents.Event> allEvents = new ArrayList<>();
        HashMap<String, Integer> appUsageMap = new HashMap<>();
        List<Map<String,Object>> result = new ArrayList<>();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        UsageEvents usageEvents = usageStatsManager.queryEvents(beginTime, endTime);
        while (usageEvents.hasNextEvent()){
            currentEvent = new UsageEvents.Event();
            usageEvents.getNextEvent(currentEvent);
            if (currentEvent.getEventType()==UsageEvents.Event.ACTIVITY_PAUSED||currentEvent.getEventType()==UsageEvents.Event.ACTIVITY_RESUMED){
                if (packageMap.isEmpty()||packageMap.get(currentEvent.getPackageName())==null){
                    packageMap.put(currentEvent.getPackageName(),0);
                }
            }
        }
        for (String packageName:packageMap.keySet()){
            UsageStatsManager usageStatsManagers = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            UsageEvents usageEvent = usageStatsManagers.queryEvents(beginTime, endTime);
            while (usageEvent.hasNextEvent()) {
                currentEvent = new UsageEvents.Event();
                usageEvent.getNextEvent(currentEvent);
                if(currentEvent.getPackageName().equals(packageName) || packageName == null) {
                    if (currentEvent.getEventType() == UsageEvents.Event.ACTIVITY_RESUMED
                            || currentEvent.getEventType() == UsageEvents.Event.ACTIVITY_PAUSED) {
                        allEvents.add(currentEvent);
                        String key = currentEvent.getPackageName();
                        if (appUsageMap.get(key) == null)
                            appUsageMap.put(key, 0);
                    }
                }
            }
            for (int i = 0; i < allEvents.size() - 1; i++) {
                UsageEvents.Event E0 = allEvents.get(i);
                UsageEvents.Event E1 = allEvents.get(i + 1);
                if (E0.getEventType() == UsageEvents.Event.ACTIVITY_RESUMED
                        && E1.getEventType() == UsageEvents.Event.ACTIVITY_PAUSED
                        && E0.getClassName().equals(E1.getClassName())) {
                    int diff = (int)(E1.getTimeStamp() - E0.getTimeStamp());
                    diff /= 1000;
                    Integer prev = appUsageMap.get(E0.getPackageName());
                    if(prev == null) prev = 0;
                    appUsageMap.put(E0.getPackageName(), prev + diff);
                }
                if ((i+1)==(allEvents.size()-1)&&(allEvents.get(i+1).getEventType())==(UsageEvents.Event.ACTIVITY_RESUMED)){
                    UsageEvents.Event lastEvent = allEvents.get(allEvents.size() - 1);///未关闭的事件的时间
                    int diff = (int)System.currentTimeMillis() - (int)lastEvent.getTimeStamp();
                    diff /= 1000;
                    Integer prev = appUsageMap.get(lastEvent.getPackageName());
                    if(prev == null) prev = 0;
                    appUsageMap.put(lastEvent.getPackageName(), prev + diff);
                }
            }
            if (appUsageMap.get(packageName)!=null&&packageName!=null){
                result.add(Map.of("packageName",packageName,"duration",appUsageMap.get(packageName)));
            }
            allEvents.clear();
        }
        return result;
    }
}
