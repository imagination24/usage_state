import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:usage_states/usage_states_method_channel.dart';

void main() {
  MethodChannelUsageStates platform = MethodChannelUsageStates();
  const MethodChannel channel = MethodChannel('usage_states');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
