buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Config.Dependencies.kotlinGradlePlugin)
        classpath(Config.Dependencies.androidGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean"){
    delete(rootProject.buildDir)
}