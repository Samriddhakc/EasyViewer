package com.codepath.skc.easyviewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG="SignUpActivity";
    private EditText rgUsername;
    private EditText rgUserType;
    private EditText rgPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toast.makeText(this, "Successfully inside Login",Toast.LENGTH_SHORT).show();
        rgUsername=findViewById(R.id.rgUsername);
        rgPassword=findViewById(R.id.rgPassword);
        rgUserType=findViewById(R.id.rgUserType);
        btnRegister=findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=rgUsername.getText().toString();
                String password=rgPassword.getText().toString();
                String userType=rgUserType.getText().toString();
                Log.i(TAG,"on Register");
                Log.i(TAG,"Registering"+username);
                registerUser(username,password,userType);
            }
        });
    }

    private void registerUser(String username, String password, String userType) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.put("UserType",userType);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goBack();
                    Log.i(TAG, "succesfullly registered");

                } else {
                    Log.e(TAG, "cannot register user", e);
                }
            }
        });
    }

    private void goBack() {
        Toast.makeText(this, "Successfully signed up!",Toast.LENGTH_SHORT).show();
        ParseUser.logOut();
        Log.i(TAG,"Inisde go back!");
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}
