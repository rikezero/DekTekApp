plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.ksp)
    id("kotlin-parcelize")
}

android {
    namespace = "com.rikezero.dektek"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rikezero.dektek"
        minSdk = 24
        targetSdk = 35

        versionName = System.getenv("VERSION_NAME") ?: "0.0.1"

        val versionParts = (versionName ?: "0.0.1").split(".")
        versionCode = (versionParts.getOrNull(0)?.toIntOrNull() ?: 0) * 10000 +
                (versionParts.getOrNull(1)?.toIntOrNull() ?: 0) * 100 +
                (versionParts.getOrNull(2)?.toIntOrNull() ?: 0)

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildToolsVersion = "35.0.0"
}

dependencies {
    // MTG SDK
    implementation(libs.mtg.sdk)

    // Coil
    implementation(libs.coil.core)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.coil.video)
    implementation(libs.coil.gif)

    // Shimmer
    implementation(libs.compose.shimmer)

    // Coroutines
    implementation(libs.coroutines)

    // Import the Compose BOM
    implementation(platform(libs.compose.bom))

    // Compose UI libraries
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)

    // Optional Compose libraries
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.material.icons.extended)

    // AndroidX Activity integration
    implementation(libs.activity.compose)

    // Lifecycle and ViewModel integration
    implementation(libs.lifecycle.viewmodel.compose)

    // Navigation component for Compose
    implementation(libs.navigation.compose)

    // Accompanist
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist)

    // Koin dependencies
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    testImplementation(libs.koin.test)

    // Retrofit2 and OkHttp3 dependencies
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)

    // Android Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.fragment.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.compose.ui.test.junit4)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.room.testing)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.core.ktx)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.kotlin.reflect)
    implementation(libs.ksp)

    // Mockk
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk)
}

tasks.withType<Test> {
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED", // Required for ByteBuddy
        "-Dnet.bytebuddy.experimental=true"           // Enable experimental Java 17 support
    )
}

tasks.named("preBuild") {
    dependsOn(rootProject.tasks.named("setupHusky"))
    dependsOn(rootProject.tasks.named("setupReleaseIt"))
}