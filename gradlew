#!/usr/bin/env bash
# Akame Forge Engine 2026
if [ ! -d "gradle/wrapper" ]; then
    mkdir -p gradle/wrapper
    echo "distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip" > gradle/wrapper/gradle-wrapper.properties
fi
exec sh -c "export TERM=dumb; ./gradlew assembleDebug"
