package com.istimaldar.encryptprogram.controller;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.istimaldar.encryptprogram.view.Retrievable;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public class Controller {

    private static Controller instance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    public void showActivity(Activity current, Class window) {
        Intent intent = new Intent(current, window);
        int action = 0, source = 0;
        if (current instanceof Retrievable) {
            action = ((Retrievable) current).retrieve(Storage.ACTION);
            source = ((Retrievable) current).retrieve(Storage.SOURCE);
        }
        intent.putExtra(Storage.ACTION, action);
        intent.putExtra(Storage.SOURCE, source);
        current.startActivity(intent);
        current.finish();
    }

    public Spinner setSpinner(Activity parrent, String[]list, int var, int ID) {
        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<>(parrent,
                android.R.layout.simple_spinner_item, list);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = (Spinner) parrent.findViewById(ID);
        spinner.setAdapter(sourceAdapter);
        spinner.setPrompt("Title");
        spinner.setSelection(var);
        return spinner;
    }

    public void changeSpinner(Spinner spinner, Activity parrent, String[]list, int var) {
        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<>(parrent,
                android.R.layout.simple_spinner_item, list);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sourceAdapter);
        spinner.setPrompt("Title");
        spinner.setSelection(var);
    }

}
