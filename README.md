# Atmosphere-RepeatedZerosBug

I've created a new repository for this issue(?) which replicated the bug: https://github.com/CPBach/Atmosphere-RepeatedZerosBug.git.

When connecting to server, the client sometimes sends a lot of '0' to the server.
I'm not sure, maybe I misconfigured something. This error seems to happen sporadically and I suspect
something to be wrong with the atmosphere handshakring between client and server.

## Build instructions.

1) Run "mvn clean package".

2) Copy the configuration file "mainConfig.yml" to the "target"-folder.

3) Run "java -jar target/repeatingZeros-0.0.1-SNAPSHOT.jar"

Dropwizard will listen on "http://localhost:8080". Use the main-html page to connect to the server and observe logs on both client and server side.

## Atmosphere bug - screenshot, server side

![Bug screenshot](https://github.com/CPBach/Atmosphere-RepeatedZerosBug/blob/master/atmosphereBug.png "Optional title")
