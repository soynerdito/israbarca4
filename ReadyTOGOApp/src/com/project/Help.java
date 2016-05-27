package com.project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Help extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView t1 = (TextView) findViewById(R.id.TextView02);
        findViewById(R.id.TextView03).setVisibility(View.GONE);
        findViewById(R.id.ImageView01).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.title)).setText(R.string.help);

        t1.setText("Welcome to RedyTOGO Metropol Restaurant App\n" +
                "\nThe left sidebar buttons take you to the Home page, Menu page, View Order page and Help page. Also the Call Waiter button will call the waiter upon click if any problem with the order you have." +
                "\nIn the menu screen you can choose from various types of dishes like appetizers, soups, maincourse etc." +
                "\nWhen in any sub-category of food, you can select the food item from the list by just clicking it and you would be redirected to it's details page where you can find the details of that food item and add it to your order." +
                "\nEnjoy As much you can this app" +
                "\nOnce you are done selecting your order, you can go to the view order screen and click on confirm order to confirm it and send it to host restaurant and the to the kitchen." +
                "\nThis Project create by Israel Diaz Santana (841-09-2367) For the final Project In the Tesis Class in colaboration and mentor Prof. Omar Diaz ");

    }

}

