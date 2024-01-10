package com.exampledemo.parsaniahardik.generate_qr_code;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DoctorPrescription extends Activity {

	Button adminbtn1,userbtn1,userbtn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctorprescriptionuser);
		
		adminbtn1=(Button)findViewById(R.id.admbtn);
		userbtn2=(Button)findViewById(R.id.userbtn2);
		userbtn1=(Button)findViewById(R.id.userbtn);
		
		
		adminbtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(DoctorPrescription.this,CartView.class));
					
				
				
			}
			
		});
		userbtn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(DoctorPrescription.this,PaymentVerify.class));

			}

		});
		userbtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(DoctorPrescription.this,MainActivity.class));
				
			}
			
		});
	}


}
