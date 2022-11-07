package com.example.eventme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText editTextLoginEmail, editTextLoginPWD;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        editTextLoginEmail = findViewById(R.id.editText_login_email);
        editTextLoginPWD = findViewById(R.id.editText_login_pwd);
        progressBar = findViewById(R.id.progressBar);

        authProfile = FirebaseAuth.getInstance();

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = editTextLoginEmail.getText().toString();
                String textPWD = editTextLoginPWD.getText().toString();

                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    editTextLoginEmail.setError("Email is required");
                    editTextLoginEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(Login.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                    editTextLoginEmail.setError("Valid email is required");
                    editTextLoginEmail.requestFocus();
                } else if (TextUtils.isEmpty(textPWD)) {
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextLoginPWD.setError("Password is required");
                    editTextLoginPWD.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(textEmail, textPWD);
                }
            }
        });
    }

    private void loginUser(String textEmail, String textPWD) {
        authProfile.signInWithEmailAndPassword(textEmail, textPWD).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, UserProfile.class));

                    FirebaseUser firebaseUser = authProfile.getCurrentUser();

                    /*if (firebaseUser.isEmailVerified()) {
                        Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, UserProfile.class));
                        finish();
                    } else {
                        firebaseUser.sendEmailVerification();
                        authProfile.signOut();
                        showAlertDialog();
                    }*/

                } else {
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthInvalidUserException e) {
                        editTextLoginEmail.setError("User does not exist. Please register before logging in.");
                        editTextLoginEmail.requestFocus();
                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        editTextLoginEmail.setError("Invalid credentials. Please try again.");
                        editTextLoginEmail.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Login.this, "An error has occured", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setTitle("Email is not verified");
        builder.setMessage("Please verify your email to continue.");

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authProfile.getCurrentUser() != null) {
            Toast.makeText(Login.this, "Already logged in", Toast.LENGTH_SHORT).show();

            //code for userprofileactivity
            startActivity(new Intent(Login.this, UserProfile.class));
            finish();
        } else {
            Toast.makeText(Login.this, "You can login now", Toast.LENGTH_SHORT).show();
        }
    }
}