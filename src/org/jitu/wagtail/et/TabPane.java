package org.jitu.wagtail.et;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TabPane extends Fragment {
    private boolean findPaneVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_pane, container, false);
        addFrags();
        return view;
    }

    private void addFrags() {
        FragmentManager mgr = getChildFragmentManager();
        FragmentTransaction tx = mgr.beginTransaction();
        if (mgr.findFragmentByTag("edit_pane") == null) {
            Fragment editPane = Fragment.instantiate(getActivity(), EditPane.class.getName());
            tx.add(R.id.tab_layout, editPane, "edit_pane");
        }
        Fragment findPane = mgr.findFragmentByTag("find_pane");
        if (findPane == null) {
            findPane = Fragment.instantiate(getActivity(), FindPane.class.getName());
            tx.add(R.id.tab_layout, findPane, "find_pane");
        }
        if (findPaneVisible) {
            tx.show(findPane);
        } else {
            tx.hide(findPane);
        }
        tx.commit();
    }

    public void showHideFindPane() {
        Fragment bottom = getChildFragmentManager().findFragmentByTag("find_pane");
        if (bottom == null) {
            return;
        }
        findPaneVisible = !findPaneVisible;
        if (findPaneVisible) {
            getChildFragmentManager().beginTransaction().show(bottom).commit();
        } else {
            getChildFragmentManager().beginTransaction().hide(bottom).commit();
        }
    }

    public boolean isFindPaneVisible() {
        return findPaneVisible;
    }

    public EditText getEdit() {
        return (EditText) getView().findViewById(R.id.edit);
    }

    public String getNeedleText() {
        if (!findPaneVisible) {
            return "";
        }
        Fragment bottom = getChildFragmentManager().findFragmentByTag("find_pane");
        if (bottom == null) {
            return "";
        }
        EditText edit = (EditText) bottom.getView().findViewById(R.id.edit_find);
        return edit.getText().toString();
    }

    public String getReplacementText() {
        if (!findPaneVisible) {
            return "";
        }
        Fragment bottom = getChildFragmentManager().findFragmentByTag("find_pane");
        if (bottom == null) {
            return "";
        }
        EditText edit = (EditText) bottom.getView().findViewById(R.id.edit_replace);
        return edit.getText().toString();
    }
}