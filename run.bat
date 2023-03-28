@echo off
:start
set /P jpath=please enter the filepath in "" : 
set /P jargs=arguments for main : 
java %jpath% %jargs%
:end
choice /c CRS /m "C for closing the window; R running again after pause; S for running a different file"
if errorlevel 3 (goto start)
if errorlevel 2 (goto rerun)
:close
echo closing...
pause
exit
:rerun
echo waitung for restart...
pause
java %jpath%
goto end
