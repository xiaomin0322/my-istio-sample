#!/bin/bash

set -e

exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /work/app.jar


tail -f /dev/null