#!/bin/sh

rm -f tpid
nohup java -jar 你的jar包名称.jar --spring.config.location=application.yml > /dev/null 2>&1 &
echo $! > tpid
echo Start Success!