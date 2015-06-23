package com.example.gamejamf;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class EventActivity extends Activity{
	View event;
	Button home_btn, puzzle_btn, task_btn; //���U���D��
	TextView task1, task2, task3, task4, task5;
	TaskGenerator tg;
	SharedPreferences saveTask;
	int today;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_event);
		//set bottom click event
		event = (View)findViewById(R.id.event);
		home_btn = (Button)event.findViewById(R.id.home_btn);
		puzzle_btn = (Button)event.findViewById(R.id.puzzle_btn);
		task_btn = (Button)event.findViewById(R.id.task_btn);
						
		home_btn.setOnClickListener(mainListener);
		puzzle_btn.setOnClickListener(mainListener);
		task_btn.setOnClickListener(mainListener);
		
		//任務產生器
		 tg = new TaskGenerator();
		 
		task1 = (TextView)findViewById(R.id.task1);
		task2 = (TextView)findViewById(R.id.task2);
		task3 = (TextView)findViewById(R.id.task3);
		task4 = (TextView)findViewById(R.id.task4);
		task5 = (TextView)findViewById(R.id.task5);
		saveTask = getSharedPreferences("DATA",0);
		
		task1.setOnClickListener(eventListener);
		task2.setOnClickListener(eventListener);
		task3.setOnClickListener(eventListener);
		task4.setOnClickListener(eventListener);
		task5.setOnClickListener(eventListener);
		
		//取得現在日期
		Calendar c=Calendar.getInstance();
		today = c.get(Calendar.DAY_OF_MONTH);

		if(today!=saveTask.getInt("day", 0)) {
//			Log.i("date","date:" + today + "..." + saveTask.getInt("day", 0));
			saveTask.edit().clear().commit();
			saveTask.edit().putInt("day", today).commit(); //將儲存日期為今天
		}
		
		
		setTask();
		isReceived();
		
		//每一天的登入更新資料
		//如果今天日期不等於儲存日期，清除所有資料
		
	}
			
	//判斷是否已接取任務
	private void isReceived() {
		if(saveTask.getBoolean("receive1", false)) 
			getTaskEvent(task1);
		if(saveTask.getBoolean("receive2", false))
			getTaskEvent(task2);
		if(saveTask.getBoolean("receive3", false))
			getTaskEvent(task3);
		if(saveTask.getBoolean("receive4", false))
			getTaskEvent(task4);
		if(saveTask.getBoolean("receive5", false))
			getTaskEvent(task5);
	}
	//下方按鈕的東東
	private OnClickListener mainListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (v.getId())
			{      
				case R.id.home_btn:  
					playMusic();
				   intent = new Intent(EventActivity.this, MainActivity.class);
				   startActivity(intent);
				   break;
				case R.id.puzzle_btn: 
					playMusic();
					intent = new Intent(EventActivity.this, PuzzleActivity.class);
					startActivity(intent);
				   break;
				case R.id.task_btn:
				   //
				   break;
			}
		}
			
	};
	
	
	//接任務事件
	private OnClickListener eventListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String s;
			switch (v.getId())
			{      
			case R.id.task1:
//				s = getTime();
				saveTask.edit().putString("TASK1", task1.getText().toString()).
								putBoolean("receive1", true).commit();
				getTaskEvent(task1);
				break;
			case R.id.task2:
//				s = getTime();
				saveTask.edit().putString("TASK2", task2.getText().toString())
				.putBoolean("receive2", true).commit();
				getTaskEvent(task2);
				break;
			case R.id.task3:
//				s = getTime();
				saveTask.edit().putString("TASK3", task3.getText().toString())
				.putBoolean("receive3", true).commit();
				getTaskEvent(task3);
				break;
			case R.id.task4:
//				s = getTime();
				saveTask.edit().putString("TASK4", task4.getText().toString())
				.putBoolean("receive4", true).commit();
				getTaskEvent(task4);
				break;
			case R.id.task5:
//				s = getTime();
				saveTask.edit().putString("TASK5", task5.getText().toString())
				.putBoolean("receive5", true).commit();
				getTaskEvent(task5);
				break;
			}
			
		}
	};
	
	//取得任務後選項不可互動
	private void getTaskEvent(TextView tv) {
		tv.setEnabled(false);
		tv.setText("Done");
		tv.setTextColor(Color.RED);
	}
	
             
	protected void playMusic() {
		// TODO Auto-generated method stub
		MediaPlayer mp = MediaPlayer.create(EventActivity.this, R.raw.push);
  	    mp.start();
	}

	public void setTask() {

		task1.setText(tg.getTask(0));
		task2.setText(tg.getTask(1));
		task3.setText(tg.getTask(2));
		task4.setText(tg.getTask(3));
		task5.setText(tg.getTask(4));
	}
}
