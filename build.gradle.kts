buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.jacocoPlugin)
    }
}

allprojects {
}

plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("org.jetbrains.kotlin.jvm") apply false
    id("jacoco")
}