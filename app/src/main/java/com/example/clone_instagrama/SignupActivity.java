package com.example.clone_instagrama;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etUsername_n;
    private EditText etPassword_n;
    private Button btnSign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        etUsername_n = findViewById(R.id.etUsername_n);
        etPassword_n = findViewById(R.id.etPassword_n);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Login Button");
                String username = etUsername_n.getText().toString();
                String password = etPassword_n.getText().toString();
                signUpUser(username, password);
            }
        });
    }


    private void signUpUser(String username, String password) {
        Log.i(TAG,"Attempting to Sign user" + username);
        //TODO: nevigate to the main activity if the user has signed in properly
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.i(TAG,"Sign up successfully");
                    goMainActivity();
                    Toast.makeText(SignupActivity.this,"Success!",Toast.LENGTH_SHORT);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG,"Fail to Sign up",e);
                    return;
                }
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
