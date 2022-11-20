#!/bin/bash

find -name "*.java" > sources.txt \
&& javac -cp junit-platform-console-standalone-1.9.1.jar @sources.txt -d testtarget \
&& java -jar junit-platform-console-standalone-1.9.1.jar -cp testtarget:target --scan-classpath