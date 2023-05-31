package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.widget.ImageViewCompat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btchar1, btchar2, btchar3, btchar4;
    private EditText nick;
    private List<ImageButton> imageButtons;
    private boolean isClicked = false;
    private Button Entrar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Entrar = (Button) findViewById(R.id.btEntrar);
        Entrar.setEnabled(false);

        nick = (EditText) findViewById(R.id.etNick);
        btchar1 = (ImageButton) findViewById(R.id.btchar1);
        btchar2 = (ImageButton) findViewById(R.id.btChar2);
        btchar3 = (ImageButton) findViewById(R.id.btChar3);
        btchar4 = (ImageButton) findViewById(R.id.btChar4);

        imageButtons = new ArrayList<>();
        imageButtons.add(btchar1);
        imageButtons.add(btchar2);
        imageButtons.add(btchar3);
        imageButtons.add(btchar4);

        for (ImageButton imageButton : imageButtons) {
            imageButton.setOnClickListener(this);
        }

        nick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Não é necessário implementar esse método
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Não é necessário implementar esse método
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateEntrarButtonState();
            }
        });
    }

    @Override
    public void onClick(View v) {
        for (ImageButton imageButton : imageButtons) {
            if (imageButton.getId() == v.getId()) {
                isClicked=true;
                imageButton.setSelected(true);
                ImageViewCompat.setImageTintList(imageButton, null); // Remover a cor
                updateEntrarButtonState();
            } else {
                imageButton.setSelected(false);
                ImageViewCompat.setImageTintList(imageButton, ContextCompat.getColorStateList(this, R.color.grey)); // Aplicar cor azul
            }
        }
    }

    private void updateEntrarButtonState() {
        boolean isNickEmpty = nick.getText().toString().isEmpty();
        boolean isAnyImageButtonSelected = btchar1.isSelected() || btchar2.isSelected() || btchar3.isSelected() || btchar4.isSelected();
        Entrar.setEnabled(!isNickEmpty && isAnyImageButtonSelected);
        if (Entrar.isEnabled()) {
            Entrar.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));
            Entrar.setTextColor(Color.WHITE);
        } else {
            Entrar.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey));
            Entrar.setTextColor(Color.BLACK);
        }
    }

    public void entrar(View v){
        System.out.println("faz alguma coisa");
    }

}