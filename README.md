# usage_states
_
A  Flutter plugin use to query user applicationUseTime/SMSRecord/callRecord.
can use in background
## install
_
```
usage_states:
      git:
        url: https://github.com/imagination24/usage_state.git
```
## Permission
_
use ```usage_permission``` to request permission
## Usage
_
```
    //queryUsageTimeOfToday
    DateTime endDate = DateTime.now();
    DateTime startDate = DateTime(endDate.year, endDate.month, endDate.day, 0, 0, 0);
    List list = await UsageStates().queryUsageTime(startDate, endDate);
    //queryCallRecordsOfToday
    List<CallRecord> callRecordList = await UsageStates().queryCallRecords();
    //querySMSRecordsOfToday
    List<SMSRecord> msgList = await UsageStates().querySMSRecords();
```


