import 'package:flutter_test/flutter_test.dart';
import 'package:usage_states/CallRecord.dart';
import 'package:usage_states/SMSRecord.dart';
import 'package:usage_states/usage_states.dart';
import 'package:usage_states/usage_states_platform_interface.dart';
import 'package:usage_states/usage_states_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockUsageStatesPlatform 
    with MockPlatformInterfaceMixin
    implements UsageStatesPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<List> queryUsageTime(DateTime startDate, DateTime endDate) {
    // TODO: implement queryUsageTime
    throw UnimplementedError();
  }




  @override
  Future<List<CallRecord>> queryCallRecords() {
    // TODO: implement queryCallRecords
    throw UnimplementedError();
  }

  @override
  Future<List<SMSRecord>> querySMSRecords() {
    // TODO: implement querySMSRecords
    throw UnimplementedError();
  }

}

void main() {
  final UsageStatesPlatform initialPlatform = UsageStatesPlatform.instance;

  test('$MethodChannelUsageStates is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelUsageStates>());
  });

  test('getPlatformVersion', () async {
    UsageStates usageStatesPlugin = UsageStates();
    MockUsageStatesPlatform fakePlatform = MockUsageStatesPlatform();
    UsageStatesPlatform.instance = fakePlatform;
  
    expect(await usageStatesPlugin.getPlatformVersion(), '42');
  });
}
