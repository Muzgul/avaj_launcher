#!/bin/sh

find -name *.java > sources.txt
javac '@sources.txt'