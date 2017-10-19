package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Max on 27.08.2017.
 */
public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    //отвечает за инициализацию контроллера.
    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }

    // будет сбрасывать текущий документ
    public void resetDocument(){
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
            this.document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
            document.addUndoableEditListener(view.getUndoListener());
            view.update();

    }

    // Он будет записывать переданный текст с html тегами в документ document
    public void setPlainText(String text){
        this.resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    // Он должен получать текст из документа со всеми html тегами
    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return String.valueOf(stringWriter);
    }

    public void createNewDocument(){
        view.selectHtmlTab();
        this.resetDocument();
        view.setTitle(" Мой HTML редактор ))");
        view.resetUndo();
//        currentFile.
        currentFile = null;
//        try {
//            currentFile.createNewFile();
//        } catch (Exception e) {
//            ExceptionHandler.log(e);
//        }

    }
    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        jFileChooser.setDialogTitle("Open File");
        int ret = jFileChooser.showOpenDialog(view);
        if (ret == jFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try(FileReader fileReader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(fileReader, document, 0);
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();

        }
    }
    public void saveDocument(){
        if (currentFile != null){
            view.selectHtmlTab();
//            JFileChooser jFileChooser = new JFileChooser();
//            jFileChooser.setFileFilter(new HTMLFileFilter());
            try(FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }
        else saveDocumentAs();
    }

    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);
        jFileChooser.setDialogTitle("Save File");
        int ret = jFileChooser.showSaveDialog(view);

        if (ret == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try(FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }catch (Exception e){
                ExceptionHandler.log(e);
            }
        }
    }


    public static void main(String[] args){
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }


}
