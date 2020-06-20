package com.d2w.dahada.data.Setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.Main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;

public class SetupInformationActivity extends AppCompatActivity {

    private EditText Nickname, Residence;
    private Button SaveInformationbutton;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;

    private ProgressDialog loadingBar;

    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_information);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        Nickname = (EditText) findViewById(R.id.setup_nickname);
        Residence = (EditText) findViewById(R.id.setup_residence);
        SaveInformationbutton = (Button) findViewById(R.id.setup_information_button);
        loadingBar = new ProgressDialog(this);


        SaveInformationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSetupInformation();
            }
        });



    }

    private void SaveSetupInformation() {
        String nickname = Nickname.getText().toString();
        String residence = Residence.getText().toString();

        if(TextUtils.isEmpty(nickname))
        {
            Toast.makeText(this,"닉네임을 입력해주세요.",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(residence))
        {
            Toast.makeText(this,"사는지역을 입력해주세요.",Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingBar.setTitle("정보를 저장중입니다.");
            loadingBar.setMessage("잠시만 기다려주세요.");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            HashMap userMap = new HashMap();
            userMap.put("nickname", nickname);
            userMap.put("residence", residence);
            UserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful())
                    {

                        Toast.makeText(SetupInformationActivity.this,"설정이 완료되었습니디..",Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(SetupInformationActivity.this,"Error : " + message,Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();


                    }
                }
            });

        }

    }


}