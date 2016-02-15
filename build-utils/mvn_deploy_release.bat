@echo off
cd ../assist-utils
call mvn deploy
cd ../assist-core
call mvn deploy
pause