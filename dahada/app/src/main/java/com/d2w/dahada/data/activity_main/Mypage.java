package com.d2w.dahada.data.activity_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
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
            Toast.makeText(getContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
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
                    signOut();
                    Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                    button_login.setText(R.string.mypage_login);
                }
                break;
            case R.id.button_notice:
                Toast.makeText(getContext(), "공지사항", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_question:
                Toast.makeText(getContext(), "문의하기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_setting:
                Toast.makeText(getContext(), "환경설정", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
