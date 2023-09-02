#!/usr/bin/env bash

# Modify the following variables according to your application and environment
REPOSITORY=/home/ec2-user/boogie
APP_NAME=boogie_cicd
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

# Stop the currently running application
CURRENT_PID=$(pgrep -f $APP_NAME)
if [ -z $CURRENT_PID ]; then
  echo "> No running application to stop."
else
  echo "> Killing process $CURRENT_PID"
  sudo kill -15 $CURRENT_PID
  sleep 5
fi

# Deploy the new JAR file
echo "> Deploying - $JAR_PATH"
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &
