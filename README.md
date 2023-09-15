## Saphhire Messanger ( Server )

Sapphire Messenger is a partially decentralized messaging service. The fact is that Sapphire does not store your correspondence or all your data on servers. Sapphire emphasizes decentralization, so the server stores hashed links of your data and messages that are in IPFS. The server only manipulates a small part of your data for fast database mapping


## 🟢 Release notes


The new version **alpha-0.0.4** adds the following features and capabilities:

 -  Implemented authentication mechanisms using JWT and Refresh tokens
 - The logic for checking, creating, and generating beta test keys has been implemented.
 - A mechanism has been implemented to catch errors and send them to the client. Errors became clear due to the fact that JSON errors were structured
 - Added mechanism for local management of user tokens
 - Created a script that simulates a multi-threaded environment
 - Updated application launch script
 - Old designs have been optimized. Bugs fixed

### ⚠️  NOTE

At the moment the application is under development, some functions or features may not work properly


## Application start

To run you will need java installed on your computer, but if you do not have it, do not worry when you run the `startup.sh` script or `startup.bat` for Windows Java will be automatically installed. And so, in order to quickly deploy and run the project, you need to run only one script
```
# Linux
sudo bash ./startup.sh

# Or Windows
./startup.bat
```
<br>

### Alternative option

If for some reason you are unable to run the application through the statrtup.sh file, you can use the following command. **However, before this you must have [Java 17](https://www.techspot.com/downloads/7440-java-se-17.html) installed**

```cmd
./mvnw.cmd install spring-boot:run
```
Or if you are on Linux

```bash
bash ./mvnw install spring-boot:run
```


After that the server will be successfully started. 

When you launch the application, test scripts will run. I recommend disabling them and running the application without tests, for this you can use the following command

```bash
bash ./mvnw install spring-boot:run -DskipTests
```

You can also wrap the application into an executable file and then deploy it to your infrastructure. To do this, run the command

```bash
bash ./mvnw clean package spring-boot:repackage -DskipTests
```

<br>




