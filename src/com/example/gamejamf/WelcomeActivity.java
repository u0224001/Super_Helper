package com.example.gamejamf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class WelcomeActivity extends Activity {
	Button go;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadding);
		go = (Button)findViewById(R.id.go);
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		
		
	}

}
