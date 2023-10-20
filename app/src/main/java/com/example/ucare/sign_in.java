package com.example.ucare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity implements View.OnClickListener {

    private TextView login;
    private EditText email,password,confirmPassword;
    private ConstraintLayout signup;
    private FirebaseAuth mAuth;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        login=findViewById(R.id.goto_login);
        email=findViewById(R.id.emailText);
        password=findViewById(R.id.passwordText);
        confirmPassword=findViewById(R.id.ConfirmPasswordText);
        signup=findViewById(R.id.sign_up);
        pb=findViewById(R.id.progress_bar);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }

    }
    public void gotoLogin(View view){
        Intent intent=new Intent(sign_in.this,login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sign_up:
                userRegistration();
                break;
            case R.id.goto_login:
                Intent intent =new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegistration() {
        String mail= email.getText().toString().trim();
        String pass= password.getText().toString().trim();
        String Cpass= confirmPassword.getText().toString().trim();


        //checking the validity of the email
        if(mail.isEmpty())
        {
            email.setError("Enter an email address");
            email.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(pass.isEmpty())
        {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            password.setError("Length of password must be greater than 6 character");
            password.requestFocus();
            return;
        }
        if(!pass.equals(Cpass))
        {
            password.setError("Password Mismatch");
            confirmPassword.setError("Password Mismatch");
            password.requestFocus();
            confirmPassword.requestFocus();
            return;
        }

        pb.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pb.setVisibility(View.GONE);
                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Registration is successful",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Registration is not successful",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}