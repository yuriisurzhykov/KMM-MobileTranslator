import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version Deps.kotlinVersion
}

android {
    namespace = ProjectConfig.Android.applicationId
    compileSdk = ProjectConfig.Android.compileVersion
    defaultConfig {
        applicationId = ProjectConfig.Android.applicationId
        minSdk = ProjectConfig.Android.minVersion
        targetSdk = ProjectConfig.Android.targetVersion
        versionCode = 1
        versionName = "1.0"
    }
    signingConfigs {
        create("release") {
            val properties = Properties()
            FileInputStream(file("signing.properties")).use { stream ->
                properties.load(stream)
            }
            storeFile = file(properties.getProperty("keystoreFile"))
            storePassword = properties.getProperty("keystorePassword").toString()
            keyAlias = properties.getProperty("keyAlias").toString()
            keyPassword = properties.getProperty("keyPassword").toString()
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Deps.composeUi)
    implementation(Deps.composeUiTooling)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterial)
    implementation(Deps.activityCompose)
    implementation(Deps.composeIconsExtended)
    implementation(Deps.composeNavigation)
    implementation(Deps.coilCompose)

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigationCompose)

    implementation(Deps.ktorAndroid)

    implementation(platform(Deps.firebaseBoom))
    implementation(Deps.firebaseCrashlytics)

    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.jUnit)
    androidTestImplementation(Deps.composeTesting)
    debugImplementation(Deps.composeTestManifest)

    kaptAndroidTest(Deps.hiltAndroidCompiler)
    androidTestImplementation(Deps.hiltTesting)
}