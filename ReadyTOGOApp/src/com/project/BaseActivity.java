package com.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by choco on 5/26/16.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    protected Button menuButton, homeButton, callWaiterButton, orderButton, helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeButton = (Button) findViewById(R.id.Button01);
        menuButton = (Button) findViewById(R.id.Button02);
        orderButton = (Button) findViewById(R.id.Button03);
        helpButton = (Button) findViewById(R.id.Button04);
        callWaiterButton = (Button) findViewById(R.id.Button05);

        homeButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        callWaiterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button01:
                //Home Button click
                onHomeClicked();

                break;
            case R.id.Button02:
                //Menu Button Click
                onMenuClicked();

                break;
            case R.id.Button03:
                //Order Button Click
                onOrderClicked();

                break;
            case R.id.Button04:
                //Help Button Click
                onHelpClicked();

                break;
            case R.id.Button05:
                //Waiter Button Click
                onCallWaiterClicked();
                break;


        }
    }

    protected void onHomeClicked() {
        doIntent(getIntent().getExtras(), Main.class);
    }

    protected void onMenuClicked() {
        doIntent(getIntent().getExtras(), Menu.class);
    }

    protected void onOrderClicked() {
        doIntent(getIntent().getExtras(), ViewOrder.class);
    }

    protected void onHelpClicked() {
        doIntent(getIntent().getExtras(), Help.class);
    }

    protected void doIntent(Bundle bundle, Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    protected void onCallWaiterClicked() {
        // TODO Auto-generated method stub

        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();


        nameValuePairs.add(new BasicNameValuePair("deviceId", deviceId));
        System.out.println(deviceId);


        try {

			/*HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost("http://localhost:/alert.php");
			HttpPost httppost = new HttpPost("http://127.0.0.1:8000/alert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();*/

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://127.0.0.1:8000/alert.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpclient.execute(httppost);
            HttpEntity resEntityGet = httpResponse.getEntity();

            String response = "";
            if (resEntityGet != null) {
                //do something with the response
                response = EntityUtils.toString(resEntityGet);

                Log.i("GET RESPONSE", response);
                Log.d("response", response);
            }
            if (response.compareTo("1") == 0)
                Toast.makeText(getApplicationContext(), "Calling Waiter...", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "Error in http connection " + toString(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {

        }
    }
}
