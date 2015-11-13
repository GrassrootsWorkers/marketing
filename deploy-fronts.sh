#!/bin/bash
mvn clean package -pl fronts -am -Dmaven.test.skip=true
ROOT_DIR=./fronts
cd $ROOT_DIR/target
jar -xvf fronts-1.0-SNAPSHOT.war 
mv fronts-1.0-SNAPSHOT front
WEIXIN_DIR=/app/deploy/weixin
rm -rf $WEIXIN_DIR/front
mv front $WEIXIN_DIR