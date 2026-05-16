plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    // Versions plugin to generate dependency update reports
    id("com.github.ben-manes.versions") version "0.53.0"
}

allprojects {
    dependencyLocking {
        lockAllConfigurations()
    }
}