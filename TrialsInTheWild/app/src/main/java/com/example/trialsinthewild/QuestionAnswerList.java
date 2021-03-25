package com.example.trialsinthewild;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QuestionAnswerList extends ArrayAdapter<QAPair> {
    private ArrayList<QAPair> discussion;
    private Context context;

    public QuestionAnswerList(Context context, ArrayList<QAPair> discussion) {
        super(context,0, discussion);
        this.discussion = discussion;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            //load content of list
            view = LayoutInflater.from(context).inflate(R.layout.question_answer_content,parent,false);
        }

        if(position % 2 == 0) {
            view.setBackgroundColor(view.getResources().getColor(R.color.common_google_signin_btn_text_light_disabled));
        }

        TextView tvQuestion = view.findViewById(R.id.tv_discussion_question);
        TextView tvAnswer = view.findViewById(R.id.tv_discussion_answer);

        EditText etAnswer = view.findViewById(R.id.et_answer_input);
        Button btnAnswer = view.findViewById(R.id.btn_submit_answer);

        QAPair obj = discussion.get(position);

        tvQuestion.setText(obj.getQuestion());

        if(obj.hasAnswer()) {
            tvAnswer.setVisibility(View.VISIBLE);
            etAnswer.setVisibility(View.GONE);
            btnAnswer.setVisibility(View.GONE);
        } else {
            tvQuestion.setVisibility(View.VISIBLE);
            tvAnswer.setVisibility(View.GONE);
            etAnswer.setVisibility(View.VISIBLE);
            btnAnswer.setVisibility(View.VISIBLE);
        }

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_answer = etAnswer.getText().toString();
                obj.addAnswer(new_answer);

                tvAnswer.setText(new_answer);

                tvAnswer.setVisibility(View.VISIBLE);
                etAnswer.setVisibility(View.GONE);
                btnAnswer.setVisibility(View.GONE);
            }
        });

        return view;
    }
}