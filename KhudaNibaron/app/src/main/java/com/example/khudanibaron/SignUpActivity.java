package com.example.khudanibaron;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, signUpEmailEditText, userNameEditText, signUpPasswordEditext;
    private CheckBox signUpCheckBox;
    private Button signUpButton;
    private TextView signInTextView;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_up);

        getWindow().setStatusBarColor(ContextCompat.getColor(SignUpActivity.this, R.color.sea_green));

        mAuth = FirebaseAuth.getInstance();

        signUpEmailEditText = findViewById(R.id.upEmailEditTextId);
        userNameEditText = findViewById(R.id.upUserNameEditTextId);
        signUpPasswordEditext = findViewById(R.id.upPasswordEditTextId);
        signUpCheckBox = findViewById(R.id.upCheckboxId);
        signUpButton = findViewById(R.id.signUpButtonId);
        signInTextView = findViewById(R.id.signInTextViewId);

        progressBar = findViewById(R.id.signUpProgressBarId);

        signUpButton.setOnClickListener(this);
        signInTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButtonId:
                userRegister();
                break;
            case R.id.signInTextViewId:
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {

        String email = signUpEmailEditText.getText().toString().trim();
        String password = signUpPasswordEditext.getText().toString().trim();
        String userName = userNameEditText.getText().toString().trim();


        //checking the validity of email
        if (email.isEmpty())
        {
            signUpEmailEditText.setError("Enter an email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signUpEmailEditText.setError("Enter a valid email address");
            signUpEmailEditText.requestFocus();
            return;
        }


        //checking the validity of password
        if (password.isEmpty())
        {
            signUpPasswordEditext.setError("Enter a password");
            signUpPasswordEditext.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            signUpPasswordEditext.setError("Minimum length of a password should be 6");
            signUpPasswordEditext.requestFocus();
            return;
        }

        //checking the validity of username
        if (userName.isEmpty())
        {
            userNameEditText.setError("Enter a username");
            userNameEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration is successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MenuSplashActivity.class);
                    startActivity(intent);

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "User is already Registered. Please sign in", Toast.LENGTH_LONG).show();
                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}