package com.exampledemo.parsaniahardik.generate_qr_code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity2 extends GenerateQR {

	EditText edtName, edtRegno,edtaddr,edtMobileNo, edtLoc, edtPrize,edtvisit;
Button btnSubmit,btnSubmit1;
Connection conn;

private String name, regno, phoneno, location,prize,addr,visittime;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visitor_details2);
		
		edtName = (EditText) findViewById(R.id.v_name);
		edtRegno = (EditText) findViewById(R.id.v_gender);

		edtMobileNo = (EditText) findViewById(R.id.v_mobile);
		edtaddr = (EditText) findViewById(R.id.v_address);
		edtLoc = (EditText) findViewById(R.id.v_city);
		//edtEmail = (EditText) findViewById(R.id.registeraddress);
		edtPrize = (EditText) findViewById(R.id.v_wtv);
		edtvisit = (EditText) findViewById(R.id.v_dt);
		
		
	
		btnSubmit = (Button) findViewById(R.id.register_btn_reg);
		btnSubmit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				name = edtName.getText().toString();
				regno = edtRegno.getText().toString();
				addr = edtaddr.getText().toString();
				phoneno = edtMobileNo.getText().toString();
				location = edtLoc.getText().toString();
				//email = edtEmail.getText().toString();
				prize = edtPrize.getText().toString();
				visittime = edtvisit.getText().toString();
				//competition = edtComp.getText().toString();
				
				//ssc = edtssc.getText().toString();
				//hsc = edthsc.getText().toString();
				try {
					if(verify())
					{
						new QuerySQL().execute();


					}
					
		
					} catch (Exception e) {
		        Log.e("ERRO",e.getMessage());
				}

				
			}
		});
		
		btnSubmit1 = (Button) findViewById(R.id.register_btn_cancel);
		btnSubmit1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), UserActivity2.class);
				startActivity(i);
				
				
			}
		});

	}
	
	public boolean verify()
	{
//		EditText name, userName, password, cpassword, email, phoneNumber;
		Boolean ret=true;
		if(edtName.getText().toString().length()<1){edtName.setError("Field Required");ret=false;}
		if(edtRegno.getText().toString().length()<1){edtRegno.setError("Field Required");ret=false;}
		if(edtLoc.getText().toString().length()<1){edtLoc.setError("Field Required");ret=false;}
		if(edtPrize.getText().toString().length()<1){edtPrize.setError("Field Required");ret=false;}
		if(edtaddr.getText().toString().length()<1){edtaddr.setError("Field Required");ret=false;}
		//if(!edtEmail.getText().toString().contains("@")){edtEmail.setError("E-Mail ID Invalid");ret=false;}
		//if(edtEmail.getText().toString().length()<1){edtEmail.setError("Field Required");ret=false;}
		if(edtMobileNo.getText().toString().length()<10){edtMobileNo.setError("Invalid Phone Number");ret=false;}//It will Set but ok it wont be visible
		if(edtMobileNo.getText().toString().length()<1){edtMobileNo.setError("Field Required");ret=false;}
		if(edtvisit.getText().toString().length()<1){edtvisit.setError("Field Required");ret=false;}
		
		String expression = "^([0-9\\+]|\\(\\d{0,1}\\))[0-9\\-\\. ]{0,15}$";
        CharSequence inputString = edtMobileNo.getText().toString();
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
		
        }
        else
        {
        	edtMobileNo.setError("Invalid Number");ret=false;
        }
		
		
		return ret;
	}


	public class QuerySQL extends AsyncTask<String, Void, Boolean> {

		ProgressDialog pDialog ;
		Exception error;
		ResultSet rs;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(AdminActivity2.this);
			pDialog.setTitle("Visitor Details");
			pDialog.setMessage("Adding Visitor details...");
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(String... args) {





			try {


				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://103.10.235.53:3306/automaticstudentid","root","password");
			} catch (SQLException se) {
				Log.e("ERRO1",se.getMessage());
			} catch (ClassNotFoundException e) {
				Log.e("ERRO2",e.getMessage());
			} catch (Exception e) {
				Log.e("ERRO3",e.getMessage());
			}


			try {
				Statement statement = conn.createStatement();
				int success=statement.executeUpdate("insert into visitordetails values('"+name+"','"+regno+"','"+addr+"','"+phoneno+"','"+location+"','"+prize+"','"+visittime+"')");



				if (success >= 1) {
					// successfully created product

					return true;
					// closing this screen
//					finish();
				} else {
					// failed to create product
					return false;
				}



				// Toast.makeText(getBaseContext(),
				// "Successfully Inserted.", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				error = e;
				return false;
//				Toast.makeText(getBaseContext(),"Successfully Registered...", Toast.LENGTH_LONG).show();
			}


		}

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Boolean result1) {
			pDialog.dismiss ( ) ;
			if(result1)
			{

				String qrinfo = "Name : "+name+"\n"+"Reg No : "+regno+"\n"+"Address : "+addr+"\n"+"Mobile : "+phoneno+"\n"+"Chase No : "+location+"\n"+"Insurance Date : "+prize;

				Intent intent=new Intent(AdminActivity2.this, GenerateQR.class);
				intent.putExtra("qrinfo", qrinfo);
				//intent.putExtra("year", year);
				//intent.putExtra("dept", dept);


				startActivity(intent);
				Toast.makeText(getBaseContext(),"Successfully QR generated." ,Toast.LENGTH_LONG).show();


			}else
			{
				if(error!=null)
				{
					Toast.makeText(getBaseContext(),error.getMessage().toString() ,Toast.LENGTH_LONG).show();
					Log.d("Error not null...", error.getMessage().toString());
				}
				else
				{
					Toast.makeText(getBaseContext(),"Not inserted!!!" ,Toast.LENGTH_LONG).show();
				}
			}
			super.onPostExecute(result1);
		}
	}


}
