#!bin/bash

echo "
--------------------------------------------------------------

░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░██████╗░█████╗░██████╗░██████╗░██╗░░██╗██╗██████╗░███████╗░░
░░██╔════╝██╔══██╗██╔══██╗██╔══██╗██║░░██║██║██╔══██╗██╔════╝░░
░░╚█████╗░███████║██████╔╝██████╔╝███████║██║██████╔╝█████╗░░░░
░░░╚═══██╗██╔══██║██╔═══╝░██╔═══╝░██╔══██║██║██╔══██╗██╔══╝░░░░
░░██████╔╝██║░░██║██║░░░░░██║░░░░░██║░░██║██║██║░░██║███████╗░░
░░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝░░░░░╚═╝░░╚═╝╚═╝╚═╝░░╚═╝╚══════╝░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 
---------------------------------------------------------------

@scriptversion : 0.0.2
@author        : Nicki-afk ( https://github.com/Nicki-afk )
@repository    : https://github.com/Nicki-afk/j-messanger-server/tree/main
@releasedate   : 28:08:2023

------------------ℙ𝕠𝕨𝕖𝕣 𝕓𝕪 𝔾𝕪𝕓𝕖𝕣.𝕠𝕣𝕘--------------------------
"


printLog(){
    echo "[ SCRIPT ] : $1"

}

startMvnw(){
    printLog "Starting mvnw script ..."
    echo " "
    bash ./mvnw install spring-boot:run
}







printLog "Check Java and Java version ..."



# Получение информации о версии Java
java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1-2)

printLog "Your Java Version is $java_version"

# Проверка наличия Java и её версии
if [ -z "$java_version" ]; then
  printLog "Java is not installed. Installing Java 17 ..."
  sudo apt update
  sudo apt install -y openjdk-17-jdk
  printLog "Java install successful !!"
  startMvnw
else
  major_version=$(echo "$java_version" | cut -d'.' -f1)
  if [ "$major_version" -eq "17" ]; then
   printLog "Your java version is actual"
   startMvnw
  else
    printLog "Java is installed, but it is not version 17. It is version $java_version. Installing the java 17..."
    sudo apt update
    sudo apt install -y openjdk-17-jdk
    printLog "Java install successful !!"
    startMvnw

  fi
fi






