
# 💻 -> 🌐ChatApplication
This Simple Chat Application was built using Java and works by hosting a Socket on your Computer at a specific port Number. 

**📖Server To Client Functions**

Which in this case is 5000 as shown below

ChatServer.java

```ServerSocket serverSocket = new ServerSocket(5000);```

ChatClientGUI.java

```this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);```

If you need to you can modify the ports as they could be used by your Computer.

**🖌️ Setup**

To Setup This Chat Application you need to 
1. Create the Server class files by going into the server folders command prompt and running:

      ```javac ChatServer.java```

2. Create the Client Class Files being going into the client folders command prompt and running:

      ```javac ChatClient.java ChatClientGUI.java```

3. Run the Server By running the command in the source folders command prompt:

     ```java server.ChatServer```

4. Run the First Client by opening in the command prompt:

     ```java server.ChatClientGUI```

5. You can run as many clients as you want.

**⚙️Functions**

When the Run the Server each Client gets a String Of Numbers as their session Username.


👋Thanks for looking at my new project👋
