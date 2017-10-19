package com.javarush.task.task32.task3209;

/**
 * Created by Max on 27.08.2017.
 */

//Это будет наш обработчик исключительных ситуаций
public class ExceptionHandler extends Throwable {
    public static void log(Exception e){
        System.out.println(e.toString());
    }
}
