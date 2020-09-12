package com.vipulasri.jetinstagram.buildsrc

object Libs {
  const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.0-alpha10"
  const val junit = "junit:junit:4.13"

  const val exoplayer = "com.google.android.exoplayer:exoplayer:2.11.8"

  object Accompanist {
    private const val version = "0.2.1"
    const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
  }

  object Kotlin {
    private const val version = "1.4.0"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
  }

  object Coroutines {
    private const val version = "1.3.9"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
  }

  object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
    const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha02"
    const val material = "com.google.android.material:material:1.2.0"

    object Compose {
      const val snapshot = ""
      const val version = "1.0.0-alpha02"

      const val runtime = "androidx.compose.runtime:runtime:$version"
      const val foundation = "androidx.compose.foundation:foundation:${version}"
      const val layout = "androidx.compose.foundation:foundation-layout:${version}"
      const val ui = "androidx.compose.ui:ui:${version}"
      const val material = "androidx.compose.material:material:${version}"
      const val animation = "androidx.compose.animation:animation:${version}"
      const val tooling = "androidx.ui:ui-tooling:${version}"
    }

    object Test {
      private const val version = "1.2.0"
      const val core = "androidx.test:core:$version"
      const val rules = "androidx.test:rules:$version"

      object Ext {
        private const val version = "1.1.2-rc01"
        const val junit = "androidx.test.ext:junit-ktx:$version"
      }

      const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    }
  }
}
