package com.example.quizgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends Activity {

	
	private Button b1;
	private Button b2;
	private String[] wordsArray;
	private String[] definitionsArray;
int currentCard=0;
private TextView card;
private Button b3;
private Button b4; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity3);
		
		 wordsArray = getResources().getStringArray(R.array.words);  
		 definitionsArray = getResources().getStringArray(R.array.definitions);  

		  card = (TextView) findViewById(R.id.textView1);
 
b1 = (Button) findViewById(R.id.button1); 
 b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flipCard();
			}
		});
 b2 = (Button) findViewById(R.id.button2); 
 b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			nextCard();	
			}
		});
 b3 = (Button) findViewById(R.id.button3); 
 b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			importData();	
			}
		});
 b4 = (Button) findViewById(R.id.buttonGetFile); 
 b4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				newFile();
			}
		});
 
 
 nextCard();
	
	}
	private static final int CREATE_REQUEST_CODE = 40;
	private static final int OPEN_REQUEST_CODE = 41;
	private static final int SAVE_REQUEST_CODE = 42;

	public void newFile()
	{
//new file
//		Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//	    intent.addCategory(Intent.CATEGORY_OPENABLE);
//	    intent.setType("text/plain");
//	    intent.putExtra(Intent.EXTRA_TITLE, "newfile.txt");
//	    startActivityForResult(intent, CREATE_REQUEST_CODE);

		//openfile to save
//		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//		intent.addCategory(Intent.CATEGORY_OPENABLE);
//		intent.setType("text/plain");
//		startActivityForResult(intent, SAVE_REQUEST_CODE);
		
//open file for read
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("text/plain");
		startActivityForResult(intent, OPEN_REQUEST_CODE);
		
	}
	public void onActivityResult(int requestCode, int resultCode,
	        Intent resultData) {
//file created
//		if (resultCode == Activity.RESULT_OK)
//		{
//			if (requestCode == CREATE_REQUEST_CODE)
//			{	
//		        	if (resultData != null) {
//		        		card.setText("");
//		        	}
//			}
//
//	    	}

		
//save file
//		Uri currentUri = null;
//
//		if (resultCode == Activity.RESULT_OK)
//		{			
//			if (requestCode == CREATE_REQUEST_CODE)
//			{
//		        	if (resultData != null) {
//		            		card.setText("");
//		        	}
//			} else if (requestCode == SAVE_REQUEST_CODE) {
//			
//				if (resultData != null) {
//					currentUri = resultData.getData();
//					writeFileContent(currentUri);
//				}
//			}
//		}

	
		Uri currentUri = null;

		if (resultCode == Activity.RESULT_OK)
		{
			////file created	
			if (requestCode == CREATE_REQUEST_CODE)
			{
			        if (resultData != null) {
			            	card.setText(resultData.getDataString());
			        }
			} 
			//save file
			else if (requestCode == SAVE_REQUEST_CODE) {
				
				if (resultData != null) {
					currentUri = resultData.getData();
					writeFileContent(currentUri);
				}
			} 
			//read	
			else if (requestCode == OPEN_REQUEST_CODE)
			{
					
				if (resultData != null) {
			      		currentUri = resultData.getData();
			            
			               try {
			                  	String content = 
	                                     	    readFileContent(currentUri);
			                  	card.setText(content);
			               } catch (IOException e) {
			            		// Handle error here
			      		}
				}
			}
	     	}		
	}
private String readFileContent(Uri uri) throws IOException {
		
		InputStream inputStream = 
             		getContentResolver().openInputStream(uri);
		BufferedReader reader = 
			new BufferedReader(new InputStreamReader(
	            		inputStream));
		StringBuilder stringBuilder = new StringBuilder();
		String currentline;
		while ((currentline = reader.readLine()) != null) {
		        stringBuilder.append(currentline + "\n");
		}
		inputStream.close();
		return stringBuilder.toString();
	}

	private void writeFileContent(Uri uri)
	{
		try{		
			ParcelFileDescriptor pfd = 
				this.getContentResolver().
                		openFileDescriptor(uri, "w");
			
			FileOutputStream fileOutputStream =
                           new FileOutputStream(pfd.getFileDescriptor());
			
			String textContent = 
				card.getText().toString();
			
			fileOutputStream.write(textContent.getBytes());
			
			fileOutputStream.close();
			pfd.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void importData() {
		// TODO Auto-generated method stub
	String path = Environment.getExternalStorageDirectory().getAbsoluteFile().toString();
	String fileName = path+"/quizgame.txt";
	alert(fileName);
	
	File dataFile = new File(fileName);
	if(dataFile.exists())
	{
		String fileString = readFile(dataFile);
		String[] lines = fileString.split("\n");
		definitionsArray = new String[lines.length]; 
		wordsArray = new String[lines.length];  
		    
		for (int i = 0; i < lines.length; i++) {
			String[] definitionWord = lines[i].split(",");
			definitionsArray[i]=definitionWord[0];
			wordsArray[i]=definitionWord[1];
		}
		
	}
		
		
	}

	private String readFile(File file) {
		// TODO Auto-generated method stub
        
		StringBuilder text = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		    }
		    br.close();
		}
		catch (IOException e) {
		    //You'll need to add proper error handling here
		}
		return text.toString();

	}

	private void alert(String text) {
		// TODO Auto-generated method stub
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();	
	}

	protected void nextCard() {
		// TODO Auto-generated method stub
		currentCard++;
		if(currentCard>=definitionsArray.length)
			currentCard=0;
		
		card.setText(definitionsArray[currentCard]);
		
	}

	protected void flipCard() {
		// TODO Auto-generated method stub
		card.setText(wordsArray[currentCard]);
	}
	
}
