::jshell debugger for Java-Playground
set /P version=enter current version: 
jshell --class-path "..\\build\\libs\\Java-Playground-%version.jar" --startup "startUp.java"
