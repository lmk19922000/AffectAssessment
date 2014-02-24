package com.example.affectassessment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Button btnAffectButton, btnSPANE, btnSPANENoPhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAffectButton = (Button) findViewById(R.id.buttonAffectButton);
		btnSPANE = (Button) findViewById(R.id.buttonSPANE);
		btnSPANENoPhoto = (Button) findViewById(R.id.buttonSPANENoPhoto);
		
		btnAffectButton.setOnClickListener(this);
		btnSPANE.setOnClickListener(this);
		btnSPANENoPhoto.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent myIntent;
		
		switch(v.getId()){
		case R.id.buttonAffectButton:
			myIntent = new Intent(MainActivity.this, AffectButtonActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonSPANE:
			myIntent = new Intent(MainActivity.this, SPANEActivity.class);
			startActivity(myIntent);
			break;
		case R.id.buttonSPANENoPhoto:
			myIntent = new Intent(MainActivity.this, SPANENoPhotoActivity.class);
			startActivity(myIntent);
			break;
		}
		
	}

	

}
