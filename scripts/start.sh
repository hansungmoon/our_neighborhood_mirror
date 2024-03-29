#!/usr/bin/env bash
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/final
PROJECT_NAME=our_neighborhood

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 Application 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME에 실행 권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME의 profile: $IDLE_PROFILE"

nohup java -jar \
  -Dspring.config.location=/home/ec2-user/app/application.yml \
  -Dspring.profiles.active=$IDLE_PROFILE \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
