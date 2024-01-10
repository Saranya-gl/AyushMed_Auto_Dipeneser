package com.exampledemo.parsaniahardik.generate_qr_code;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrescriptionActivity extends MainActivity  {

	Connection conn;
	EditText username,password,hostIP;
	Button signin,signup;
	String user,pass,user1,pass1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prescription);
		signin=(Button)findViewById(R.id.loginbtn);
		signup=(Button)findViewById(R.id.regbtn);
		
		
		username=(EditText)findViewById(R.id.edtloginusername);

//		conn=CONN();
		signin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				user=username.getText().toString();

				SharedPreferences.Editor editor =getSharedPreferences("username",Context.MODE_PRIVATE).edit();
                editor.putString("username",user);
                editor.commit();
                editor.apply();

				startActivity(new Intent(PrescriptionActivity.this,OnlineShopActivity.class));

				
			}
			
		});
		signup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(PrescriptionActivity.this,AdminActivity.class));
				
			}
			
		});
	}

	



}
