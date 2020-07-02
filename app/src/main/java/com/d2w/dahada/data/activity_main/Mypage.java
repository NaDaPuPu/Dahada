package com.d2w.dahada.data.activity_main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_mypage.NoticeActivity;
import com.d2w.dahada.data.activity_main.fragment_mypage.QuestionActivity;
import com.d2w.dahada.data.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mypage extends Fragment implements View.OnClickListener{

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    Button button_login, button_notice, button_question, button_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        button_login = v.findViewById(R.id.button_log);
        button_notice = v.findViewById(R.id.button_notice);
        button_question = v.findViewById(R.id.button_question);
        button_setting = v.findViewById(R.id.button_setting);

        button_login.setOnClickListener(this);
        button_notice.setOnClickListener(this);
        button_question.setOnClickListener(this);
        button_setting.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            button_login.setText(R.string.mypage_logout);
        } else {
            button_login.setText(R.string.mypage_login);
        }
    }

    private void signOut() {
        mAuth.signOut();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_log:
                if (button_login.getText() == getString(R.string.mypage_login)) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else if (button_login.getText() == getString(R.string.mypage_logout)) {
                    showMessage();
                }
                break;
            case R.id.button_notice:
                Intent intent = new Intent(getActivity().getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_question:
                Intent intent2 = new Intent(getActivity().getApplicationContext(), QuestionActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_setting:
                Toast.makeText(getContext(), "환경설정", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("알림");
        builder.setMessage("로그아웃 하시겠습니까?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                signOut();
                Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                button_login.setText(R.string.mypage_login);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
