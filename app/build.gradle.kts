plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
}

apply{
    from("../shared_dependencies.gradle")
}

android {
    namespace = "com.example.mychallenge3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mychallenge3"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Dependency on a local library module
    implementation(project(":data"))
    implementation(project(":domain"))

    // Dependency on local binaries
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))


    //splash
    implementation(libs.androidx.core.splashscreen)

    //lottie
    implementation(libs.lottie)

    //work manager
    implementation(libs.androidx.work.runtime.ktx)
}