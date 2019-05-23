object Config {
    object Android {
        const val buildToolsVersion = "28.0.3"
        const val minSdkVersion = 21
        const val targetSdkVersion = 28
        const val compileSdkVersion = 28
        const val applicationId = "com.mfomin.sunrise.app"
        const val versionCode = 1
        const val versionName = "1"
        const val junitTestRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Versions {
        // Build versions
        const val androidGradle = "3.4.1"
        const val kotlin = "1.3.31"

        // Libraries
        const val constraintLayout = "2.0.0-alpha3"
        const val support = "1.0.2"
        const val ktxCore = "1.0.1"
        const val navigation = "1.0.0-beta01"
        const val lifecycle = "2.0.0"
        const val dagger = "2.21"
        const val room = "2.1.0-alpha04"
        const val retrofit = "2.5.0"
        const val moshi = "1.8.0"
        const val retrofitCoroutinesAdapter = "0.9.2"
        const val junit = "4.12"
        const val testRunner = "1.1.0"
        const val espresso = "3.1.1"
        const val material = "1.0.0"
    }

    object Dependencies {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Config.Versions.kotlin}"
        const val androidGradlePlugin = "com.android.tools.build:gradle:${Config.Versions.androidGradle}"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Config.Versions.kotlin}"
        const val appCompat = "androidx.appcompat:appcompat:${Config.Versions.support}"
        const val ktxCore = "androidx.core:core-ktx:${Config.Versions.ktxCore}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Config.Versions.constraintLayout}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Config.Versions.lifecycle}"
        const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Config.Versions.lifecycle}"
        const val lifecycleRx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Config.Versions.lifecycle}"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Config.Versions.lifecycle}"
        const val navigationFragmentKtx =
            "android.arch.navigation:navigation-fragment-ktx:${Config.Versions.navigation}"
        const val navigationUiKtx = "android.arch.navigation:navigation-ui-ktx:${Config.Versions.navigation}"
        const val navigationGradlePlugin =
            "android.arch.navigation:navigation-safe-args-gradle-plugin:${Config.Versions.navigation}"
        const val roomRuntime = "androidx.room:room-runtime:${Config.Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Config.Versions.room}"
        const val dagger = "com.google.dagger:dagger:${Config.Versions.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Config.Versions.dagger}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Config.Versions.dagger}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Config.Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Config.Versions.dagger}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Config.Versions.retrofit}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Config.Versions.retrofit}"
        const val retrofitCouroutines =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Config.Versions.retrofitCoroutinesAdapter}"
        const val moshi = "com.squareup.moshi:moshi:${Config.Versions.moshi}"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Config.Versions.moshi}"
        const val junit = "junit:junit:${Config.Versions.junit}"
        const val testRunner = "androidx.test.ext:junit:${Config.Versions.testRunner}"
        const val espresso = "androidx.test.espresso:espresso-core:${Config.Versions.espresso}"
        const val coreTesting = "androidx.arch.core:core-testing:${Config.Versions.lifecycle}"
    }

}