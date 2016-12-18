package com.example.quizgame;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity1 extends Activity {
	//Declare Views
	public TextView tvScore;
	public TextView timer;
	public TextView tvQ;
	public Button btnA; 
	public Button btnB;
	public Button btnC;
	public Button btnD; 
	public String[] questionsArray;
	public String[] answersArrayA;
	public String[] answersArrayB;
	public String[]  answersArrayC;
	public String[] answersArrayD;
	public String[] correctAnswersArray; 
	
	int totalQuestions = 0; 
	public int score=0;
	
	String currentAnswer = "a";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// initialize 
	 questionsArray = getResources().getStringArray(R.array.questions);  
	 answersArrayA = getResources().getStringArray(R.array.answerA);  
	 answersArrayB = getResources().getStringArray(R.array.answerB);  
	 answersArrayC = getResources().getStringArray(R.array.answerC);  
	 answersArrayD = getResources().getStringArray(R.array.answerD);  
	 correctAnswersArray = getResources().getStringArray(R.array.answers);  

	 totalQuestions = questionsArray.length;
	  tvScore = (TextView) findViewById(R.id.textViewScore);
	  timer = (TextView) findViewById(R.id.textViewTime);
	  tvQ = (TextView) findViewById(R.id.textViewQ);
	  btnA = (Button) findViewById(R.id.ButtonA);
	  btnB = (Button) findViewById(R.id.ButtonB);
	  btnC = (Button) findViewById(R.id.ButtonC);
	  btnD = (Button) findViewById(R.id.ButtonD);

	 new CountDownTimer(30000, 1000) {
	     public void onTick(long millisUntilFinished) {
	         timer.setText("time: " + millisUntilFinished / 1000);
	     }
	     public void onFinish() {
	         timer.setText("Game Over!");
	         btnA.setEnabled(false);
	         btnB.setEnabled(false);
	         btnC.setEnabled(false);
	         btnD.setEnabled(false);
	     }
	  }.start();

	  getRandomQuestion();

	  btnA.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					checkAnswer("a");
				}
			});
 	 btnB.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					checkAnswer("b");
				}
			});
	 btnC.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					checkAnswer("c");				}
			});

	 btnD.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					checkAnswer("d");				}
			});

		
	}//end method on create

	protected void checkAnswer(String answer) {
		if(currentAnswer.equals(answer))
		{
			score++;
			tvScore.setText("Score: "+score);
		}
		getRandomQuestion();
	}//end method
	public void getRandomQuestion() {
		Random r = new Random();
		int randomInteger = r.nextInt(totalQuestions - 0) + 0;	
		tvQ.setText(questionsArray[randomInteger]);
		btnA.setText(answersArrayA[randomInteger]);
		btnB.setText(answersArrayB[randomInteger]);
		btnC.setText(answersArrayC[randomInteger]);
		btnD.setText(answersArrayD[randomInteger]);
		currentAnswer= correctAnswersArray[randomInteger];
	}//end method
	
	
}//end class
