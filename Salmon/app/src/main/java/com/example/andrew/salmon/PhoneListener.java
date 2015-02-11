package com.example.andrew.salmon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.widget.Toast;

import com.example.salmon.R;

/**
 * Created by Andrew on 2/9/2015.
 */
public class PhoneListener extends PhoneStateListener{
    private Context context;
    public PhoneListener(Context _context){
        this.context = _context;
    }
    public void onCallStateChanged(int state, String incomingNumber) {

        // Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

        // state = 1 means when phone is ringing
        if (state == 2) {

            String msg = " New Phone Call Event. Incoming Number : "+incomingNumber;
            int mID = 1;
            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notificationIntent = new Intent(context, afterCall.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                    context).setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("Phone Call Made")
                    .setContentText("Touch to Take Survey")
                    .setSound(alarmSound)
                    .setAutoCancel(true).setWhen(when)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
            notificationManager.notify(mID,mNotifyBuilder.build());
            mID++;

        }
    }
}
