object Version {
    object Calculator {
        const val compileSdk = 30
        const val targetSdk = 30
        const val minSdk = 22

        private val version = ApplicationVersion(1, 1, 0, 0)
        val code = version.code
        val name = version.name
    }

    object Kotlin {
        const val kotlin = "1.5.10"
        const val coroutine = "1.5.0"
    }

    object Gradle {
        const val gradlePlugin = "4.1.3"
    }

    object Google {
        const val ossLicensesPlugin = "0.10.4"
        const val ossLicenses = "17.0.0"
        const val dagger = "2.37"
        const val googleService = "4.3.8"

        object Firebase {
            const val gradle = "2.7.1"
            const val bom = "28.2.0"
        }
    }

    object Androidx {
        const val coreKtx = "1.6.0"
        const val appCompat = "1.3.0"
        const val material = "1.4.0"
        const val constraintLayout = "2.0.4"
        const val lifecycle = "2.2.0"
        const val fragment = "1.3.3"
        const val activity = "1.2.3"
    }

    object ThirdParty {
        const val stetho = "1.5.1"
    }

    object Test {
        const val jUnit = "4.13.2"
    }

    object AndroidTest {
        const val jUnit = "1.1.2"
        const val espresso = "3.3.0"
    }
}