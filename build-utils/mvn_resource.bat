@echo off
cd ..
call mvn dependency:sources
call mvn dependency:resolve -Dclassifier=javadoc
pause