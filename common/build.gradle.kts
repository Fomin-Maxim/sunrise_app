plugins {
    id("kotlin")
}

dependencies {
    implementation(Config.Dependencies.kotlinStdLib)

    testImplementation(Config.Dependencies.junit)
}
