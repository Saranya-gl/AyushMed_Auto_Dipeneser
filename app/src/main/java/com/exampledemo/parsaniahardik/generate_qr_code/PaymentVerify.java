package com.exampledemo.parsaniahardik.generate_qr_code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.json.JSONException;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentVerify extends MainActivity  {

	Connection conn;
	EditText password,hostIP, edtquant, edtcardno, edtcvv, edtdate, edtholdername;
	Button signin,signup;
	String user,pass,user1,pass1, quant, username, price;
	String s1,s2,s3,s4, cardno,cvvno,holdername,date;
	int totprice,med1=0,med2=0,med3=0,med4=0,med5=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		
		signin=(Button)findViewById(R.id.paybtn);
		signup=(Button)findViewById(R.id.cancelbtn);

		SharedPreferences preferences1=getSharedPreferences("username", Context.MODE_PRIVATE);
		username=preferences1.getString("username",null);



		edtcardno=(EditText)findViewById(R.id.cardno);
		
		edtcvv=(EditText)findViewById(R.id.cvvno);
		
		edtdate=(EditText)findViewById(R.id.date);
		
		edtholdername=(EditText)findViewById(R.id.holdername);
		
		
//		conn=CONN();
		signin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				cardno=edtcardno.getText().toString();
				cvvno=edtcvv.getText().toString();
				date=edtdate.getText().toString();
				holdername=edtholdername.getText().toString();
				
				
				//SharedPreferences preferences1=getSharedPreferences("username", Context.MODE_PRIVATE);
				//username=preferences1.getString("username",null);
				
				
				new QuerySQL().execute();
				
				
						}
			
		});
		
		
		signup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(PaymentVerify.this,UserActivity.class));
				
			}
			
		});
	}

	
	
	public class QuerySQL extends AsyncTask<String, Void, Boolean> {

		ProgressDialog pDialog ;
		Exception error;
		ResultSet rs;
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        
	        pDialog = new ProgressDialog(PaymentVerify.this);
	        pDialog.setTitle("Authentication");
	        pDialog.setMessage("Verifying your card details...");
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }

	    @Override
	    protected Boolean doInBackground(String... args) {
	    	
	    	
	    	
	    	
			
			try {
				
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://mysql-75344-0.cloudclusters.net:18880/medicinedispenserqr","admin","cU5zYktH");
			} catch (SQLException se) {
				Log.e("ERRO1",se.getMessage());
			} catch (ClassNotFoundException e) {
				Log.e("ERRO2",e.getMessage());
			} catch (Exception e) {
			    Log.e("ERRO3",e.getMessage());
			}
			

			try {
				String COMANDOSQL="select * from carddetails where cardno ='"+cardno+"' && cvvno ='"+cvvno+"' && date ='"+date+"' && holdername ='"+holdername+"'";
				Statement statement = conn.createStatement();
				rs = statement.executeQuery(COMANDOSQL);
			if(rs.next()){
				int count=0;
				String COMANDOSQL1="select * from cartitems where username='"+username+"'";
				Statement statement1 = conn.createStatement();
				ResultSet rs1 = statement1.executeQuery(COMANDOSQL1);
				while(rs1.next()){
					if(count==0)
					{
						med1=rs1.getInt(3);
					}
					if(count==1)
					{
						med2=rs1.getInt(3);
					}
					if(count==2)
					{
						med3=rs1.getInt(3);
					}
					if(count==3)
					{
						med4=rs1.getInt(3);
					}
					if(count==4)
					{
						med5=rs1.getInt(3);
					}

					count++;
				}


				return true;
			}

			return false;
				
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
                
	    		Toast.makeText(getBaseContext(),"Payment Success...", Toast.LENGTH_LONG).show();

				String qrinfo = med1+","+med2+","+med3+","+med4+","+med5+"\n";

				Intent intent=new Intent(PaymentVerify.this, GenerateQR.class);
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
	    		}
	    		else
	    		{
	    			Toast.makeText(getBaseContext(),"Check your credentials!!!" ,Toast.LENGTH_LONG).show();
	    		}
	    	}
	    	super.onPostExecute(result1);
	    }
	}
	
	
	


}
