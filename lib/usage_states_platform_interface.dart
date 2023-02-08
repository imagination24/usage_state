import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:usage_states/CallRecord.dart';
import 'package:usage_states/SMSRecord.dart';

import 'usage_states_method_channel.dart';

abstract class UsageStatesPlatform extends PlatformInterface {
  /// Constructs a UsageStatesPlatform.
  UsageStatesPlatform() : super(token: _token);

  static final Object _token = Object();

  static UsageStatesPlatform _instance = MethodChannelUsageStates();

  /// The default instance of [UsageStatesPlatform] to use.
  ///
  /// Defaults to [MethodChannelUsageStates].
  static UsageStatesPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [UsageStatesPlatform] when
  /// they register themselves.
  static set instance(UsageStatesPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<List<dynamic>> queryUsageTime(DateTime startDate, DateTime endDate) {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<List<CallRecord>> queryCallRecords(){
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<List<SMSRecord>> querySMSRecords(){
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
