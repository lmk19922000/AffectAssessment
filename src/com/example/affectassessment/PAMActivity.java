package com.example.affectassessment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class PAMActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	Button btnSave, btnNote, btnShare, btnLoadMore;

	GridView gv;

	Integer[] imageIDs = { R.drawable.pam_afraid1, R.drawable.pam_tense1,
			R.drawable.pam_excited1, R.drawable.pam_delighted1,
			R.drawable.pam_frustrated1, R.drawable.pam_angry1,
			R.drawable.pam_happy1, R.drawable.pam_glad1,
			R.drawable.pam_miserable1, R.drawable.pam_sad1,
			R.drawable.pam_calm1, R.drawable.pam_satisfied1,
			R.drawable.pam_gloomy1, R.drawable.pam_tired1,
			R.drawable.pam_sleepy1, R.drawable.pam_serene1, };

	Point pointToGetSize;

	int currentPos;
	ImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_pam);

		initializeCompnentView();
	}

	@SuppressLint("NewApi")
	private void initializeCompnentView() {
		btnSave = (Button) findViewById(R.id.buttonSavePAM);
		btnNote = (Button) findViewById(R.id.buttonNotePAM);
		btnShare = (Button) findViewById(R.id.buttonSharePAM);
		btnLoadMore = (Button) findViewById(R.id.buttonLoadMorePAM);

		btnSave.setOnClickListener(this);
		btnNote.setOnClickListener(this);
		btnShare.setOnClickListener(this);
		btnLoadMore.setOnClickListener(this);

		gv = (GridView) findViewById(R.id.gridViewPAM);

		adapter = new ImageAdapter(this);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(this);

		Display display = getWindowManager().getDefaultDisplay();
		pointToGetSize = new Point();
		display.getSize(pointToGetSize);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSavePAM:
			// TODO: Save to DB

			break;
		case R.id.buttonSharePAM:
			// TODO: Open option to share by various means
			break;
		case R.id.buttonNotePAM:
			displayNoteDialog();
			break;
		case R.id.buttonLoadMorePAM:
			// TODO
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

	public class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context c) {
			context = c;
		}

		// ---returns the number of images---
		public int getCount() {
			return imageIDs.length;
		}

		// ---returns the ID of an item---
		public Object getItem(int position) {
			return position;
		}

		// ---returns the ID of an item---
		public long getItemId(int position) {
			return position;
		}

		// ---returns an ImageView view---
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(
						pointToGetSize.x / 4, pointToGetSize.x / 4));

				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

				 imageView.setPadding(5, 5, 5, 5);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(imageIDs[position]);

			if (position == currentPos) {
				imageView.setBackgroundResource(R.drawable.list_selector);
			} else{
				imageView.setBackgroundResource(0);
			}

			return imageView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		currentPos = position;

		imageIDs[0] = R.drawable.pam_afraid2;
		adapter.notifyDataSetChanged();
	}
}
