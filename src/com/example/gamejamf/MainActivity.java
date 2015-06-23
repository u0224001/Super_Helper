package com.example.gamejamf;





import java.util.Random;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Intent intent;
	View home;
	Button home_btn, puzzle_btn, task_btn; 
	SharedPreferences getTask;
	TextView taskText1, taskText2, taskText3, taskText5, taskText4;
	Random rand = new Random();
	int scene=0;
	GifView gf1;
	RelativeLayout mainLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
		
		//set bottom click event
		home = (View)findViewById(R.id.home);
		home_btn = (Button)home.findViewById(R.id.home_btn);
		puzzle_btn = (Button)home.findViewById(R.id.puzzle_btn);
		task_btn = (Button)home.findViewById(R.id.task_btn);
		
		
		
		
		home_btn.setOnClickListener(mainListener);
		puzzle_btn.setOnClickListener(mainListener);
		task_btn.setOnClickListener(mainListener);
		
		//設定動畫
		//取得螢幕大小
		DisplayMetrics metrics = new DisplayMetrics();  
	    getWindowManager().getDefaultDisplay().getMetrics(metrics);
	    //設定動畫
		gf1 = (GifView)findViewById(R.id.gif1);
		changeScene();
//		gf1.setShowDimension(metrics.widthPixels, metrics.heightPixels*3/7);
		gf1.setGifImageType(GifImageType.SYNC_DECODER);

		
		//任務訊息
		taskText1 = (TextView)findViewById(R.id.taskText1);
		taskText2 = (TextView)findViewById(R.id.taskText2);
		taskText3 = (TextView)findViewById(R.id.taskText3);
		taskText4 = (TextView)findViewById(R.id.taskText4);
		taskText5 = (TextView)findViewById(R.id.taskText5);
		getTask = getSharedPreferences("DATA",0);
		//設置已獲得的任務
		setTask();
		taskText1.setOnClickListener(textListener);
		taskText2.setOnClickListener(textListener);
		taskText3.setOnClickListener(textListener);
		taskText4.setOnClickListener(textListener);
		taskText5.setOnClickListener(textListener);
		// set SharedPreferences
		
		
		//檢查是否有完成事項
		checkTask();
		
	}
	

	//改變場景
	private void changeScene() {
		scene = rand.nextInt(3);
		switch(scene) {
		case 0:
			mainLayout.setBackground(getResources().getDrawable(R.drawable.bathroom));
			break;
		case 1:
			mainLayout.setBackground(getResources().getDrawable(R.drawable.living_room));
			break;
		case 2:
			mainLayout.setBackground(getResources().getDrawable(R.drawable.kitchen));
			break;
		}
		scene  = rand.nextInt(5);
//		scene = 0;
		switch(scene) {
		case 0:
			gf1.setGifImage(R.drawable.text_animation);
			
//			gf1.setGifImage(R.drawable.waving);
			Log.i("change", "sence:0");
			
			break;
		case 1:

			gf1.setGifImage(R.drawable.moppingthefloor);
			Log.i("change", "sence:1");
			
			break;
		case 2:

			gf1.setGifImage(R.drawable.waving);
			Log.i("change", "sence:2");
			break;
		case 3:

			gf1.setGifImage(R.drawable.clean);
			Log.i("change", "sence:2");
			break;
		case 4:

			gf1.setGifImage(R.drawable.wash);
			Log.i("change", "sence:2");
		}
		
	}
	
	//點擊完成任務
	private OnClickListener textListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//如果textview內沒東西代表未接取任務，則無法勾取
			Intent intent_camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			switch(v.getId()) {
			case R.id.taskText1:
				if(taskText1.getText() != "") {
				textviewEvent(taskText1);
				//獲得並儲存拼圖
				savePuzzle();
				//儲存完成的任務
				getTask.edit().putBoolean("finish01",true).commit();
				//changeScene();
				startActivityForResult(intent_camera, 0);
				}
				break;
			case R.id.taskText2:
				if(taskText2.getText() != "") {
				textviewEvent(taskText2);
				savePuzzle();
				getTask.edit().putBoolean("finish02",true).commit();
				//changeScene();
				startActivityForResult(intent_camera, 0);
				}
				break;
			case R.id.taskText3:
				if(taskText3.getText() != "") {
				textviewEvent(taskText3);
				savePuzzle();
				getTask.edit().putBoolean("finish03",true).commit();
				//changeScene();
				startActivityForResult(intent_camera, 0);
				}
				break;
			case R.id.taskText4:
				if(taskText4.getText() != "") {
				textviewEvent(taskText4);
				savePuzzle();
				getTask.edit().putBoolean("finish04",true).commit();
				//changeScene();
				startActivityForResult(intent_camera, 0);
				}
				break;
			case R.id.taskText5:
				if(taskText5.getText() != "") {
				textviewEvent(taskText5);
				savePuzzle();
				getTask.edit().putBoolean("finish05",true).commit();
				//changeScene();
				startActivityForResult(intent_camera, 0);
				}
				break;
			}
			
		}
	};
	
	//拍完照後的回傳動作
	 @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 //提示使用者獲得拼圖
		 new AlertDialog.Builder(this)   
	        .setTitle("Congratulations")
	        .setMessage("You get a puzzle, Let's to check out it!")
	        .setPositiveButton("Ok", null)
	        .show();
		 
		 if (resultCode == RESULT_OK)
	     {
			// 取出拍照後回傳資料
			Bundle extras = data.getExtras();
			// 將資料轉換為圖像格式
			Bitmap bmp = (Bitmap) extras.get("data");
			// 將Bitmap轉為Drawable
			Drawable drawable = new BitmapDrawable(bmp);
			// 設背景
			mainLayout.setBackground(drawable);
	     }
	 }
	
	//檢查任務是否已被完成
	public void checkTask() {
		if(getTask.getBoolean("finish01", false))
			textviewEvent(taskText1);
		
		if(getTask.getBoolean("finish02", false))
			textviewEvent(taskText2);
		
		if(getTask.getBoolean("finish03", false))
			textviewEvent(taskText3);
		
		if(getTask.getBoolean("finish04", false))
			textviewEvent(taskText4);
		
		if(getTask.getBoolean("finish05", false))
			textviewEvent(taskText5);
	}
	//textview變換成打勾圖案，文字畫刪除線
	public void textviewEvent(TextView tv) {
		Resources res = this.getResources();
        Drawable drawable = res.getDrawable(R.drawable.mission_ok);
		tv.setBackground(drawable);
		tv.setEnabled(false);
		Paint paint = tv.getPaint();
		paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		paint.setAntiAlias(true);
		
		
	}
	
	//下方主按鈕
	private OnClickListener mainListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId())
		    {      
		      case R.id.home_btn:  
		    	 // do not anything
		    	  break;
		      case R.id.puzzle_btn: 
		    	  playMusic();
		    	 intent = new Intent(MainActivity.this, PuzzleActivity.class);
		    	 startActivity(intent);
		    	 break;
		      case R.id.task_btn:
		    	  playMusic();
		    	 intent = new Intent(MainActivity.this, EventActivity.class);
		    	 startActivity(intent);
		    	 break;
		    }
		}
	
	};
	
	public void playMusic() {
		MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.push);
  	    mp.start();
	}
	//
	public void savePuzzle() {
		DrawnPuzzles dp = new DrawnPuzzles();
		PuzzleDB pdb = new PuzzleDB(this);
		SQLiteDatabase db = pdb.getWritableDatabase();
		ContentValues args = new ContentValues();
		int i = dp.draw();
		
		args.put("PUZZLE_FRAG", i);
		long rowid = db.insert("INCOME", null, args);
		Log.i("PuzzleDB","SQL:record inserted, id=" + rowid);
//		Toast.makeText(this, "Congratulations, get NO." + i + "puzzle",
//		          Toast.LENGTH_LONG).show();
		
		db.close();
		
	}
	
	public void setTask() {
		//if (getTask.getString("TASK1", "") != null)
		taskText1.setText(getTask.getString("TASK1", ""));
		taskText2.setText(getTask.getString("TASK2", ""));
		taskText3.setText(getTask.getString("TASK3", ""));
		taskText4.setText(getTask.getString("TASK4", ""));
		taskText5.setText(getTask.getString("TASK5", ""));

	}
	
	
}
