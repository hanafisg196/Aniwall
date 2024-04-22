plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.apptesting.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.apptesting.test"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        setProperty("archivesBaseName", "Sphere2")

    }

    buildFeatures {
        buildConfig = true
    }
    dataBinding {
        enable = true
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.jakewharton.rxbinding3:rxbinding:3.0.0-alpha2")



    implementation("com.github.Dimezis:BlurView:version-2.0.3")

    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    implementation("com.google.android.gms:play-services-ads:23.0.0")


    //billing in app purchase
    implementation("com.android.billingclient:billing:6.2.0")
    implementation("com.google.guava:guava:24.1-jre")

//firebase
    implementation(platform("com.google.firebase:firebase-bom:31.1.0"))
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")
//    cardSlider

    implementation("com.github.IslamKhSh:CardSlider:1.0.1")


    // dependancy fo r exoplayer
    implementation("androidx.media3:media3-exoplayer:1.3.0")
    implementation("androidx.media3:media3-exoplayer-dash:1.3.0")
    implementation("androidx.media3:media3-ui:1.3.0")
    implementation("com.github.alexto9090:PRDownloader:1.0")


}