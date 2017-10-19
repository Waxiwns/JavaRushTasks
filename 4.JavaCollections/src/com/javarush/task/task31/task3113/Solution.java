package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?

Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

1 Первым делом считай путь к папке с консоли.

Если введенный путь не является директорией — выведи «[полный путь] — не папка» и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок — [количество папок в директории]
Всего файлов — [количество файлов в директории и поддиректориях]
Общий размер — [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.
*/
public class Solution {
    static int countDir = -1;
    static int countFile;
    static int countSize;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathFile = reader.readLine();
        reader.close();
//        File file = new File(pathFile);
        Path file = Paths.get(pathFile);

        if (!Files.isDirectory(file)){
            System.out.println(file.toAbsolutePath().toString() + " - не папка");
//            return;
        }
        else {
            Files.walkFileTree(file, new Visitor());
            System.out.println("Всего папок - " + countDir);
            System.out.println("Всего файлов - " + countFile);
            System.out.println("Общий размер - " + countSize);
        }


    }

    private static class Visitor extends SimpleFileVisitor<Path>{

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            countDir++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            countFile++;
            countSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }
    }
}
