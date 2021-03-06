package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 22.03.2017.
 */
public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("A", 1, 1));
        userList.add(new User("B", 2, 1));
        modelData.setUsers(userList);
    }
}
