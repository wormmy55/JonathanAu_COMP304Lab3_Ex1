import java.util.regex.Pattern.compile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.jonathan.jonathanau_comp304lab3_ex1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jonathan.jonathanau_comp304lab3_ex1"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "35.0.0"

}

dependencies {
    //val work_version = "2.9.1"
    // Kotlin + coroutines
    implementation(libs.androidx.work.runtime.ktx.v291)

    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json.v122)

    // Koin for Android
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.insert.koin.koin.androidx.workmanager)
    implementation(libs.kotlinx.serialization)
    implementation(libs.androidx.benchmark.macro)
    implementation(libs.my.library)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.koin.androidx.compose)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.v490)
    //implementation("libs.androidx.material3.windowsizeclass:1.3")
    implementation(libs.material3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.window)
    implementation(libs.androidx.espresso.core)
    implementation(libs.support.annotations)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.window.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}