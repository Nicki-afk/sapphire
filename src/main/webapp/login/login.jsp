<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link rel="stylesheet" type="text/css" href="./css/login.css" /> -->
    <title>Document</title>
</head>
<body>

   <div class="header">

   </div>

   <div class="main_block">

    <div class="form_block"> 

        <p class="login_text">Log in</p>

        <form action="http://localhost:8080/gyberwebsocket-0.0.2-inside-test/login" method="post">
            
            <input type="text" , name="username" placeholder="@nick_name">
            <input type="password" name="password" placeholder="Password">
            <br>
            <input type="submit" value="login" class="submit_btn">
        </form>
    </div>

   </div>
    

   <style>


   /* Комментарий к CSS коду: */


body {
  display: flex;
  justify-content: center; 
  align-items: center; 
  height: 100vh; 
  margin: 0; 
}

/* Общие стили для блока формы */
.form_block {
  
  background-color: rgb(255, 255, 255);
  width: 300px; 
  max-width: 90%;
  margin: 0 auto;
  padding: 20px; 
  color: black;
  text-transform: uppercase;
  border-radius: 10px;
  text-align: center; 

  
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.2);
}

.submit_btn {
  color: white; 
  /* background-color: #007bff;  */
  border: none; 
  border-radius: 5px;

  margin-top: 20px;
  padding: 8px 16px; 
  cursor: pointer; 
  text-transform: uppercase;
  font-weight: bold;
  transition: background-color 0.3s ease; 
  width: 120px;


}

.submit_btn:hover {
  background-color: #0056b3; /* Изменяем цвет фона при наведении */
}

/* Стили для текста "Login" или заголовка формы */
.login_text {
  margin-bottom: 70px; /* Добавим небольшой отступ снизу для разделения с полями */
  font-weight: bold;
  font-family: Arial, Helvetica, sans-serif;
  font-size: large;

  
}

/* Стили для всех полей ввода */
input {

  border: 1px solid #ccc;
  border-radius: 5px;
  height: 30px;
  width: 87%;
  margin-bottom: 10px;
  padding: 5px; 
}

/* Убираем стандартные стили для кнопки */
input[type="submit"] {
  appearance: none;
  -webkit-appearance: none;
}

/* Настроим внешний вид для нажатой кнопки на iOS */
@media screen and (-webkit-min-device-pixel-ratio: 0) {
  input[type="submit"] {
    background-color: #007bff;
    color: white;
    border-radius: 5px;
    padding: 8px 16px;
    cursor: pointer;
    font-weight: bold;
    transition: background-color 0.3s ease;
  }
}

/* Добавим отступы между текстовыми полями и кнопкой */
.form_block input:last-child {
  margin-bottom: 0;
}

/* Добавим отступ снизу для последнего текстового поля */
.form_block input:last-of-type {
  margin-bottom: 20px;
}



   </style>
</body>
</html>