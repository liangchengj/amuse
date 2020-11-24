#!/usr/bin/env bash
# Created at 2020/5/26 22:07.
# @author Liangcheng Juves

if [ -d "../libgous.so" ]; then
    sudo rm -rf ../libgous.so
fi

if [ -d "../gous/target" ]; then
    sudo rm -rf ../gous/target
fi
if [ -d "../gous/src/main/resources/jniLibs/libgous.so" ]; then
    sudo rm -rf ../gous/src/main/resources/jniLibs/libgous.so
fi
gcc -I $JAVA_HOME/include/linux -I $JAVA_HOME/include -I include -fPIC -shared -o ../gous/src/main/resources/jniLibs/libgous.so gous.c

echo "All is OK!"