import org.gradle.kotlin.dsl.kapt

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
//    id("kotlin-kapt")
    alias(libs.plugins.daggerHiltAndroid)
//    id("kotlin-parcelize")
    alias(libs.plugins.devtoolsKsp)

    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "com.example.ecommerceapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ecommerceapp"
        minSdk = 28
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.material3)
    implementation(libs.androidx.window)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.runtime)

    // OpenCSV
    implementation(libs.opencsv)

    // Compose dependencies
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose.v190beta01)
    implementation(libs.accompanist.swiperefresh)

    // Compose navigation destinations
    implementation(libs.core)
    ksp(libs.ksp)

    // coil
    implementation(libs.coil.compose)

    // Dagger - hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.work.runtime.ktx)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.work)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Room KTX
    implementation(libs.androidx.room.ktx)

    //EncryptedSharedPreferences
    implementation(libs.androidx.security.crypto)

}