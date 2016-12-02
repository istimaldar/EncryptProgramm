package com.istimaldar.encryptprogram.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.istimaldar.encryptprogram.R;
import com.istimaldar.encryptprogram.controller.Controller;
import com.istimaldar.encryptprogram.controller.Storage;
import com.istimaldar.encryptprogram.model.DSA;
import com.istimaldar.encryptprogram.model.Model;

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
                Storage.ALGORITHMS, 0, R.id.algorithm);
        final TextView sizeText = (TextView) findViewById(R.id.size_text);
        final Spinner sizeSelector = Controller.getInstance().setSpinner(this, Storage.SIZES, 0,
                R.id.size);
        final TextView methodText = (TextView) findViewById(R.id.method_text);
        final Spinner methodSelector = Controller.getInstance().setSpinner(this,
                Storage.METHODS_ENCRYPT, 0, R.id.method);
        final EditText decsriptionText = (EditText) findViewById(R.id.result_text);
        final Button actionButton = (Button) findViewById(R.id.action_button);
        final Button backButton = (Button) findViewById(R.id.back);
        final TextView keyNameText = (TextView) findViewById(R.id.keyNameText);
        final EditText keyName = (EditText) findViewById(R.id.keyName);
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
        }

        final int source = getIntent().getIntExtra(Storage.SOURCE, 0);

        View.OnClickListener act = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action==0) {
                    Model.getInstance().encrypt(new DSA());
                }
                else {
                    Model.getInstance().decrypt(new DSA());
                }
                decsriptionText.setText(Model.getInstance().getProceededDataString());
            }
        };

        actionButton.setOnClickListener(act);
    }
}
