plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Config.Dependencies.kotlinStdLib)

    implementation(Config.Dependencies.dagger)
    kapt(Config.Dependencies.daggerCompiler)

    implementation(Config.Dependencies.rxJava)

    implementation(Config.Dependencies.roomRuntime)
    kapt(Config.Dependencies.roomCompiler)

    testImplementation(Config.Dependencies.junit)
}