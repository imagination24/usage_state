/*
Time:2022/6/8
Description:短信记录实体
Author:
*/
class SMSRecord {
  final String number;
  final String body;
  final String type;
  final String date;
  bool blk;

  SMSRecord(
      {required this.number,
      required this.body,
      required this.type,
      required this.date,this.blk = false});

  factory SMSRecord.fromMap(Map map) {
    return SMSRecord(number: map["NUM"], body: map["MESSAGE"], type: map["TYPE"], date: map["DATE"]);
  }

  Map toMap(){
    return {
      "at":date,
      "mob":number,
      "typ":type,
      "blk":blk
    };
  }
}
