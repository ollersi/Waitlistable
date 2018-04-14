package waitlistable.com.waitlistable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;



public class AddCustomer extends Activity {

    public static ArrayList<Customer> customers = new ArrayList<>();
    private EditText nameField;
    private EditText partyField;
    private EditText phoneField;
    public String lastName;
    public String partyNumber;
    public String phoneNumber;

    public static final String KEY_NAME= "cust_name";
    public static final String KEY_PARTY = "party_no";
    public static final String KEY_PHONE = "phone_no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        nameField = findViewById(R.id.EditLastName);

        partyField = findViewById(R.id.EditPartyNumber);

        phoneField = findViewById(R.id.EditPhoneNumber);

    }
    //save entered information
    public void onSaveInfo(View view) {
        lastName = nameField.getText().toString().trim();
        partyNumber = partyField.getText().toString().trim();
        phoneNumber = phoneField.getText().toString().trim();
        Customer customer = new Customer();
        customer.setName(lastName);
        customer.setParty(partyNumber);
        customer.setPhoneNum(phoneNumber);
        customers.add(customer);
        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
        i.putExtra(KEY_NAME, customer.getName());
        i.putExtra(KEY_PARTY, customer.getParty());
        i.putExtra(KEY_PHONE, customer.getPhoneNum());

        startActivity(i);

    }



}
