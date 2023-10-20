package com.example.ucare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ucare.medicine.MedicineActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv=findViewById(R.id.BottomNavigationView);
        bnv.setBackground(null);
        //bnv.getMenu().getItem(1).setChecked(true);
        bnv.setSelectedItemId(R.id.holder);

    }
    public void homeOnClick(View view){
        bnv.getMenu().getItem(0).setChecked(false);
        bnv.getMenu().getItem(1).setChecked(false);
    }
    public void bmiCalculate(View view){
        Intent intent=new Intent(MainActivity.this,bmi_calculator.class);
        startActivity(intent);
    }
    public void signup(View v){
        Intent intent=new Intent(MainActivity.this,sign_in.class);
        startActivity(intent);
    }
    public void pill_remainder(View v){
        Intent intent=new Intent(MainActivity.this, MedicineActivity.class);
        startActivity(intent);
    }

    public void FirstAid (View v){
        Intent intent=new Intent(MainActivity.this,firstAid_main.class);
        startActivity(intent);
    }
    public void profile (View v){
        Intent intent=new Intent(MainActivity.this,profile_user.class);
        startActivity(intent);
    }
    public void pdf (View v){
        Intent intent=new Intent(MainActivity.this,med_pdf.class);
        startActivity(intent);
    }
    public void user_profile (View v){
        Intent intent=new Intent(MainActivity.this,profile_user.class);
        startActivity(intent);
    }


}