package eit11c.bs1.questionsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eit11c.bs1.questionsapp.R;
import eit11c.bs1.questionsapp.pojos.Question;
import eit11c.bs1.questionsapp.pojos.Quiz;

public class QuizActivity extends AppCompatActivity {

    // Whole quiz with every question
    private Quiz _quiz;
    private int _position;
    private int _correctCount;

    // question that matters
    private Question _question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setIntentions();
        setAnswers();
    }

    public void setButtonAction(View v) {
        Button answerButton = (Button)v;
        String buttonText = answerButton.getText().toString();
        _position += 1;
        if (_question.getCorrectAnswer().equals(buttonText)) {
            _correctCount += 1;
            displayElement(Status.SUCCESS);
        } else {
            displayElement(Status.FAILURE);
        }
    }

    public void goToActivity(View v) {
        if (_position <= 10) {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("quiz", _quiz);
            intent.putExtra("position", _position);
            intent.putExtra("correctCount", _correctCount);
            startActivity(intent);
        } else {
            // new screen for the ciedentials required
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("correctCount", _correctCount);
            startActivity(intent);
        }
    }

    private void displayElement(Status STATUS) {
        // creating new components
        LinearLayout linearLayout = new LinearLayout(this);
        TextView questionAnsweredView = new TextView(this);

        // set the setting for these components
        questionAnsweredView.setWidth(50000);
        questionAnsweredView.setHeight(50000);
        questionAnsweredView.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                goToActivity(v);
            }
        });
        questionAnsweredView.setTextSize(24);
        questionAnsweredView.setGravity(Gravity.CENTER);
        switch (STATUS) {
            case FAILURE:
                questionAnsweredView.setText("WRONG ANSWER \n CORRECT: " + replace(_question.getCorrectAnswer()));
                break;
            case SUCCESS:
                questionAnsweredView.setText("CORRECT ANSWER!");
                break;
        }
        linearLayout.setGravity(Gravity.CENTER);

        // display the new components
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(questionAnsweredView);
    }

    private void setTextView(int id, String text) throws UnsupportedEncodingException {
        TextView txtView = findViewById(id);
        txtView.setText(replace(text));
    }

    private void setButton(int id, String text) throws UnsupportedEncodingException {
        Button button = findViewById(id);
        button.setText(replace(text));
    }

    private String getRandomItem(List<String> answers) {
        int itemPos = new Random().nextInt(answers.size());
        String answer = answers.get(itemPos);
        answers.remove(itemPos);
        return answer;
    }

    private void setAnswers() {
        List<String> answers = getAnswersAsList(_question);
        try {
            setTextView(R.id.QUESTION, _question.getQuestion());
            setButton(R.id.ANSWERONE, getRandomItem(answers));
            setButton(R.id.ANSWERTWO, getRandomItem(answers));
            setButton(R.id.ANSWERTHREE, getRandomItem(answers));
            setButton(R.id.ANSWERFOUR, getRandomItem(answers));
        } catch (UnsupportedEncodingException unEnEx) {
            unEnEx.printStackTrace();
        }
    }

    private List<String> getAnswersAsList(Question question) {
        List<String> answers = new ArrayList<>();
        answers.add(question.getIncorrectAnswers()[0]);
        answers.add(question.getIncorrectAnswers()[1]);
        answers.add(question.getIncorrectAnswers()[2]);
        answers.add(question.getCorrectAnswer());
        return answers;
    }

    private void setIntentions() {
        _quiz = getIntent().getParcelableExtra("quiz");
        _position = getIntent().getIntExtra("position", 1);
        _correctCount = getIntent().getIntExtra("correctCount", 0);
        _question = _quiz.getResults()[_position - 1];
    }

    private String replace(String s) {
        s = s.replace("&quot;","\"");
        s = s.replace("&shy;", "-");
        s = s.replace("&#039;", "‘");
        s = s.replace("&AMP;", "&");
        s = s.replace("&Auml;", "Ä");
        s = s.replace("&auml;", "ä");
        s = s.replace("&Ouml;", "Ö");
        s = s.replace("&ouml;", "ö");
        s = s.replace("&Uuml;", "Ü");
        s = s.replace("&uuml;", "ü");
        s = s.replace("&szlig;", "ß");
        s = s.replace("&deg;", "°");
        s = s.replace("&sect;", "§");
        s = s.replace("&OACUTE;", "Ó");
        s = s.replace("&UACUTE;", "Ú");
        s = s.replace("&OGRAVE;", "Ò");
        return s;
    }

    enum Status {
        SUCCESS, FAILURE
    }
}
