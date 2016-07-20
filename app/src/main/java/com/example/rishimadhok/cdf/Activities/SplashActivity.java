package com.example.rishimadhok.cdf.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.rishimadhok.cdf.Base.BaseActivity;
import com.example.rishimadhok.cdf.R;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class SplashActivity extends BaseActivity implements ViewSwitcher.ViewFactory {

    private HTextView hTextView;
    private TextSwitcher textSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        // To track statistics around application
//        ParseAnalytics.trackAppOpened(getIntent());


        final String[] sentences = new String[]{"CONNECTING ", "DREAMS ", "FOUNDATION "};
        hTextView = (HTextView) findViewById(R.id.cooltext);

        hTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

        hTextView.setAnimateType(HTextViewType.ANVIL);
        hTextView.animateText("CDF");


//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hTextView.setAnimateType(HTextViewType.LINE);
//                hTextView.animateText(sentences[0] + sentences[1] + sentences[2]);
//
//            }
//        }, 3000);
//
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();

            }
        }, 2000);

    }

    @Override
    public View makeView() {
        TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        return t;
    }
}
