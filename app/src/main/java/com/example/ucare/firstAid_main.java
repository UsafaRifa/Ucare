package com.example.ucare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class firstAid_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_main);
    }
    public void HeartAttack (View v){
        Intent intent=new Intent(firstAid_main.this,heartAttack.class);
        startActivity(intent);
    }

    public void Stroke (View v){
        Intent intent=new Intent(firstAid_main.this,stroke.class);
        startActivity(intent);
    }
    public void Fracture (View v){
        Intent intent=new Intent(firstAid_main.this,fracture.class);
        startActivity(intent);
    }
    public void poisoning (View v){
        Intent intent=new Intent(firstAid_main.this,poisoning.class);
        startActivity(intent);
    }
    public void Burn (View v){
        Intent intent=new Intent(firstAid_main.this,burn.class);
        startActivity(intent);
    }
    public void Bandage (View v){
        Intent intent=new Intent(firstAid_main.this,bandage.class);
        startActivity(intent);
    }
    public void Bleeding (View v){
        Intent intent=new Intent(firstAid_main.this,bleed.class);
        startActivity(intent);
    }
    public void shock (View v){
        Intent intent=new Intent(firstAid_main.this,shock.class);
        startActivity(intent);
    }
    public void corona (View v){
        Intent intent=new Intent(firstAid_main.this,corona.class);
        startActivity(intent);
    }
    public void chocking (View v){
        Intent intent=new Intent(firstAid_main.this,chocking.class);
        startActivity(intent);
    }
}