package com.nayan.braintraine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * 1.Craete A  Button onClick Start Method
 * 2.creating Random number Seeting SumTextView
 * 3.seting what the answer should be ,making an array that can hold all the potential answers
 * 4.ones the ans & no. are displayed we have to add appr code so that ones the user clicks the button saying crct or wrong
 * 5.focus on the score
 * 6.Reseting the game ones agin onse somebody completes one question
 * 7.clearing the array one answered corectly or after one whole process
 * 8.seetting Timer
 * 9.setting PlayAgainButton
 * 8.Setting go Button
 * **/
public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorectAnswer;

    TextView resultTextView;
    TextView scoreTextView;

    int score = 0; //to keep track of score
    int numberOfQuestions = 0;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    TextView sumTextView;
    TextView timerTextView;

    ConstraintLayout gameLayout;



    //for playing Again
    public void playAgain(View view){
        //ones the game is finished we have to make play again button visible
        //next we have to set score ,numberOfQuestions back to 0
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30S");//then we have to recreate the timer

        //some sort of thing not happen as we are caling so get that here
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        //we want to reset the questions even before timer
        newQuestion();

        //one thing after rn we missed is play again is not become invisible after prssing play again
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100 ,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000 )+ "s");  //long to string conversion to display seconds

            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                //ones the game is finished we have to make play again button visible
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();
    }


    //creating new function for saying ans clickedright or wrong
    public  void chooseAnswer(View view){
        if (Integer.toString(locationOfCorectAnswer).equals(view.getTag().toString())) {
            //1st thing what we have to know is which button user has pressed
            //inorder we have to acess view
            //to know which btn pressed(get the tag annd turn it to string)
            //2nd step is to now how we know the user has pressed corect ans
            //for that we perform if along with equals operation locationOfCoreectAnswer and with tag

            resultTextView.setText("CORRECT!");

            //if someone got correct answer
            score++;
        }else {
            resultTextView.setText("WRONG!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();

    }

    public void start(View view){
       //seting invisibility
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain( findViewById(R.id.timerTextView));


    }

    public void newQuestion(){

        //Detrmining what should be added together in sumTextView
        /**Generating Random Number**/
        Random rand = new Random();

        int a =rand.nextInt(21);  //range from 0 to 20
        int b =rand.nextInt(21);

        //displaying in the sumTextView
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        //sting array for displayimg answers and some random numbers

        locationOfCorectAnswer = rand.nextInt(4); //we need to pic from 0 to 3 since total 4

        answers.clear();//clearing the array after one whole proess

        for (int i =0 ; i<4 ; i++){ //it should run 4 time mean while the numbers will be added to array
            //in this we have to specify which button should display correct answer and which of those not so we have tu use random numbers gain
            //before adding amswers we have to check whether i = loca
            if (i == locationOfCorectAnswer){
                answers.add(a + b);
            }else{
                //if it is not corect ans we have to add incorect ans
                int wrongAnswer = rand.nextInt(41);//wehave seen if it gives some random crct ans so inorder use while

                while (wrongAnswer == a + b){//if wrong ans is sam as random pick a new one

                    wrongAnswer = rand.nextInt(41);

                }

                answers.add(wrongAnswer);
                //after making sure we have a unique number next we have to show this inside of the numbers
            }

        }
        //after seting buton next step isto display buttons
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //acesing sumTextView
        sumTextView = findViewById(R.id.sumTextView);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        //creating button after making sure about ans and wrong answers display it in $buutons
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        playAgainButton = findViewById(R.id.playAgainButton);

        //Acesing the button and setting visibility to invisible
        goButton = findViewById(R.id.goButton);

        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);

        timerTextView = findViewById(R.id.timerTextView);

        gameLayout.setVisibility(View.INVISIBLE);








    }
}
