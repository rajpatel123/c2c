package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;

public class LoginActivity extends AppCompatActivity {
    private EditText _editMobile, _editPassword;
    private TextView newUser;
    private Button _loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        _editMobile = findViewById(R.id.mobile);
        _editPassword = findViewById(R.id.password);
        TextView newUser = (TextView) findViewById(R.id.newuser);
        Button _loginbtn = (Button) findViewById(R.id.loginbutton);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
        _loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
                startActivity(intent);
            }
        });


    }



}
