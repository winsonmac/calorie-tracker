package com.example.plugins

import Compose
import DaggerHilt
import ProjectConfig
import Testing
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

abstract class BaseModulePlugin(
    private val packageName: String,
    private val isCompose: Boolean
) : Plugin<Project> {

    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Not an Android module: $name")

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            applyKotlinOptions()
            if (isCompose) {
                androidComposeConfig()
                composeDependenciesConfig()
            } else {
                androidConfig()
                dependenciesConfig()
            }

        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("kotlin-kapt")
            apply("kotlin-parcelize")
        }
    }

    private fun Project.applyKotlinOptions() {
        tasks.withType(KotlinCompile::class.java) {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

    private fun Project.androidConfig() {
        android.run {
            namespace = packageName
            compileSdkVersion(ProjectConfig.compileSdk)
            defaultConfig {
                minSdk = ProjectConfig.minSdk
                targetSdk = ProjectConfig.targetSdk
                versionCode = ProjectConfig.versionCode
                versionName = ProjectConfig.versionName

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }


    private fun Project.androidComposeConfig() {
        android.run {
            namespace = packageName
            compileSdkVersion(ProjectConfig.compileSdk)
            defaultConfig {
                minSdk = ProjectConfig.minSdk
                targetSdk = ProjectConfig.targetSdk
                versionCode = ProjectConfig.versionCode
                versionName = ProjectConfig.versionName

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }

            buildFeatures.compose = true

            composeOptions {
                kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
            }
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            "kapt"(DaggerHilt.hiltCompiler)
            "implementation"(DaggerHilt.hiltAndroid)

            "testImplementation"(Testing.junit4)
            "testImplementation"(Testing.junitAndroidExt)
            "testImplementation"(Testing.truth)
            "testImplementation"(Testing.coroutines)
            "testImplementation"(Testing.turbine)
            "testImplementation"(Testing.composeUiTest)
            "testImplementation"(Testing.mockk)
            "testImplementation"(Testing.mockWebServer)

            "androidTestImplementation"(Testing.junit4)
            "androidTestImplementation"(Testing.junitAndroidExt)
            "androidTestImplementation"(Testing.truth)
            "androidTestImplementation"(Testing.coroutines)
            "androidTestImplementation"(Testing.turbine)
            "androidTestImplementation"(Testing.composeUiTest)
            "androidTestImplementation"(Testing.mockk)
            "androidTestImplementation"(Testing.mockWebServer)
            "androidTestImplementation"(Testing.hiltTesting)

            "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:1.2.2")
        }
    }

    private fun Project.composeDependenciesConfig() {
        dependencies {
            "implementation"(Compose.compiler)
            "implementation"(Compose.ui)
            "implementation"(Compose.uiToolingPreview)
            "implementation"(Compose.hiltNavigationCompose)
            "implementation"(Compose.material)
            "implementation"(Compose.runtime)
            "implementation"(Compose.navigation)
            "implementation"(Compose.viewModelCompose)
            "implementation"(Compose.activityCompose)

            "kapt"(DaggerHilt.hiltCompiler)
            "implementation"(DaggerHilt.hiltAndroid)

            "testImplementation"(Testing.junit4)
            "testImplementation"(Testing.junitAndroidExt)
            "testImplementation"(Testing.truth)
            "testImplementation"(Testing.coroutines)
            "testImplementation"(Testing.turbine)
            "testImplementation"(Testing.composeUiTest)
            "testImplementation"(Testing.mockk)
            "testImplementation"(Testing.mockWebServer)

            "androidTestImplementation"(Testing.junit4)
            "androidTestImplementation"(Testing.junitAndroidExt)
            "androidTestImplementation"(Testing.truth)
            "androidTestImplementation"(Testing.coroutines)
            "androidTestImplementation"(Testing.turbine)
            "androidTestImplementation"(Testing.composeUiTest)
            "androidTestImplementation"(Testing.mockk)
            "androidTestImplementation"(Testing.mockWebServer)
            "androidTestImplementation"(Testing.hiltTesting)

            "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:1.2.2")
        }
    }
}
