package com.example.project1jasper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private ArrayList<QuestionNumber> questionList = null;
    QuestionNumber currentNumber = null;
    int currentQuestionNumber = 1;
    private int currentScore = 0;
    private int maxScore = 0;
    RadioGroup radioGroup = null;
    RadioButton answerA = null;
    RadioButton answerB = null;
    RadioButton answerC = null;
    RadioButton answerD = null;

    TextView askQuestion = null;
    TextView questionNumber = null;
    TextView scoreView = null;

    Button submit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_view);


        //Submit Button
        this.submit = (Button)findViewById(R.id.submitButton);
        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerCheck()){
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    // TO-DO: Set currentQuestion to the next question (because we want to process what is next).
                    // You can set the reference from the quizQuestionList.
                    // Use currentQuestionNumber as the index (remember to increment this at the end so that we can fetch the next question index).
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////

                    currentQuestionNumber++;
                    //this.currentNumber = questionList[currentQuestionNumber];

                }else {
                    Intent intent = new Intent(QuestionActivity.this,
                            com.example.project1jasper.ResultActivity.class);
                    startActivity(intent);
                    // HINT: We want to pass in some extra values for the results class to use. So use something like:
                    //       intent.putExtra(...);
                   // intent.putExtra(String name, int value)
                }
            }
        });
        // Intialize the radio buttons for question multiple choice answers.
        this.radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        this.answerA = (RadioButton)findViewById(R.id.answerA);
        this.answerB = (RadioButton)findViewById(R.id.answerB);
        this.answerC = (RadioButton)findViewById(R.id.answerC);
        this.answerD = (RadioButton)findViewById(R.id.answerD);
        // Initialize widgets.
        this.askQuestion = (TextView)findViewById(R.id.askQuestion);
        this.questionNumber = (TextView)findViewById(R.id.questionNumber);
        this.scoreView = (TextView)findViewById(R.id.scoreView);

        // Initiate all questions first.
        this.initQuestions();

        // Ask use the first question when this activity loads.
        this.setQuestionView(this.currentNumber);

    }
    private void initQuestions() {
        // Create some questions to ask the questions.

        this.questionList = new ArrayList<QuestionNumber>();  // Initialize our question array.


    //First question
        QuestionNumber question1 = new QuestionNumber();
        question1.setQuestion("Who is the 45th President?");
        question1.setChoiceA("Donald Trump");
        question1.setChoiceB("Barack Obama");
        question1.setChoiceC("Ronald Reagan");
        question1.setChoiceD("George H.W. Bush");
        question1.setCorrectAnswer("Donald Trump");
        questionList.add(question1);

    //Second question
        QuestionNumber question2 = new QuestionNumber();
        question2.setQuestion("Where was Napoleon Bonaparte exiled to after his defeat in 1812");
        question2.setChoiceA("Paris");
        question2.setChoiceB("london");
        question2.setChoiceC("Elba");
        question2.setChoiceD("St. Helena");
        question2.setCorrectAnswer("Elba");
        questionList.add(question2);
    //Third question
        QuestionNumber question3 = new QuestionNumber();
        question3.setQuestion("Which one of the below was an art style developed during the Interwar period?");
        question3.setChoiceA("Abstract");
        question3.setChoiceB("German Expressionism");
        question3.setChoiceC("Realism");
        question3.setChoiceD("Romanticism");
        question3.setCorrectAnswer("German Expressionism");
        questionList.add(question3);
    //Fourth question
        QuestionNumber question4 = new QuestionNumber();
        question4.setQuestion("");
        question4.setChoiceA("");
        question4.setChoiceB("");
        question4.setChoiceC("");
        question4.setChoiceD("");
        question4.setCorrectAnswer("");
        questionList.add(question4);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Create instances (using the new QuizQuestion()) of your questions.
        // You will have to call into QuizQuestion() setters to set the follow:
        // - The question to ask.
        // - Set choice options for A, B, C, and D.
        // - Set the correct answer (so that class knows which one is correct or not).
        // - Remember to add the object to our quizQuestionList array. Hint: Use .add(...) function here.
        // NOTE: No widgets should be set in this method.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set your currentQuestion to point to your first question here (uncomment out the code below).
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


        //this.currentQuestion = /*Your first question*/;
        this.currentNumber = question1;
        // Set the current, score, and total question size.
        this.currentQuestionNumber = 0;
        this.maxScore = this.questionList.size();
        this.currentScore = 0;
    }

    private void setQuestionView(QuestionNumber quizQuestion) {
        if(quizQuestion == null) {
            Log.d("[DEBUG]", "quizQuestion is null in setQuestionView.");
            return;
        }

        // Clear the radio button checks just encase it was been set previously.
        radioGroup.clearCheck();
        /*
        *this.scoreView.setText(currentScore);
        *this.askQuestion.setText(quizQuestion.getQuestion());
        *this.answerA.setText(quizQuestion.getChoiceA());
        *this.answerB.setText(quizQuestion.getChoiceB());
        *this.answerC.setText(quizQuestion.getChoiceC());
        *this.answerD.setText(quizQuestion.getChoiceD());
        *this.questionNumber.setText(currentQuestionNumber);
        */
        // Loads the current question view.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set the UI view (all your widgets) with the current QuizQuestion passed in.
        // Hint: Use your getters from the QuizQuestion class to get the values stored there.
        // Set the following widget text:
        // - The question text (i.e. Question #1).
        // - Set the question to ask.
        // - Set all for radio button text.
        // - Set the score view with the current score (remeber to convert integer to string).
        //   Example: Score: 2
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    private boolean answerCheck(){
        int selectedButtonId = this.radioGroup.getCheckedRadioButtonId();
        if(selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton)findViewById(selectedButtonId)).getText().toString();

            if (currentNumber.isCorrectAnswer(answerSelectedStr)) {
                Log.d("ANSWER: ", "Correct");
                currentScore++;
            }
            else {
                Log.d("ANSWER: ", "Incorrect");
            }
            return true; // Allow to continue to next question.
        }
        else {
            // No answer selected.
            Toast.makeText(getApplicationContext(), "Please Select An Answer",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
