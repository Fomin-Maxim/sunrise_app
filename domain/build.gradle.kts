plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":common"))

    implementation(Config.Dependencies.kotlinStdLib)
    implementation(Config.Dependencies.ktxCore)

    implementation(Config.Dependencies.dagger)
    kapt(Config.Dependencies.daggerCompiler)

    implementation(Config.Dependencies.liveData)
    implementation(Config.Dependencies.rxKotlin)
    implementation(Config.Dependencies.rxJava)

    implementation(Config.Dependencies.retrofit)
    implementation(Config.Dependencies.retrofitMoshi)

    implementation(Config.Dependencies.roomRuntime)
    kapt(Config.Dependencies.roomCompiler)

    testImplementation(Config.Dependencies.junit)
}