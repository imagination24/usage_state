import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:usage_states/CallRecord.dart';
import 'package:usage_states/SMSRecord.dart';

import 'usage_states_platform_interface.dart';

/// An implementation of [UsageStatesPlatform] that uses method channels.
class MethodChannelUsageStates extends UsageStatesPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('usage_states');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<List<dynamic>> queryUsageTime(DateTime startDate, DateTime endDate) async{
    int end = endDate.millisecondsSinceEpoch;
    int start = startDate.millisecondsSinceEpoch;
    Map<String, int> interval = {'start': start, 'end': end};
    List list = await methodChannel.invokeMethod("queryUsageTime",interval);
    return list;
  }

  @override
  Future<List<CallRecord>> queryCallRecords() async{
    List list = await methodChannel.invokeMethod("queryCallRecords");
    List<CallRecord> callRecordList = [];
    for (var element in list) {
      CallRecord callRecord = CallRecord.fromMap(element);
      callRecordList.add(callRecord);
    }
    return callRecordList;
  }

  @override
  Future<List<SMSRecord>> querySMSRecords() async{
    List list = await methodChannel.invokeMethod("querySMSRecords");
    List<SMSRecord> smsRecordList = [];
    for (var element in list) {
      SMSRecord smsRecord = SMSRecord.fromMap(element);
      DateTime smsDate = DateTime.parse(smsRecord.date);
      DateTime now = DateTime.now();
      if(smsDate.year==now.year&&smsDate.month==now.month&&smsDate.day==now.day){
        smsRecordList.add(smsRecord);
      }
    }
    return smsRecordList;
  }
}
