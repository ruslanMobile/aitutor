plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.languages.tutordebug"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.languages.tutordebug"
        minSdk = 26
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"
            manifestPlaceholders["appName"] = "Ai tutor"
        }

        debug {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"
            manifestPlaceholders["appName"] = "Debug Ai tutor"
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

    flavorDimensions("version")

    productFlavors {
        create("dev") {
            //signingConfig = signingConfigs.getByName("release")
            applicationId = "com.languages.tutordebug"
        }
        create("prod") {
            //signingConfig = signingConfigs.getByName("release")
            applicationId = "com.languages.aitutor"
        }
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    implementation platform('org.jetbrains.kotlin:kotlin-bom:1.8.0')
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'
    implementation("androidx.core:core-splashscreen:1.0.0")
//
    // Compose
    // implementation "androidx.compose.ui:ui:1.4.3"
//    implementation "androidx.compose.ui:ui-tooling-preview:1.4.3"
//    androidTestImplementation platform('androidx.compose:compose-bom:2022.10.00')
//    androidTestImplementation 'androidx.compose.ui:ui-test-junit4'
//    debugImplementation 'androidx.compose.ui:ui-tooling'
//    debugImplementation 'androidx.compose.ui:ui-test-manifest'
//    implementation 'androidx.activity:activity-compose:1.7.2'
//    implementation platform('androidx.compose:compose-bom:2022.10.00')
//    implementation 'androidx.compose.ui:ui'
//    implementation 'androidx.compose.ui:ui-graphics'
//    implementation 'androidx.compose.ui:ui-tooling-preview'
//    implementation 'androidx.compose.material3:material3'
//
    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.test:runner:1.5.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.1.0-alpha01")

//
//    //Test
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
//    testImplementation("com.google.dagger:hilt-android-testing:2.44")
//    kaptTest("com.google.dagger:hilt-android-compiler:2.44")
//    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
//    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
//
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics-ktx")

    //Play services
    implementation("com.google.android.gms:play-services-base:18.2.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
}