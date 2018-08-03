package com.td.test.topfacts.uicomponents.facts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.td.test.topfacts.R;

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
