pom_file="pom.xml"   # The file name 
line_to_change=84    # The string var

# Check for file exisist
# Проверка существует ли файл 
if [ ! -f "$file" ]; then
  echo "File not found or it's don't simple file : $pom_file"
  exit 1
fi

# Check for right
# Проверка на права для файла 
if [! -r "$pom_file"] || [! -w "$pom_file"]; then 
        echo "File cannot be written or read (permission denied) try running with sudo"
        exit 1 
fi    


# Requesting your path
# Запрос пути каталога в котором находится tomacat 

echo "### ATTENTION !! Specify the path to the tomcat directory before the webapps directories ( '/webapps/' ) ###"
read -p "Write directory Tomcat in which you want install application : " tomcat_directory



# Request confirmation
# Запрашиваем потверждение 
read -p "Are you sure you want to replace the $line_to_change string with \"$tomcat_directory\"? (y/n):" confirm
if [ "$confirm" != "y" ]; then
  echo "Operation cancaled."
  exit 0
fi

formating_path="          <outputDirectory>$tomcat_directory</outputDirectory>"

sed -i "${line_to_change}s#.*#$formating_path#" "$pom_file"

echo "String $line_number in file change successful to :  \"$tomacat_directory\"."
