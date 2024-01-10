package com.exampledemo.parsaniahardik.generate_qr_code;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserActivity2 extends Activity {

	Button choosebtn1,mycollegebtn1,markbtn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainpage2);
		

		
		choosebtn1=(Button)findViewById(R.id.hpbtn);

		mycollegebtn1=(Button)findViewById(R.id.stdbtn);
		
		choosebtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(UserActivity2.this, AdminActivity2.class));
					
				
				
			}
			
		});
		
		mycollegebtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(UserActivity2.this, RegisterActivity2.class));
					
				
				
			}
			
		});
		

		
	}



}
