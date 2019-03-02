package com.example.project1jasper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    int curScore = 0;
    int maxScore = 0;

    TextView textViewScore = null;
    TextView textViewResultDescription = null;
    TextView Total = null;

    Button buttonPlayAgain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        this.textViewResultDescription = (TextView)findViewById(R.id.textViewResultDesc);
        this.textViewScore = (TextView)findViewById(R.id.score);
        this.Total = (TextView)findViewById(R.id.total);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Get the player score and max score from the previous Activity.
        // Set thse to curScore and maxScore respectively.
        // HINT: Remember that we stored these variables in .putExtra (from the question activity.
        //       To get this back try: getIntent().getIntExtra(..., 0)
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        this.curScore = getIntent().getIntExtra("currentScore", 0);
        this.maxScore = getIntent().getIntExtra("maxScore", 0);



        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Display the score and score description for the user to see (set widgets text).
        // Hint: You can use the getPercentage() method provided to get the percentage correct value.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        String finalScore = Integer.toString(curScore);
        String maxScoreStr = Integer.toString(maxScore);
        textViewScore.setText(finalScore);
        Total.setText(maxScoreStr);
        textViewResultDescription.setText("You answered " + getPercentage(curScore,maxScore) +" of the questions correctly.");

        this.buttonPlayAgain = (Button)findViewById(R.id.button);
        this.buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAgain();
            }
        });
    }

    private String getPercentage(int current, int total) {
        float fPercent = (float)current/(float)total;
        return Integer.toString((int)Math.ceil((fPercent) * 100)) + "%";
    }
    private void startAgain(){
        Intent intent = new Intent(ResultActivity.this,
                com.example.project1jasper.MainActivity.class);
        startActivity(intent);
    }
}
