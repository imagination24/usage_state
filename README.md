# usage_states
A  Flutter plugin use to query user applicationUseTime/SMSRecord/callRecord.
can use in background
## install
```
usage_states:
      git:
        url: https://github.com/imagination24/usage_state.git
```
## Permission
use ```usage_permission``` to request permission
## Usage
```
    import 'package:usage_states/SMSRecord.dart';
    import 'package:usage_states/usage_states.dart';
    import 'package:usage_states/CallRecord.dart';
    
    //queryUsageTimeOfToday
    DateTime endDate = DateTime.now();
    DateTime startDate = DateTime(endDate.year, endDate.month, endDate.day, 0, 0, 0);
    List list = await UsageStates().queryUsageTime(startDate, endDate);
    //queryCallRecordsOfToday
    List<CallRecord> callRecordList = await UsageStates().queryCallRecords();
    //querySMSRecordsOfToday
    List<SMSRecord> msgList = await UsageStates().querySMSRecords();
```


