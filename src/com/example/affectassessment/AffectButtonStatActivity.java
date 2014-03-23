package com.example.affectassessment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AffectButtonStatActivity extends Activity {

	private static final String AFFECTBUTTON_DATA_FILENAME = "AffectButtonData.txt";

	/** Colors to be used for the pie slices. */
	private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE,
			Color.MAGENTA, Color.CYAN };
	
	/** The main series that will include all the data. */
	private CategorySeries mSeries = new CategorySeries("");
	
	/** The main renderer for the main dataset. */
	private DefaultRenderer mRenderer = new DefaultRenderer();
	
	/** Button for adding entered data to the current series. */
	//private Button mAdd;
	/** Edit text field for entering the slice value. */
	//private EditText mValue;
	
	/** The chart view that displays the data. */
	private GraphicalView mChartView;
	
	/*
	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		mSeries = (CategorySeries) savedState.getSerializable("current_series");
		mRenderer = (DefaultRenderer) savedState
				.getSerializable("current_renderer");
	}
	*/
	
	/*
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("current_series", mSeries);
		outState.putSerializable("current_renderer", mRenderer);
	}
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xy_chart);
		//mValue = (EditText) findViewById(R.id.xValue);
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setStartAngle(180);
		mRenderer.setDisplayValues(true);
		
		SimpleSeriesRenderer renderer;
		
		mSeries.add("Series " + (mSeries.getItemCount() + 1), 1);
		renderer = new SimpleSeriesRenderer();
		renderer.setColor(COLORS[(mSeries.getItemCount() - 1)
				% COLORS.length]);
		mRenderer.addSeriesRenderer(renderer);
		
		mSeries.add("Series " + (mSeries.getItemCount() + 1), 2);
		renderer = new SimpleSeriesRenderer();
		renderer.setColor(COLORS[(mSeries.getItemCount() - 1)
				% COLORS.length]);
		
		mRenderer.addSeriesRenderer(renderer);
		
		mSeries.add("Series " + (mSeries.getItemCount() + 1), 3);
		renderer = new SimpleSeriesRenderer();
		renderer.setColor(COLORS[(mSeries.getItemCount() - 1)
				% COLORS.length]);
		mRenderer.addSeriesRenderer(renderer);
		
		mSeries.add("Series " + (mSeries.getItemCount() + 1), 4);
		renderer = new SimpleSeriesRenderer();
		renderer.setColor(COLORS[(mSeries.getItemCount() - 1)
				% COLORS.length]);
		mRenderer.addSeriesRenderer(renderer);
		
		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
			mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
			mRenderer.setClickEnabled(true);
			mChartView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					SeriesSelection seriesSelection = mChartView
							.getCurrentSeriesAndPoint();
					if (seriesSelection == null) {
						Toast.makeText(AffectButtonStatActivity.this,
								"No chart element selected", Toast.LENGTH_SHORT)
								.show();
					} else {
						for (int i = 0; i < mSeries.getItemCount(); i++) {
							mRenderer.getSeriesRendererAt(i).setHighlighted(
									i == seriesSelection.getPointIndex());
						}
						mChartView.repaint();
						Toast.makeText(
								AffectButtonStatActivity.this,
								"Chart data point index "
										+ seriesSelection.getPointIndex()
										+ " selected" + " point value="
										+ seriesSelection.getValue(),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			layout.addView(mChartView, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		} else {
			mChartView.repaint();
		}
		
		mChartView.repaint();
		
		//mAdd = (Button) findViewById(R.id.add);
		//mAdd.setEnabled(true);
		//mValue.setEnabled(true);
		
		/*
		mAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				double value = 0;
				try {
					value = Double.parseDouble(mValue.getText().toString());
				} catch (NumberFormatException e) {
					mValue.requestFocus();
					return;
				}
				mValue.setText("");
				mValue.requestFocus();
				mSeries.add("Series " + (mSeries.getItemCount() + 1), value);
				SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
				renderer.setColor(COLORS[(mSeries.getItemCount() - 1)
						% COLORS.length]);
				mRenderer.addSeriesRenderer(renderer);
				mChartView.repaint();
			}
		});
		*/
	}
	
	/*
	@Override
	protected void onResume() {
		super.onResume();
		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
			mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
			mRenderer.setClickEnabled(true);
			mChartView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					SeriesSelection seriesSelection = mChartView
							.getCurrentSeriesAndPoint();
					if (seriesSelection == null) {
						Toast.makeText(AffectButtonStatActivity.this,
								"No chart element selected", Toast.LENGTH_SHORT)
								.show();
					} else {
						for (int i = 0; i < mSeries.getItemCount(); i++) {
							mRenderer.getSeriesRendererAt(i).setHighlighted(
									i == seriesSelection.getPointIndex());
						}
						mChartView.repaint();
						Toast.makeText(
								AffectButtonStatActivity.this,
								"Chart data point index "
										+ seriesSelection.getPointIndex()
										+ " selected" + " point value="
										+ seriesSelection.getValue(),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			layout.addView(mChartView, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		} else {
			mChartView.repaint();
		}
	}
	*/
	
	private String readData() {
		String ret = "";

		try {
			InputStream inputStream = openFileInput(AFFECTBUTTON_DATA_FILENAME);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		return ret;

	}
}
