package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Max on 29.08.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory() || f.getName().endsWith(".html") || f.getName().endsWith(".htm") || f.getName().endsWith(".HTML") || f.getName().endsWith(".HTM"))
        return true;
        else return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
