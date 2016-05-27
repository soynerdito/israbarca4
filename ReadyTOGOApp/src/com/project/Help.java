package com.project;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends Activity {

    Button menuButton, homeButton, callwaiterButton, orderButton, helpButton;
    Intent homeit, menuit, orderit, helpit;
    Bundle g;
    TextView t1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        g = getIntent().getExtras();

        homeButton = (Button) findViewById(R.id.Button01);
        menuButton = (Button) findViewById(R.id.Button02);
        orderButton = (Button) findViewById(R.id.Button03);
        helpButton = (Button) findViewById(R.id.Button04);
        callwaiterButton = (Button) findViewById(R.id.Button05);
        t1 = (TextView) findViewById(R.id.TextView02);
        findViewById(R.id.TextView03).setVisibility(View.GONE);
        findViewById(R.id.ImageView01).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.title)).setText(R.string.help);
        homeit = new Intent(getBaseContext(), main.class);
        menuit = new Intent(getBaseContext(), Menu.class);
        helpit = new Intent(getBaseContext(), Help.class);
        orderit = new Intent(getBaseContext(), ViewOrder.class);

        homeButton.setOnClickListener((OnClickListener) new homeListener());
        menuButton.setOnClickListener((OnClickListener) new menuListener());
        orderButton.setOnClickListener((OnClickListener) new orderListener());
        helpButton.setOnClickListener((OnClickListener) new helpListener());
        callwaiterButton.setOnClickListener((OnClickListener) new callwaiterListener());

        t1.setText("Welcome to RedyTOGO Metropol Restaurant App\n" +
                "\nThe left sidebar buttons take you to the Home page, Menu page, View Order page and Help page. Also the Call Waiter button will call the waiter upon click if any problem with the order you have." +
                "\nIn the menu screen you can choose from various types of dishes like appetizers, soups, maincourse etc." +
                "\nWhen in any sub-category of food, you can select the food item from the list by just clicking it and you would be redirected to it's details page where you can find the details of that food item and add it to your order." +
                "\nEnjoy As much you can this app" +
                "\nOnce you are done selecting your order, you can go to the view order screen and click on confirm order to confirm it and send it to host restaurant and the to the kitchen." +
                "\nThis Project create by Israel Diaz Santana (841-09-2367) For the final Project In the Tesis Class in colaboration and mentor Prof. Omar Diaz ");

    }

    private class homeListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            Toast.makeText(getApplicationContext(), "Home Screen Loading...", Toast.LENGTH_SHORT).show();
            homeit.putExtras(g);
            startActivity(homeit);
        }

    }

    private class menuListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            Toast.makeText(getApplicationContext(), "Loading Menu...", Toast.LENGTH_SHORT).show();
            menuit.putExtras(g);
            startActivity(menuit);
        }

    }


    private class orderListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            Toast.makeText(getApplicationContext(), "Loading your current Order...", Toast.LENGTH_SHORT).show();
            orderit.putExtras(g);
            startActivity(orderit);
        }

    }


    private class helpListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            Toast.makeText(getApplicationContext(), "Loading Help Screen...", Toast.LENGTH_SHORT).show();
            helpit.putExtras(g);
            startActivity(helpit);
        }

    }

    private class callwaiterListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
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

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://localhost:3306/alert.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();


                Toast.makeText(getApplicationContext(), "Calling Waiter...", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Error in http connection " + e.toString(), Toast.LENGTH_LONG).show();
            } finally {
            }

        }

    }

}

