package com.example.galon_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegitered;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword= (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegitered = (Button)findViewById(R.id. textview_register);
        mTextViewLogin = (TextView)findViewById(R.id. textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);


            }
        });

        mButtonRegitered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString(). trim();
                String pwd = mTextPassword.getText().toString(). trim();
                String cnf_pwd = mTextCnfPassword.getText().toString(). trim();

                if(pwd.equals(cnf_pwd)){
                    db.addUser(user,pwd);
                    long val = db.addUser(user,pwd);
                    if (val >0){
                        Toast.makeText(RegisterActivity.this, "You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent( RegisterActivity.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Register Error",Toast.LENGTH_SHORT).show();
                    }

                }

                else{
                    Toast.makeText(RegisterActivity.this, "Password is not matchng",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}