#!/bin/bash

find -name "*.java" | grep -vwE "(test)" > sources.txt &&  javac @sources.txt -d target && java -cp target com.vegas.fruit_ride.Application