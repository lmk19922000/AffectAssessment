package com.example.affectassessment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SPANEWithPhotoActivity extends Activity implements OnClickListener, OnCheckedChangeListener{
	
	Button btnSave, btnNote, btnShare;
	
	RadioGroup radioGroupPositive;
	
	ImageView imvPositive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_spane_with_photo);
		
		initializeCompnentView();
	}
	
	private void initializeCompnentView() {
		btnSave = (Button) findViewById(R.id.buttonSaveSPANEWithPhoto);
		btnNote = (Button) findViewById(R.id.buttonNoteSPANEWithPhoto);
		btnShare = (Button) findViewById(R.id.buttonShareSPANEWithPhoto);
		
		btnSave.setOnClickListener(this);
		btnNote.setOnClickListener(this);
		btnShare.setOnClickListener(this);
		
		radioGroupPositive = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoPositive);
		
		radioGroupPositive.setOnCheckedChangeListener(this);
		
		imvPositive = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoPositive);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.buttonSaveSPANEWithPhoto:
			// TODO: Save to DB
			int radioButtonID = radioGroupPositive.getCheckedRadioButtonId();
			View radioButton = radioGroupPositive.findViewById(radioButtonID);
			int idx = radioGroupPositive.indexOfChild(radioButton);
			Log.i("Button selected", String.valueOf(idx));
			break;
		case R.id.buttonShareSPANEWithPhoto:
			// TODO: Open option to share by various means
			break;
		case R.id.buttonNoteSPANEWithPhoto:
			displayNoteDialog();
			break;
		}
	}

	private void displayNoteDialog() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.prompt, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("OK",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
			    	
			    // TODO: Save user input
				//result.setText(userInput.getText());
			    	
			    }
			  })
			.setNegativeButton("Cancel",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			    }
			  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();	
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int id) {
		int radioButtonID, idx;
		
		switch(group.getId()){
		case R.id.radioGroupSPANEWithPhotoPositive:
			radioButtonID = radioGroupPositive.getCheckedRadioButtonId();
			View radioButton = radioGroupPositive.findViewById(radioButtonID);
			idx = radioGroupPositive.indexOfChild(radioButton);
			
			switch(idx){
			case 0:
				imvPositive.setImageResource(R.drawable.spane_positive1);
				break;
			case 1:
				imvPositive.setImageResource(R.drawable.spane_positive2);
				break;
			case 2:
				imvPositive.setImageResource(R.drawable.spane_positive3);
				break;
			case 3:
				imvPositive.setImageResource(R.drawable.spane_positive4);
				break;
			case 4:
				imvPositive.setImageResource(R.drawable.spane_positive5);
				break;
			}
			
			
			break;
		}
		
		
	}
}
