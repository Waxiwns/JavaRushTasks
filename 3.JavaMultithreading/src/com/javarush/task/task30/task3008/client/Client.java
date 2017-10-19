package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Max on 02.08.2017.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args){
        // Он должен создавать новый объект типа Client и вызывать у него метод run().
        Client client = new Client();
        client.run();
    }

    // Отвечает за поток, устанавливающий сокетное соединение и читающий сообщения сервера
    public class SocketThread extends Thread{

        //должен выводить текст message в консоль
        protected void processIncomingMessage(String message){
            System.out.println(message);
        }

        // должен выводить в консоль информацию о том, что участник с именем userName присоединился к чату
        protected void informAboutAddingNewUser(String userName){
            System.out.println(userName + " присоединился к чату");
        }

        // олжен выводить в консоль, что участник с именем userName покинул чат
        protected void informAboutDeletingNewUser(String userName){
            System.out.println(userName + " покинул чат");
        }

        //этот метод должен:
        // а) Устанавливать значение поля clientConnected внешнего объекта Client в соответствии с переданным параметром.
        // б) Оповещать (пробуждать ожидающий) основной поток класса Client.
        // Подсказка: используй синхронизацию на уровне текущего объекта внешнего класса и метод notify.
        // Для класса SocketThread внешним классом является класс Client.
        protected void notifyConnectionStatusChanged(boolean clientConnected){
           Client.this.clientConnected = clientConnected;


            //Оповещать (пробуждать ожидающий) основной поток класса Client.
            // Подсказка: используй синхронизацию на уровне текущего объекта внешнего класса и метод notify.
            // Для класса SocketThread внешним классом является класс Client
            synchronized (Client.this){
                Client.this.notify();
            }
        }

        //Этот метод будет представлять клиента серверу.
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true){
                Message message = connection.receive();
                try {
                    if (message.getType().equals(MessageType.NAME_REQUEST)) {
                        String name = getUserName();
                        Message message1 = new Message(MessageType.USER_NAME, name);
                        connection.send(message1);
                    } else if (message.getType().equals(MessageType.NAME_ACCEPTED)) {
                        notifyConnectionStatusChanged(true);
                        break;
                    }
                    else throw new IOException("Unexpected MessageType");
                }
                catch (Exception e){
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        // Этот метод будет реализовывать главный цикл обработки сообщений сервера
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            try {
                while (true){


                        Message message = connection.receive();
                        if (message.getType().equals(MessageType.TEXT)) {
                            processIncomingMessage(message.getData());
                        }
                        else if (message.getType().equals(MessageType.USER_ADDED)) {
                            informAboutAddingNewUser(message.getData());
                        }
                        else if (message.getType().equals(MessageType.USER_REMOVED)) {
                            informAboutDeletingNewUser(message.getData());
                        }
    //                else if (!(message.getType().equals(MessageType.TEXT) && (!message.getType().equals(MessageType.USER_ADDED) && (!message.getType().equals(MessageType.USER_REMOVED)))))
                        else
                            throw new IOException("Unexpected MessageType");
                }
            }
                catch (Exception e){
                    throw new IOException("Unexpected MessageType");
                }

        }

        public void run(){
            String adress = getServerAddress();
            int port = getServerPort();
//            Connection connection;
            try {
                Socket socket = new Socket(adress, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();

            } catch (IOException e) {
                e.printStackTrace();
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                notifyConnectionStatusChanged(false);
            }
        }
    }

    // должен запросить ввод адреса сервера у пользователя и вернуть введенное значение.
    // Адрес может быть строкой, содержащей ip, если клиент и сервер запущен на разных машинах
    // или ‘localhost’, если клиент и сервер работают на одной машине.
    protected String getServerAddress(){
        System.out.println("Введите IP адрес удаленного сервера или 'localhost'");
        return ConsoleHelper.readString();
    }

    //должен запрашивать ввод порта сервера и возвращать его.
    protected int getServerPort(){
        System.out.println("Введите порт удаленного сервера");
        return ConsoleHelper.readInt();
    }

    //должен запрашивать и возвращать имя пользователя.
    protected String getUserName(){
        System.out.println("Введите имя пользователя");
        return ConsoleHelper.readString();
    }

    //в данной реализации клиента всегда должен возвращать true (мы всегда отправляем текст введенный в консоль).
    // Этот метод может быть переопределен, если мы будем писать какой-нибудь другой клиент, унаследованный от нашего,
    // который не должен отправлять введенный в консоль текст.
    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    //должен создавать и возвращать новый объект класса SocketThread
    protected SocketThread getSocketThread(){

        return  new SocketThread();
    }

    //создает новое текстовое сообщение, используя переданный текст и отправляет его серверу через соединение connection.
    protected void sendTextMessage(String  text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
            clientConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка отправки/создания сообщения");
            clientConnected = false;
        }
    }


    // Он должен создавать вспомогательный поток SocketThread, ожидать пока тот установит соединение с сервером,
    // а после этого в цикле считывать сообщения с консоли и отправлять их серверу.
    // Условием выхода из цикла будет отключение клиента или ввод пользователем команды ‘exit‘.
    // Для информирования главного потока, что соединение установлено во
    // вспомогательном потоке, используй методы wait и notify объекта класса Client.
    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Ошибка ожидания");
                return;
            }
        }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
        }
        else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        // Считывай сообщения с консоли пока клиент подключен. Если будет введена команда ‘exit‘, то выйди из цикла.
        while (clientConnected){
            String mes = ConsoleHelper.readString();
            if (mes.equals("exit"))
                break;
                // После каждого считывания, если метод shouldSendTextFromConsole() возвращает true,
                // отправь считанный текст с помощью метода sendTextMessage().
            else if (shouldSendTextFromConsole()){
                    sendTextMessage(mes);
            }
        }
    }
}
