package com.example.rishimadhok.cdf.Activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rishimadhok.cdf.Base.BaseActivity;
import com.example.rishimadhok.cdf.R;
import com.github.jorgecastillo.FillableLoader;

public class AboutUs extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.drawable.info_about_icon);

        FillableLoader fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader_about);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fababoutus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGoogleMapsInstalled()) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=28.6571699,77.3513706"));
                    startActivity(intent);


                } else {
                    Snackbar.make(view, "Google Maps not installed", Snackbar.LENGTH_LONG).show();

                }
            }
        });

        fillableLoader.setSvgPath(getString(R.string.svgcdf));
        fillableLoader.start();

    }

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
