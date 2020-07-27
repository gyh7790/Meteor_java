#!/bin/bash

pid=`ps -ef|grep meteor-java.jar|grep -v grep|awk '{print $2}'`
if [ ! -n "$pid" ]; then
	echo 'start failer'
else
	echo 'start process is => '$pid
	echo 'closeing'
	kill -9 $pid
	echo 'closed'
fi