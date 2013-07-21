package com.sundesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sundesign.text.method.InteractiveSpanMovementMethod;
import com.sundesign.text.style.FixedInteractiveImageSpan;
import com.sundesign.text.style.InteractiveImageSpan;
import com.sundesign.text.style.RelativeInteractiveImageSpan;

/**
 * @author Oleg Green
 * @since 14.07.13
 */
public class InteractiveSpanActivity extends Activity {

    public static final String TEXT1 = "Android is a Linux-based operating system designed primarily "
            + "for touchscreen mobile devices such as smartphones and tablet computers";
    public static final String TEXT2 = "Here's a link to a developers website: " +
            "link http://developer.android.com";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView textView = (TextView) findViewById(R.id.TextView);
        textView.setText(TEXT1);
        Spannable spannable = (Spannable) textView.getText();

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannable.setSpan(boldSpan, 13, 24, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        TextView textView2 = (TextView) findViewById(R.id.TextView2);
        SpannableStringBuilder ssb = new SpannableStringBuilder(TEXT2 + TEXT1 + TEXT1 + TEXT1);

        InteractiveImageSpan imageSpan =
                new RelativeInteractiveImageSpan(getStateListDrawable(), 2.4f, 5);
        InteractiveImageSpan imageSpan2 =
                new FixedInteractiveImageSpan(getStateListDrawable(), 150, 30);
        InteractiveImageSpan imageSpan3 =
                new RelativeInteractiveImageSpan(getStateListDrawable(), 1.4f, 5);

        imageSpan.setOnClickListener(new InteractiveImageSpan.OnClickListener() {

            @Override
            public void onClick() {
                Toast.makeText(getApplicationContext(),
                        "hello from click method(span1)", Toast.LENGTH_SHORT).show();
                Log.d("test", "span1 clicked");
            }
        });
        imageSpan2.setOnClickListener(new InteractiveImageSpan.OnClickListener() {

            @Override
            public void onClick() {
                Toast.makeText(getApplicationContext(),
                        "hello from click method(span2)", Toast.LENGTH_SHORT).show();
                Log.d("test", "span2 clicked");
            }
        });
        imageSpan3.setOnClickListener(new InteractiveImageSpan.OnClickListener() {

            @Override
            public void onClick() {
                Toast.makeText(getApplicationContext(),
                        "hello from click method(span3)", Toast.LENGTH_SHORT).show();
                Log.d("test", "span3 clicked");
            }
        });

        ssb.setSpan(imageSpan, 16, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(imageSpan2, 37, 38, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(imageSpan3, 437, 438, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        URLSpan urlSpan = new URLSpan("http://developer.android.com") {

            @Override
            public void onClick(View widget) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getURL()));
                startActivity(i);
            }
        };
        ssb.setSpan(urlSpan, 44, 72, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView2.setText(ssb, TextView.BufferType.SPANNABLE);
        textView2.setSoundEffectsEnabled(true);
        textView2.setMovementMethod(InteractiveSpanMovementMethod.getInstance());

        Toast.makeText(getApplicationContext(), "text size = " + textView2.getTextSize(),
                Toast.LENGTH_SHORT).show();
    }

    private Drawable getStateListDrawable() {
        return getResources().getDrawable(R.drawable.button_text_widget);
    }
}
