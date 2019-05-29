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
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
    dataBinding.isEnabled = true
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))

    implementation(Config.Dependencies.kotlinStdLib)
    implementation(Config.Dependencies.appCompat)
    implementation(Config.Dependencies.ktxCore)

    implementation(Config.Dependencies.constraintLayout)
    implementation(Config.Dependencies.material)

    implementation(Config.Dependencies.navigationFragmentKtx)
    implementation(Config.Dependencies.navigationUiKtx)

    implementation(Config.Dependencies.viewModelKtx)
    implementation(Config.Dependencies.lifecycleExt)
    implementation(Config.Dependencies.lifecycleRx)
    kapt(Config.Dependencies.lifecycleCompiler)

    implementation(Config.Dependencies.rxJava)
    implementation(Config.Dependencies.rxAndroid)

    implementation(Config.Dependencies.dagger)
    implementation(Config.Dependencies.daggerAndroid)
    implementation(Config.Dependencies.daggerAndroidSupport)
    kapt(Config.Dependencies.daggerAndroidProcessor)
    kapt(Config.Dependencies.daggerCompiler)

    implementation(Config.Dependencies.retrofit)
    implementation(Config.Dependencies.retrofitMoshi)

    testImplementation(Config.Dependencies.junit)
    androidTestImplementation(Config.Dependencies.testRunner)
    androidTestImplementation(Config.Dependencies.espresso)
}
