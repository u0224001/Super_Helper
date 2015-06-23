package com.example.gamejamf;

import java.util.Random;

public class DrawnPuzzles {
	Random rand = new Random();
	int[] puzzle = new int[45];
	
	public DrawnPuzzles() {
		for (int i=0;i<puzzle.length;i++) {
			puzzle[i] = i;
		}
	}
	//洗牌
	public void shuffle() {
		
		for(int i=0;i<27;i++) {
			int p = rand.nextInt(45); 
			int temp = puzzle[i]; 
			puzzle[i] = puzzle[p]; 
			puzzle[p] = temp;
		}
		
	}
	
	//抽牌
	public int draw() {
		return puzzle[rand.nextInt(45)];
		//return puzzle[i];
	}
	
	

}
