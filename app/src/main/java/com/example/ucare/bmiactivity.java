package com.example.ucare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class bmiactivity extends AppCompatActivity {

    android.widget.Button mrecalculatebmi;
    TextView mbmidisplay,mbmicatagory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbmi;
    float intbmi;

    String height;
    String weight;
    float inheight,inweight;
    RelativeLayout mbackground;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
      //  getSupportActionBar().setElevation(0);
      //  getSupportActionBar().setTitle(Html.fromHtml("<front color=\"white\"></font>"));
      //  getSupportActionBar().setTitle("Result");
      //   ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
      //   getSupportActionBar().setBackgroundDrawable(colorDrawable);


         intent=getIntent();
         mbmidisplay=findViewById(R.id.bmidisplay);
         mbmicatagory=findViewById(R.id.bmicatagory);
         mgender=findViewById(R.id.genderdisplay);
         mbackground=findViewById(R.id.contentlayout);
         mimageview=findViewById(R.id.imageview);
         mrecalculatebmi=findViewById(R.id.recalculatebmi);

         height=intent.getStringExtra("height");
         weight=intent.getStringExtra("weight");

         inheight=Float.parseFloat(height);
         inweight=Float.parseFloat(weight);
        System.out.println(inheight);

         inheight/=100;
         intbmi=inweight/(inheight*inheight);

         mbmi=Float.toString(intbmi);

         if (intbmi<16)
         {
             mbmicatagory.setText("Serve Thinness");
             mbackground.setBackgroundColor(Color.RED);
             mimageview.setImageResource(R.drawable.crosss);

         }

         else if (intbmi<16.9 && intbmi>16)

         {
             mbmicatagory.setText("Moderate Thinness");
             mbackground.setBackgroundColor(Color.RED);
             mimageview.setImageResource(R.drawable.warning);

         }


         else if (intbmi<18.4&&intbmi>17)

         {
             mbmicatagory.setText("Mild Thinness");
             mbackground.setBackgroundColor(Color.RED);
             mimageview.setImageResource(R.drawable.warning);

         }

         else if (intbmi<25&&intbmi>18.4)

         {
             mbmicatagory.setText("Normal");
            // mbackground.setBackgroundColor(Color.YELLOW);
             mimageview.setImageResource(R.drawable.ok);

         }

         else if (intbmi<29.4&&intbmi>25)

         {
             mbmicatagory.setText("Overweight");
             mbackground.setBackgroundColor(Color.RED);
             mimageview.setImageResource(R.drawable.warning);

         }
         else
         {

             mbmicatagory.setText("Obese Class i");
             mbackground.setBackgroundColor(Color.RED);
             mimageview.setImageResource(R.drawable.warning);

         }


         mgender.setText(intent.getStringExtra("gender"));
         mbmidisplay.setText(mbmi);









        //getSupportActionBar().hide();
        mrecalculatebmi=findViewById(R.id.recalculatebmi);
mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(bmiactivity.this,bmi_calculator.class);
        startActivity(intent);
        finish();
    }

});

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        Intent intent=new Intent(bmiactivity.this, MainActivity.class);
        startActivity(intent);
        //super.onBackPressed();

    }
}