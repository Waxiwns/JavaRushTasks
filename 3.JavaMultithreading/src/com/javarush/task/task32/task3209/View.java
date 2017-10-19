package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Max on 27.08.2017.
 */
public class View extends JFrame implements ActionListener{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();     //это будет панель с двумя вкладками.
    private JTextPane htmlTextPane = new JTextPane();       //это будет компонент для визуального редактирования html.
    private JEditorPane plainTextPane = new JEditorPane();  //это будет компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое).
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           new ExceptionHandler();
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    //отвечает за инициализацию представления.
    public void init(){
        initGui();
        // Добавляем слушателя событий нашего окна
        FrameListener listener = new FrameListener(this);
        this.addWindowListener(listener);
        //Показывает наше окно
        this.setVisible(true);

    }

    // отвечает за инициализацию меню
    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    // отвечает за инициализацию панелей редактора
    public void initEditor(){
        htmlTextPane.setContentType("text/html");

        JScrollPane scrollPaneHtml = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", scrollPaneHtml);

        JScrollPane scrollPaneText = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", scrollPaneText);

        tabbedPane.setPreferredSize(new Dimension(500, 300));

        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    // Он будет инициализировать графический интерфейс
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void undo(){
        try{
            undoManager.undo();
        }
        catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public void redo(){
        try{
            undoManager.redo();
        }
        catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void exit(){
        controller.exit();
    }

    public boolean isHtmlTabSelected(){
        if (tabbedPane.getSelectedIndex() == 0)
            return true;
        else return false;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public  void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(tabbedPane, "Информация о программе", "About me", JOptionPane.INFORMATION_MESSAGE);
    }

    // Этот метод вызывается, когда произошла смена выбранной вкладки
    public void selectedTabChanged(){
        if (tabbedPane.getSelectedIndex() == 0)
            controller.setPlainText(plainTextPane.getText());
        else
            plainTextPane.setText(controller.getPlainText());
        resetUndo();
    }

    public boolean canUndo(){
        return  undoManager.canUndo();
    }
    public boolean canRedo(){
        return  undoManager.canRedo();
    }
    public  void resetUndo(){
        undoManager.discardAllEdits();
    }

    // будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Получи из события команду с помощью метода getActionCommand().
        // Это будет обычная строка. По этой строке ты можешь понять какой пункт меню создал данное событие.
        String command = actionEvent.getActionCommand();
//        if (command.equals("Новый"))
//            controller.createNewDocument();
        switch (command){
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                this.showAbout();
                break;
        }
    }
}
