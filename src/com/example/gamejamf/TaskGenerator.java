package com.example.gamejamf;

import java.util.Random;

import android.util.Log;

public class TaskGenerator {
	String[] task = new String[10];
	Random rand = new Random();
	public TaskGenerator() {

		task[0] = "Dining with family";
		task[1] = "Dialogue with friends.";
		task[2] = "Sweep the floor";
		task[3] = "Cleaning toilets";
		task[4] = "Wash dishes";
		task[5] = "Clean the kitchen";
		task[6] = "Chest movement one minute";
		task[7] = "Go to places never visited";
		task[8] = "Take a walk";
		task[9] = "Try to make meals";		
		confusion();
	}
	
	public String getTask(int i) {
		return task[i];
	}
	
	//讓任務混亂
	public void confusion() {
		
		for(int i=0;i<10;i++) {
			int p = rand.nextInt(10); //隨機的位置
			String temp = task[i]; //暫存
			task[i] = task[p]; //交換
			task[p] = temp;
		}
		
		
		for(String i:task) {
			Log.i("task", "task:" + i);
		}
	}
}
