@echo off
setlocal

echo --------------------------------------------------------------
echo                         
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░██████╗░█████╗░██████╗░██████╗░██╗░░██╗██╗██████╗░███████╗░░
░░██╔════╝██╔══██╗██╔══██╗██╔══██╗██║░░██║██║██╔══██╗██╔════╝░░
░░╚█████╗░███████║██████╔╝██████╔╝███████║██║██████╔╝█████╗░░░░
░░░╚═══██╗██╔══██║██╔═══╝░██╔═══╝░██╔══██║██║██╔══██╗██╔══╝░░░░
░░██████╔╝██║░░██║██║░░░░░██║░░░░░██║░░██║██║██║░░██║███████╗░░
░░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝░░░░░╚═╝░░╚═╝╚═╝╚═╝░░╚═╝╚══════╝░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░

echo --------------------------------------------------------------
echo @scriptversion : 0.0.2
echo @author        : Nicki-afk ( https://github.com/Nicki-afk )
echo @repository    : https://github.com/Nicki-afk/j-messanger-server/tree/main
echo @releasedate   : 28:08:2023
echo --------------------------------------------------------------
echo.

:CheckJava
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do set JAVAVER=%%g
set JAVAVER=%JAVAVER:~1,2%

if "%JAVAVER%"=="" (
    echo Java is not installed. Installing Java 17 ...
    powershell.exe -Command "Invoke-WebRequest -Uri 'https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe' -OutFile 'jdk-17_installer.exe'"
    start /wait jdk-17_installer.exe /s
    del jdk-17_installer.exe
) else if "%JAVAVER%"=="17" (
    echo Your Java version is up to date.
) else (
    echo Your Java version is not up to date. Updating...
    powershell.exe -Command "Invoke-WebRequest -Uri 'https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe' -OutFile 'jdk-17_installer.exe'"
    start /wait jdk-17_installer.exe /s
    del jdk-17_installer.exe
)



goto RunMvnw

:RunMvnw
cd %~dp0
echo Starting mvnw script ...
./mvnw install spring-boot:run
goto :eof


