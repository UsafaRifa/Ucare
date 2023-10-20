package com.example.ucare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import javax.crypto.spec.OAEPParameterSpec;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_user extends AppCompatActivity {

    private Button UpdateAccountSettings;
    private EditText username,UserGender,UserAge;
    private CircleImageView userProfileImage;
    private static final int GalleryPick=1;
    private StorageReference UserProfileImagesRef;
    private DatabaseReference RootRef;
   private String currentUserID ;
   private FirebaseAuth mAuth;
   private ProgressDialog loadingBar;
   private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        UserProfileImagesRef= FirebaseStorage.getInstance().getReference().child("Profile Images");
        mAuth=FirebaseAuth.getInstance();
        currentUserID=mAuth.getCurrentUser().getUid();
        RootRef=FirebaseDatabase.getInstance().getReference();


        InitializeFields();
        UpdateAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSettings();
            }
        });


        userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent galleryIntent= new Intent();
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,GalleryPick);
                }

            }
        });
        RetrieveUserInformation();

logout=(Button)findViewById(R.id.logOut);
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(profile_user.this,login.class));
    }
});
    }




    private void InitializeFields() {
        UpdateAccountSettings=(Button) findViewById(R.id.UpdateAccountSettings);
        username=(EditText)findViewById(R.id.set_user_name);
        UserGender=(EditText)findViewById(R.id.set_user_gender);
        UserAge=(EditText)findViewById(R.id.set_user_age);
        userProfileImage=(CircleImageView)findViewById(R.id.imageView);
        loadingBar= new ProgressDialog(this );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick&&resultCode==RESULT_OK&& data!=null)
        {
            Uri imageUri =data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)

            {
                loadingBar.setTitle("Set Profile Image");
                loadingBar.setMessage("Please wait,your profile image is updating");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                Uri resultUri=result.getUri();

                StorageReference filePath =UserProfileImagesRef.child(currentUserID+".jpg");
                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull  Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(profile_user.this,"Profile Image uploaded Successfully",Toast.LENGTH_SHORT).show();
                            final String downloaedUrl=task.getResult().getStorage().getDownloadUrl().toString();
                            RootRef.child("Users").child(currentUserID).child("image").setValue(downloaedUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(profile_user.this, "Image save in database ,successfully... ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                    else
                                    {
                                        String message =task.getException().toString();
                                        Toast.makeText(profile_user.this,"Error",Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                    }

                                }
                            });

                        }
                        else
                        {
                            String message =task.getException().toString();
                            Toast.makeText(profile_user.this,"Error",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }

                    }
                });

            }

        }
    }
    private void UpdateSettings() {
        String setUserName=username.getText().toString();
        String setGender=UserGender.getText().toString();
        String setAge=UserAge.getText().toString();
        if(TextUtils.isEmpty(setUserName))
        {
            Toast.makeText(this,"Please write your user name first...",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(setGender))
        {
            Toast.makeText(this,"Please write your Gender first...",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(setAge))
        {
            Toast.makeText(this,"Please write your Age  first...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String,String>profileMap=new HashMap<>();
            profileMap.put("uid",currentUserID);
            profileMap.put("name",setUserName);
            profileMap.put("gender",setGender);
            profileMap.put("age",setAge);
            RootRef.child("Users").child(currentUserID).setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull  Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(profile_user.this,"Update successfully",Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        String message =task.getException().toString();
                        Toast.makeText(profile_user.this,"Error",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
    private void RetrieveUserInformation() {

        RootRef.child("Users").child(currentUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        if((dataSnapshot.exists()) &&(dataSnapshot.hasChild("name") &&(dataSnapshot.hasChild("image"))))
                        {
                            String retrieveUserName =dataSnapshot.child("name").getValue().toString();
                            String retrieveGender =dataSnapshot.child("gender").getValue().toString();
                            String retrieveAge =dataSnapshot.child("age").getValue().toString();
                            String retrieveProfileImage =dataSnapshot.child("image").getValue().toString();

                            username.setText(retrieveUserName);
                            UserAge.setText(retrieveAge);
                            UserGender.setText(retrieveGender);
                            Picasso.get().load(retrieveProfileImage).into(userProfileImage);
                        }


                        else
                        {

                            Toast.makeText(profile_user.this,"Set And Update Your Profile Information....",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled( DatabaseError error) {

                    }
                });
    }


}