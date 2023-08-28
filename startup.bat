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
    goto ConfigurePropertyFile
) else if "%JAVAVER%"=="17" (
    echo Your Java version is up to date.
    goto ConfigurePropertyFile
) else (
    echo Your Java version is not up to date. Updating...
    powershell.exe -Command "Invoke-WebRequest -Uri 'https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe' -OutFile 'jdk-17_installer.exe'"
    start /wait jdk-17_installer.exe /s
    del jdk-17_installer.exe
    goto ConfigurePropertyFile
)

:ConfigurePropertyFile
cd src\main\resources
if not exist "application.properties" (
    echo Creating property file...
    call :WriteProperties
) else (
    echo File property exists.
)

goto RunMvnw

:RunMvnw
cd %~dp0
echo Starting mvnw script ...
./mvnw install spring-boot:run
goto :eof

:WriteProperties
(
    echo spring.datasource.url=jdbc:h2:mem:testdb
    echo spring.datasource.driver-class-name=org.h2.Driver
    echo spring.datasource.username=sa
    echo spring.datasource.password=
    echo spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    echo spring.jpa.show-sql=true
    echo logging.level.org.hibernate.SQL=DEBUG
    echo spring.h2.console.enabled=true
    echo server.port=8081
    echo jwt.token.validity.time=14400000
    echo jwt.token.sign=jszzGehyVyWZJmalACLgJEJqkykJYsVnipYXdJHtUCckimchXSjszzGehyVyWZJmalACLgJEJqkykJYsVnipYXdJHtUCckimchXS
      
    
) > "application.properties"
echo File "application.properties" created successfully.
goto :eof
