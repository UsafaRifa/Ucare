package com.example.ucare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrievePDF extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<putPDF> uploadedPDF;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_pdf);
        listView = findViewById(R.id.listView);
        uploadedPDF = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploadPDF").child(user.getUid());

        retrievePDFFiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                putPDF putPDF=uploadedPDF.get(i);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setType("application.pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);

            }
        });


    }
    private void retrievePDFFiles()
    {
        //databaseReference= FirebaseDatabase.getInstance().getReference("uploadPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    putPDF putPDF=ds.getValue(com.example.ucare.putPDF.class);
                    uploadedPDF.add(putPDF);

                }
                String[] uploadsName=new String[uploadedPDF.size()];
                for (int i=0;i<uploadsName.length;i++)
                {
                    uploadsName[i]=uploadedPDF.get(i).getName();

                }
                ArrayAdapter<String>arrayAdapter= new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,uploadsName){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position, convertView,parent);
                        TextView textView=(TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(20);
                        return view;
                    }
                };

                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}