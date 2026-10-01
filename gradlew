#!/bin/bash
# Gradle wrapper script - downloads gradle if needed
# This is a minimal wrapper for CI environments

GRADLE_VERSION="8.5"
GRADLE_DIST_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_USER_HOME="${GRADLE_USER_HOME:-$HOME/.gradle}"
WRAPPER_DIR="${GRADLE_USER_HOME}/wrapper/dists/gradle-${GRADLE_VERSION}-bin"

# Find or download gradle
find_gradle() {
    if [ -d "${WRAPPER_DIR}" ]; then
        GRADLE_HOME=$(find "${WRAPPER_DIR}" -name "gradle-${GRADLE_VERSION}" -type d 2>/dev/null | head -1)
        if [ -n "${GRADLE_HOME}" ] && [ -f "${GRADLE_HOME}/bin/gradle" ]; then
            echo "${GRADLE_HOME}/bin/gradle"
            return 0
        fi
    fi
    return 1
}

download_gradle() {
    mkdir -p "${WRAPPER_DIR}"
    cd "${WRAPPER_DIR}"
    echo "Downloading Gradle ${GRADLE_VERSION}..."
    if command -v curl >/dev/null; then
        curl -L -o gradle.zip "${GRADLE_DIST_URL}"
    elif command -v wget >/dev/null; then
        wget -O gradle.zip "${GRADLE_DIST_URL}"
    else
        echo "Error: curl or wget required to download Gradle"
        exit 1
    fi
    unzip -q gradle.zip
    rm gradle.zip
    GRADLE_HOME=$(find "${WRADLE_DIR}" -name "gradle-${GRADLE_VERSION}" -type d 2>/dev/null | head -1)
    echo "${GRADLE_HOME}/bin/gradle"
}

GRADLE_BIN=$(find_gradle) || GRADLE_BIN=$(download_gradle)

exec "${GRADLE_BIN}" "$@"