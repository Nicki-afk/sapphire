# Sapphire Messanger (Server)

<br>
<br>

## What is Sapphire Messanger ?  
Sapphire is a new messenger with a unique message forwarding mechanism. The fact is that this messenger does not forward the message as such, it only manipulates links to these messages. The messages themselves are stored in IPFS and the server manipulates only these links.

<br>
<br>

> ## Version note
> The version that is currently in the repository may not describe all the functionality of the project as it is still under development. A version matching the description will be released soon.

>
 


 

## Launch parameters

To start you will need : 

```
Apache Maven 3.6.3
java 17.0.6 2023-01-17 LTS
Java(TM) SE Runtime Environment (build 17.0.6+9-LTS-190)
```




## Application launch

And so this project is written using the modern Spring framework. As we all know Spring needs a property file, so first we have to go to the resources folder and create the needed property file there.

```
cd /resources
touch application.properties
```

After we have created the resource file, we need to configure it. And so here's what you need to specify

```
# You must specify data to connect to your database. By default you
  can use h2 database. If you want to add a connection to your 
  database, you will need to add your database dependency to the 
  pom.xml file and then rebuild the project


spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG



# This parameter sets the registration of database queries, you can 
turn it off or comment it out.

spring.h2.console.enabled=true



# You must also specify the application port, if you want to use the standard port 8080 you can comment out this line. If port 8080 is busy, you can specify the port on which you want to run the application as a value
server.port=8081

# After that, you must specify the JWT and RT parameters of the authorization tokens

jwt.token.validity.time=yourtime
rt.token.validity.time=yourtime
sign.token.sign=your-secret


```


</br>
</br>

After that, if you have the necessary versions of Java and Maven installed, you should start the server

## Test Client
You can also write simple JavaScript code on the client with which you can test the server. The example was taken from the official Spring website

```
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/app/tohimself/{your username}', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};


function sendName() {
    stompClient.publish({
        destination: "/app/tohimself",
        body: JSON.stringify({'name': $("#name").val()})
    });
}


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

```

**Note!!!** I don't understand JavaScript well and the code above may not work despite the fact that it was taken from the official Spring documentation.







---

