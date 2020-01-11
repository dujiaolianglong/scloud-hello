#!/bin/sh

#check JAVA_HOME & java
#if [ -z "$JAVA_HOME" ] ; then
    #JAVA_HOME=/usr/local/java
#fi

SCRIPT_DIR=$(cd $(dirname $0); pwd)

BASE_HOME=$(dirname $SCRIPT_DIR)

#==============================================================================
#set JAVA_OPTS
JAVA_OPTS="-server -Xms215m -Xmx512m -Xmn512m -Xss256k -XX:MaxMetaspaceSize=128m"

#performance Options
JAVA_OPTS="$JAVA_OPTS -XX:+DisableExplicitGC"
JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC" 
JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC"
JAVA_OPTS="$JAVA_OPTS -XX:+CMSClassUnloadingEnabled" 
JAVA_OPTS="$JAVA_OPTS -XX:+UseCMSInitiatingOccupancyOnly"
JAVA_OPTS="$JAVA_OPTS -XX:CMSInitiatingOccupancyFraction=70" 
JAVA_OPTS="$JAVA_OPTS -XX:+CMSScavengeBeforeRemark"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintGC"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDateStamps"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCTimeStamps"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCApplicationStoppedTime"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintFlagsFinal"
JAVA_OPTS="$JAVA_OPTS -Xloggc:/data/configserverlog/hello-config-gc.log"
JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError"
JAVA_OPTS="$JAVA_OPTS -XX:HeapDumpPath=/data/configserverlog/dump_core_pid%p.hprof"
JAVA_OPTS="$JAVA_OPTS -XX:ErrorFile=/data/configserverlog/hs_err_pid%p.log"


#debug Options
JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=8072,server=y,suspend=n"
#==============================================================================

PORT="9411"
EUREKA="http://192.168.1.102:8761/eureka/"
APP_SPROFILE="dev"

#while getopts ":p:e:f:" opt
#do
#			case $opt in
#					p)
#							echo "参数port的值为: $OPTARG"
#							PORT="$OPTARG"
#							;;
#					e)
#							echo "参数eureka的值为: $OPTARG"
#							EUREKA="$OPTARG"
#							;;
#					f)
#							echo "参数app.sprofile的值为: $OPTARG"
#							APP_SPROFILE="$OPTARG"
#							;;
#					?)
#							echo "未知参数"
#							exit 1;;
#			esac
#done

#application init
APP_INIT="--server.port=$PORT"
APP_INIT="$APP_INIT --eureka.client.serviceUrl.defaultZone=$EUREKA"
APP_INIT="$APP_INIT --app.sprofile=$APP_SPROFILE"

TEMP_CLASSPATH="$BASE_HOME/conf"
for i in "$BASE_HOME"/lib/*.jar
do
    TEMP_CLASSPATH="$TEMP_CLASSPATH:$i"
done
#==============================================================================
#startup server
RUN_CMD="nohup $JAVA_HOME/bin/java"
RUN_CMD="$RUN_CMD -classpath $TEMP_CLASSPATH"
RUN_CMD="$RUN_CMD $JAVA_OPTS"
RUN_CMD="$RUN_CMD com.lxl.hello.zipkin.HelloZipkinAppStarter"
RUN_CMD="$RUN_CMD $APP_INIT"
RUN_CMD="$RUN_CMD &"

echo $RUN_CMD
eval $RUN_CMD

sleep 10

nc -z -w 10 127.0.0.1 $PORT
if [ $? -ne 0 ];then
	echo "--------------------------hello-zipkin server start fail-----------------------------------"
else
	echo "--------------------------hello-zipkin server start success-----------------------------------"
fi
#==============================================================================