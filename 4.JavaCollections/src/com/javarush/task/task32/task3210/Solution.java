package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
Используем RandomAccessFile
В метод main приходят три параметра:
1) fileName — путь к файлу;
2) number — число, позиция в файле;
3) text — текст.

Считать текст с файла начиная с позиции number, длинной такой же как и длинна переданного текста в третьем параметре.
Если считанный текст такой же как и text, то записать в конец файла строку ‘true‘, иначе записать ‘false‘.
Используй RandomAccessFile и его методы seek(long pos), read(byte b[], int off, int len), write(byte b[]).
Используй convertByteToString(byte readBytes[]) для конвертации считанной строчки в текст.
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
//        String fileName = "D:\\1\\1\\1.txt";
        int number = Integer.parseInt(args[1]);
//        int number = 1;
        String text = args[2];
//        String text = "Past ";
        int len = text.length();
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        randomAccessFile.seek(number);
        byte[] buf= new byte[len];

        randomAccessFile.read(buf, 0, len);
        String str =  convertByteToString(buf);
        String toWrite = text.equals(str) ? "true" : "false";
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.write(toWrite.getBytes());
        randomAccessFile.close();

    }

    public static String convertByteToString(byte readBytes[]){
        return new String(readBytes);
    }
}
