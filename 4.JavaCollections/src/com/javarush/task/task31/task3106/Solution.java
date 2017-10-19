package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент — имя результирующего файла resultFileName, остальные аргументы — имена файлов fileNamePart.
Каждый файл (fileNamePart) — это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002


Требования:
1. В методе main нужно создать ZipInputStream для архива, собранного из кусочков файлов. Файлы приходят аргументами в main, начиная со второго.
2. Создай поток для записи в файл, который приходит первым аргументом в main. Запиши туда содержимое файла из архива.
3. Поток для чтения из архива должен быть закрыт.
4. Поток для записи в файл должен быть закрыт.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String s = "D:\\\\1\\1\\";
        String[] args1 = {s+"veselaya-muzyka-krutaya-pesnya.mp3", s+"veseaya-muzyka-krutaya-pesnya.z00", s+"veseaya-muzyka-krutaya-pesnya.z01", s+"veseaya-muzyka-krutaya-pesnya.z02", s+"veseaya-muzyka-krutaya-pesnya.z03", s+"veseaya-muzyka-krutaya-pesnya.z00"};
        args = args1;
        File resultFile = new File(args[0]);    // результатирующий разархивированный файл
        List files = new ArrayList();   // список файлов частей архива
//        if (args.length > 2) {         // если колличество аргументов (файлов) больше 2 то
            for (int i = 1; i < args.length; i++) {
                files.add(new FileInputStream(args[i]));           //добавляем в список файлов (архивов)
//            }
        }
//        Collections.sort(files);        // сортируем список по порядку что б правильно собрать файл

        byte[] buffer = new byte[1024]; // массив байт для чтения кусками (по 1024)

        /*ByteArrayOutputStream byteArr = new ByteArrayOutputStream();    // сюда будут записаны все биты

        for (File file :
                files) {
            FileInputStream fis = new FileInputStream(file);
            while (fis.read(arr) > -1){
                byteArr.write(arr);
                byteArr.flush();
            }
        }*/

        ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(files)));
        FileOutputStream fileOutputStream = new FileOutputStream(resultFile);
//        ZipEntry zipEntry = zis.getNextEntry();
//        while (zipEntry != null){
            int count;
            while ((count = zis.read(buffer)) > -1){
                fileOutputStream.write(buffer, 0, count);
                fileOutputStream.flush();
            }
//            zipEntry = zis.getNextEntry();
//        }
        fileOutputStream.close();
    }

}
