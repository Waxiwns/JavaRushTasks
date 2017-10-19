package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

/**
 * Created by Max on 22.03.2017.
 */
public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        System.out.println("All users:");
        for (User users :
                modelData.getUsers()) {
            System.out.println("\t" + users);
        }
        System.out.println("==================================================");
    }
    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }


    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
