plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("core-plugin") {
            id = "core-plugin"
            implementationClass = "com.example.plugins.CorePlugin"
        }
        register("core-ui-plugin") {
            id = "core-ui-plugin"
            implementationClass = "com.example.plugins.CoreUiPlugin"
        }
        register("onboarding-domain-plugin") {
            id = "onboarding-domain-plugin"
            implementationClass = "com.example.plugins.OnBoardingDomainPlugin"
        }
        register("onboarding-presentation-plugin") {
            id = "onboarding-presentation-plugin"
            implementationClass = "com.example.plugins.OnBoardingPresentationPlugin"
        }
        register("tracker-data-plugin") {
            id = "tracker-data-plugin"
            implementationClass = "com.example.plugins.TrackerDataPlugin"
        }
        register("tracker-domain-plugin") {
            id = "tracker-domain-plugin"
            implementationClass = "com.example.plugins.TrackerDomainPlugin"
        }
        register("tracker-presentation-plugin") {
            id = "tracker-presentation-plugin"
            implementationClass = "com.example.plugins.TrackerPresentationPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.0.0")
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.8.10")
    implementation("org.jetbrains.kotlin.android:org.jetbrains.kotlin.android.gradle.plugin:1.8.10")
    implementation("com.squareup:javapoet:1.13.0")
}