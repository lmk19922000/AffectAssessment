package com.example.affectassessment;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SPANENoPhotoActivity extends Activity implements OnClickListener {

	private static final String SPANE_NO_PHOTO_DATA_FILENAME = "SPANENoPhotoData.txt";
	
	Button btnSave, btnNote, btnShare;

	RadioGroup radioGroupPositive, radioGroupNegative, radioGroupBad,
			radioGroupGood, radioGroupPleasant, radioGroupUnpleasant,
			radioGroupHappy, radioGroupSad, radioGroupAfraid, radioGroupJoyful,
			radioGroupAngry, radioGroupContented;
	
	String note = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spane_no_photo);

		initializeCompnentView();
	}

	private void initializeCompnentView() {
		btnSave = (Button) findViewById(R.id.buttonSaveSPANENoPhoto);
		btnNote = (Button) findViewById(R.id.buttonNoteSPANENoPhoto);
		btnShare = (Button) findViewById(R.id.buttonShareSPANENoPhoto);

		btnSave.setOnClickListener(this);
		btnNote.setOnClickListener(this);
		btnShare.setOnClickListener(this);

		radioGroupPositive = (RadioGroup) findViewById(R.id.radioGroupPositive);
		radioGroupNegative = (RadioGroup) findViewById(R.id.radioGroupNegative);
		radioGroupGood = (RadioGroup) findViewById(R.id.radioGroupGood);
		radioGroupBad = (RadioGroup) findViewById(R.id.radioGroupBad);
		radioGroupPleasant = (RadioGroup) findViewById(R.id.radioGroupPleasant);
		radioGroupUnpleasant = (RadioGroup) findViewById(R.id.radioGroupUnpleasant);
		radioGroupSad = (RadioGroup) findViewById(R.id.radioGroupSad);
		radioGroupHappy = (RadioGroup) findViewById(R.id.radioGroupHappy);
		radioGroupAfraid = (RadioGroup) findViewById(R.id.radioGroupAfraid);
		radioGroupJoyful = (RadioGroup) findViewById(R.id.radioGroupJoyful);
		radioGroupAngry = (RadioGroup) findViewById(R.id.radioGroupAngry);
		radioGroupContented = (RadioGroup) findViewById(R.id.radioGroupContented);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSaveSPANENoPhoto:
			saveData();
			
			break;
		case R.id.buttonShareSPANENoPhoto:
			// TODO: Open option to share by various means
			break;
		case R.id.buttonNoteSPANENoPhoto:
			displayNoteDialog();
			break;
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void saveData() {
		int radioButtonID, idx;
		View radioButton;
		String data, currentDateAndTime;
		
		radioButtonID = radioGroupPositive.getCheckedRadioButtonId();
		radioButton = radioGroupPositive.findViewById(radioButtonID);
		idx = radioGroupPositive.indexOfChild(radioButton);
		data = "positive" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupNegative.getCheckedRadioButtonId();
		radioButton = radioGroupNegative.findViewById(radioButtonID);
		idx = radioGroupNegative.indexOfChild(radioButton);
		data = data + "negative" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupGood.getCheckedRadioButtonId();
		radioButton = radioGroupGood.findViewById(radioButtonID);
		idx = radioGroupGood.indexOfChild(radioButton);
		data = data + "good" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupBad.getCheckedRadioButtonId();
		radioButton = radioGroupBad.findViewById(radioButtonID);
		idx = radioGroupBad.indexOfChild(radioButton);
		data = data + "bad" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupPleasant.getCheckedRadioButtonId();
		radioButton = radioGroupPleasant.findViewById(radioButtonID);
		idx = radioGroupPleasant.indexOfChild(radioButton);
		data = data + "pleasant" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupUnpleasant.getCheckedRadioButtonId();
		radioButton = radioGroupUnpleasant.findViewById(radioButtonID);
		idx = radioGroupUnpleasant.indexOfChild(radioButton);
		data = data + "unpleasant" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupHappy.getCheckedRadioButtonId();
		radioButton = radioGroupHappy.findViewById(radioButtonID);
		idx = radioGroupHappy.indexOfChild(radioButton);
		data = data + "happy" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupSad.getCheckedRadioButtonId();
		radioButton = radioGroupSad.findViewById(radioButtonID);
		idx = radioGroupSad.indexOfChild(radioButton);
		data = data + "sad" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupAfraid.getCheckedRadioButtonId();
		radioButton = radioGroupAfraid.findViewById(radioButtonID);
		idx = radioGroupAfraid.indexOfChild(radioButton);
		data = data + "afraid" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupJoyful.getCheckedRadioButtonId();
		radioButton = radioGroupJoyful.findViewById(radioButtonID);
		idx = radioGroupJoyful.indexOfChild(radioButton);
		data = data + "joyful" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupAngry.getCheckedRadioButtonId();
		radioButton = radioGroupAngry.findViewById(radioButtonID);
		idx = radioGroupAngry.indexOfChild(radioButton);
		data = data + "angry" + "," + String.valueOf(idx) + ",";
		
		radioButtonID = radioGroupContented.getCheckedRadioButtonId();
		radioButton = radioGroupContented.findViewById(radioButtonID);
		idx = radioGroupContented.indexOfChild(radioButton);
		data = data + "contented" + "," + String.valueOf(idx) + ",";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		currentDateAndTime = sdf.format(new Date());
		
		data = currentDateAndTime + "," + data + note + "\n";
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					openFileOutput(SPANE_NO_PHOTO_DATA_FILENAME,
							Context.MODE_APPEND));
			outputStreamWriter.append(data);
			outputStreamWriter.close();
		} catch (IOException e) {

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
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						note = userInput.getText().toString();

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
}
