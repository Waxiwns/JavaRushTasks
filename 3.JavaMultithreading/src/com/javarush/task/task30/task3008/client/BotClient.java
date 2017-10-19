package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Max on 03.08.2017.
 */
public class BotClient extends Client {

    public static void main(String[] args){
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);


            String[] str = message.split(": ");
            SimpleDateFormat sdp;
            if (str.length >= 2)
            {
                if (str[1].equals("дата"))
                {
                    sdp = new SimpleDateFormat("d.MM.YYYY");
                } else if (str[1].equals("день"))
                {
                    sdp = new SimpleDateFormat("d");
                } else if (str[1].equals("месяц"))
                {
                    sdp = new SimpleDateFormat("MMMM");
                } else if (str[1].equals("год"))
                {
                    sdp = new SimpleDateFormat("YYYY");
                } else if (str[1].equals("время"))
                {
                    sdp = new SimpleDateFormat("H:mm:ss");
                } else if (str[1].equals("час"))
                {
                    sdp = new SimpleDateFormat("H");
                } else if (str[1].equals("минуты"))
                {
                    sdp = new SimpleDateFormat("m");
                } else if (str[1].equals("секунды"))
                {
                    sdp = new SimpleDateFormat("s");
                } else sdp = null;
            }
            else sdp = null;
            Calendar cal = Calendar.getInstance();
            Date d = cal.getTime();
            if(sdp != null){
                sendTextMessage(String.format("Информация для %s: %s",str[0],sdp.format(d)));
            }




        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    //Он должен всегда возвращать false. Мы не хотим, чтобы бот отправлял текст введенный в консоль.
    @Override
    protected boolean shouldSendTextFromConsole(){
        return false;
    }

    // метод должен генерировать новое имя бота, например: date_bot_XX, где XX – любое число от 0 до 99.
    // Для генерации XX используй метод Math.RANDOM().
    @Override
    protected String getUserName(){
        int max = 99;
        String name = "date_bot_" + ((int) (Math.random()*++max));
        return name;
    }

}
