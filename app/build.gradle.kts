import java.util.Properties
import java.io.FileInputStream
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs.kotlin")
    // Make sure that you have the Google services Gradle plugin
    id ("com.google.gms.google-services")
    id ("kotlin-kapt")

    // Add the Crashlytics Gradle plugin
  //  id ("com.google.firebase.crashlytics")
}

val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()

if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}
android {
    namespace = "com.nurtaz.dev.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nurtaz.dev.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField ("String", "WEATHER_KEY", localProperties["weatherKey"].toString())
        buildConfigField ("String", "TREFLE_KEY", localProperties["trefleKey"].toString())
        buildConfigField ("String", "PLANTNET_KEY", localProperties["plantnetKey"].toString())
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true

        android.buildFeatures.buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    //Coil
    implementation ("io.coil-kt:coil:2.2.1")
    implementation("io.coil-kt:coil-svg:2.2.1")

    // camera
    implementation ("androidx.camera:camera-camera2:1.3.0-alpha02")
    implementation ("androidx.camera:camera-view:1.3.0-alpha02")
    implementation ("androidx.camera:camera-lifecycle:1.3.0-alpha02")

    // chart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("com.guolindev.permissionx:permissionx:1.7.1")

    //api
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    //Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // paging Data
    implementation ("androidx.paging:paging-runtime:3.1.1")



    // room
    implementation ("androidx.room:room-runtime:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.5.0")

    // optional - Paging 3 Integration
    implementation ("androidx.room:room-paging:2.5.0")

    // Compressor
    implementation("id.zelory:compressor:3.0.1")
    implementation("com.github.Shouheng88:compressor:1.6.0")

    // image

    implementation("com.github.bumptech.glide:glide:4.14.2")
    kapt ("com.github.bumptech.glide:compiler:4.14.2")

// load with glide
    implementation("com.github.piasy:GlideImageLoader:1.8.1")
    implementation("com.github.piasy:BigImageViewer:1.8.1")

// support thumbnail and gif with Glide
    implementation("com.github.piasy:GlideImageViewFactory:1.8.1")

    // Import the BoM for the Firebase platform
   // implementation("com.google.firebase:firebase-bom:31.2.2")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
  //  implementation("com.google.firebase:firebase-crashlytics-ktx")
   implementation("com.google.firebase:firebase-analytics-ktx")

    // leaks
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

}
kapt{
    correctErrorTypes = true
//    arguments {
//        arg("dagger.fastInit", "enabled")
//        arg("dagger.hilt.android.internal.disableAndroidSuperclassValidation", "true")
//        arg("dagger.hilt.android.internal.projectType", "APP")
//        arg("dagger.hilt.internal.useAggregatingRootProcessor", "false")
//    }
}
