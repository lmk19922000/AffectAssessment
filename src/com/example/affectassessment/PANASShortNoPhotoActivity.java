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
import android.widget.SeekBar;

public class PANASShortNoPhotoActivity extends Activity implements
		OnClickListener {

	private static final String PANAS_SHORT_NO_PHOTO_DATA_FILENAME = "PANASShortNoPhotoData.txt";

	Button btnSave, btnNote, btnShare;

	SeekBar seekBarUpset, seekBarHostile, seekBarAlert, seekBarAshamed,
			seekBarInspired, seekBarNervous, seekBarDetermined,
			seekBarAttentive, seekBarActive, seekBarAfraid;

	String note = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_panas_short_no_photo);

		initializeCompnentView();
	}

	private void initializeCompnentView() {
		btnSave = (Button) findViewById(R.id.buttonSavePANASShortNoPhoto);
		btnNote = (Button) findViewById(R.id.buttonNotePANASShortNoPhoto);
		btnShare = (Button) findViewById(R.id.buttonSharePANASShortNoPhoto);

		btnSave.setOnClickListener(this);
		btnNote.setOnClickListener(this);
		btnShare.setOnClickListener(this);

		seekBarUpset = (SeekBar) findViewById(R.id.seekBarPANASLongUpset);
		seekBarHostile = (SeekBar) findViewById(R.id.seekBarPANASLongHostile);
		seekBarAlert = (SeekBar) findViewById(R.id.seekBarPANASLongAlert);
		seekBarAshamed = (SeekBar) findViewById(R.id.seekBarPANASLongAshamed);
		seekBarInspired = (SeekBar) findViewById(R.id.seekBarPANASLongInspired);
		seekBarNervous = (SeekBar) findViewById(R.id.seekBarPANASLongNervous);
		seekBarDetermined = (SeekBar) findViewById(R.id.seekBarPANASLongDetermined);
		seekBarAttentive = (SeekBar) findViewById(R.id.seekBarPANASLongAttentive);
		seekBarActive = (SeekBar) findViewById(R.id.seekBarPANASLongActive);
		seekBarAfraid = (SeekBar) findViewById(R.id.seekBarPANASLongAfraid);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSavePANASShortNoPhoto:
			saveData();
			break;
		case R.id.buttonSharePANASShortNoPhoto:
			// TODO: Open option to share by various means
			break;
		case R.id.buttonNotePANASShortNoPhoto:
			displayNoteDialog();
			break;
		}

	}

	@SuppressLint("SimpleDateFormat")
	private void saveData() {
		String currentDateAndTime, data;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		currentDateAndTime = sdf.format(new Date());

		data = currentDateAndTime + "," + "upset" + ","
				+ String.valueOf(seekBarUpset.getProgress() + 1) + ","
				+ "hostile" + ","
				+ String.valueOf(seekBarHostile.getProgress() + 1) + ","
				+ "alert" + ","
				+ String.valueOf(seekBarAlert.getProgress() + 1) + ","
				+ "ashamed" + ","
				+ String.valueOf(seekBarAshamed.getProgress() + 1) + ","
				+ "inspired" + ","
				+ String.valueOf(seekBarInspired.getProgress() + 1) + ","
				+ "nervous" + ","
				+ String.valueOf(seekBarNervous.getProgress() + 1) + ","
				+ "determined" + ","
				+ String.valueOf(seekBarDetermined.getProgress() + 1) + ","
				+ "attentive" + ","
				+ String.valueOf(seekBarAttentive.getProgress() + 1) + ","
				+ "active" + ","
				+ String.valueOf(seekBarActive.getProgress() + 1) + ","
				+ "afraid" + ","
				+ String.valueOf(seekBarAfraid.getProgress() + 1) + "," + note
				+ "\n";

		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					openFileOutput(PANAS_SHORT_NO_PHOTO_DATA_FILENAME,
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
