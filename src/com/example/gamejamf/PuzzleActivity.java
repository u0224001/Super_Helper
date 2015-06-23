package com.example.gamejamf;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PuzzleActivity extends Activity {
	View puzzle;
	Button home_btn, puzzle_btn, task_btn; //底下的主鍵
	ImageView frag1, frag2, frag3, frag4, frag5, frag6, frag7, frag8, frag9 ;

	Set<String> flags; //存已獲得的拼圖
//	String 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_puzzle);
		
		//set bottom click event
		puzzle = (View)findViewById(R.id.puzzle);
		home_btn = (Button)puzzle.findViewById(R.id.home_btn);
		puzzle_btn = (Button)puzzle.findViewById(R.id.puzzle_btn);
		task_btn = (Button)puzzle.findViewById(R.id.task_btn);
				
		home_btn.setOnClickListener(mainListener);
		puzzle_btn.setOnClickListener(mainListener);
		task_btn.setOnClickListener(mainListener);
		
		flags = new HashSet<String>();
		
		frag1 = (ImageView)findViewById(R.id.frag1);
		frag2 = (ImageView)findViewById(R.id.frag2);
		frag3 = (ImageView)findViewById(R.id.frag3);
		frag4 = (ImageView)findViewById(R.id.frag4);
		frag5 = (ImageView)findViewById(R.id.frag5);
		frag6 = (ImageView)findViewById(R.id.frag6);
		frag7 = (ImageView)findViewById(R.id.frag7);
		frag8 = (ImageView)findViewById(R.id.frag8);
		frag9 = (ImageView)findViewById(R.id.frag9);
		puzzleEnabled();
		//因為只有五章拼圖
		frag1.setOnClickListener(imageListener);
		frag2.setOnClickListener(imageListener);
		frag3.setOnClickListener(imageListener);
		frag4.setOnClickListener(imageListener);
		frag5.setOnClickListener(imageListener);
//		frag6.setOnClickListener(imageListener);
//		frag7.setOnClickListener(imageListener);
//		frag8.setOnClickListener(imageListener);
//		frag9.setOnClickListener(imageListener);
	}
	
	protected void playMusic() {
		// TODO Auto-generated method stub
		MediaPlayer mp = MediaPlayer.create(PuzzleActivity.this, R.raw.push);
  	    mp.start();
	}
	
	private OnClickListener imageListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			coverUnknown();//先複蓋所有的拼圖
			getPuzzleDB();//取得已獲得的拼圖
			switch(v.getId()) {
			case R.id.frag1:
				puzzle1Event();	//第一組的九張拼圖
				puzzleNotEnabled();
				break;
			case R.id.frag2:
				puzzle2Event();//第二組的九張拼圖
				puzzleNotEnabled();
				break;
			case R.id.frag3:
				puzzle3Event();//第三組的九張拼圖
				puzzleNotEnabled();
				break;
			case R.id.frag4:
				puzzle4Event();//第四組的九張拼圖
				puzzleNotEnabled();
				break;
			case R.id.frag5:
				puzzle5Event();//第五組的九張拼圖
				puzzleNotEnabled();
				break;

			}
		}

		
	};
	
	private void puzzle2Event() {
		// TODO Auto-generated method stub
		for (int i=9;i<18;i++) {	//所屬第二組的拼圖編號
			for(String s:flags) {	//資料庫裡獲得的拼圖碎片
				int tmp = Integer.parseInt(s);
				if(i == tmp) {	//如果有相等的就拼上去
					switch(i) {
					case 9:
						frag1.setImageDrawable(getResources().getDrawable(R.drawable.puzzle9));
						break;
					case 10:
						frag2.setImageDrawable(getResources().getDrawable(R.drawable.puzzle10));
						break;
					case 11:
						frag3.setImageDrawable(getResources().getDrawable(R.drawable.puzzle11));
						break;
					case 12:
						frag4.setImageDrawable(getResources().getDrawable(R.drawable.puzzle12));
						break;
					case 13:
						frag5.setImageDrawable(getResources().getDrawable(R.drawable.puzzle13));
						break;
					case 14:
						frag6.setImageDrawable(getResources().getDrawable(R.drawable.puzzle14));
						break;
					case 15:
						frag7.setImageDrawable(getResources().getDrawable(R.drawable.puzzle15));
						break;
					case 16:
						frag8.setImageDrawable(getResources().getDrawable(R.drawable.puzzle16));
						break;
					case 17:
						frag9.setImageDrawable(getResources().getDrawable(R.drawable.puzzle17));
						break;
						
					}
				}
			}
		}
	}
	
	private void puzzle5Event() {
		// TODO Auto-generated method stub
		for (int i=36;i<45;i++) {
			for(String s:flags) {
				int tmp = Integer.parseInt(s);
				if(i == tmp) {
					switch(i) {
					case 36:
						frag1.setImageDrawable(getResources().getDrawable(R.drawable.puzzle36));
						break;
					case 37:
						frag2.setImageDrawable(getResources().getDrawable(R.drawable.puzzle37));
						break;
					case 38:
						frag3.setImageDrawable(getResources().getDrawable(R.drawable.puzzle38));
						break;
					case 39:
						frag4.setImageDrawable(getResources().getDrawable(R.drawable.puzzle39));
						break;
					case 40:
						frag5.setImageDrawable(getResources().getDrawable(R.drawable.puzzle40));
						break;
					case 41:
						frag6.setImageDrawable(getResources().getDrawable(R.drawable.puzzle41));
						break;
					case 42:
						frag7.setImageDrawable(getResources().getDrawable(R.drawable.puzzle42));
						break;
					case 43:
						frag8.setImageDrawable(getResources().getDrawable(R.drawable.puzzle43));
						break;
					case 44:
						frag9.setImageDrawable(getResources().getDrawable(R.drawable.puzzle44));
						break;
						
					}
				}
			}
		}
	}

	private void puzzle4Event() {
		// TODO Auto-generated method stub
		for (int i=27;i<35;i++) {
			for(String s:flags) {
				int tmp = Integer.parseInt(s);
				if(i == tmp) {
					switch(i) {
					case 27:
						frag1.setImageDrawable(getResources().getDrawable(R.drawable.puzzle27));
						break;
					case 28:
						frag2.setImageDrawable(getResources().getDrawable(R.drawable.puzzle28));
						break;
					case 29:
						frag3.setImageDrawable(getResources().getDrawable(R.drawable.puzzle29));
						break;
					case 30:
						frag4.setImageDrawable(getResources().getDrawable(R.drawable.puzzle30));
						break;
					case 31:
						frag5.setImageDrawable(getResources().getDrawable(R.drawable.puzzle31));
						break;
					case 32:
						frag6.setImageDrawable(getResources().getDrawable(R.drawable.puzzle32));
						break;
					case 33:
						frag7.setImageDrawable(getResources().getDrawable(R.drawable.puzzle33));
						break;
					case 34:
						frag8.setImageDrawable(getResources().getDrawable(R.drawable.puzzle34));
						break;
					case 35:
						frag9.setImageDrawable(getResources().getDrawable(R.drawable.puzzle35));
						break;
						
					}
				}
			}
		}
	}

	private void puzzle3Event() {
		// TODO Auto-generated method stub
		for (int i=18;i<27;i++) {
			for(String s:flags) {
				int tmp = Integer.parseInt(s);
				if(i == tmp) {
					switch(i) {
					case 18:
						frag1.setImageDrawable(getResources().getDrawable(R.drawable.puzzle18));
						break;
					case 19:
						frag2.setImageDrawable(getResources().getDrawable(R.drawable.puzzle19));
						break;
					case 20:
						frag3.setImageDrawable(getResources().getDrawable(R.drawable.puzzle20));
						break;
					case 21:
						frag4.setImageDrawable(getResources().getDrawable(R.drawable.puzzle21));
						break;
					case 22:
						frag5.setImageDrawable(getResources().getDrawable(R.drawable.puzzle22));
						break;
					case 23:
						frag6.setImageDrawable(getResources().getDrawable(R.drawable.puzzle23));
						break;
					case 24:
						frag7.setImageDrawable(getResources().getDrawable(R.drawable.puzzle24));
						break;
					case 25:
						frag8.setImageDrawable(getResources().getDrawable(R.drawable.puzzle25));
						break;
					case 26:
						frag9.setImageDrawable(getResources().getDrawable(R.drawable.puzzle26));
						break;
						
					}
				}
			}
		}
	}

	private void puzzle1Event() {
		for (int i=0;i<9;i++) {
			for(String s:flags) {
				int tmp = Integer.parseInt(s);
				if(i == tmp) {
					switch(i) {
					case 0:
						frag1.setImageDrawable(getResources().getDrawable(R.drawable.puzzle0));
						break;
					case 1:
						frag2.setImageDrawable(getResources().getDrawable(R.drawable.puzzle1));
						break;
					case 2:
						frag3.setImageDrawable(getResources().getDrawable(R.drawable.puzzle2));
						break;
					case 3:
						frag4.setImageDrawable(getResources().getDrawable(R.drawable.puzzle3));
						break;
					case 4:
						frag5.setImageDrawable(getResources().getDrawable(R.drawable.puzzle4));
						break;
					case 5:
						frag6.setImageDrawable(getResources().getDrawable(R.drawable.puzzle5));
						break;
					case 6:
						frag7.setImageDrawable(getResources().getDrawable(R.drawable.puzzle6));
						break;
					case 7:
						frag8.setImageDrawable(getResources().getDrawable(R.drawable.puzzle7));
						break;
					case 8:
						frag9.setImageDrawable(getResources().getDrawable(R.drawable.puzzle8));
						break;
						
					}
				}
			}
		}
	}

	private void getPuzzleDB() {
		PuzzleDB pdb = new PuzzleDB(this);
		SQLiteDatabase db = pdb.getWritableDatabase();
		Cursor cursor = db.query("INCOME", new String[] { "_ID",
		    	"PUZZLE_FRAG" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
	        do {
	        	flags.add(cursor.getString(1));
	          
	        } while (cursor.moveToNext());
	    }
		cursor.close();
		db.close();
		
	}
	
	//所有牌不可點擊
	private void puzzleNotEnabled() {
		frag1.setEnabled(false);
		frag2.setEnabled(false);
		frag3.setEnabled(false);
		frag4.setEnabled(false);
		frag5.setEnabled(false);
		frag6.setEnabled(false);
		frag7.setEnabled(false);
		frag8.setEnabled(false);
		frag9.setEnabled(false);
	}
	//所有牌可點擊
	private void puzzleEnabled() {
		frag1.setEnabled(true);
		frag2.setEnabled(true);
		frag3.setEnabled(true);
		frag4.setEnabled(true);
		frag5.setEnabled(true);
		frag6.setEnabled(true);
		frag7.setEnabled(true);
		frag8.setEnabled(true);
		frag9.setEnabled(true);
	}
	//複蓋所有的牌
	private void coverUnknown() {
		frag1.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag2.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag3.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag4.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag5.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag6.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag7.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag8.setImageDrawable(getResources().getDrawable(R.drawable.none));
		frag9.setImageDrawable(getResources().getDrawable(R.drawable.none));
	}
	
	//下方button
	private OnClickListener mainListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (v.getId())
		    {      
		      case R.id.home_btn:  
		    	  playMusic();
		    	intent = new Intent(PuzzleActivity.this, MainActivity.class);
			    startActivity(intent);
		    	break;
		      case R.id.puzzle_btn: 
		    	//don't do
		    	break;
		      case R.id.task_btn:
		    	  playMusic();
		    	intent = new Intent(PuzzleActivity.this, EventActivity.class);
			    startActivity(intent);
			    break;
		    }
		}
	
	};

}
