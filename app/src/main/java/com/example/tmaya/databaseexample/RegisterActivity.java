package com.example.tmaya.databaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword,editTextConfirmPassword;
    Button buttonRegister,buttonLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        editTextUsername=findViewById(R.id.editText_username);
        editTextPassword=findViewById(R.id.editText_password);
        editTextConfirmPassword=findViewById(R.id.editText_confirmpassword);
        buttonRegister=findViewById(R.id.button_register);
        buttonLogin=findViewById(R.id.button_login);

        dbHelper = new DBHelper(RegisterActivity.this);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                finish();
                startActivity(intent);

            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextUsername.getText().toString().equals("")){
                    editTextUsername.setError("Required");
                }
                else if (editTextPassword.getText().toString().equals("")){
                    editTextPassword.setError("Required");
                }
                else if(editTextConfirmPassword.getText().toString().equals("")){
                    editTextConfirmPassword.setError("Required");
                }
                else if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString()/[])){
                    editTextConfirmPassword.setError("Password do not match");
                }else {
                    boolean success=dbHelper.saveUser(editTextUsername.getText().toString(),editTextPassword.getText().toString());

                    if (success){
                    Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Alredy Registered", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
