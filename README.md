ShareToBrowser
==============

Adds "Browser" to the share menu.


Usage
=====

Before ShareToBrowser/build.gradle can be synched, 'signing.gradle' (which is not included in the repository, but is required for generating signed release builds) will need to be resolved.

If you do not plan on generating signed release builds, remove "apply from: 'signing.gradle'" from line 2 in ShareToBrowser/build.gradle. 

If you choose to generate signed releases, you will need to create ShareToBrowser/signing.gradle with the signing information in the form of:

android {
	signingConfig {
		release {
            keyAlias 'key_alias'
            keyPassword 'key_password'
            storeFile file('/path/to/keystore')
            storePassword 'keystore_password'
		}
	}
}
