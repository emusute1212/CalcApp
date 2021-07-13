object Dep {
    object Kotlin {
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin.kotlin}"
        const val stdLib =
            "org.jetbrains.kotlin:kotlin-stdlib:${Version.Kotlin.kotlin}"
        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Kotlin.coroutine}"
    }

    object Gradle {
        const val gradlePlugin =
            "com.android.tools.build:gradle:${Version.Gradle.gradlePlugin}"
    }

    object Google {
        const val ossLicensesPlugin =
            "com.google.android.gms:oss-licenses-plugin:${Version.Google.ossLicensesPlugin}"
        const val ossLicenses =
            "com.google.android.gms:play-services-oss-licenses:${Version.Google.ossLicenses}"

        object Dagger {
            const val core = "com.google.dagger:dagger:${Version.Google.dagger}"
            const val compiler = "com.google.dagger:dagger-compiler:${Version.Google.dagger}"
            const val android = "com.google.dagger:dagger-android:${Version.Google.dagger}"
            const val androidSupport =
                "com.google.dagger:dagger-android-support:${Version.Google.dagger}"
            const val androidProcessor =
                "com.google.dagger:dagger-android-processor:${Version.Google.dagger}"
        }
    }

    object Androidx {
        const val coreKtx = "androidx.core:core-ktx:${Version.Androidx.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.Androidx.appCompat}"
        const val material = "com.google.android.material:material:${Version.Androidx.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.Androidx.constraintLayout}"
        const val fragment = "androidx.fragment:fragment-ktx:${Version.Androidx.fragment}"
        const val activity = "androidx.activity:activity-ktx:${Version.Androidx.activity}"

        object Lifecycle {
            const val ktx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Androidx.lifecycle}"
            const val commonJava8 =
                "androidx.lifecycle:lifecycle-common-java8:${Version.Androidx.lifecycle}"
        }
    }

    object ThirdParty {
        object Stetho {
            const val core = "com.facebook.stetho:stetho:${Version.ThirdParty.stetho}"
        }
    }

    object Test {
        const val jUnit = "junit:junit:${Version.Test.jUnit}"
    }

    object AndroidTest {
        const val jUnit = "androidx.test.ext:junit:${Version.AndroidTest.jUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.AndroidTest.espresso}"
    }
}