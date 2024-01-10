package com.exampledemo.parsaniahardik.generate_qr_code;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	Button adminbtn1,userbtn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adminbtn1=(Button)findViewById(R.id.admbtn);
		userbtn1=(Button)findViewById(R.id.userbtn);
		
		
		adminbtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(MainActivity.this,AdminLoginActivity.class));
					
				
				
			}
			
		});
		userbtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(MainActivity.this,LoginActivity.class));		
				
			}
			
		});
	}


}
