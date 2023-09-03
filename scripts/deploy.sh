#!/usr/bin/env bash

# Modify the following variables according to your application and environment
REPOSITORY=/home/ec2-user/boogie/build/libs
APP_NAME=boogie_cicd
JAR_FILE="boogie-0.0.1-SNAPSHOT.jar"

# Stop the currently running application
CURRENT_PID=$(pgrep -f $APP_NAME)
if [ -z "$CURRENT_PID" ]; then
  echo "> No running application to stop."
else
  echo "> Killing process $CURRENT_PID"
  sudo kill -15 $CURRENT_PID
  sleep 5
fi

# Deploy the new JAR file
JAR_PATH="$REPOSITORY/$JAR_FILE"
if [ -f "$JAR_PATH" ]; then
  echo "> Deploying - $JAR_PATH"
  nohup java -jar "$JAR_PATH" > /dev/null 2> /dev/null < /dev/null &
else
  echo "> JAR file not found at $JAR_PATH"
fi