@echo off

if exist "bin" rd /s/q bin

if not exist "bin" md "bin"

javac -d bin src/Model/*.java src/View/*.java src/Controller/*.java

cd bin
java Controller.Main
cd ..