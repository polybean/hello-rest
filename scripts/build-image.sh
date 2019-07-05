#!/bin/bash

PROJECT_HOME=$(realpath "$(dirname -- $0)/..")
IMAGE_NAME=$($PROJECT_HOME/mvnw help:evaluate -Dexpression=project.name -q -DforceStdout)
IMAGE_VERSION=$($PROJECT_HOME/mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)

docker run --rm \
  -v $HOME/.m2:/root/.m2:rw \
  -v $PROJECT_HOME:/app:rw \
  -w "/app" \
  openjdk:8u191-jdk-alpine3.9 \
  sh -c "./mvnw -Dmaven.test.skip=true clean package"

docker build $PROJECT_HOME -t $IMAGE_NAME:$IMAGE_VERSION --build-arg jar_file=$IMAGE_NAME-$IMAGE_VERSION.jar
