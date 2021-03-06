package org.jitu.wagtail.et;

import java.io.File;

import android.app.Activity;
import android.app.Fragment;
import android.widget.EditText;

public class ControlBoard {
    private final String tag = getClass().getName() + "." + System.currentTimeMillis();

    private EditControl editControl = new EditControl();
    private FileControl fileControl = new FileControl();
    private TabPaneListener tabPaneListener;

    public String getTag() {
        return tag;
    }

    public TabPaneListener newTabPaneListener(Fragment fragment) {
        tabPaneListener = new TabPaneListener(fragment);
        return tabPaneListener;
    }

    public void setupEditHistorian(EditText edit) {
        editControl.setupEditHistorian(edit);
    }

    public String getCurrentFileName() {
        return fileControl.getCurrentFileName();
    }

    public void setCurrentFile(File file) {
        fileControl.setCurrentFile(file);
    }

    public void setCurrentDirectory(File dir) {
        fileControl.setCurrentDirectory(dir);
    }

    public String getFileErrorMessage() {
        return fileControl.getErrorMessage();
    }

    public String fileRead() {
        return fileControl.read();
    }

    public boolean fileSave(TabPane tabPane) {
        EditText edit = tabPane.getEdit();
        return fileControl.save(edit.getText().toString());
    }

    public boolean fileSaveAs(TabPane tabPane, String filename) {
        EditText edit = tabPane.getEdit();
        return fileControl.saveAs(filename, edit.getText().toString());
    }

    public void editPaste(TabPane tabPane) {
        Activity act = tabPane.getActivity();
        EditText edit = tabPane.getEdit();
        editControl.paste(act, edit);
    }

    public void editUndo(TabPane tabPane) {
        EditText edit = tabPane.getEdit();
        editControl.undo(edit);
    }

    public void editRedo(TabPane tabPane) {
        EditText edit = tabPane.getEdit();
        editControl.redo(edit);
    }

    public void editFind(TabPane tabPane, boolean doesFindDown, boolean doesIgnoreCase) {
        String needle = tabPane.getNeedleText();
        EditText edit = tabPane.getEdit();
        editControl.find(edit, needle, doesFindDown, doesIgnoreCase);
    }

    public void editReplaceFind(TabPane tabPane, boolean doesFindDown, boolean doesIgnoreCase) {
        String needle = tabPane.getNeedleText();
        String replacement = tabPane.getReplacementText();
        EditText edit = tabPane.getEdit();
        editControl.replaceFind(edit, needle, replacement, doesFindDown, doesIgnoreCase);
    }

    public void editReplaceAll(TabPane tabPane, boolean doesFindDown, boolean doesIgnoreCase) {
        String needle = tabPane.getNeedleText();
        String replacement = tabPane.getReplacementText();
        EditText edit = tabPane.getEdit();
        editControl.replaceAll(edit, needle, replacement, doesFindDown, doesIgnoreCase);
    }

    public void setupIndentMan(EditText edit) {
        IndentMan man = new IndentMan();
        edit.setKeyListener(man);
        edit.addTextChangedListener(man);
    }
}
