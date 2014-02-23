package com.example.affectassessment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Button btnAffectButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAffectButton = (Button) findViewById(R.id.buttonAffectButton);
		
		btnAffectButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.buttonAffectButton:
			Intent myIntent = new Intent(MainActivity.this, AffectButtonActivity.class);
			startActivity(myIntent);
			break;
		}
		
	}

	

}
