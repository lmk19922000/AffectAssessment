package com.example.affectassessment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Button btnAffectButton, btnSPANEWithPhoto, btnSPANENoPhoto, btnPANASShortNoPhoto, btnPANASLongWithPhoto, btnPAM;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAffectButton = (Button) findViewById(R.id.buttonAffectButton);
		btnSPANEWithPhoto = (Button) findViewById(R.id.buttonSPANEWithPhoto);
		btnSPANENoPhoto = (Button) findViewById(R.id.buttonSPANENoPhoto);
		btnPANASShortNoPhoto = (Button) findViewById(R.id.buttonPANASShortNoPhoto);
		btnPANASLongWithPhoto = (Button) findViewById(R.id.buttonPANASLongWithPhoto);
		btnPAM = (Button) findViewById(R.id.buttonPAM);
		
		btnAffectButton.setOnClickListener(this);
		btnSPANEWithPhoto.setOnClickListener(this);
		btnSPANENoPhoto.setOnClickListener(this);
		btnPANASShortNoPhoto.setOnClickListener(this);
		btnPANASLongWithPhoto.setOnClickListener(this);
		btnPAM.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent myIntent;
		
		switch(v.getId()){
		case R.id.buttonAffectButton:
			myIntent = new Intent(MainActivity.this, AffectButtonActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonSPANEWithPhoto:
			myIntent = new Intent(MainActivity.this, SPANEWithPhotoActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonSPANENoPhoto:
			myIntent = new Intent(MainActivity.this, SPANENoPhotoActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonPANASShortNoPhoto:
			myIntent = new Intent(MainActivity.this, PANASShortNoPhotoActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonPANASLongWithPhoto:
			myIntent = new Intent(MainActivity.this, PANASLongWithPhotoActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonPAM:
			myIntent = new Intent(MainActivity.this, PAMActivity.class);
			startActivity(myIntent);
			break;
		}
		
	}

	

}
