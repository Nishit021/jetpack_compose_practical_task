plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.salekart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.salekart"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Common
    implementation(Dependencies.coreKtx)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.material)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)

    // ViewModels
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.lifecycleViewModelComposeKtx)
    implementation(Dependencies.lifecycleViewModelRuntimeCompose)

    // Unit testing
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testJunit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeTestJunit4)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
    testImplementation(Dependencies.mockWebServer)
    testImplementation(Dependencies.testCoroutines)
    testImplementation(Dependencies.truth)

    // Retrofit, GSON, Moshi, logging
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.moshi)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.loggingInterceptor)

    // Imported Modules
    implementation(project(Modules.utilities))

    // Hilt
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltNavigation)
    kapt(Dependencies.hiltAndroidCompiler)

    // coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutineAndroid)

    // splash screen
    implementation(Dependencies.splashScreen)

    // network image
    implementation(Dependencies.coil)

}
 kapt {
     correctErrorTypes = true
 }