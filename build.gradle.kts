// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {



    dependencies {
//        classpath "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        // Make sure that you have the Google services Gradle plugin dependency
     //   classpath ("com.google.gms:google-services:4.3.15")

        // Add the dependency for the Crashlytics Gradle plugin
     //   classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.6")
    }
    repositories {
        mavenCentral()
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
//    id ("com.android.library") version "7.4.1" apply false
//    id ("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
}