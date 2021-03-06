package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

/* 
Загрузчик файлов

Реализуй метод downloadFile(String urlString, Path downloadDirectory),
на вход которого подается ссылка для скачивания файла и папка, куда нужно закачать файл.
Все ссылки имеют вид:
https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png
http://toogle.com/files/rules.txt
https://pacemook.com/photos/image1.jpg

Метод должен создать объект URL и загрузить содержимое файла на локальный диск.
Выкачивай сначала во временную директорию, чтобы в случае неуспешной загрузки в твоей директории не оставались недокачанные файлы.
Затем перемести файл в пользовательскую директорию. Имя для файла возьми из ссылки.
Используй только классы и методы из пакета java.nio.
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));
//        Path passwords = downloadFile("http://4pda.ru/robots.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        String fName = urlString.substring(urlString.lastIndexOf("/"));
        Path result = Paths.get(downloadDirectory + fName);

        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

//        Path tmpDir = Files.createTempDirectory("temp");
        Path tmpFile = Files.createTempFile("", "");
//        Path tmpFile1 = Files.createTempFile(fName, tmpd);
        Files.copy(inputStream, tmpFile);
        inputStream.close();
        Files.move(tmpFile, result);

//        if (downloadDirectory == null) {
//            Files.createDirectory(downloadDirectory);
//        }
//        else {
//            Files.move(downloadDirectory, tmpFile);
//        }

        return result;
    }
}
