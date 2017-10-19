package com.javarush.task.task31.task3105;


import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент — полный путь к файлу fileName.
Второй аргумент — путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию ‘new‘.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри, а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
//        String fileName = "D:\\\\1\\1\\1 amigo.txt";
        String fileName = args[0];
        String zipName = args[1];
//        String zipName = "D:\\\\1\\1\\arch.zip";

        File file = new File(fileName);
//        File zipFile = new File(zipName);

        Map<String, ByteArrayOutputStream> map = new HashMap<>();

        try(ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipName))){
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null){
                ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipIn.read(buffer)) != -1){
                    byteOutputStream.write(buffer, 0, count);
                }
                map.put(entry.getName(), byteOutputStream);
            }
        }

        try(ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipName))){
            for (Map.Entry<String, ByteArrayOutputStream> pair: map.entrySet()){
                if (pair.getKey().substring(pair.getKey().lastIndexOf("/") + 1).equals(file.getName())) continue;
                zipOut.putNextEntry(new ZipEntry(pair.getKey()));
                zipOut.write(pair.getValue().toByteArray());
            }

            ZipEntry entry = new ZipEntry("new/" + file.getName());
            zipOut.putNextEntry(entry);
            Files.copy(file.toPath(), zipOut);
        }
    }
}
