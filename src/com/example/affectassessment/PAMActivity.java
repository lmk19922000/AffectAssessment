package com.example.affectassessment;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

public class PAMActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	private static final String PAM_DATA_FILENAME = "PAMData.txt";

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

	int currentPos = -1;

	ImageAdapter adapter; // adapter to provide images for GridView

	String note = "";

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
			saveData();
			break;
		case R.id.buttonSharePAM:
			// TODO: Open option to share by various means
			break;
		case R.id.buttonNotePAM:
			displayNoteDialog();
			break;
		case R.id.buttonLoadMorePAM:
			changeImageSet();
			currentPos = -1;
			adapter.notifyDataSetChanged();
			break;
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void saveData() {
		String currentDateAndTime, data;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		currentDateAndTime = sdf.format(new Date());

		data = currentDateAndTime + "," + String.valueOf(currentPos + 1) + ","
				+ note + "\n";

		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					openFileOutput(PAM_DATA_FILENAME, Context.MODE_APPEND));
			outputStreamWriter.append(data);
			outputStreamWriter.close();
		} catch (IOException e) {

		}

	}

	private void changeImageSet() {
		Random ran = new Random();
		int num;

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[0] = R.drawable.pam_afraid1;
		} else if (num == 1) {
			imageIDs[0] = R.drawable.pam_afraid2;
		} else {
			imageIDs[0] = R.drawable.pam_afraid3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[1] = R.drawable.pam_tense1;
		} else if (num == 1) {
			imageIDs[1] = R.drawable.pam_tense2;
		} else {
			imageIDs[1] = R.drawable.pam_tense3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[2] = R.drawable.pam_excited1;
		} else if (num == 1) {
			imageIDs[2] = R.drawable.pam_excited2;
		} else {
			imageIDs[2] = R.drawable.pam_excited3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[3] = R.drawable.pam_delighted1;
		} else if (num == 1) {
			imageIDs[3] = R.drawable.pam_delighted2;
		} else {
			imageIDs[3] = R.drawable.pam_delighted3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[4] = R.drawable.pam_frustrated1;
		} else if (num == 1) {
			imageIDs[4] = R.drawable.pam_frustrated2;
		} else {
			imageIDs[4] = R.drawable.pam_frustrated3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[5] = R.drawable.pam_angry1;
		} else if (num == 1) {
			imageIDs[5] = R.drawable.pam_angry2;
		} else {
			imageIDs[5] = R.drawable.pam_angry3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[6] = R.drawable.pam_happy1;
		} else if (num == 1) {
			imageIDs[6] = R.drawable.pam_happy2;
		} else {
			imageIDs[6] = R.drawable.pam_happy3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[7] = R.drawable.pam_glad1;
		} else if (num == 1) {
			imageIDs[7] = R.drawable.pam_glad2;
		} else {
			imageIDs[7] = R.drawable.pam_glad3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[8] = R.drawable.pam_miserable1;
		} else if (num == 1) {
			imageIDs[8] = R.drawable.pam_miserable2;
		} else {
			imageIDs[8] = R.drawable.pam_miserable3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[9] = R.drawable.pam_sad1;
		} else if (num == 1) {
			imageIDs[9] = R.drawable.pam_sad2;
		} else {
			imageIDs[9] = R.drawable.pam_sad3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[10] = R.drawable.pam_calm1;
		} else if (num == 1) {
			imageIDs[10] = R.drawable.pam_calm2;
		} else {
			imageIDs[10] = R.drawable.pam_calm3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[11] = R.drawable.pam_satisfied1;
		} else if (num == 1) {
			imageIDs[11] = R.drawable.pam_satisfied2;
		} else {
			imageIDs[11] = R.drawable.pam_satisfied3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[12] = R.drawable.pam_gloomy1;
		} else if (num == 1) {
			imageIDs[12] = R.drawable.pam_gloomy2;
		} else {
			imageIDs[12] = R.drawable.pam_gloomy3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[13] = R.drawable.pam_tired1;
		} else if (num == 1) {
			imageIDs[13] = R.drawable.pam_tired2;
		} else {
			imageIDs[13] = R.drawable.pam_tired3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[14] = R.drawable.pam_sleepy1;
		} else if (num == 1) {
			imageIDs[14] = R.drawable.pam_sleepy2;
		} else {
			imageIDs[14] = R.drawable.pam_sleepy3;
		}

		num = ran.nextInt(3);
		if (num == 0) {
			imageIDs[15] = R.drawable.pam_serene1;
		} else if (num == 1) {
			imageIDs[15] = R.drawable.pam_serene2;
		} else {
			imageIDs[15] = R.drawable.pam_serene3;
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
			} else {
				imageView.setBackgroundResource(0);
			}

			return imageView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		currentPos = position;
		adapter.notifyDataSetChanged();
	}
}
