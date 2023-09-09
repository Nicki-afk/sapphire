## Saphhire Messanger ( Server )

Sapphire Messenger is a partially decentralized messaging service. The fact is that Sapphire does not store your correspondence or all your data on servers. Sapphire emphasizes decentralization, so the server stores hashed links of your data and messages that are in IPFS. The server only manipulates a small part of your data for fast database mapping


## Release notes
At the moment the project is under active development, and some functions or features may not work. At the time of **08/28/23**, the server does not have practically any full-fledged and working functions, but only has a basic implementation of the components. At the time of writing the README, the current version of **gyberwebsocket-0.0.4-alphaS**. Later versions will start with the prefix **sapphire**. It is expected that by version **alpha-0.0.7** the service will have full-fledged functions for messaging.


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
./mvnw install spring-boot:run
```


After that the server will be successfully started


<br>




