package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Created by Max on 27.08.2017.
 */

/* Этот слушатель будет следить за меню, а если конкретнее, то за моментом, когда меню редактирования будет выбрано пользователем.
В этот момент он будет запрашивать у представления можем ли мы сейчас отменить или вернуть какое-то действие,
и в зависимости от этого делать доступными или не доступными пункты меню «Отменить» и «Вернуть«.
 */
public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        undoMenuItem.setEnabled(view.canUndo());
        redoMenuItem.setEnabled(view.canRedo());
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
