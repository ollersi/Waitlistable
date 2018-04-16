package waitlistable.com.waitlistable;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

import static waitlistable.com.waitlistable.AddCustomer.KEY_NAME;
import static waitlistable.com.waitlistable.AddCustomer.KEY_PARTY;
import static waitlistable.com.waitlistable.AddCustomer.KEY_PHONE;

public class HomeScreen extends Activity {

    public static ArrayList<Customer> customers = new ArrayList<>();
    Customer customer = new Customer();
    public static final String KEY_NUMBER_SEND = "number";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        String name = "";
        String party = "";
        String phone = "";


        Intent intent = getIntent();
        if (null != intent) {
            name = intent.getStringExtra(KEY_NAME);
            party = intent.getStringExtra(KEY_PARTY);
            phone = intent.getStringExtra(KEY_PHONE);
        }
        customer.setName(name);
        customer.setParty(party);
        customer.setPhoneNum(phone);
        customers.add(customer);

        TableLayout table = findViewById(R.id.cust_table);
        TableRow rowH = new TableRow(this);
        TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        rowH.setLayoutParams(layout);
        TextView nHead = new TextView(this);
        TextView pHead = new TextView(this);
        TextView phHead = new TextView(this);
        TextView sendHead = new TextView(this);
        TextView removeHead = new TextView(this);
        nHead.setText(R.string.col1);
        TextViewCompat.setTextAppearance(nHead, android.R.style.TextAppearance_Large);
        nHead.setGravity(Gravity.CENTER_HORIZONTAL);
        nHead.setPadding(15, 1, 15, 1);
        pHead.setText(R.string.col2);
        TextViewCompat.setTextAppearance(pHead, android.R.style.TextAppearance_Large);
        pHead.setGravity(Gravity.CENTER_HORIZONTAL);
        pHead.setPadding(15, 1, 15, 1);
        phHead.setText(R.string.col3);
        TextViewCompat.setTextAppearance(phHead, android.R.style.TextAppearance_Large);
        phHead.setGravity(Gravity.CENTER_HORIZONTAL);
        phHead.setPadding(15, 1, 15, 1);
        sendHead.setText(R.string.col4);
        TextViewCompat.setTextAppearance(sendHead, android.R.style.TextAppearance_Large);
        sendHead.setGravity(Gravity.CENTER_HORIZONTAL);
        sendHead.setPadding(2, 1, 2, 1);
        removeHead.setText(R.string.col5);
        TextViewCompat.setTextAppearance(removeHead, android.R.style.TextAppearance_Large);
        removeHead.setGravity(Gravity.CENTER_HORIZONTAL);
        removeHead.setPadding(2, 1, 2, 1);
        rowH.addView(nHead);
        rowH.addView(pHead);
        rowH.addView(phHead);
        rowH.addView(sendHead);
        rowH.addView(removeHead);
        //rowH.setBackgroundColor(Color.BLUE);
        table.addView(rowH);

        if (customers.size() > 0) {
            for (int i = 0; i < customers.size(); i++) {
                //TableLayout table = findViewById(R.id.cust_table);
                TableRow row = new TableRow(this);
                //TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                row.setLayoutParams(layout);
                TextView n = new TextView(this);
                TextView p = new TextView(this);
                TextView ph = new TextView(this);
                Button send = new Button(this);
                Button del = new Button(this);
                n.setText(customers.get(i).getName());
                p.setText(customers.get(i).getParty());
                ph.setText(customers.get(i).getPhoneNum());
                send.setText(R.string.send_message);
                send.setContentDescription(customers.get(i).getPhoneNum());
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sendNumber = view.getContentDescription().toString().trim();
                        Intent intentSend = new Intent(getApplicationContext(), SendTextMessage.class);
                        intentSend.putExtra(KEY_NUMBER_SEND, sendNumber);
                        startActivity(intentSend);
                    }
                });
                del.setText(R.string.delete);
                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                row.addView(n);
                row.addView(p);
                row.addView(ph);
                row.addView(send);
                row.addView(del);
                row.setBackgroundColor(Color.LTGRAY);
                //table.removeView(row);
                table.addView(row);

                //TextView n = (TextView) findViewById(R.id.cust_view);
                //n.setText(customers.get(i).getName());

                //TextView p = (TextView) findViewById(R.id.party_view);
                //p.setText(customers.get(i).getParty());

                //TextView ph = (TextView) findViewById(R.id.phone_view);
                //ph.setText(customers.get(i).getPhoneNum());
            }
        }





    }
    //add new waiting customer information
    public void onAddCustomer(View view) {
        Intent intent = new Intent(this, AddCustomer.class);
        startActivity(intent);
    }



}
