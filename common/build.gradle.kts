plugins {
    id("kotlin")
}

dependencies {
    implementation(Config.Dependencies.kotlinStdLib)
    implementation(Config.Dependencies.rxJava)

    testImplementation(Config.Dependencies.junit)
}
