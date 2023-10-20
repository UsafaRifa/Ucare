package com.example.ucare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpass extends AppCompatActivity {
    private EditText forEmail;
    private Button forgot;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        forEmail=findViewById(R.id.forEmail);
        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=forEmail.getText().toString();

                if (email.isEmpty())
                {
                    Toast.makeText(forgetpass.this,"Please provide your email",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    forgetPassword();
                }
            }


        });
    }
    private void forgetPassword() {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(forgetpass.this,"Check Your Email",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(forgetpass.this,login.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(forgetpass.this,"Error:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

}