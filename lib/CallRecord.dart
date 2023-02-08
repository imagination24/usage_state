/*
Time:2022/6/7
Description:通话记录实体
Author:lai
*/
class CallRecord {
  final String callName;
  final String callNumber;
  final String callType;
  final String callDate;
  final String callDuration;
  bool blk;

  CallRecord(
      {required this.callName,
      required this.callNumber,
      required this.callType,
      required this.callDate,
      required this.callDuration,this.blk = false});

  factory CallRecord.fromMap(Map<dynamic, dynamic> map) {
    return CallRecord(
      callName: map["CACHED_NAME"]??"",
      callNumber: map["NUMBER"],
      callType: map["TYPE"]=="Block"?"from":map["TYPE"],
      callDate: map["DATE"],
      callDuration: map["DURATION"],
      blk: (map["BLK"]=="true"||map["TYPE"]=="Block")
    );
  }

  factory CallRecord.fromLocalMap(Map<dynamic, dynamic> map){
    return CallRecord(
        callName: "",
        callNumber: map["mob"],
        callType: map["typ"],
        callDate: map["at"],
        callDuration: map["dur"],
        blk:map["blk"]
    );
  }

  Map toMap(){
    return {
      "at":callDate,
      "dur":callDuration,
      "mob":callNumber,
      "typ":callType,
      "blk":blk
    };
  }
}
