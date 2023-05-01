// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
    }
}
plugins {
    id("com.android.application") version "8.2.0-alpha01" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    kotlin("kapt") version "1.8.10"
}