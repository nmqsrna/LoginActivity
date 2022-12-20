package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private EditText email, password;
    private Button btnLogin;
    private TextView tvRegister, tvForgotPassword;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /*
     * This method is to initialize views
     */
    private void initViews() {
        email =  findViewById(R.id.etEmail);
        password =  findViewById(R.id.etPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
    }

    /*
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    /*
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);

    }

    /*
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                verifyFromSQLite();
                Intent intentLogin = new Intent(getApplicationContext(), SplashHomeActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.tvRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.tvForgotPassword:
                // Navigate to RegisterActivity
                Intent intentForgotPass = new Intent(getApplicationContext(), ResetActivity.class);
                startActivity(intentForgotPass);
                break;
        }
    }


    /*
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (databaseHelper.checkUser(email.getText().toString().trim(),
                password.getText().toString().trim())){


            Intent intentMain = new Intent(LoginActivity.this, SplashHomeActivity.class);

            emptyInputEditText();
            startActivity(intentMain);
        } else {
            // Toast to show success message that record is wrong
               Toast.makeText(LoginActivity.this,"Email or password is wrong. Check and try again", Toast.LENGTH_LONG).show();
        }
    }

    /*
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        email.setText(null);
        password.setText(null);
}
}