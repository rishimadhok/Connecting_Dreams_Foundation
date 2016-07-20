package com.example.rishimadhok.cdf.Activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.text.Html;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.rishimadhok.cdf.Base.BaseActivity;
import com.example.rishimadhok.cdf.R;

public class ContactUs extends BaseActivity {

    EditText message,name,phone_no,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.contact);

        message = (EditText) findViewById(R.id.et_message);
        name = (EditText) findViewById(R.id.et_name);
        phone_no = (EditText) findViewById(R.id.et_phone_number);
        email = (EditText) findViewById(R.id.et_email);

    }

    public void submit(View view)
    {
        String test = "Feedback from " + name.getText().toString() + " ";
        String phone = "whose phone number is: "+phone_no.getText().toString()+ " ";
        String text = test + phone+ "and message is: "+ message.getText().toString();
        Intent i = new Intent(android.content.Intent.ACTION_SEND);
        Intent chooser;
        i.setData(Uri.parse("mailto:"));
        String[] to={"rishi_madhok@hotmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL, to);
        i.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");
        i.putExtra(Intent.EXTRA_TEXT, text);
        i.setType("message/rfc822");
        chooser = Intent.createChooser(i, "Send email");
        startActivity(chooser);

    }

//    public void location(View view)
//    {
//        Intent i= new Intent(android.content.Intent.ACTION_VIEW);
//        Intent chooser;
//        i.setData(Uri.parse("geo:28.6571699,77.3513706 "));
//        chooser = Intent.createChooser(i, "Launch Maps");
//        startActivity(chooser);
//    }

    public boolean isGoogleMapsInstalled()
    {
        try
        {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
}
