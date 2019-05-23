plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    buildToolsVersion(Config.Android.buildToolsVersion)
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName
        testInstrumentationRunner = Config.Android.junitTestRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Config.Dependencies.kotlinStdLib)
    implementation(Config.Dependencies.appCompat)
    implementation(Config.Dependencies.ktxCore)

    testImplementation(Config.Dependencies.junit)
    androidTestImplementation(Config.Dependencies.testRunner)
    androidTestImplementation(Config.Dependencies.espresso)
}
