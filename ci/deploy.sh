#!/usr/bin/env sh

set -e -u

cd java-buildpack-container-customizer
./mvnw -q -Dmaven.test.skip=true deploy
