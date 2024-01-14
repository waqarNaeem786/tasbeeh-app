package com.example.tasbeeh;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SaveZikr extends Fragment {
    private static final String KEY_ZIKR_NAME = "name";
    private static final String KEY_ZIKR_VALUE = "value";
    private static final String SHARED_PREF_NAME = "mypref";

    private SharedPreferences sharedPreferences;
    private LinearLayout dataContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_zikr, container, false);
        dataContainer = view.findViewById(R.id.dataContainer);

        // Initialize SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, getActivity().MODE_PRIVATE);

        // Load and display saved data
        loadAndDisplaySavedData();

        return view;
    }

    // Create a method to load and display saved data
    private void loadAndDisplaySavedData() {
        String dataName = sharedPreferences.getString(KEY_ZIKR_NAME, "");
        String dataValue = sharedPreferences.getString(KEY_ZIKR_VALUE, "");

        if (!dataName.isEmpty() && !dataValue.isEmpty()) {
            LinearLayout itemLayout = new LinearLayout(getActivity());
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textViewName = new TextView(getActivity());
            TextView textViewValue = new TextView(getActivity());

            textViewName.setText(dataName);
            textViewValue.setText(dataValue);

            // Set layout parameters for TextViews
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.width = 180;
            params.height = 180;
            textViewName.setLayoutParams(params);
            textViewValue.setLayoutParams(params);

            textViewName.setTextSize(30);
            textViewValue.setTextSize(30);

            Button deleteButton = new Button(getActivity());
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove data from SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(KEY_ZIKR_NAME);
                    editor.remove(KEY_ZIKR_VALUE);
                    editor.apply();

                    // Remove the item layout from the dataContainer
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
