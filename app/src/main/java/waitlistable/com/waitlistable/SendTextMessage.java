package waitlistable.com.waitlistable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


import static waitlistable.com.waitlistable.HomeScreen.KEY_NUMBER_SEND;

public class SendTextMessage extends Activity {

    public String phNumber;
    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text_message);
        TextView number = findViewById(R.id.number);

        Intent i = getIntent();
        if (null != i) {
            phNumber = i.getStringExtra(KEY_NUMBER_SEND);
        }
        number.setText(phNumber);
    }

    public void onSelect(View view) {
        TextView messageToSend = findViewById(R.id.display_message);
        Spinner selChoice = findViewById(R.id.send_text_choice);
        String selected = String.valueOf(selChoice.getSelectedItem());

        if (selected.equals("Default wait")) {
            message = "Thank you for waiting, your expected wait time is 20 minutes.";
        }
        else if (selected.equals("Default ready")) {
            message = "Your table is now ready, please return to the hostess station for seating.";
        }

        messageToSend.setText(message);

    }

    public void onSend(View view) {
        android.telephony.SmsManager sms = android.telephony.SmsManager.getDefault();
        sms.sendTextMessage(phNumber, null, message, null, null);
        Intent in = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(in);
    }
}
