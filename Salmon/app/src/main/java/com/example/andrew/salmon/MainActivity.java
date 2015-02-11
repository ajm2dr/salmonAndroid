package com.example.andrew.salmon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.example.salmon.R;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		Button randomTime = (Button) findViewById(R.id.randomTime);
		randomTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, randomTimePrompt.class);
				MainActivity.this.startActivity(i);
			}
		});
		
		Button afterCall = (Button) findViewById(R.id.afterCall);
		afterCall.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, afterCall.class);
				MainActivity.this.startActivity(i);
			}
		});
		
		Button endofday = (Button) findViewById(R.id.endButton);
		endofday.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, endOfDay.class);
				MainActivity.this.startActivity(i);
			}
		});
		
		Button data = (Button) findViewById(R.id.data);
		data.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, data.class);
				MainActivity.this.startActivity(i);
			}
		});

        Button start = (Button) findViewById(R.id.startService);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setUpNotif();
                Toast.makeText(getApplicationContext(),"Notification Service Started",Toast.LENGTH_LONG).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void setUpNotif(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
        intent1.putExtra("requestCode", 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 14);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        Intent intent2 = new Intent(MainActivity.this, AlarmReceiver.class);
        intent2.putExtra("requestCode", 1);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager afternoon = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        afternoon.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
    }
}
