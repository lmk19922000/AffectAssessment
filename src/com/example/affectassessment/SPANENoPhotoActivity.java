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
import android.widget.RadioGroup;

public class SPANENoPhotoActivity extends Activity implements OnClickListener {

	Button btnSave, btnNote, btnShare;

	RadioGroup radioGroupPositive, radioGroupNegative, radioGroupBad,
			radioGroupGood, radioGroupPleasant, radioGroupUnpleasant,
			radioGroupHappy, radioGroupSad, radioGroupAfraid, radioGroupJoyful,
			radioGroupAngry, radioGroupContented;

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
			// TODO: Save to DB
			int radioButtonID = radioGroupPositive.getCheckedRadioButtonId();
			View radioButton = radioGroupPositive.findViewById(radioButtonID);
			int idx = radioGroupPositive.indexOfChild(radioButton);
			Log.i("Button selected", String.valueOf(idx));
			break;
		case R.id.buttonShareSPANENoPhoto:
			// TODO: Open option to share by various means
			break;
		case R.id.buttonNoteSPANENoPhoto:
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
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						// TODO: Save user input
						// result.setText(userInput.getText());

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
