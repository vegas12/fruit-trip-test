#!/bin/bash

find -name "*.java" > sources.txt && javac @sources.txt -d target && java -cp target com.vegas.fruit_ride.Application