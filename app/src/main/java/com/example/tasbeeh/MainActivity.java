package com.example.tasbeeh;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    private boolean onVibe = true;
    private Vibrator vibrator;
    private TextView count;
    private int counter;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "data";
    int[] drawables = new int[]{R.drawable.circlewatch, R.drawable.circlewatch2,R.drawable.circlewatch3,R.drawable.circlewatch4,R.drawable.wristwatch2,R.drawable.wristcirclewatch,R.drawable.wristwatch2,R.drawable.wristcirclewatch3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adds

        count = (TextView) findViewById(R.id.count);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        headerChange();

        String zikrName = getIntent().getStringExtra("zikr_name");
        String zikrValue = getIntent().getStringExtra("zikr_value");

        // Display the data in your TextViews or perform any other necessary actions
        if (zikrName != null && zikrValue != null) {
            // Update your TextViews or perform other actions here
            // For example, you can set them to TextViews in your layout
            TextView textViewName = findViewById(R.id.tasbeeh_header);
            TextView textViewValue = findViewById(R.id.header_counter);
            textViewName.setText(zikrName);
            textViewValue.setText(zikrValue);
        }
    }


    public void headerChange(){
        TextView th = findViewById(R.id.tasbeeh_header);
        TextView header_taps = findViewById(R.id.header_counter);

        // data from zikar list is assigned to header for tasbeeh
        String ahName = getIntent().getStringExtra(zikars.EXTRA_AH_NAME);
        String ahTaps = getIntent().getStringExtra(zikars.EXTRA_AH_TAPS);
        String alName = getIntent().getStringExtra(zikars.EXTRA_AL_NAME);
        String alTaps = getIntent().getStringExtra(zikars.EXTRA_AL_TAPS);
        String asName = getIntent().getStringExtra(zikars.EXTRA_AS_NAME);
        String asTaps = getIntent().getStringExtra(zikars.EXTRA_AS_TAPS);


        th.setText(ahName);
        header_taps.setText(ahTaps);
        th.setText(alName);
        header_taps.setText(alTaps);


    }


    // counter TextView
    public void btnCounter(View view) {
        click();
    }
    public void click(){
        // Vibration
        if (onVibe){
            vibrator.vibrate(50);
        }

        // Increment the counter
        counter++;
        if (counter != 0){
            count.setText(Integer.toString(counter));
        }

        TextView header_taps = findViewById(R.id.header_counter);
        String taps = header_taps.getText().toString();

        int noTaps = 0;
        try {
            if (!taps.isEmpty()) {
                noTaps = Integer.parseInt(taps);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (counter == noTaps) {
            // Tasbeeh Completed, reset the counter to 0
            counter = 0;
            Toast.makeText(this, "Tasbeeh Completed", Toast.LENGTH_SHORT).show();
        }




    }
     // button which takes to zikr list activity
    public void nxtFragment(View view) {
        Intent intent = new Intent(MainActivity.this, zikars.class);
        startActivity(intent);

    }

    // button to save number of counts in shared preferences
    public void saveBtn(View view) {

    }



//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        String data;
//        data = sharedPreferences.getString(KEY_NAME, null);
//        int nmbr;
//        nmbr = parseInt(data);
//        if (nmbr == 0) {
//            nmbr = counter;
//            editor.putString(KEY_NAME, Integer.toString(nmbr));
//            editor.apply();
//        }else if(nmbr != 0){
//            nmbr += counter;
//            editor.putString(KEY_NAME, Integer.toString(nmbr));
//            editor.apply();
//        }
//        TextView th = findViewById(R.id.tasbeeh_header);
//        TextView header_taps = findViewById(R.id.header_counter);
//
//        th.setText(" ");
//        header_taps.setText(" ");





    // Logic for vibaration button

    public void vibeClick(View view) {
    ImageView imageView = findViewById(R.id.imageVibe);
            if (onVibe){
                onVibe = false;
                imageView.setImageResource(R.drawable.viberation_off);
            }else {
                onVibe = true;
                imageView.setImageResource(R.drawable.baseline_vibration_24);
            }

    }

    public void resetClick(View view) {
            counter = 0;
            count.setText(Integer.toString(counter));

    }

    private int currentImageIndex = 0;

    public void forwardImgageClick(View view) {
        ImageView img = findViewById(R.id.dial);

        currentImageIndex++;

        if (currentImageIndex >= drawables.length) {
            currentImageIndex = 0;
        }

        img.setImageResource(drawables[currentImageIndex]);
    }



    private int backImage = 0;
    public void backImageClick(View view) {
        ImageView img = findViewById(R.id.dial);

        backImage--;

        if (backImage < 0) {
            backImage = drawables.length - 1;
        }

        img.setImageResource(drawables[backImage]);
    }

}






