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

public class AdminActivity extends Activity {

	Button addbtn1,viewbtn1,presbtn,logbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_main);
		
		addbtn1=(Button)findViewById(R.id.addmedbtn);
		viewbtn1=(Button)findViewById(R.id.viewmedbtn);
		presbtn=(Button)findViewById(R.id.presbtn);
		logbtn=(Button)findViewById(R.id.logbtn);
		
		
		addbtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(AdminActivity.this,AddMedicineActivity.class));
					
				
				
			}
			
		});
		viewbtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(AdminActivity.this,AddDrugActivity.class));		
				
			}
			
		});
		presbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(AdminActivity.this,PrescriptionActivity.class));

			}

		});
		logbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(AdminActivity.this,MainActivity.class));

			}

		});
	}



}
