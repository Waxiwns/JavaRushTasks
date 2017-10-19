package com.javarush.task.task30.task3008.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Max on 03.08.2017.
 */
public class ClientGuiModel {
    // В нем будет храниться список всех участников чата
    private final Set<String>  allUserNames = new HashSet<String>();
    // в нем будет храниться новое сообщение, которое получил клиент
    private String newMessage;

    //геттер для allUserNames, запретив модифицировать возвращенное множество.
    // Разберись, как это можно сделать с помощью метода класса Collections
    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames);
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    //должен добавлять имя участника во множество, хранящее всех участников
    public void addUser(String newUserName){
        allUserNames.add(newUserName);
    }

    //будет удалять имя участника из множества
    public void deleteUser(String userName){
        allUserNames.remove(userName);
    }
}
