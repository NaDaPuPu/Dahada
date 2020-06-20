package com.d2w.dahada.data.activity_main.fragment_main.community;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.d2w.dahada.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PhotoPost_Activity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private ImageButton SelectPostImage;
    private Button UpdatePostButton;
    private EditText PostDescription;

    private static final int Galley_Pick = 1;
    private Uri ImageUri;
    private String Description;
    private StorageReference PostsImageRefrence;

    private DatabaseReference UserRef, PostRef;

    private FirebaseAuth mAuth; //추가



    private String saveCurrentDate, saveCurrentTime, postRandomName, downloadUrl, current_user_id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_post_);




        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        PostsImageRefrence = FirebaseStorage.getInstance().getReference();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users"); //추가
        PostRef = FirebaseDatabase.getInstance().getReference().child("Posts"); //추가


        SelectPostImage = (ImageButton)findViewById(R.id.select_post_image);
        UpdatePostButton = (Button)findViewById(R.id.update_post_button);
        PostDescription = (EditText)findViewById(R.id.post_description);
        loadingBar = new ProgressDialog(this);

        SelectPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opengalley();
            }
        });

        UpdatePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePostInfo();
            }
        });

    }

    private void ValidatePostInfo() {
        Description = PostDescription.getText().toString();
        if(ImageUri == null)
        {
            Toast.makeText(this,"이미지를 선택해 주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description))
        {
            Toast.makeText(this,"코멘트를 달아주세요.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("사진을 게시중입니다");
            loadingBar.setMessage("잠시만 기다려주세요.");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            StoringImageToStorage();
        }
    }

    private void StoringImageToStorage() {
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;


        StorageReference filePath = PostsImageRefrence.child("Post Images").child(ImageUri.getLastPathSegment() + postRandomName + ".jpg");

        filePath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful())
                {
                    downloadUrl = task.getResult().getUploadSessionUri().toString(); //추가
                    Toast.makeText(PhotoPost_Activity.this, "이미지가 업로드 되었습니다!", Toast.LENGTH_SHORT).show();
                    SavingPostInformationToDatabase(); //추가


                }
                else
                {
                    String message = task.getException().getMessage();
                    Toast.makeText(PhotoPost_Activity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void SavingPostInformationToDatabase() { //추가
        UserRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String userNickname = dataSnapshot.child("nickname").getValue().toString();
                    String userResidence = dataSnapshot.child("residence").getValue().toString();

                    HashMap postsMap = new HashMap();
                    postsMap.put("uid", current_user_id);
                    postsMap.put("date", saveCurrentDate);
                    postsMap.put("time", saveCurrentTime);
                    postsMap.put("description", Description);
                    postsMap.put("postImage", downloadUrl);
                    postsMap.put("nickname", userNickname);
                    postsMap.put("Residence", userResidence);

                    PostRef.child(current_user_id + postRandomName).updateChildren(postsMap)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful())
                                    {
                                        SendUserToPhotoCommunity();
                                        Toast.makeText(PhotoPost_Activity.this, "성공적으로 게시되었습니다.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                    else
                                    {
                                        Toast.makeText(PhotoPost_Activity.this, "Error.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SendUserToPhotoCommunity() {
        Intent myIntent = new Intent(PhotoPost_Activity.this, PhotoCommunity_Activity.class);
        startActivity(myIntent);
    }


    private void Opengalley() {
        Intent galleyIntent = new Intent();
        galleyIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleyIntent.setType("image/*");
        startActivityForResult(galleyIntent, Galley_Pick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Galley_Pick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            SelectPostImage.setImageURI(ImageUri);

        }
    }


}