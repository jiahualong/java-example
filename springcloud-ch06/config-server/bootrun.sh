#!/bin/sh 
export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
export ENCRYPT_KEY=IMSYMMETRIC
gradle bootRun
