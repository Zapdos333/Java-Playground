@echo off
:start
set /P jpath=please enter the path from \com\Ace009\ in "" : 
echo compiling...
:restart
javac com\Ace009\%jpath%\*.java
echo files in path:
echo:
dir "com\Ace009\%jpath%\"
echo:
set /P jname=name of entry class : 
if %jname% equ \recompile (goto rerun)
set /P jargs=arguments for main : 
echo executing...
echo:
java "com.Ace009.%jpath:\=.%.%jname%" %jargs%
echo:
choice /c CRS /m "C for closing the window; R running again from same path; S for running a different file"
if errorlevel 3 (goto start)
if errorlevel 2 (goto rerun)
echo closing...
pause
exit
:rerun
echo recompiling...
goto restart
