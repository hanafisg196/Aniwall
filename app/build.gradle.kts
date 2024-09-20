import java.util.Properties


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.anime.live_wallpapershd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.anime.live_wallpapershd"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField ("String","BASE_URL","\"${properties.getProperty("BASE_URL")}\"")
        buildConfigField ("String","API_KEY","\"${properties.getProperty("API_KEY")}\"")
        buildConfigField ("String","APP_ID","\"${properties.getProperty("APP_ID")}\"")
        buildConfigField ("String","WEB_CLIENT_ID","\"${properties.getProperty("WEB_CLIENT_ID")}\"")

    }
    android {
        buildFeatures {
            buildConfig = true
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

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("com.google.firebase:firebase-analytics:22.1.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("androidx.compose.ui:ui-util-android:1.6.6")


    //Navigation Compose
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    //Splash Api
    implementation ("androidx.core:core-splashscreen:1.0.1")
    //Coil
    implementation("io.coil-kt:coil-compose:2.6.0")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.moshi:moshi:1.15.1")
    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //Datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.1")
    //exoplayer
    implementation ("androidx.media3:media3-exoplayer:1.3.1")
    implementation ("androidx.media3:media3-ui:1.3.1")
    // Paging 3.0
    implementation ("androidx.paging:paging-compose:1.0.0-alpha16")
    //Shape
    implementation ("androidx.graphics:graphics-shapes:1.0.0-beta01")
    //Permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.24.9-beta")
    //Pref Mananger
    implementation ("com.pixplicity.easyprefs:EasyPrefs:1.10.0")
    //One Tap SignIn
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    implementation ("androidx.credentials:credentials-play-services-auth:1.2.1")


}
kapt {
    correctErrorTypes = true
}