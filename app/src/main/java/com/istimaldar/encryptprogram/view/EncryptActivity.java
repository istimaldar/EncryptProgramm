package com.istimaldar.encryptprogram.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.istimaldar.encryptprogram.R;
import com.istimaldar.encryptprogram.controller.Controller;
import com.istimaldar.encryptprogram.controller.Storage;
import com.istimaldar.encryptprogram.model.DES;
import com.istimaldar.encryptprogram.model.Encryptor;
import com.istimaldar.encryptprogram.model.Model;
import com.istimaldar.encryptprogram.model.RSA;

public class EncryptActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        final TextView typeText = (TextView) findViewById(R.id.type_text);
        final Spinner typeSelector = Controller.getInstance().setSpinner(this, Storage.TYPES, 0,
                R.id.type);
        final TextView algorithmText = (TextView) findViewById(R.id.algorithm_text);
        final Spinner algorithmSelector = Controller.getInstance().setSpinner(this,
                Storage.ASYMETRIC_ALGORITHMS, 0, R.id.algorithm);
        final TextView sizeText = (TextView) findViewById(R.id.size_text);
        final Spinner sizeSelector = Controller.getInstance().setSpinner(this, Storage.DES_SIZES, 0,
                R.id.size);
        final TextView methodText = (TextView) findViewById(R.id.method_text);
        final Spinner methodSelector;
        final EditText decsriptionText = (EditText) findViewById(R.id.result_text);
        final Button actionButton = (Button) findViewById(R.id.action_button);
        final Button backButton = (Button) findViewById(R.id.back);
        final TextView keyNameText = (TextView) findViewById(R.id.keyNameText);
        final EditText keyName = (EditText) findViewById(R.id.keyName);
        final Button loadKey = (Button) findViewById(R.id.loadKey);
        final TextView keyPath = (TextView) findViewById(R.id.keyPath);
        final Spinner output = Controller.getInstance().setSpinner(this, Storage.OUTPUT, 0, R.id.output);
        final EditText outptFileName = (EditText) findViewById(R.id.outputFileName);
        final TextView outptFileNameText = (TextView) findViewById(R.id.outputFileNameText);
        final EncryptActivity activity = this;
        View.OnClickListener back = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.getInstance().showActivity(activity, MainActivity.class);
            }
        };

        backButton.setOnClickListener(back);

        final int action = getIntent().getIntExtra(Storage.ACTION, 0);
        if (action == 1) {
            actionButton.setText("Дешифровать");
            methodSelector = Controller.getInstance().setSpinner(this,
                    Storage.METHODS_DECRYPT, 0, R.id.method);
        }
        else {
            methodSelector = Controller.getInstance().setSpinner(this,
                    Storage.METHODS_ENCRYPT, 0, R.id.method);
        }

        final int source = getIntent().getIntExtra(Storage.SOURCE, 0);

        View.OnClickListener act = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encryptor encryptor;
                if (typeSelector.getSelectedItemPosition() == 0) {
                    encryptor = new DES();
                }
                else {
                    encryptor = new RSA();
                }
                if(action==0) {
                    if (methodSelector.getSelectedItemPosition() == 0) {
                        Model.getInstance().encrypt(encryptor, false, keyPath.getText().toString());
                    }
                    else {
                        Model.getInstance().encrypt(encryptor, true, keyName.getText().toString());
                    }
                }
                else {
                    Model.getInstance().decrypt(encryptor, keyPath.getText().toString());
                }
                if (output.getSelectedItemPosition() == 0) {
                    Model.getInstance().saveToFile(outptFileName.getText().toString());
                }
                else {
                    decsriptionText.setText(Model.getInstance().getProceededDataString());
                }
            }
        };

        actionButton.setOnClickListener(act);

        output.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position==0) {
                    outptFileName.setVisibility(View.VISIBLE);
                    outptFileNameText.setVisibility(View.VISIBLE);
                }
                else {
                    outptFileName.setVisibility(View.GONE);
                    outptFileNameText.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        methodSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position==0) {
                    keyPath.setVisibility(View.VISIBLE);
                    loadKey.setVisibility(View.VISIBLE);
                    keyName.setVisibility(View.GONE);
                    keyNameText.setVisibility(View.GONE);
                }
                else {
                    keyPath.setVisibility(View.GONE);
                    loadKey.setVisibility(View.GONE);
                    keyName.setVisibility(View.VISIBLE);
                    keyNameText.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        typeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position==0) {
                    Controller.getInstance().changeSpinner(algorithmSelector, activity,
                            Storage.ASYMETRIC_ALGORITHMS, 0);
                    Controller.getInstance().changeSpinner(sizeSelector, activity,
                            Storage.DES_SIZES, 0);
                }
                else {
                    Controller.getInstance().changeSpinner(algorithmSelector, activity,
                            Storage.SYMMETRIC_ALGORITHMS, 0);
                    Controller.getInstance().changeSpinner(sizeSelector, activity,
                            Storage.RSA_SIZES, 0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        View.OnClickListener load = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileDialog fileDialog = new OpenFileDialog(activity)
                        .setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
                            @Override
                            public void OnSelectedFile(String fileName) {
                                keyPath.setText(fileName);
                            }
                        });
                fileDialog.show();
            }
        };
        loadKey.setOnClickListener(load);
    }
}
