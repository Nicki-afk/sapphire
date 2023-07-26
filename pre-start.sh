pom_file="pom.xml"   # The file name 

# Check for file exisist
# Проверка существует ли файл 
if [ ! -f "$file" ]; then
  echo "File not found or it's don't simple file : $pom_file"
  exit 1
fi
