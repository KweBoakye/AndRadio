

import org.gradle.api.JavaVersion

object Config{
    val minSdk = 19
    val compileSdk = 29
    val targetSdk = 29
    val javaVersion = JavaVersion.VERSION_1_8

}

object Versions{
    const val gradle_android_version = "3.5.2"
    const val gradle_versions_plugin_version = "0.27.0"
    const val kotlin_version = "1.3.61"

    const val androidx_media_version = "1.1.0"
    const val androidx_media2_version = "1.0.1"
    const val exoplayer_version = "2.11.1"
    const val nav_version = "2.1.0"
    const val retrofit2_version ="2.7.1"
    const val moshi_version = "1.9.2"
    const val coroutines_version = "1.3.3"
    const val android_ktx_version = "1.2.0"
    const val android_appcompat_version = "1.1.0"
    const val constraint_layout_version = "2.0.0-beta4"
    const val junit_version = "4.12"
    const val junit_android_version = "1.1.1"
    const val espresso_core_version = "3.2.0"
    const val dagger_version = "2.25.2"
    const val coroutine_test = "1.3.2"
    const val recyclerview_version = "1.1.0"
    const val lifecycle_version = "2.2.0"
    const val material_design_version = "1.2.0-alpha02"
    const val coil_version = "0.9.2"
    const val multidex_version = "2.0.1"
    const val viewmodel_savedstate_version = "1.0.0-rc03"
    const val assisted_inject_version = "0.5.2"



}

object Navigation{
    val navigation_fragment ="androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    val navigation_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
}



object Networking{
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit2_version}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi_version}"
    val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
    val moshi_kapt =  "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}"
}



object DependencyInjection{
    val dagger = "com.google.dagger:dagger:${Versions.dagger_version}"
    val dagger_kapt = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"
    val assisted_inject = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assisted_inject_version}"
    val assisted_inject_processer= "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assisted_inject_version}"
}

object AndroidLibs{
    val android_ktx = "androidx.core:core-ktx:${Versions.android_ktx_version}"
    val  android_appcompat = "androidx.appcompat:appcompat:${Versions.android_appcompat_version}"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview_version}"
    val lifecycle_extensions=  "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
    val lifecycle_common ="androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"
    val viewmodel_scope = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val viewmodel_savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.viewmodel_savedstate_version}"

}

object KotlinLibs{
    val coroutines_core ="org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_version}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
}

object Plugins{
    val gradle_android = "com.android.tools.build:gradle:${Versions.gradle_android_version}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    val gradle_versions_plugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradle_versions_plugin_version}"
}

object Libs {

    val exoplayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer_version}"
    val androidx_media = "androidx.media:media:${Versions.androidx_media_version}"
    val androidx_media2_session ="androidx.media2:media2-session:${Versions.androidx_media2_version}"
    val coil = "io.coil-kt:coil:${Versions.coil_version}"
    val coil_svg = "io.coil-kt:coil-svg:${Versions.coil_version}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex_version}"
}

object LoggingLibs{
    val timber = "com.jakewharton.timber:timber:4.7.1"
}

object Testing {
    val coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine_test}"
    val junit = "junit:junit:${Versions.junit_version}"
    val junit_android = "androidx.test.ext:junit:${Versions.junit_android_version}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"
    val Android_JUnit_Runner ="androidx.test.runner.AndroidJUnitRunner"
}

object UiLibs{
    val material_design = "com.google.android.material:material:${Versions.material_design_version}"
}