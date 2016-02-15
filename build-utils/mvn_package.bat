@echo off
cd ..
call mvn clean install -Dmaven.test.skip=true -e -U
pause