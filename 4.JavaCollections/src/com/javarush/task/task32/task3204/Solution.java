package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/* 
Генератор паролей
Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов.
2) только цифры и латинские буквы разного регистра.
3) обязательно должны присутствовать цифры, и буквы разного регистра.
Все сгенерированные пароли должны быть уникальные.

Пример правильного пароля:
wMh7smNu


Требования:
1. Класс Solution должен содержать метод getPassword(), который возвращает ByteArrayOutputStream со сгенерированным паролем.
2. Длина пароля должна составлять 8 символов.
3. Пароль должен содержать хотя бы одну цифру.
4. Пароль должен содержать хотя бы одну латинскую букву нижнего регистра.
5. Пароль должен содержать хотя бы одну латинскую букву верхнего регистра.
6. Пароль не должен содержать других символов, кроме цифр и латинских букв разного регистра.
7. Сгенерированные пароли должны быть уникальными.
*/
public class Solution {
    public static void main(String[] args) {
//        char c = 97;
//        System.out.println(c);
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();


//        char first = (char) (48 + (int) Math.random()*57);
//        char sec = (char) (65 + (int) Math.random()*90);
//        stringBuilder.append(first);
//        stringBuilder.append(sec);
        for (int i = 0; i < 8; i++) {
            int num = 1 + random.nextInt(4 - 1);

            char first = (char) (48 + random.nextInt(58-48));
            char sec = (char) (65 + random.nextInt(91-65));
            char thr = (char) (97 + random.nextInt(123-97));

            switch (num){
                case 1:
                    stringBuilder.append(first);
                    break;
                case 2:
                    stringBuilder.append(sec);
                    break;
                case 3:
                    stringBuilder.append(thr);
                    break;
            }
        }
        ByteArrayOutputStream os =  new ByteArrayOutputStream();
        try {
            os.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return os;
    }
}