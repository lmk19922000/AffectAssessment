package com.example.affectassessment;

import java.util.Stack;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AffectButtonActivity extends Activity implements OnTouchListener{
	public enum MoodStates {
		NEUTRAL("Neutral"), ANGRY("Angry"), UPSET("Upset"), SAD("Sad"), HAPPY(
				"Happy"), CONTENT("Content"), SURPRISED("Surprised"), FRUSTRATED(
				"Frustrated"), RELAXED("Relaxed");
		
		private MoodStates(final String text) {
			this.text = text;
		}

		private final String text;

		@Override
		public String toString() {
			return text;
		}
	}
	
	float xCord = -1, yCord = -1;
	
	
	
	public static double[] FACE_MIXTURE_DISTANCE; // 1.3 for all is the default
	// tested in all evaluation
	// studies.

	public double p = 0;
	public double a = 0;
	public double d = 0;

	int x, y;

	public int size;

	int fdx;

	int fdy;
	int sx, sy, slex, sley, srex, srey, smx, smy, baseMW, baseMH, baseEW,
			baseEH;
	int eh, eyebrowSpace, eyebrowOuter, eyebrowInner, mw, mt, mo, tv;
	int mx, my; // mouse x and mouse y of user to simulate that the face looks
			// at the user
	double pupilRandomness = 0;
	double mouthRandomness = 0;

	double[][] emotions;

	//GraphicsView canvasView;
	GraphicsView affectButtonCanvas;

	//Button buttonNext;
	
	//public TextView moodName, dateTime;
	
	//EditText AffectButtonNoteFragment;
	//String note = "";
	
	Point pointToGetSize; 								// Size of the sudoku board (in terms of phone screen)
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_affect_button);
		
		initializeVariables();
		
		addCanvasView();
		
		initializeCompnentView();
	}
	
	@SuppressLint("NewApi")
	private void initializeVariables() {
		// Get size of the screen to decide size of the canvas
		Display display = getWindowManager().getDefaultDisplay();
		pointToGetSize = new Point();
		display.getSize(pointToGetSize);
		
		size = pointToGetSize.x;
	}
	
	private void addCanvasView() {
		affectButtonCanvas = new GraphicsView(this);

		// Set the position of the canvas relative to the activity view
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 50, 0, 0);
		// layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, 1);
		layoutParams.addRule(RelativeLayout.BELOW, R.id.textView1);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);

		layoutParams.height = pointToGetSize.x;
		layoutParams.width = pointToGetSize.x;
		
		setDimensions(sx, sy, size);
        emotions=defineEmotions();
        setEmotion(p,a,d,0,0);
        
		// Add the canvas to the activity view
		ViewGroup v = (ViewGroup) getWindow().getDecorView().findViewById(R.id.RelativeLayoutAffectButton);
		v.addView(affectButtonCanvas, layoutParams);
	}
	
	private void initializeCompnentView() {
		affectButtonCanvas.setOnTouchListener(this);

	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
	
				break;
			case MotionEvent.ACTION_UP:
	
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("xCord", String.valueOf(event.getX())); 
				Log.i("yCord", String.valueOf(event.getY()));
				if (event.getX() > this.size) {
					xCord = this.size;
				} else if (event.getX() < 0) {
					xCord = 0;
				} else {
					xCord = event.getX();
				}
				if (event.getY() > this.size) {
					yCord = this.size;
				} else if (event.getY() < 0) {
					yCord = 0;
				} else {
					yCord = event.getY();
				}
				// Display name of the mood
				//displayMoodName(xCord, yCord);
				
				this.p = ((double) xCord - this.size / (double) 2)
						/ ((double) (this.size + 1) / 2.0);
				this.d = -((double) yCord - (double) this.size / (double) 2)
						/ ((double) (this.size + 1) / 2.0);
				this.setXY(this.p, this.d);
				this.setEmotion(this.p, this.a, this.d, (int) xCord,
						(int) yCord);
				//Log.i("p", String.valueOf(screen1.p)); 
				//Log.i("a", String.valueOf(screen1.a));
				//Log.i("d", String.valueOf(screen1.d));
				break;
		}
		return true;
		
	}
	
	
	
	
	
	
	
	
	private double[][] defineEmotions(){
    	double[][] emos=new double[9][11];
    	//0=-p-a-d 	= sad / lonely / bored
    	//1=-p-ad 	= jealous / disdainful
    	//2=-pa-d	= terrified / fearful / humiliated / frustrated
    	//3=-pad	= angry / cruel / hostile
    	//4=p-a-d	= protected / humble
    	//5=p-ad	= leisured / relaxed
    	//6=pa-d	= impressed
    	//7=pad 	= happy / joyful / masterful / excited
    	//8=000 	= neutral
    	//this is the influence sphere of the emotion (used for mixed expressions, standard =1.3)
    	
    	FACE_MIXTURE_DISTANCE=new double[9];
    	FACE_MIXTURE_DISTANCE[0]=1.3;
    	FACE_MIXTURE_DISTANCE[1]=1.3;
    	FACE_MIXTURE_DISTANCE[2]=1.3;
    	FACE_MIXTURE_DISTANCE[3]=1.3;
    	FACE_MIXTURE_DISTANCE[4]=1.3;
    	FACE_MIXTURE_DISTANCE[5]=1.3;
    	FACE_MIXTURE_DISTANCE[6]=1.3;
    	FACE_MIXTURE_DISTANCE[7]=1.3;
    	FACE_MIXTURE_DISTANCE[8]=1.7;
    	
    	//eyes height
    	emos[0][0]=-1;emos[1][0]=-0.3;emos[2][0]=1;emos[3][0]=0.5;emos[4][0]=-1;emos[5][0]=-0.5;emos[6][0]=0.3;emos[7][0]=0.5;emos[8][0]=0;
    	//eyebrowSpace between eyes and brows
    	emos[0][1]=-1;emos[1][1]=0;emos[2][1]=1;emos[3][1]=0;emos[4][1]=-1;emos[5][1]=0;emos[6][1]=1;emos[7][1]=0.5;emos[8][1]=0;
    	//eyebrowOuter height
    	emos[0][2]=-1;emos[1][2]=0;emos[2][2]=-.8;emos[3][2]=.8;emos[4][2]=0;emos[5][2]=0;emos[6][2]=0;emos[7][2]=0;emos[8][2]=0;
    	//eyebrowInner height
    	emos[0][3]=1;emos[1][3]=-1;emos[2][3]=.8;emos[3][3]=-.8;emos[4][3]=0;emos[5][3]=0;emos[6][3]=0;emos[7][3]=0;emos[8][3]=0;
    	//mouth width
    	emos[0][4]=-1;emos[1][4]=-1;emos[2][4]=0;emos[3][4]=1;emos[4][4]=0;emos[5][4]=0;emos[6][4]=-1.5;emos[7][4]=1;emos[8][4]=0;
    	//mouth openness (new impressed .7)
    	emos[0][5]=-1;emos[1][5]=-0.5;emos[2][5]=0;emos[3][5]=1;emos[4][5]=-1;emos[5][5]=-0.5;emos[6][5]=0.7;emos[7][5]=0.5;emos[8][5]=-0.5;
    	//mouth twist (positive = happy)
    	emos[0][6]=-1;emos[1][6]=-0.5;emos[2][6]=-0.3;emos[3][6]=-1;emos[4][6]=0.7;emos[5][6]=1;emos[6][6]=0.5;emos[7][6]=1;emos[8][6]=0;
    	//teeth visible (new version, teeth less visible when elated .5)
    	emos[0][7]=1;emos[1][7]=1;emos[2][7]=0.5;emos[3][7]=1;emos[4][7]=1;emos[5][7]=1;emos[6][7]=-0.5;emos[7][7]=0.5;emos[8][7]=1;
    	//p value of emotions
    	emos[0][8]=-1;emos[1][8]=-1;emos[2][8]=-1;emos[3][8]=-1;emos[4][8]=1;emos[5][8]=1;emos[6][8]=1;emos[7][8]=1;emos[8][8]=0;
    	//a value of emotions
    	emos[0][9]=-1;emos[1][9]=-1;emos[2][9]=1;emos[3][9]=1;emos[4][9]=-1;emos[5][9]=-1;emos[6][9]=1;emos[7][9]=1;emos[8][9]=0;
    	//d value of emotions
    	emos[0][10]=-1;emos[1][10]=1;emos[2][10]=-1;emos[3][10]=1;emos[4][10]=-1;emos[5][10]=1;emos[6][10]=-1;emos[7][10]=1;emos[8][10]=0;
    
    	return emos;
    }
	
	public void setEmotion(double p, double a, double d, int mx, int my) //accepts pad between -1 and 1
    {   
		// Dynamic face positioning based on where the user's mouse pointer is
		// face
		this.mx = (mx * (size / 20)) / size - 1;// eye tracking of mouse
		this.my = (my * (size / 20)) / size - 1;// eye tracking of mouse

		// fdx and fdy are the start coordinate of the face within the yellow
		// circle (the skin)
		fdy = size / 10 - (int) ((d + a) * (size / 20));
		fdx = this.mx;

		// here a mapping is selected from the predefined 8 emotions in PAD
		// space;
		// and subsequently interpolated.
		double[] emotion = new double[8];
		for (int j = 0; j < 8; j++) {
			double weight = 0;
			for (int i = 0; i < 9; i++) {
				double featureWeight = FACE_MIXTURE_DISTANCE[i]
						- Math.min(FACE_MIXTURE_DISTANCE[i],
								distance(p, a, d, emotions[i]));
				weight += featureWeight;
				emotion[j] += featureWeight * emotions[i][j];
			}
			emotion[j] /= weight;
		}
		eh = (int) (baseEH * (1 + emotion[0])) + 2;
		eyebrowSpace = (int) (baseEH * (1 + emotion[1]) / 2) + baseEH / 2 + 1;
		eyebrowOuter = (int) (-emotion[2] * baseEH) / 2;
		eyebrowInner = (int) (-emotion[3] * baseEH) / 2;

		mw = (int) (baseMW * (emotion[4] + 1) / 6) + baseMW / 2;
		mo = (int) (baseMH * (emotion[5] + 1) / 3);
		mt = (int) ((baseMH * emotion[6]) / 2);

		// teeth visible
		tv = (int) (baseMH * (emotion[7] - 1) / 3);
    }
    private double distance(double p, double a, double d, double[] emotion){
		return Math.sqrt(Math.pow(p - emotion[8], 2)
				+ Math.pow(a - emotion[9], 2) + Math.pow(d - emotion[10], 2));
    }
    
	public void setEmotion(double p, double a, double d) // accepts pad between
															// -1 and 1
	{
		setEmotion(p, a, d, size / 2, size / 2);
	}
    
	public void setDimensions(int xx, int yy, int size) {
		sx = xx;
		sy = yy;
		x = size;
		y = size;
		this.size = size;
		baseEW = size / 4;
		baseEH = baseEW / 3;
		baseMW = size / 2;
		baseMH = baseMW / 3;

		slex = fdx + sx + size / 2 - (int) ((double) baseEW * 1.4);
		sley = fdy + sy + size / 3;
		srex = fdx + sx + size / 2 + (int) ((double) baseEW * 0.4);
		srey = fdy + sy + size / 3;
		smx = fdx + sx + size / 2;
		smy = fdy + sy + 2 * (size / 3);
		x += (sx + 20);
		y += (sx + 20);
	}
	
	public void setXY(double x, double y){
		//x and y between -1 and 1
		double factor=0.55;//old factor used in acii, affine, mpref: 0.66, now it is 0.55 to increase responsiveness of arousal, tested in many different studies
		double sensitivity=1.1; //evaluated with set to 1.1: a sensitivity of 1 means the complete space of the button is used. 2 means only the middle part is used, making it more sensitive and more easy to get to extreme arousal (from a CHI perspective).
		p=x*sensitivity;
		d=y*sensitivity;
		//now do the mapping to arousal.
		//Arousal is controlled in the outer ring of the button.
		//The inner ring is a square of size <factor>% of the button in which p and d are controlled (see <factor> below).
		//Arousal is controlled in the outer ring (between <factor>% and the edge of the button).
		//Arousal is based on the position of the mouse pointer in the outer ring.
		//So at the edge, a = 1, and within the <factor>% of the inner square, a=-1.
		
		double x1, x2, y1, y2, d1, a_0, rc;
		if (p > factor | d > factor | p < -factor | d < -factor) {
			if (p == 0) {
				if (d > 0) {
					x1 = 0;
					y1 = factor;
					x2 = 0;
					y2 = 1;
				} else {
					x1 = 0;
					y1 = -factor;
					x2 = 0;
					y2 = -1;
				}
			} else {
				rc = d / p;
				if (p >= d & !(-p >= d)) {
					x1 = factor;
					x2 = 1;
					y1 = x1 * rc;
					y2 = x2 * rc;
				} else if (p >= d & -p >= d) {
					y1 = -factor;
					y2 = -1;
					x1 = y1 / rc;
					x2 = y2 / rc;
				} else if (!(p >= d) & -p >= d) {
					x1 = -factor;
					x2 = -1;
					y1 = x1 * rc;
					y2 = x2 * rc;
				} else {
					// (!(p>=d) & !(-p>=d))
					y1 = factor;
					y2 = 1;
					x1 = y1 / rc;
					x2 = y2 / rc;
				}
			}
			d1 = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
			a_0 = Math.sqrt(Math.pow(x1 - p, 2) + Math.pow(y1 - d, 2));
			a = 2 * (a_0 / d1) - 1;
		} else
			a = -1;

		p = (p > 1 ? 1 : (p < -1 ? -1 : p));
		d = (d > 1 ? 1 : (d < -1 ? -1 : d));
		a = (a > 1 ? 1 : (a < -1 ? -1 : a));
	}
	
	/**
	 * 
	 * @author Le Minh Khue
	 * Canvas class to draw AffectButton
	 *
	 */
	public class GraphicsView extends View {

		private Paint bluePaint, yellowPaint, whitePaint, blackPaint, redThickPaint, blackThickPaint, yellowThickPaint;

		public GraphicsView(Context context) {
			super(context);
			bluePaint = new Paint();
			bluePaint.setColor(Color.rgb(0, 200, 200));
			bluePaint.setStyle(Paint.Style.FILL);
			
			yellowPaint = new Paint();
			yellowPaint.setColor(Color.rgb(255, 200, 0));
			yellowPaint.setStyle(Paint.Style.FILL);
			
			whitePaint = new Paint();
			whitePaint.setColor(Color.WHITE);
			whitePaint.setStyle(Paint.Style.FILL);
			
			blackPaint = new Paint();
			blackPaint.setColor(Color.BLACK);
			blackPaint.setStyle(Paint.Style.FILL);
			
			redThickPaint = new Paint();
			redThickPaint.setColor(Color.RED);
			redThickPaint.setStyle(Paint.Style.STROKE);
			redThickPaint.setStrokeWidth(4);
			
			blackThickPaint = new Paint();
			blackThickPaint.setColor(Color.BLACK);
			blackThickPaint.setStyle(Paint.Style.STROKE);
			blackThickPaint.setStrokeWidth(10);
			
			yellowThickPaint = new Paint();
			yellowThickPaint.setColor(Color.rgb(255, 200, 0));
			yellowThickPaint.setStyle(Paint.Style.STROKE);
			yellowThickPaint.setStrokeWidth(3);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			canvas.drawColor(Color.rgb(152, 255, 152));

			// draw pacman
			canvas.drawOval(new RectF(sx + 4, sy, size - 8, size), yellowPaint);
			canvas.drawOval(
					new RectF(sx + 4, sy, size - 8, (int) (size * 0.66)),
					yellowPaint);

			// draw left eye
			int pupilVarX = mx + (int) (pupilRandomness * Math.random());
			int pupilVarY = my + (int) (pupilRandomness * Math.random());

			canvas.drawArc(new RectF(slex, sley - eh / 2, slex + baseEW, sley
					- eh / 2 + eh), 0, 360, true, whitePaint);
			canvas.drawArc(new RectF(
					pupilVarX + slex + baseEW / 2 - baseEW / 4, pupilVarY
							+ sley - baseEW / 4, pupilVarX + slex + baseEW / 2
							- baseEW / 4 + baseEW / 2, pupilVarY + sley
							- baseEW / 4 + baseEW / 2), 0, 360, true, bluePaint);
			canvas.drawArc(new RectF(pupilVarX + slex + baseEW / 2 - baseEW
					/ 10, pupilVarY + sley - baseEW / 10, pupilVarX + slex
					+ baseEW / 2 - baseEW / 10 + baseEW / 5, pupilVarY + sley
					- baseEW / 10 + baseEW / 5), 0, 360, true, blackPaint);
			canvas.drawRect(new RectF(slex, sley - eh / 2 - baseEW / 2, slex
					+ baseEW, sley - eh / 2 - baseEW / 2 + baseEW / 2),
					yellowPaint);
			canvas.drawRect(new RectF(slex, sley + eh / 2, slex + baseEW, sley
					+ eh / 2 + baseEW / 2), yellowPaint);
	        
	        // draw left eyebrow
			canvas.drawLine(slex, sley - eyebrowSpace + eyebrowOuter, slex
					+ baseEW, sley - eyebrowSpace + eyebrowInner,
					blackThickPaint);
			canvas.drawLine(slex, 1 + sley - eyebrowSpace + eyebrowOuter, slex
					+ baseEW, 1 + sley - eyebrowSpace + eyebrowInner,
					blackThickPaint);
	        
			if (size > 70) {
				canvas.drawLine(slex, 2 + sley - eyebrowSpace + eyebrowOuter,
						slex + baseEW, 2 + sley - eyebrowSpace + eyebrowInner,
						blackThickPaint);
			}
	        	
	        // draw right eye
			pupilVarX = mx + (int) (pupilRandomness * Math.random());
			pupilVarY = my + (int) (pupilRandomness * Math.random());
			canvas.drawArc(new RectF(srex, srey - eh / 2, srex + baseEW, srey
					- eh / 2 + eh), 0, 360, true, whitePaint);
			canvas.drawArc(new RectF(
					pupilVarX + srex + baseEW / 2 - baseEW / 4, pupilVarY
							+ srey - baseEW / 4, pupilVarX + srex + baseEW / 2
							- baseEW / 4 + baseEW / 2, pupilVarY + srey
							- baseEW / 4 + baseEW / 2), 0, 360, true, bluePaint);
			canvas.drawArc(new RectF(pupilVarX + srex + baseEW / 2 - baseEW
					/ 10, pupilVarY + srey - baseEW / 10, pupilVarX + srex
					+ baseEW / 2 - baseEW / 10 + baseEW / 5, pupilVarY + srey
					- baseEW / 10 + baseEW / 5), 0, 360, true, blackPaint);
			canvas.drawRect(new RectF(srex, srey - eh / 2 - baseEW / 2, srex
					+ baseEW, srey - eh / 2 - baseEW / 2 + baseEW / 2),
					yellowPaint);
			canvas.drawRect(new RectF(srex, srey + eh / 2, srex + baseEW, srey
					+ eh / 2 + baseEW / 2), yellowPaint);

	        // draw right eyebrow
			canvas.drawLine(srex, srey - eyebrowSpace + eyebrowInner, srex
					+ baseEW, srey - eyebrowSpace + eyebrowOuter,
					blackThickPaint);
			canvas.drawLine(srex, 1 + srey - eyebrowSpace + eyebrowInner, srex
					+ baseEW, 1 + srey - eyebrowSpace + eyebrowOuter,
					blackThickPaint);
			if (size > 70) {
				canvas.drawLine(srex, 2 + srey - eyebrowSpace + eyebrowInner,
						srex + baseEW, 2 + srey - eyebrowSpace + eyebrowOuter,
						blackThickPaint);
			}
	         
	      //draw lips
	      int mr=(int)(mouthRandomness*Math.random());
	      int upperlip, lowerlip, shift;
	      upperlip = mt - mo;
	      lowerlip = mt + mo;
	      shift = -mt;

	      // fill the mouth in the right order
			if (upperlip > 0) {
				if (lowerlip > 0) {
					canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
							- lowerlip, smx - mw / 2 + mw, shift + mr + smy
							- lowerlip + lowerlip * 2), 0, 180, false,
							whitePaint);
				} else {
					canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
							+ lowerlip, smx - mw / 2 + mw, shift + mr + smy
							+ lowerlip - lowerlip * 2), -180, 180, false,
							yellowPaint);
				}
				canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
						- upperlip, smx - mw / 2 + mw, shift + mr + smy
						- upperlip + upperlip * 2), 0, 180, false, yellowPaint);
			} else {
				canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
						+ upperlip, smx - mw / 2 + mw, shift + mr + smy
						+ upperlip - upperlip * 2), -180, 180, false, whitePaint);
				if (lowerlip > 0) {
					canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
							- lowerlip, smx - mw / 2 + mw, shift + mr + smy
							- lowerlip + lowerlip * 2), 0, 180, false,
							whitePaint);
				} else {
					canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
							+ lowerlip, smx - mw / 2 + mw, shift + mr + smy
							+ lowerlip - lowerlip * 2), -180, 180, false,
							yellowPaint);
				}
			}
	        
			//fill mouth hole black
	        canvas.drawRect(new RectF(smx-mw/2, smy+tv, smx-mw/2+mw, smy+tv-tv*2), yellowPaint);
	        
	        // draw teeth
			for (int i = 0; i < 6; i++) {
				canvas.drawLine(smx - baseMW / 2 + i * baseMW / 5,
						smy - Math.abs(mt) - mo, smx - baseMW / 2 + i * baseMW
								/ 5, smy + Math.abs(mt) + mo, yellowThickPaint);
			}
			canvas.drawLine(smx - baseMW / 2, smy, smx + baseMW / 2, smy,
					yellowThickPaint);
	        
	        
	      //draw red lips
			if (upperlip > 0) {
				canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
						- upperlip, smx - mw / 2 + mw, shift + mr + smy
						- upperlip + upperlip * 2), 0, 180, false,
						redThickPaint);
			} else {
				canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
						+ upperlip, smx - mw / 2 + mw, shift + mr + smy
						+ upperlip - upperlip * 2), -180, 180, false,
						redThickPaint);
			}

			if (lowerlip > 0) {
				canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
						- lowerlip, smx - mw / 2 + mw, shift + mr + smy
						- lowerlip + lowerlip * 2), 0, 180, false,
						redThickPaint);
			} else {
				canvas.drawArc(new RectF(smx - mw / 2, shift + mr + smy
						+ lowerlip, smx - mw / 2 + mw, shift + mr + smy
						+ lowerlip - lowerlip * 2), -180, 180, false,
						redThickPaint);
			}

			invalidate();
		}
	}
}