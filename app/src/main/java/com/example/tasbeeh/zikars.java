package com.example.tasbeeh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

public class zikars extends AppCompatActivity {
    public final static String EXTRA_AL_NAME = "al_header_name";
    public final static String EXTRA_AL_TAPS = "al_taps";
    public final static String EXTRA_AS_NAME = "as_header_name";
    public final static String EXTRA_AS_TAPS = "as_taps";
    public final static String EXTRA_AH_NAME = "ah_header_name";
    public final static String EXTRA_AH_TAPS = "ah_taps";
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "data";

    private static final String KEY_ZIKR_NAME = "name";
    private static final String KEY_ZIKR_VALUE = "value";

    private SharedPreferences sharedPreferences;
   private SharedPreferences.Editor editor;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    view_pager_adapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikars);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        tabLayout = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.view_pager2);
        viewPagerAdapter = new view_pager_adapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        ImageView imageView = findViewById(R.id.backImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(zikars.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    public void alClick(View view) {
        TextView tasbeeh_al = findViewById(R.id.Alhamdulillah_zikr);
        TextView taps_al = findViewById(R.id.Alhamdulillah_counter);
        String al_name = (String) tasbeeh_al.getText();
        String al_taps = (String) taps_al.getText();

        Intent intent = new Intent(zikars.this, MainActivity.class);
        intent.putExtra(EXTRA_AL_NAME, al_name);
        intent.putExtra(EXTRA_AL_TAPS, al_taps);
        startActivity(intent);

    }


    public void ahClick(View view) {
        TextView tasbeeh_ah = findViewById(R.id.Allahuakbar_zikr);
        TextView taps_ah = findViewById(R.id.Allahuakbar_counter);
        String ah_name = (String) tasbeeh_ah.getText();
        String ah_taps = (String) taps_ah.getText();

        Intent intent = new Intent(zikars.this, MainActivity.class);
        intent.putExtra(EXTRA_AL_NAME, ah_name);
        intent.putExtra(EXTRA_AL_TAPS, ah_taps);
        startActivity(intent);
    }


    public void asClick(View view) {
        TextView tasbeeh_as = findViewById(R.id.Astagfirullah_zikr);
        TextView taps_as = findViewById(R.id.Astagfirullah_counter);
        String as_name = (String) tasbeeh_as.getText();
        String as_taps = (String) taps_as.getText();

        Intent intent = new Intent(zikars.this, MainActivity.class);
        intent.putExtra(EXTRA_AL_NAME, as_name);
        intent.putExtra(EXTRA_AL_TAPS, as_taps);
        startActivity(intent);
    }

    public void backBtn(View view) {
        Intent intent = new Intent(zikars.this, MainActivity.class);
        startActivity(intent);
    }

    public void saveTab(View view) {
        Intent intent = new Intent(zikars.this, SaveTab.class);
        startActivity(intent);
    }
    public void bookMarkClick(View view) {
       View view1 = LayoutInflater.from(zikars.this).inflate(R.layout.activity_book_mark_view, null);
        TextInputEditText editText = view1.findViewById(R.id.your_zikr);
        TextInputEditText editText2 = view1.findViewById(R.id.your_counts);
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(zikars.this).setTitle("Zikr").setView(view1).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String zikr_name = String.valueOf(editText.getText());
                String zikr_counts = String.valueOf(editText2.getText());
                editor.putString(KEY_ZIKR_NAME, zikr_name);
                editor.putString(KEY_ZIKR_VALUE, zikr_counts);
                dialogInterface.dismiss();
            }
        }).create();
        alertDialog.show();
    }



}