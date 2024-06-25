plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.navigation_drawer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.navigation_drawer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    val fragment_version = "1.6.1"

    val room_version="2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")


    val lifecycle_version = "2.8.2"
// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")

    val appcompat_version = "1.7.0"

    implementation("androidx.appcompat:appcompat:$appcompat_version")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
}


