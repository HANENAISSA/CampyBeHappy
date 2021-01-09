package com.example.campybehappy.Controller.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.campybehappy.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    Button BtnGoLogin,BtnValidRegister;
    TextInputEditText fullnameInput, usernameInput, emailInput, passwdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fullnameInput = findViewById(R.id.name_input);
        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.email_input);
        passwdInput = findViewById(R.id.password_input);
        BtnValidRegister = findViewById(R.id.btn_ValidRegister);

        BtnGoLogin = findViewById(R.id.btn_goLogIn);

        //Listeners
        BtnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        BtnValidRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addUser();
                Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}