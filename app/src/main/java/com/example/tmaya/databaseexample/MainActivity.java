package com.example.tmaya.databaseexample;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserNAME,editTextPassword;
    Button buttonLogin,buttonRegister;
    DBHelper  dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserNAME=findViewById(R.id.editText_username);
        editTextPassword=findViewById(R.id.editText_password);
        buttonLogin=findViewById(R.id.button_login);
        buttonRegister=findViewById(R.id.button_register);
        dbHelper =new DBHelper(MainActivity.this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextUserNAME.getText().toString().equals("")){
                    editTextUserNAME.setError("Required");
                }
                else if (editTextPassword.getText().toString().equals("")){
                    editTextPassword.setError("Required");
                }else {
                    boolean success=dbHelper.chekUser(editTextUserNAME.getText().toString(),
                            editTextPassword.getText().toString());
                    if (success){
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }
}
