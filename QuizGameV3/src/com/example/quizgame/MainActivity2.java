	package com.example.quizgame;

	import java.util.Random;

	import android.app.Activity;
	import android.os.Bundle;
	import android.os.CountDownTimer;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.TextView;

	public class MainActivity2 extends Activity {
		//Declare Views
		public TextView tvScore;
		public TextView timer;
		public TextView tvQ;
		public EditText submittedAnswer;
		public Button btnA; 
		public String[] questionsArray;
		public String[] correctAnswersArray; 
		
		int totalQuestions = 0; 
		public int score=0;
		
		String currentAnswer = "a";
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_activity2);
			// initialize 
		 questionsArray = getResources().getStringArray(R.array.questionsFillin);  
		 correctAnswersArray = getResources().getStringArray(R.array.answersFillin);  

		 totalQuestions = questionsArray.length;
		  tvScore = (TextView) findViewById(R.id.textViewScore);
		  timer = (TextView) findViewById(R.id.textViewTime);
		  tvQ = (TextView) findViewById(R.id.textViewQ);
		  submittedAnswer = (EditText) findViewById(R.id.editText1);
		  
		  btnA = (Button) findViewById(R.id.ButtonA);

		 new CountDownTimer(60000, 1000) {
		     public void onTick(long millisUntilFinished) {
		         timer.setText("time: " + millisUntilFinished / 1000);
		     }
		     public void onFinish() {
		         timer.setText("Game Over!");
		         btnA.setEnabled(false);
		     }
		  }.start();

		  getRandomQuestion();

		  btnA.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						checkAnswer();
					}
				});
			
		}//end method on create

		protected void checkAnswer() {
			String answer = submittedAnswer.getText().toString().toLowerCase(); 
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
			submittedAnswer.setText("");
			currentAnswer= correctAnswersArray[randomInteger];
		}//end method
		
		
	}//end class
