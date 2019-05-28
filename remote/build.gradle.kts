plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Config.Dependencies.kotlinStdLib)
    implementation(Config.Dependencies.ktxCore)

    implementation(Config.Dependencies.dagger)
    kapt(Config.Dependencies.daggerCompiler)

    implementation(Config.Dependencies.places)

    implementation(Config.Dependencies.rxKotlin)
    implementation(Config.Dependencies.rxJava)

    implementation(Config.Dependencies.retrofit)
    implementation(Config.Dependencies.retrofitMoshi)

    testImplementation(Config.Dependencies.junit)
}