package com.example.campybehappy.Controller.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.campybehappy.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button callSignUp, btnValidLogin;
    ImageView logo_img;
    TextView logo_title, logo_subtitle;
    TextInputEditText usernameInput2, passwdInput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        callSignUp = findViewById(R.id.btn_goSignUp);
        logo_img = findViewById(R.id.app_logo);
        logo_title = findViewById(R.id.logo_name);
        logo_subtitle = findViewById(R.id.slogan_name);

        usernameInput2 = findViewById(R.id.username_input2);
        passwdInput2 = findViewById(R.id.password_input2);
        btnValidLogin = findViewById(R.id.btn_ValidLogin);

        /* Onclick listener */
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnValidLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}