package eit11c.bs1.questionsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import eit11c.bs1.questionsapp.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int correctCount = getIntent().getIntExtra("correctCount", 0);
        setTextView(correctCount);
    }

    public void setTextView(int count) {
        TextView resultView = findViewById(R.id.resultView);
        resultView.setText(count + " / 10 \n are correct" );
    }

    public void buttonEvent(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
