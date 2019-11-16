
import org.gradle.api.JavaVersion

object Config{
    val minSdk = 19
    val compileSdk = 28
    val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8

}

object Versions{
    val gradle_android_version = "3.5.2"
    val gradle_versions_plugin_version = "0.27.0"
    val kotlin_version = "1.3.50"
    val exoplayer_version = "2.10.7"
    val nav_version = "2.1.0"
    val retrofit2_version ="2.6.2"
    val moshi_version = "1.9.1"
    val coroutines_version = "1.3.2"
    val android_ktx_version = "1.1.0"
    val android_appcompat_version = "1.1.0"
    val constraint_layout_version = "1.1.3"
    val junit_version = "4.12"
    val junit_android_version = "1.1.1"
    val espresso_core_version = "3.2.0"

}

object Libs {
    val navigation_fragment ="androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    val navigation_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    val coroutines_core ="org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit2_version}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi_version}"
    val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
    val moshi_kapt =  "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}"
    val exoplayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer_version}"
    val android_ktx = "androidx.core:core-ktx:${Versions.android_ktx_version}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_version}"
    val  android_appcompat = "androidx.appcompat:appcompat:${Versions.android_appcompat_version}"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    val junit = "junit:junit:${Versions.junit_version}"
    val junit_android = "androidx.test.ext:junit:${Versions.junit_android_version}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"


    val gradle_android = "com.android.tools.build:gradle:${Versions.gradle_android_version}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    val gradle_versions_plugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradle_versions_plugin_version}"
}