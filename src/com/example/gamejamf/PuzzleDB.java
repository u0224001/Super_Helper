package com.example.gamejamf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleDB extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME="SavePuzzle";
	private Context m_context;
	private static final String INCOME_CREATE_DDL = 
		      "CREATE TABLE INCOME ("
		      + "_ID INTEGER PRIMARY KEY," 
		      + "PUZZLE_FRAG TEXT);";
	private static final String INCOME_DELETE_DDL = 
		      "DROP TABLE IF EXISTS INCOME;";
	public PuzzleDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		
	}
	public PuzzleDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		m_context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(INCOME_CREATE_DDL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(INCOME_DELETE_DDL);
		db.execSQL(INCOME_CREATE_DDL);
	}

}
