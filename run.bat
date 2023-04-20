@echo off
:start
set /P jpath=please enter the path from \com\Ace009\ in "" : 
:rerun
set /P jname=name of entry class point: 
set /P jargs=arguments for main : 
javac "com\Ace009\%jpath%\*.java"
java "com.Ace009.%jpath:\=.%.%jname%" %jargs%
:end
choice /c CRS /m "C for closing the window; R running again from same path; S for running a different file"
if errorlevel 3 (goto start)
if errorlevel 2 (goto rerun)
:close
echo closing...
pause
exit
