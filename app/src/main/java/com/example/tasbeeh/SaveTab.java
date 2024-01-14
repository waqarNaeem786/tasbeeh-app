package com.example.tasbeeh;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SaveTab extends AppCompatActivity {
    private static final String KEY_ZIKR_NAME = "name";
    private static final String KEY_ZIKR_VALUE = "value";
    private static final String SHARED_PREF_NAME = "mypref";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LinearLayout dataContainer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_tab);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dataContainer = findViewById(R.id.dataContainer);
        dataExtracter();
    }

    public void dataExtracter() {
        String dataName = sharedPreferences.getString(KEY_ZIKR_NAME, null);
        String dataValue = sharedPreferences.getString(KEY_ZIKR_VALUE, null);

        if (dataName != null && dataValue != null) {
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textViewName = new TextView(this);
            TextView textViewValue = new TextView(this);

            textViewName.setText(dataName);
            textViewValue.setText(dataValue);
            textViewName.setWidth(180);
            textViewName.setHeight(180);
            textViewValue.setWidth(180);
            textViewValue.setHeight(180);
            textViewName.setTextSize(30);
            textViewValue.setTextSize(30);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataContainer.removeView(itemLayout);
                }
            });

            itemLayout.addView(textViewName);
            itemLayout.addView(textViewValue);
            itemLayout.addView(deleteButton);

            dataContainer.addView(itemLayout);
        }
    }

}
