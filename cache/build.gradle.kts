plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    buildToolsVersion(Config.Android.buildToolsVersion)
    defaultConfig {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName
        testInstrumentationRunner = Config.Android.junitTestRunner
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Config.Dependencies.kotlinStdLib)

    implementation(Config.Dependencies.dagger)
    kapt(Config.Dependencies.daggerCompiler)

    implementation(Config.Dependencies.rxJava)

    api(Config.Dependencies.roomRuntime)
    implementation(Config.Dependencies.roomRx)
    kapt(Config.Dependencies.roomCompiler)

    testImplementation(Config.Dependencies.junit)
}