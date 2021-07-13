plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    compileSdkVersion(Version.Calculator.compileSdk)

    defaultConfig {
        applicationId = "io.github.emusute1212.calculator"
        minSdkVersion(Version.Calculator.minSdk)
        targetSdkVersion(Version.Calculator.targetSdk)
        versionCode = Version.Calculator.code
        versionName = Version.Calculator.name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    // Androidx
    implementation(Dep.Androidx.appCompat)
    implementation(Dep.Androidx.constraintLayout)
    implementation(Dep.Androidx.coreKtx)
    implementation(Dep.Androidx.material)
    implementation(Dep.Androidx.activity)
    implementation(Dep.Androidx.fragment)
    implementation(Dep.Androidx.Lifecycle.ktx)
    implementation(Dep.Androidx.Lifecycle.commonJava8)

    // Test
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.AndroidTest.espresso)
    androidTestImplementation(Dep.AndroidTest.jUnit)

    //Kotlin
    implementation(Dep.Kotlin.stdLib)
    implementation(Dep.Kotlin.coroutine)

    //Stetho
    implementation(Dep.ThirdParty.Stetho.core)

    //Dagger2
    implementation(Dep.Google.Dagger.core)
    kapt(Dep.Google.Dagger.compiler)
    implementation(Dep.Google.Dagger.android)
    implementation(Dep.Google.Dagger.androidSupport)
    kapt(Dep.Google.Dagger.androidProcessor)

    // OssLicenseView
    implementation(Dep.Google.ossLicenses)
}
