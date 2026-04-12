# 40kCC Modernized Build Setup

This project has been modernized with:

- Gradle 8.10.2 wrapper
- Android Gradle Plugin 8.6.1
- Kotlin 1.9.24 + KSP 1.9.24-1.0.20
- Java 21 build runtime with Java 17 bytecode target
- Centralized dependency/plugin versions in `gradle/libs.versions.toml`
- Dependency locking enabled for all configurations
- Dependency verification enforced by Gradle command flags in CI/scripts
- CI build workflow in `.github/workflows/android.yml`

## Prerequisites

- JDK 21 (Android Studio bundled JBR works)
- Optional: set `JAVA_HOME` to your JDK 21 install
- App compilation still targets Java 17 bytecode (`sourceCompatibility`/`targetCompatibility`)

## Quick Verify

```powershell
Set-Location "C:\Users\Freakiod\Documents\GitHub\40kCC"
.\gradlew.bat --dependency-verification strict :app:assembleDebug
.\gradlew.bat --dependency-verification strict :app:lintDebug
.\gradlew.bat --dependency-verification strict :app:testDebugUnitTest
```

## Dependency Integrity Artifacts

Generate/update verification metadata and lockfiles:

```powershell
Set-Location "C:\Users\Freakiod\Documents\GitHub\40kCC"
.\tools\dependency-integrity.ps1
```

The script now resolves `assemble`, `lint`, and `test` dependencies while writing metadata,
so strict verification passes for all CI validation tasks.

If you need a temporary bootstrap run before metadata is fully populated:

```powershell
Set-Location "C:\Users\Freakiod\Documents\GitHub\40kCC"
.\tools\dependency-integrity.ps1 -Lenient
```

Expected artifacts after generation include:

- `gradle/verification-metadata.xml`
- `gradle.lockfile` files written by Gradle for resolved configurations
