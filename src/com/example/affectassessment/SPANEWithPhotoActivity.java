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

public class SPANEWithPhotoActivity extends Activity implements
		OnClickListener, OnCheckedChangeListener {

	Button btnSave, btnNote, btnShare;

	RadioGroup radioGroupPositive, radioGroupNegative, radioGroupGood,
			radioGroupBad, radioGroupPleasant, radioGroupUnpleasant,
			radioGroupHappy, radioGroupSad, radioGroupAfraid, radioGroupJoyful,
			radioGroupAngry, radioGroupContented;

	ImageView imvPositive, imvNegative, imvGood, imvBad, imvPleasant, imvUnpleasant, imvHappy, imvSad, imvAfraid, imvJoyful, imvAngry, imvContented;

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
		radioGroupNegative = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoNegative);
		radioGroupGood = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoGood);
		radioGroupBad = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoBad);
		radioGroupPleasant = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoPleasant);
		radioGroupUnpleasant = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoUnpleasant);
		radioGroupHappy = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoHappy);
		radioGroupSad = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoSad);
		radioGroupAfraid = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoAfraid);
		radioGroupJoyful = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoJoyful);
		radioGroupAngry = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoAngry);
		radioGroupContented = (RadioGroup) findViewById(R.id.radioGroupSPANEWithPhotoContented);

		radioGroupPositive.setOnCheckedChangeListener(this);
		radioGroupNegative.setOnCheckedChangeListener(this);
		radioGroupGood.setOnCheckedChangeListener(this);
		radioGroupBad.setOnCheckedChangeListener(this);
		radioGroupPleasant.setOnCheckedChangeListener(this);
		radioGroupUnpleasant.setOnCheckedChangeListener(this);
		radioGroupHappy.setOnCheckedChangeListener(this);
		radioGroupSad.setOnCheckedChangeListener(this);
		radioGroupAfraid.setOnCheckedChangeListener(this);
		radioGroupJoyful.setOnCheckedChangeListener(this);
		radioGroupAngry.setOnCheckedChangeListener(this);
		radioGroupContented.setOnCheckedChangeListener(this);

		imvPositive = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoPositive);
		imvNegative = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoNegative);
		imvGood = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoGood);
		imvBad = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoBad);
		imvPleasant = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoPleasant);
		imvUnpleasant = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoUnpleasant);
		imvHappy = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoHappy);
		imvSad = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoSad);
		imvAfraid = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoAfraid);
		imvJoyful = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoJoyful);
		imvAngry = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoAngry);
		imvContented = (ImageView) findViewById(R.id.imageViewSPANEWithPhotoContented);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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

	@Override
	public void onCheckedChanged(RadioGroup group, int id) {
		int radioButtonID, idx;
		View radioButton;

		switch (group.getId()) {
		case R.id.radioGroupSPANEWithPhotoPositive:
			radioButtonID = radioGroupPositive.getCheckedRadioButtonId();
			radioButton = radioGroupPositive.findViewById(radioButtonID);
			idx = radioGroupPositive.indexOfChild(radioButton);

			switch (idx) {
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
			
		case R.id.radioGroupSPANEWithPhotoNegative:
			radioButtonID = radioGroupNegative.getCheckedRadioButtonId();
			radioButton = radioGroupNegative.findViewById(radioButtonID);
			idx = radioGroupNegative.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvNegative.setImageResource(R.drawable.spane_negative1);
				break;
			case 1:
				imvNegative.setImageResource(R.drawable.spane_negative2);
				break;
			case 2:
				imvNegative.setImageResource(R.drawable.spane_negative3);
				break;
			case 3:
				imvNegative.setImageResource(R.drawable.spane_negative4);
				break;
			case 4:
				imvNegative.setImageResource(R.drawable.spane_negative5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoGood:
			radioButtonID = radioGroupGood.getCheckedRadioButtonId();
			radioButton = radioGroupGood.findViewById(radioButtonID);
			idx = radioGroupGood.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvGood.setImageResource(R.drawable.spane_good1);
				break;
			case 1:
				imvGood.setImageResource(R.drawable.spane_good2);
				break;
			case 2:
				imvGood.setImageResource(R.drawable.spane_good3);
				break;
			case 3:
				imvGood.setImageResource(R.drawable.spane_good4);
				break;
			case 4:
				imvGood.setImageResource(R.drawable.spane_good5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoBad:
			radioButtonID = radioGroupBad.getCheckedRadioButtonId();
			radioButton = radioGroupBad.findViewById(radioButtonID);
			idx = radioGroupBad.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvBad.setImageResource(R.drawable.spane_bad1);
				break;
			case 1:
				imvBad.setImageResource(R.drawable.spane_bad2);
				break;
			case 2:
				imvBad.setImageResource(R.drawable.spane_bad3);
				break;
			case 3:
				imvBad.setImageResource(R.drawable.spane_bad4);
				break;
			case 4:
				imvBad.setImageResource(R.drawable.spane_bad5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoPleasant:
			radioButtonID = radioGroupPleasant.getCheckedRadioButtonId();
			radioButton = radioGroupPleasant.findViewById(radioButtonID);
			idx = radioGroupPleasant.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvPleasant.setImageResource(R.drawable.spane_pleasant1);
				break;
			case 1:
				imvPleasant.setImageResource(R.drawable.spane_pleasant2);
				break;
			case 2:
				imvPleasant.setImageResource(R.drawable.spane_pleasant3);
				break;
			case 3:
				imvPleasant.setImageResource(R.drawable.spane_pleasant4);
				break;
			case 4:
				imvPleasant.setImageResource(R.drawable.spane_pleasant5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoUnpleasant:
			radioButtonID = radioGroupUnpleasant.getCheckedRadioButtonId();
			radioButton = radioGroupUnpleasant.findViewById(radioButtonID);
			idx = radioGroupUnpleasant.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvUnpleasant.setImageResource(R.drawable.spane_unpleasant1);
				break;
			case 1:
				imvUnpleasant.setImageResource(R.drawable.spane_unpleasant2);
				break;
			case 2:
				imvUnpleasant.setImageResource(R.drawable.spane_unpleasant3);
				break;
			case 3:
				imvUnpleasant.setImageResource(R.drawable.spane_unpleasant4);
				break;
			case 4:
				imvUnpleasant.setImageResource(R.drawable.spane_unpleasant5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoHappy:
			radioButtonID = radioGroupHappy.getCheckedRadioButtonId();
			radioButton = radioGroupHappy.findViewById(radioButtonID);
			idx = radioGroupHappy.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvHappy.setImageResource(R.drawable.spane_happy1);
				break;
			case 1:
				imvHappy.setImageResource(R.drawable.spane_happy2);
				break;
			case 2:
				imvHappy.setImageResource(R.drawable.spane_happy3);
				break;
			case 3:
				imvHappy.setImageResource(R.drawable.spane_happy4);
				break;
			case 4:
				imvHappy.setImageResource(R.drawable.spane_happy5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoSad:
			radioButtonID = radioGroupSad.getCheckedRadioButtonId();
			radioButton = radioGroupSad.findViewById(radioButtonID);
			idx = radioGroupSad.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvSad.setImageResource(R.drawable.spane_sad1);
				break;
			case 1:
				imvSad.setImageResource(R.drawable.spane_sad2);
				break;
			case 2:
				imvSad.setImageResource(R.drawable.spane_sad3);
				break;
			case 3:
				imvSad.setImageResource(R.drawable.spane_sad4);
				break;
			case 4:
				imvSad.setImageResource(R.drawable.spane_sad5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoAfraid:
			radioButtonID = radioGroupAfraid.getCheckedRadioButtonId();
			radioButton = radioGroupAfraid.findViewById(radioButtonID);
			idx = radioGroupAfraid.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvAfraid.setImageResource(R.drawable.spane_afraid1);
				break;
			case 1:
				imvAfraid.setImageResource(R.drawable.spane_afraid2);
				break;
			case 2:
				imvAfraid.setImageResource(R.drawable.spane_afraid3);
				break;
			case 3:
				imvAfraid.setImageResource(R.drawable.spane_afraid4);
				break;
			case 4:
				imvAfraid.setImageResource(R.drawable.spane_afraid5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoJoyful:
			radioButtonID = radioGroupJoyful.getCheckedRadioButtonId();
			radioButton = radioGroupJoyful.findViewById(radioButtonID);
			idx = radioGroupJoyful.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvJoyful.setImageResource(R.drawable.spane_joyful1);
				break;
			case 1:
				imvJoyful.setImageResource(R.drawable.spane_joyful2);
				break;
			case 2:
				imvJoyful.setImageResource(R.drawable.spane_joyful3);
				break;
			case 3:
				imvJoyful.setImageResource(R.drawable.spane_joyful4);
				break;
			case 4:
				imvJoyful.setImageResource(R.drawable.spane_joyful5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoAngry:
			radioButtonID = radioGroupAngry.getCheckedRadioButtonId();
			radioButton = radioGroupAngry.findViewById(radioButtonID);
			idx = radioGroupAngry.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvAngry.setImageResource(R.drawable.spane_angry1);
				break;
			case 1:
				imvAngry.setImageResource(R.drawable.spane_angry2);
				break;
			case 2:
				imvAngry.setImageResource(R.drawable.spane_angry3);
				break;
			case 3:
				imvAngry.setImageResource(R.drawable.spane_angry4);
				break;
			case 4:
				imvAngry.setImageResource(R.drawable.spane_angry5);
				break;
			}
			break;
			
		case R.id.radioGroupSPANEWithPhotoContented:
			radioButtonID = radioGroupContented.getCheckedRadioButtonId();
			radioButton = radioGroupContented.findViewById(radioButtonID);
			idx = radioGroupContented.indexOfChild(radioButton);

			switch (idx) {
			case 0:
				imvContented.setImageResource(R.drawable.spane_contented1);
				break;
			case 1:
				imvContented.setImageResource(R.drawable.spane_contented2);
				break;
			case 2:
				imvContented.setImageResource(R.drawable.spane_contented3);
				break;
			case 3:
				imvContented.setImageResource(R.drawable.spane_contented4);
				break;
			case 4:
				imvContented.setImageResource(R.drawable.spane_contented5);
				break;
			}
			break;

		}
	}
}