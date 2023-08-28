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

writeAProperties(){

  printLog "Start writing ... "
  
  echo "

  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driver-class-name=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  spring.jpa.show-sql=true
  logging.level.org.hibernate.SQL=DEBUG

  spring.h2.console.enabled=true
  server.port=8081

  # tokens settings
  jwt.token.validity.time=14400000
  jwt.token.sign=jszzGehyVyWZJmalACLgJEJqkykJYsVnipYXdJHtUCckimchXSjszzGehyVyWZJmalACLgJEJqkykJYsVnipYXdJHtUCckimchXS
  rt.token.sign=fmvBydeuEYhSWiUoimEEwxKyrNGjgRzgyqjSgZRUVOaNcjmfJwfmvBydeuEYhSWiUoimEEwxKyrNGjgRzgyqjSgZRUVOaNcjmfJw
    
  
  
  " > "application.properties"

  printLog "File application.properties write successful !!! "
}


configurePropertyFile(){

  printLog "Start config property file for test ..."
  cd src/main/resources

  property_file="application.properties"

  if [ ! -f "$property_file" ]; then
    printLog "The property file don't exisist"
    printLog "Creating property file ... "
    touch "application.properties"
    printLog "File creating successful !!! Writing a test properties..."
    writeAProperties

  else
    printLog "File property exist"
  fi  

  


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
  configurePropertyFile
  startMvnw
else
  major_version=$(echo "$java_version" | cut -d'.' -f1)
  if [ "$major_version" -eq "17" ]; then
   printLog "Your java version is actual"
   configurePropertyFile
  startMvnw
  else
    printLog "Java is installed, but it is not version 17. It is version $java_version. Installing the java 17..."
    sudo apt update
    sudo apt install -y openjdk-17-jdk
    printLog "Java install successful !!"
    configurePropertyFile
    startMvnw

  fi
fi






