@echo off
cd ../assist-utils
call mvn deploy -e
cd ../assist-core
call mvn deploy -e
pause