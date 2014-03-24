package com.example.affectassessment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatisticsActivity extends Activity implements OnClickListener{
	
	Button btnAffectButton, btnSPANEWithPhoto, btnSPANENoPhoto, btnPANASShortNoPhoto, btnPANASLongWithPhoto, btnPAM;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_statistics);
		
		btnAffectButton = (Button) findViewById(R.id.buttonAffectButtonStat);
		btnSPANEWithPhoto = (Button) findViewById(R.id.buttonSPANEWithPhotoStat);
		btnSPANENoPhoto = (Button) findViewById(R.id.buttonSPANENoPhotoStat);
		btnPANASShortNoPhoto = (Button) findViewById(R.id.buttonPANASShortNoPhotoStat);
		btnPANASLongWithPhoto = (Button) findViewById(R.id.buttonPANASLongWithPhotoStat);
		btnPAM = (Button) findViewById(R.id.buttonPAMStat);
		
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
		case R.id.buttonAffectButtonStat:
			myIntent = new Intent(getApplicationContext(), AffectButtonStatActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonSPANEWithPhotoStat:
			myIntent = new Intent(getApplicationContext(), SPANEWithPhotoStatActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonPANASShortNoPhotoStat:
			myIntent = new Intent(getApplicationContext(), PANASShortNoPhotoStatActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonSPANENoPhotoStat:
			myIntent = (new SPANENoPhotoStatActivity()).execute(getApplicationContext());
			startActivity(myIntent);
			break;
			
		case R.id.buttonPANASLongWithPhotoStat:
			myIntent = (new PANASLongWithPhotoStatActivity()).execute(getApplicationContext());
			startActivity(myIntent);
			break;
		case R.id.buttonPAMStat:
			myIntent = new Intent(getApplicationContext(), PAMStatActivity.class);
			startActivity(myIntent);
			break;
		default:
			break;
		}
	}
}
