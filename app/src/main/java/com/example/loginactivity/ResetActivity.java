package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView email;
    EditText newPass,conPass;
    Button btnConfirm;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = (TextView) findViewById(R.id.etEmailReset);
        newPass = (EditText) findViewById(R.id.etRepassword);
        conPass = (EditText) findViewById(R.id.etConfirmPassword);
        btnConfirm = (Button) findViewById(R.id.btnCon);
        DB = new DatabaseHelper (this);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("Email"));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = email.getText().toString();
                String password = newPass.getText().toString();
                String confirmPassword = conPass.getText().toString();

                if(password.equals(confirmPassword))
                {
                    Boolean checkpasswordupdate = DB.updateUser(user,password);

                    if(checkpasswordupdate == true)
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Success Update!", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        Toast.makeText(ResetActivity.this, "Unsuccessfully Update!", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(ResetActivity.this, "Password Not Matching!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}