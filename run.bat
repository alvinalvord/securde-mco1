@echo off

if exist "bin" rd /s/q "bin"
if not exist "bin" md "bin"
if exist "logs" rd /s/q "logs"
if not exist "logs" md "logs"

javac -d bin src/Model/*.java src/View/*.java src/Controller/*.java

cd bin
java -classpath ".;./../sqlite-jdbc-3.23.1.jar" Controller.Main
cd ..