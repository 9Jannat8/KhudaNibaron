package com.example.khudanibaron;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signInEmailEditText, signInPasswordEditText;
    private CheckBox signInCheckBox;
    private Button signInButton;
    private TextView signUpTextView, forgetPasswordTextView;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in);

        getWindow().setStatusBarColor(ContextCompat.getColor(SignInActivity.this, R.color.sea_green));

        //StatusBarUtil.setTransparent(this);//to make status bar transparent..for this at first i have to add dependency

        //this.setTitle("Sign In");

        mAuth = FirebaseAuth.getInstance();

        signInEmailEditText = findViewById(R.id.inEmailEditTextId);
        signInPasswordEditText = findViewById(R.id.inPasswordEditTextId);
        signInCheckBox = findViewById(R.id.inCheckboxId);
        signInButton = findViewById(R.id.signInButtonId);
        signUpTextView = findViewById(R.id.signUpTextViewId);
        forgetPasswordTextView = findViewById(R.id.inForgetPasswordId);

        progressBar = findViewById(R.id.signInProgressBarId);

        signInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInButtonId:
                userLogin();
                break;

            case R.id.signUpTextViewId:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin() {
        String email = signInEmailEditText.getText().toString().trim();
        String password = signInPasswordEditText.getText().toString().trim();


        //checking the validity of email
        if (email.isEmpty())
        {
            signInEmailEditText.setError("Enter an email address");
            signInEmailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;
        }


        //checking the validity of password
        if (password.isEmpty())
        {
            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            signInPasswordEditText.setError("Minimum length of a password should be 6");
            signInPasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(getApplicationContext(), MenuSplashActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//after entering new activity when we will press backbutton then there will be everythin is cleared
                    startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(), "Login is unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}