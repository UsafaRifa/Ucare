package com.example.ucare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener{
    private TextView recover;
    private EditText email,password;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.emailTxt);
        password=findViewById(R.id.passwordTxt);
        login=findViewById(R.id.logInBtn);
        recover=findViewById(R.id.recoverTxt);
        pb=findViewById(R.id.pg_bar);


        login.setOnClickListener(this);

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,forgetpass.class));
                finish();
            }
        });

    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.recoverTxt:
                recoverPassword();
                break;
            case R.id.logInBtn:
                LogIn();
                break;
        }
    }

    private void recoverPassword() {
    }

    private void LogIn() {
        String mail= email.getText().toString().trim();
        String pass= password.getText().toString().trim();
        pb.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                pb.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG ).show();
                }

            }
        });
    }

}