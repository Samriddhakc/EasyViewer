package com.codepath.skc.easyviewer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG="LoginActivity";
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                loginUser(username,password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Inside the login function");
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                goSignUpActivity();
            }
        });
    }

    private void goSignUpActivity() {
        Intent i=new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }

    private void loginUser(String username, String password) {
        Log.i(TAG,"trying to login user here");
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e!=null)
                {
                    Log.e(TAG,"login is not successful",e);
                    Toast.makeText(LoginActivity.this, "Wrong Username or password", Toast.LENGTH_SHORT).show();
                    etUsername.setText("");
                    etPassword.setText("");
                    return;
                };
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                goViewActivity();
            }
        });
    }

    private void goViewActivity() {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
