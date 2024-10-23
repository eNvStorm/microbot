@echo off

REM Set the folder name to symlink
set "folderName=storm"

REM Set source as the current directory where the batch file is located
set "source=%~dp0"

REM Set destination as a relative path to go back 10 directories to reach the plugins folder
set "destination=..\..\..\..\..\..\..\..\..\..\plugins"

echo Source: %source%
echo Destination: %destination%

REM Check if the source folder exists
if not exist "%source%" (
    echo The source folder does not exist: %source%
    pause
    exit /b
)

REM Check if the destination folder exists
if not exist "%destination%" (
    echo The destination folder does not exist: %destination%
    pause
    exit /b
)

REM Remove existing symbolic link if it exists
if exist "%destination%\%folderName%" (
    echo Removing existing link: %destination%\%folderName%
    rmdir /S /Q "%destination%\%folderName%"
)

REM Create symbolic link for the folder
echo Creating link for directory: %folderName%
mklink /J "%destination%\%folderName%" "%source%"

echo Symbolic link created successfully!
pause
