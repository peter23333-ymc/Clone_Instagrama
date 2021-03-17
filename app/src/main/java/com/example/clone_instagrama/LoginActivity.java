package com.example.clone_instagrama;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();

        }

        etUsername = findViewById(R.id.etUsername_n);
        etPassword = findViewById(R.id.etPassword_n);
        btnLogin = findViewById(R.id.btnSign);
        btnSignup = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick Login Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSignupActivity();

            }
        });
    }

    private void goSignupActivity() {
        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);
    }

    private void loginUser(String username, String password) {
        Log.i(TAG,"Attempting to login user" + username);
        //TODO: nevigate to the main activity if the user has signed in properly
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.e(TAG,"Issue with login",e);
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this,"Success!",Toast.LENGTH_SHORT);
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
