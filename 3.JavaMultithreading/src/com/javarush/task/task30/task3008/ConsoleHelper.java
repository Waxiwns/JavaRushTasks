package com.javarush.task.task30.task3008;
import java.io.*;
/**
 * Created by Max on 01.08.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public  static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        String string = "";
        while (true){
            try {
                string = reader.readLine();
                return string;
            }
            catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                readString();
            }

        }
    }

    public static int readInt(){
        int i = 0;
        while (true) {
            try {
                i = Integer.parseInt(readString());
                return i;
            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                readInt();
            }
        }
    }
}
