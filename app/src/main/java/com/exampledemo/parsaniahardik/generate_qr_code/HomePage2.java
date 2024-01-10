package com.exampledemo.parsaniahardik.generate_qr_code;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class HomePage2 extends Activity {
	int splashTime = 2500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page2);
		Thread thread = new Thread() {
			public void run() {
				try {
					synchronized (this) {
						wait(splashTime);
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					startActivity(new Intent(HomePage2.this,
							UserActivity2.class));
					finish();
				}
			}
		};
		thread.start();
		 
	}

	
}
