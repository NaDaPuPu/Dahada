package com.d2w.dahada.data.activity_main.fragment_mypage.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.d2w.dahada.R;

public class QuestionFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_1, container, false);

        Spinner spinner = v.findViewById(R.id.spinner);
        return v;
    }
}
