package com.laizhenghuo.usage_states;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** UsageStatesPlugin */
public class UsageStatesPlugin implements FlutterPlugin, MethodCallHandler {
  private MethodChannel channel;
  private Context context;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "usage_states");
    channel.setMethodCallHandler(this);
    context = flutterPluginBinding.getApplicationContext();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + Build.VERSION.RELEASE);
    } else if(call.method.equals("queryUsageTime")){
      long start = call.argument("start");
      long end = call.argument("end");
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        result.success(EventUtils.queryUsageTime(context,start,end));
      }
    } else if(call.method.equals("queryCallRecords")){
      result.success(getCallRecords.getCallHistoryList(context));
    } else if (call.method.equals("querySMSRecords")){
      result.success(getSMSRecords.getSMSRecordsList(context));
    }else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
