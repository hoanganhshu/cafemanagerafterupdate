plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.cafemanagerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cafemanagerapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.google.code.gson:gson:2.8.9")

    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation("com.android.car.ui:car-ui-lib:2.6.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.core:core-ktx:1.13.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    implementation("com.google.android.gms:play-services-tflite-acceleration-service:16.0.0-beta01")
    implementation("com.google.ar.sceneform:filament-android:1.17.1")




    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}