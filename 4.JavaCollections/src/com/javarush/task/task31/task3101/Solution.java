package com.javarush.task.task31.task3101;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый — path — путь к директории, второй — resultFileAbsolutePath — имя существующего файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его (используй метод FileUtils.deleteFile).
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2.2. Переименовать resultFileAbsolutePath в ‘allFilesContent.txt‘ (используй метод FileUtils.renameFile).
2.2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String path = args[0]; // путь к дерриктории
        File resultFileAbsolutePath = new File(args[1]); // имя существующего файла, который будет содержать результат.
//        File allFilesContent = new File("allFilesContent.txt"); // имя существующего файла, который будет содержать результат.

//        String resultFileAbsolutePathS = "D:\\1\\1.txt";         //имя существующего файла, который будет содержать результат.
//        File resultFileAbsolutePath = new File(resultFileAbsolutePathS);
        String allFilesContentS = resultFileAbsolutePath.getParent() + "\\allFilesContent.txt";         //имя существующего файла, который будет содержать результат.
        File allFilesContent = new File(allFilesContentS);
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
//
//        String path = "D:\\1\\1"; // путь к дерриктории  10 files   4 folders
        File directory = new File(path);
        ArrayList<File> files = new ArrayList<>(); // список имен всех файлов в т.ч. во вложеных папках


        try(FileOutputStream fos = new FileOutputStream(allFilesContent)){
            ifDir(directory, files);

            Collections.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file :
                    files) {
                FileInputStream fis = new FileInputStream(file);
                while (fis.available() > 0){
                    fos.write(fis.read());
                }
                fos.write(System.lineSeparator().getBytes());

                fos.flush();
                fis.close();
            }
        }
    }


    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    // если это папка то добавит в список имена файлов всех вложеных папок
    public static void ifDir(File directory, ArrayList<File> files){
        if (directory.isDirectory() && directory != null){
            for (File file :
                    directory.listFiles()) {
                if (file.isDirectory()) ifDir(file, files);
                else {
                    if (file.length() > 50)
                        FileUtils.deleteFile(file);
                    else
                        files.add(file);
                }
            }
        }
    }
}
