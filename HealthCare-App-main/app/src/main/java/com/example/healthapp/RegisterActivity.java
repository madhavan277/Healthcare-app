package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edEmail, edUsername, edPassword, edConfirmPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail = findViewById(R.id.editTextAppFullName);
        edUsername = findViewById(R.id.editTextAppAddress);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edConfirmPassword = findViewById(R.id.editTextAppFees);
        btn = findViewById(R.id.buttonBookApp);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,loginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                String email = edEmail.getText().toString();
                Database db = new Database(getApplicationContext(), "healthapp", null, 1);
                if(username.length()==0 || password.length()==0 || confirmPassword.length()==0 || email.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill all details...", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmPassword) == 0) {
                        if(isValid(password)){
                            db.register(email, username, password);
                            Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,loginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain atleast 8 characters, a letter, a digit and a special symbol", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String password) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (password.length() < 8) {
            return false;
        } else {
            for(int p=0; p<password.length(); p++){
                char ch = password.charAt(p);
                if(Character.isLetter(password.charAt(p))){
                    f1 = 1;
                }
                if(Character.isDigit(password.charAt(p))){
                    f2 = 1;
                }
                char c = password.charAt(p);
                if(c >= 33 && c <= 46 || c == 64){
                    f3 = 1;
                }
            }
            if(f1 == 1 && f2 == 1 && f3 == 1){
                return true;
            } else {
                return false;
            }
        }
    }
}