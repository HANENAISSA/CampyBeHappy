package com.example.campybehappy.Controller.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.campybehappy.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button callSignUp, btnValidLogin;
    ImageView logo_img;
    TextView logo_title, logo_subtitle;
    TextInputEditText usernameInput2, passwdInput2;
    private static final Pattern PASSWORED_Pattern = Pattern.compile("^" + ".{4,}" +"$");
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
               goToRegister();
            }
        });
        btnValidLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail() && validatePassword()) {
                    goToHome();
                }
            }
        });
    }

    private void goToHome() {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    private void goToRegister() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    private boolean validateEmail() {
        String emailInput = usernameInput2.getText().toString().trim();
        if (emailInput.isEmpty()) {
            usernameInput2.setError("Le champ ne peut pas être vide");
            usernameInput2.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            usernameInput2.setError("S'il vous plaît, mettez une adresse email valide");
            usernameInput2.requestFocus();
            return false;
        } else {
            usernameInput2.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = passwdInput2.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwdInput2.setError("Le champ ne peut pas être vide");
            return false;
        } else if (!PASSWORED_Pattern.matcher(passwordInput).matches()) {
            passwdInput2.setError("S'il vous plaît, mettez une Mot de pass valide :/ ");
            return false;
        } else {
            passwdInput2.setError(null);
            return true;
        }
    }
}