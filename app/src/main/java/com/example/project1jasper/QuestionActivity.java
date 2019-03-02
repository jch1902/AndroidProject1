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
                if(currentQuestionNumber < maxScore && answerCheck()){
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    // TO-DO: Set currentQuestion to the next question (because we want to process what is next).
                    // You can set the reference from the questionList.
                    // Use currentQuestionNumber as the index (remember to increment this at the end so that we can fetch the next question index).
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    int currentQuestionNumberArray = currentQuestionNumber;
                    currentQuestionNumber++;
                    currentNumber = questionList.get(currentQuestionNumberArray);
                    setQuestionView(currentNumber);
                }else {
                    Intent intent = new Intent(QuestionActivity.this,
                            com.example.project1jasper.ResultActivity.class);
                    intent.putExtra("currentScore", currentScore);
                    intent.putExtra("maxScore", maxScore);
                    startActivity(intent);
                    // HINT: We want to pass in some extra values for the results class to use. So use something like:
                    //intent.putExtra(...);
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
        question1.setQuestion("In 'The Prince', what did Machiavelli believe?");
        question1.setChoiceA("It is better to be feared than loved as a ruler");
        question1.setChoiceB("Women are incapable of ruling");
        question1.setChoiceC("It is important to take everyone's needs into consideration");
        question1.setChoiceD("A prince should not keep prisoners and should execute them");
        question1.setCorrectAnswer("It is better to be feared than loved as a ruler");
        questionList.add(question1);

    //Second question
        QuestionNumber question2 = new QuestionNumber();
        question2.setQuestion("Where was Napoleon Bonaparte exiled to after his defeat in 1812?");
        question2.setChoiceA("Paris");
        question2.setChoiceB("London");
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
        question4.setQuestion("Which one of the following was most likely an effect of the second Industrial Revolution?");
        question4.setChoiceA("Development of trains");
        question4.setChoiceB("Urbanization as more people flocked to the cities to be closer to factories");
        question4.setChoiceC("Crop failures as farmers went to work in factories");
        question4.setChoiceD("Agricultural Revolution");
        question4.setCorrectAnswer("Urbanization as more people flocked to the cities to be closer to factories");
        questionList.add(question4);
    //Fifth question
        QuestionNumber question5 = new QuestionNumber();
        question5.setQuestion("Which of the following was not used by Adolf Hitler to establish his totalitarian regime?");
        question5.setChoiceA("Mass purges of military officers, intellectuals, lawyers, etc. to get rid of any opposition to power");
        question5.setChoiceB("Regulating and promoting state-controlled mass leisure");
        question5.setChoiceC("Hitler youth used to indoctrinate the youth into the beliefs and goals of the regime");
        question5.setChoiceD("Use of propaganda to gain more support for the Nazi party's goals and beliefs");
        question5.setCorrectAnswer("Mass purges of military officers, intellectuals, lawyers, etc. to get rid of any opposition to power");
        questionList.add(question5);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set your currentQuestion to point to your first question here (uncomment out the code below).
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


        //this.currentQuestion = /*Your first question*/;
        this.currentNumber = question1;
        // Set the current, score, and total question size.
        this.currentQuestionNumber = 1;
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
        String scoreString = Integer.toString(currentScore);
        String currentQuestionNumberString = Integer.toString(currentQuestionNumber);
        scoreView.setText(scoreString);
        askQuestion.setText(quizQuestion.getQuestion());
        answerA.setText(quizQuestion.getChoiceA());
        answerB.setText(quizQuestion.getChoiceB());
        answerC.setText(quizQuestion.getChoiceC());
        answerD.setText(quizQuestion.getChoiceD());
        questionNumber.setText(currentQuestionNumberString);

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
