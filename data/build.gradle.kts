plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(Config.Dependencies.kotlinStdLib)

    implementation(Config.Dependencies.rxJava)

    implementation(Config.Dependencies.dagger)
    kapt(Config.Dependencies.daggerCompiler)

    testImplementation(Config.Dependencies.junit)
}
