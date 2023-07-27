# J-messanger server

## What is J-Messenger? 
This is a small messenger project based on a simple WebSocket data transfer protocol. The main difference of this messenger is that it allows you to encrypt data with your own public key. The idea is for users to use their own encryption keys, so the server is just transmitting encrypted messages. This repository provides the code of the messenger server itself. You can find the client code in my GitHub profile.

## Application launch

I am using maven to build projects. In this case, I wrote in the markup of the pom.xml file my path to the **Tomcat** directory, so that when building the file, it would immediately go to the webapps folder and automatically deploy there.

So here's what you need to do first. You need to download the Tomcat server and extract it to any folder. Next, you **need to start Tomcat**. This can be done in the following way:

<code>
# Go to bin directory
</br>
cd /your/tomcat/directory/bin/
</br>
</br>
# Start server
</br>
sudo bash ./startup.sh
</code>
</br>
</br>

Great ! Now that the server is up and running, we can move on to deploying our WebSocket application. To do this, download the archive from GitHub, or simply clone

</br>

`git clone https://github.com/Nicki-afk/j-messanger-server.git`

</br>
</br>

Next, when you have downloaded the application, you must make sure that you have maven installed, this can be done with the following command

</br>

`mvn --version`

</br>
</br>

If you see something like this, then you can proceed to launch the application

</br>
<code>

**Apache Maven 3.6.3** <br>
**Maven home: /usr/share/maven** <br>
**Java version: 11.0.19, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64** <br>
**Default locale: ru_RU, platform encoding: UTF-8** <br>
**OS name: "linux", version: "5.19.0-50-generic", arch: "amd64", family: "unix"** <br>
</code>

<br>
<br>

To run the application, you can use my **pre-start.sh** script written in **bash** which will change the path to the Tomcat directory. 

You can use the command

<br>

`sudo bash ./pre-start.sh`

<br>

Next, you will be asked which Tomcat directory you want to specify. Specify the full path to the directory. Also, do not forget to specify the webapps directory at the end where the application will be deployed.

<br>

<code>

**Write directory Tomcat in which you want install application : /home/nicki/Tomcat/apache-tomcat-8.0/webapps/** <br>
**Are you sure you want to replace the 84 string with /home/nicki/Tomcat/apache-tomcat-8.0/webapps/? (y/n): y** <br>
**String 84 in file change successful to : /home/nicki/Tomcat/apache-tomcat-8.0/webapps/.** <br>

</code>

<br>

Great ! Now we can build our application using maven. Let's use the command

<br>

`mvn clean package`

<br>

Great ! That's all . Now the application will be automatically saved to the webapps folder in the Tomcat directory and will be automatically launched. Now you can interact with the application through the **j-messanger client**

---

