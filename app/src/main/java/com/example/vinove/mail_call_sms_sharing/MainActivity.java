package com.example.vinove.mail_call_sms_sharing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMobileNumber;
    private EditText editTextMessage;
    private EditText editTextToAddress;
    private EditText editTextSubject;
    private EditText editTextEmailMessage;
    private  Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMobileNumber = findViewById(R.id.editTextMobileNumber);
        editTextMessage = findViewById(R.id.editTextMessage);
        editTextToAddress = findViewById(R.id.editTextToAddress);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextEmailMessage = findViewById(R.id.editTextEmailMessage);

    }

    public void SendSMS(View view) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(editTextMobileNumber.getText().toString(), null, editTextMessage.getText().toString(), null, null);

    }

    public void Call(View view) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + editTextMobileNumber.getText().toString())); // we are sending a telephony type of data
        startActivity(intent);
    }

    public void Email(View view) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{editTextToAddress.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT,editTextSubject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,editTextEmailMessage.getText().toString());
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("message/rfc822"); // enable MIME
        startActivity(intent.createChooser(intent,"Select Any Email Client"));
    }

    public void attach(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,200);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
    }
}
