package waitlistable.com.waitlistable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import static waitlistable.com.waitlistable.HomeScreen.KEY_NUMBER_SEND;
import static waitlistable.com.waitlistable.HomeScreen.KEY_WAIT_TIME;

public class SendTextMessage extends Activity {
    //private static final int SEND_SMS_CODE = 23;
    public String phNumber;
    public String message;
    public String waitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text_message);
        TextView number = findViewById(R.id.number);

        Intent i = getIntent();
        if (null != i) {
            phNumber = i.getStringExtra(KEY_NUMBER_SEND);
            waitTime = i.getStringExtra(KEY_WAIT_TIME);
        }
        number.setText(phNumber);

        Button startBtn = findViewById(R.id.final_send_message);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });
    }

    public void onSelect(View view) {
        TextView messageToSend = findViewById(R.id.display_message);
        Spinner selChoice = findViewById(R.id.send_text_choice);
        String selected = String.valueOf(selChoice.getSelectedItem());

        if (selected.equals("Default wait")) {
            message = "Thank you for waiting, your expected wait time is " + waitTime + " minutes.";
        } else if (selected.equals("Default ready")) {
            message = "Your table is now ready, please return to the hostess station for seating.";
        }

        messageToSend.setText(message);

    }

    protected void sendSMS() {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , phNumber);
        smsIntent.putExtra("sms_body"  , message);

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendTextMessage.this,
                    "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }



    public void onSend(View view) {
        //android.telephony.SmsManager sms = android.telephony.SmsManager.getDefault();
        //sms.sendTextMessage(phNumber, null, message, null, null);
        //Intent in = new Intent(getApplicationContext(), HomeScreen.class);
        //startActivity(in);
    }
}
