package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
//            File your_file_name = File.createTempFile("your_file_name", null);
            String your_file_name = "D:\\1\\5.txt";
            OutputStream outputStream = new FileOutputStream(your_file_name);
            OutputStream outputStream1 = new FileOutputStream("D:\\1\\4.txt");
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            User user1 = new User();
            User user2 = new User();
            User user3 = null;
            user1.setFirstName("Первый");
            user1.setLastName("ФПервый");
            user2.setLastName("ФВторой");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            Date bbDate = sdf.parse("30-11-1983");
//            Date bbDate2 = sdf.parse("30-05-1983");
//            user1.setBirthDate(bbDate);
//            user2.setBirthDate(bbDate2);
            user1.setCountry(User.Country.UKRAINE);
            user2.setCountry(User.Country.OTHER);
            user2.setMale(true);
            user1.setMale(false);
            javaRush.users.add(user1);
            javaRush.users.add(new User());
            javaRush.users.add(user2);
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            loadedObject.save(outputStream1);
            outputStream1.flush();

            // Проверка


            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            outputStream1.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(users.size());
            for (User user : users){
                writer.println(user.getFirstName());
                writer.println(user.getLastName());
                writer.println(user.getBirthDate().getTime());
                writer.println(user.isMale());
                writer.println(user.getCountry().getDisplayedName());
            }
            writer.flush();
            //implement this method - реализуйте этот метод
        }
        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int size = Integer.parseInt(reader.readLine());
            if (size > 0){
                for (int i = 0; i < size; i++){
                    User user = new User();
                    user.setFirstName(reader.readLine());
                    user.setLastName(reader.readLine());
                    user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                    user.setMale(Boolean.parseBoolean(reader.readLine()));
                    String s = reader.readLine();
                    if (s.equals("Ukraine")){
                        user.setCountry(User.Country.UKRAINE);
                    }
                    else if(s.equals("Russia")){
                        user.setCountry(User.Country.RUSSIA);
                    }
                    else if (s.equals("Other")){
                        user.setCountry(User.Country.OTHER);
                    }
                    users.add(user);
                }
            }
            //implement this method - реализуйте этот метод
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
