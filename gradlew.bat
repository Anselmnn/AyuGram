@echo off
rem Gradle wrapper for Windows - delegates to system Gradle or downloads if needed

set GRADLE_VERSION=8.5
set GRADLE_DIST=gradle-%GRADLE_VERSION%-bin.zip
set GRADLE_URL=https://services.gradle.org/distributions/%GRADLE_DIST%
set GRADLE_USER_HOME=%GRADLE_USER_HOME%
if "%GRADLE_USER_HOME%"=="" set GRADLE_USER_HOME=%USERPROFILE%\.gradle

set WRAPPER_DIR=%GRADLE_USER_HOME%\wrapper\dists\gradle-%GRADLE_VERSION%-bin

if exist "%WRAPPER_DIR%" (
    for /d %%i in ("%WRAPPER_DIR%\gradle-%GRADLE_VERSION%") do (
        if exist "%%i\bin\gradle.bat" (
            call "%%i\bin\gradle.bat" %*
            exit /b %ERRORLEVEL%
        )
    )
)

echo Downloading Gradle %GRADLE_VERSION%...
if not exist "%WRAPPER_DIR%" mkdir "%WRAPPER_DIR%"
cd /d "%WRAPPER_DIR%"

if exist gradle.zip del gradle.zip
curl -L -o gradle.zip "%GRADLE_URL%" 2>nul || powershell -Command "Invoke-WebRequest -Uri '%GRADLE_URL%' -OutFile 'gradle.zip'"
if not exist gradle.zip (
    echo Error: Failed to download Gradle
    exit /b 1
)

powershell -Command "Expand-Archive -Path 'gradle.zip' -DestinationPath '.' -Force"
del gradle.zip

for /d %%i in ("%WRAPPER_DIR%\gradle-%GRADLE_VERSION%") do (
    if exist "%%i\bin\gradle.bat" (
        call "%%i\bin\gradle.bat" %*
        exit /b %ERRORLEVEL%
    )
)

echo Error: Gradle not found after download
exit /b 1