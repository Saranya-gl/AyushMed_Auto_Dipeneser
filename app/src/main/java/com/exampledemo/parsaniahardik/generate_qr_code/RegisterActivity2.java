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

public class RegisterActivity2 extends GenerateQR {

	EditText edtName, edtMobileNo, edtEmail, edtusername,	edtPassword, edtprof,edtfathername,edtaddr,edtcaste,edtreligion,edtssc,edthsc;
Button btnSubmit,btnSubmit1;
Connection conn;

private String name, mobilenumber, email, username,prof, password,fathername,addr,caste,religion,ssc,hsc;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_reg2);
		
		edtName = (EditText) findViewById(R.id.register_name);
		edtusername = (EditText) findViewById(R.id.register_username);
		edtEmail = (EditText) findViewById(R.id.register_email);
		edtPassword = (EditText) findViewById(R.id.register_password);
		edtMobileNo = (EditText) findViewById(R.id.register_phno);
		edtprof = (EditText) findViewById(R.id.register_prof);
		
		edtfathername = (EditText) findViewById(R.id.register_fathername);
		edtaddr = (EditText) findViewById(R.id.register_addr);
		edtcaste = (EditText) findViewById(R.id.register_caste);
		edtreligion = (EditText) findViewById(R.id.register_religion);
		//edtssc = (EditText) findViewById(R.id.register_ssc);
		//edthsc = (EditText) findViewById(R.id.register_hsc);
	
		btnSubmit = (Button) findViewById(R.id.register_btn_reg);
		btnSubmit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				name = edtName.getText().toString();
				username = edtusername.getText().toString();
				email = edtEmail.getText().toString();
				password = edtPassword.getText().toString();
				mobilenumber = edtMobileNo.getText().toString();
				prof = edtprof.getText().toString();
				
				fathername = edtfathername.getText().toString();
				addr = edtaddr.getText().toString();
				caste = edtcaste.getText().toString();
				religion = edtreligion.getText().toString();
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
		if(edtusername.getText().toString().length()<1){edtusername.setError("Field Required");ret=false;}
		if(edtPassword.getText().toString().length()<1){edtPassword.setError("Field Required");ret=false;}
		if(edtprof.getText().toString().length()<1){edtprof.setError("Field Required");ret=false;}
		if(!edtPassword.getText().toString().equals(edtprof.getText().toString())){edtPassword.setError("Password not same");ret=false;}
		
		if(edtfathername.getText().toString().length()<1){edtfathername.setError("Field Required");ret=false;}
		if(edtaddr.getText().toString().length()<1){edtaddr.setError("Field Required");ret=false;}
		if(edtcaste.getText().toString().length()<1){edtcaste.setError("Field Required");ret=false;}
		if(edtreligion.getText().toString().length()<1){edtreligion.setError("Field Required");ret=false;}
		//if(edtssc.getText().toString().length()<1){edtssc.setError("Field Required");ret=false;}
		//if(edthsc.getText().toString().length()<1){edthsc.setError("Field Required");ret=false;}
				
		if(!edtEmail.getText().toString().contains("@")){edtEmail.setError("E-Mail ID Invalid");ret=false;}
		if(edtEmail.getText().toString().length()<1){edtEmail.setError("Field Required");ret=false;}
		if(edtMobileNo.getText().toString().length()<10){edtMobileNo.setError("Invalid Phone Number");ret=false;}//It will Set but ok it wont be visible
		if(edtMobileNo.getText().toString().length()<1){edtMobileNo.setError("Field Required");ret=false;}
		
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
	        
	        pDialog = new ProgressDialog(RegisterActivity2.this);
	        pDialog.setTitle("Registration");
	        pDialog.setMessage("Registering your credentials...");
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
				int success=statement.executeUpdate("insert into studentdetails values('"+name+"','"+username+"','"+password+"','"+fathername+"','"+addr+"','"+caste+"','"+religion+"','"+email+"','"+mobilenumber+"')");
			
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
				String qrinfo = "Name : "+name+"\n"+"Username : "+username+"\n"+"Address : "+addr+"\n"+"Mobile : "+mobilenumber+"\n"+"Email : "+email+"\n"+"Fathername : "+fathername;

				Intent intent=new Intent(RegisterActivity2.this, GenerateQR.class);
				intent.putExtra("qrinfo", qrinfo);
				//intent.putExtra("year", year);
				//intent.putExtra("dept", dept);


				startActivity(intent);

	    		Toast.makeText(getBaseContext(),"Successfully created a student." ,Toast.LENGTH_LONG).show();
					
//					System.out.println("ELSE(JSON) LOOP EXE");
					try {// try3 open
						
						Intent i = new Intent(getApplicationContext(),
								UserActivity2.class);
						startActivity(i);		
						
					} catch (Exception e1) {
						Toast.makeText(getBaseContext(), e1.toString(),
								Toast.LENGTH_LONG).show();

					}					
				
            
	    	}else
	    	{
	    		if(error!=null)
	    		{
	    			Toast.makeText(getApplicationContext(),error.toString() ,Toast.LENGTH_LONG).show();
	    			Log.d("Error not null...", error.toString());
	    		}
	    		else
	    		{
	    			Toast.makeText(getBaseContext(),"Not crreated your credentials!!!" ,Toast.LENGTH_LONG).show();
	    		}
	    	}
	    	super.onPostExecute(result1);
	    }
	}


}
