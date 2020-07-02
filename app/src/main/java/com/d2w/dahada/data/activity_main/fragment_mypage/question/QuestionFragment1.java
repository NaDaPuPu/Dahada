package com.d2w.dahada.data.activity_main.fragment_mypage.question;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_mypage.Question;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuestionFragment1 extends Fragment {
    private FirebaseAuth mAuth;

    AlertDialog.Builder alertDialogBuilder;
    EditText content, emailText;
    Button sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_1, container, false);

        mAuth = FirebaseAuth.getInstance();

        final Spinner spinner = v.findViewById(R.id.spinner);

        content = v.findViewById(R.id.content);
        emailText = v.findViewById(R.id.emailText);
        sendButton = v.findViewById(R.id.sendButton);

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
                                "- 문의내용 : ");
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

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("알림")
                        .setMessage("문의를 보내시겠습니까?")
                        .setCancelable(false)

                        // 확인 시
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                Date date = new Date(System.currentTimeMillis());
                                String contentStr = content.getText().toString();
                                int startIndex = contentStr.indexOf("문의내용 : ");
                                int endIndex = contentStr.length();
                                String title;

                                if (endIndex - startIndex >= 20) {
                                    title = contentStr.substring(startIndex + 7, startIndex + 27);
                                } else {
                                    title = contentStr.substring(startIndex + 7, endIndex);
                                }

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Question question = new Question(mAuth.getUid(), format.format(date), spinner.getSelectedItemPosition(), title, contentStr, null, emailText.getText().toString());

                                db.collection("mypage/question/questions").document(question.getType() + " " + question.getDate() + " " + question.getEmail()).set(question);
                                Toast.makeText(getContext(), "문의를 보냈습니다.", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        })

                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        return v;
    }
}
