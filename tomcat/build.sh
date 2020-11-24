#!/bin/bash
# @author Liangcheng Juves
# Created at 2020/5/5 15:55

if [ -d "catalina" ]; then
  rm -rf catalina
fi
ant
