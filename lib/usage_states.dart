
import 'package:usage_states/CallRecord.dart';
import 'package:usage_states/SMSRecord.dart';

import 'usage_states_platform_interface.dart';

class UsageStates {
  Future<String?> getPlatformVersion() {
    return UsageStatesPlatform.instance.getPlatformVersion();
  }

  Future<List<dynamic>> queryUsageTime(DateTime startDate, DateTime endDate) {
    return UsageStatesPlatform.instance.queryUsageTime(startDate,endDate);
  }

  Future<List<CallRecord>> queryCallRecords(){
    return UsageStatesPlatform.instance.queryCallRecords();
  }

  Future<List<SMSRecord>> querySMSRecords(){
    return UsageStatesPlatform.instance.querySMSRecords();
  }
}
