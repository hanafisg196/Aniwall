import java.util.Properties


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    alias(libs.plugins.compose.compiler)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.anime.live_wallpapershd"
    compileSdk = 35

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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation(libs.firebase.analytics)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.material3:material3")
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.tools.core)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.11.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("androidx.compose.ui:ui-util-android:1.7.5")


    //Navigation Compose
    implementation (libs.androidx.navigation.compose)
    //Splash Api
    implementation (libs.androidx.core.splashscreen)
    //Coil
    implementation(libs.coil.compose)
    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.moshi)
    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    //Datastore
    implementation (libs.androidx.datastore.preferences)
    //exoplayer
    implementation ("androidx.media3:media3-exoplayer:1.4.1")
    implementation ("androidx.media3:media3-ui:1.4.1")
    // Paging 3.0
    implementation (libs.androidx.paging.compose)
    //Shape
    implementation (libs.androidx.graphics.shapes)
    //Permissions
    implementation (libs.accompanist.permissions)
    //Pref Manager
    implementation (libs.easyprefs)
    //One Tap SignIn
    implementation(libs.androidx.credentials)
    implementation(libs.googleid)
    implementation (libs.androidx.credentials.play.services.auth)
    //admob
    implementation(libs.play.services.ads)

}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
kapt {
    correctErrorTypes = true
}