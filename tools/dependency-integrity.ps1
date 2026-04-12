param(
    [switch]$Lenient
)

$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $PSScriptRoot
Set-Location $projectRoot

$verificationMode = if ($Lenient) { "lenient" } else { "strict" }

function Invoke-Gradle {
    param(
        [Parameter(Mandatory = $true)]
        [string[]]$Arguments
    )

    & .\gradlew.bat @Arguments
    if ($LASTEXITCODE -ne 0) {
        throw "Gradle command failed: gradlew.bat $($Arguments -join ' ')"
    }
}

Write-Host "Generating verification metadata (SHA-256)..."
Invoke-Gradle -Arguments @(
    "--dependency-verification", $verificationMode,
    "--write-verification-metadata", "sha256",
    ":app:assembleDebug", ":app:lintDebug", ":app:testDebugUnitTest"
)

Write-Host "Writing dependency lock files..."
Invoke-Gradle -Arguments @("--dependency-verification", $verificationMode, "--write-locks", ":app:dependencies", "--configuration", "debugRuntimeClasspath")
Invoke-Gradle -Arguments @("--dependency-verification", $verificationMode, "--write-locks", ":app:dependencies", "--configuration", "releaseRuntimeClasspath")

Write-Host "Dependency integrity artifacts updated."




