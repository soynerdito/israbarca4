package com.project;

import android.os.Bundle;

public class Main extends BaseActivity {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }


}