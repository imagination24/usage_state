# usage_states

A  Flutter plugin use to query user applicationUseTime/SMSRecord/callRecord.

Add the following permission to the manifest namespace in AndroidManifest.xml:
‘’‘    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.PACKAGE_USAGE_STATS" tools:ignore="ProtectedPermissions"/>
<uses-permission android:name="android.permission.READ_CONTACTS"/>
<uses-permission android:name="android.permission.READ_CALL_LOG"/>
<uses-permission android:name="android.permission.READ_SMS" />
’‘’
## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter development, view the
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

