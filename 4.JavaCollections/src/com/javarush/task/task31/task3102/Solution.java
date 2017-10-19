package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.util.*;
import java.io.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();
        File rootDir = new File(root);
        Queue<File> fileTree = new PriorityQueue<>();
//        ArrayDeque fileTree = new ArrayDeque();

        Collections.addAll(fileTree, rootDir.listFiles());

        while (!fileTree.isEmpty()){
            File currentFile = (File) fileTree.remove();
            if (currentFile.isDirectory())
                Collections.addAll(fileTree, currentFile.listFiles());
            else list.add(currentFile.getAbsolutePath());
        }

        return list;

    }

    public static void main(String[] args) {
        
    }
}
