# khs-stockticker
Example using Netty as a web server, and WebSockets to push unsolicited messages to a very simple JavaScript client

Cobbled together from several examples plus my own additions.

Some examples used:  
https://www.websocket.org/echo.html  
http://www.jarloo.com/get-near-real-time-stock-data-from-yahoo-finance/ (with help from Stack Overflow to make it return JSON)  
https://github.com/netty/netty/blob/4.0/example/src/main/java/io/netty/example/http/file/HttpStaticFileServerHandler.java  


Technologies Used:
------------------
Netty  
GSON (for parsing incoming websocket requests into known classes)  
SLF4j  
HttpClient (for calling Yahoo's ticker REST service)  
Eclipse minimal-json (for parsing Yahoo's huge JSON results without creating classes)  

The app is a no-brainer. Use 'gradle run' to get the server going, then hit http://localhost:8080/wsticker to start the websocket connection. I auto-send GOOG and F as two symbols to start with. You can add more or remove symbols using the A the X buttons along with the symbol entry field. The connection must be active when you send or it won't get to the server, so click "Start" before adding or removing symbols.  

What I didn't have time to do: add Shiro authentication. Maybe next time!
