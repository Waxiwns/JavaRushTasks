package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Max on 01.08.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public  static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (Exception e){
            serverSocket.close();
            e.printStackTrace();

            }
    }

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name = null;
            while (true) {
                Message message = new Message(MessageType.NAME_REQUEST);
                connection.send(message);
                Message message1 = connection.receive();
                if (message1.getType() == MessageType.USER_NAME) {
                    name = message1.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }

                }
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair: connectionMap.entrySet()) {
                String name = pair.getKey();
                Message message = new Message(MessageType.USER_ADDED, name);
                if (!name.contains(userName))
                    connection.send(message);
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true){
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT){
                    String mes = userName + ": " + message.getData();
                    Message message1 = new Message(MessageType.TEXT, mes);
                    sendBroadcastMessage(message1);
                }
                else
                    System.out.println("Ошибка типа сообщения");
            }

        }

        @Override
        public void run() {
            String adress = "";
            String userName = null;

            ConsoleHelper.writeMessage("Установлено новое сообщение с адресом: " + socket.getRemoteSocketAddress());
            try(Connection connection = new Connection(socket)) {

                adress = String.valueOf(connection.getRemoteSocketAddress());
//                System.out.println("Установлено новое сообщение с адресом: " + connection.getRemoteSocketAddress());

                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);


            } catch (IOException e) {
                e.printStackTrace();
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными" + adress);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными" + adress);
            }
            if (userName!=null){
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");


        }
    }

    public static void sendBroadcastMessage(Message message){
        try {
            for (Map.Entry<String, Connection> pair :
                connectionMap.entrySet()) {

                pair.getValue().send(message);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка отправки сообщения");
        }
    }


}
