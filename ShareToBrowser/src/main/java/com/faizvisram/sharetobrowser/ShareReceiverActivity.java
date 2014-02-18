package com.faizvisram.sharetobrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by faiz on 2/13/2014.
 */
public class ShareReceiverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                parseIntent(intent); // Handle text being sent
            }
        }

        finish();
    }

    void parseIntent(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            String myPattern = ".*?((https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
            Pattern p = Pattern.compile(myPattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(sharedText);

            if (m.matches()) {
                sendUrl(m.group(1));
                return;
            }
        }

        Toast.makeText(this, getString(R.string.error_link_not_found), Toast.LENGTH_SHORT).show();
    }

    void sendUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
