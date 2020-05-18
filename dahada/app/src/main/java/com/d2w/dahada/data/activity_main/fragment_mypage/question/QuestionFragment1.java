package com.d2w.dahada.data.activity_main.fragment_mypage.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.d2w.dahada.R;

public class QuestionFragment1 extends Fragment {
    EditText content, emailText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_1, container, false);

        Spinner spinner = v.findViewById(R.id.spinner);

        content = v.findViewById(R.id.content);
        emailText = v.findViewById(R.id.emailText);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 1:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 2:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 오류 문구 창 내 메시지 확인 : \n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                    case 3:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 오류 문구 창 내 메시지 확인 : \n" +
                                "- 문제발생상품 :\n" +
                                "- 문제발생일시 :\n" +
                                "- 문의내용 : ");
                        break;
                    case 4:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 문제발생게시판 : \n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 :");
                        break;
                    case 5:
                        content.setText("아래 내용을 함께 보내주시면 더욱 빨리 처리가 가능합니다.\n" +
                                "- 오류 문구 창 내 메시지 확인 : \n" +
                                "- 문제발생일시 : \n" +
                                "- 문의내용 : ");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }
}
