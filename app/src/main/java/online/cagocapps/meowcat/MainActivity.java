package online.cagocapps.meowcat;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private Button meowButton;
    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        meowButton = (Button) findViewById(R.id.button);
        addListenerOnButton(meowButton);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-9578879157437430~8008603301");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("76717E7B15FCA5425DF294B119AA246A").build();
        mAdView.loadAd(adRequest);
    }
    public void addListenerOnButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meowButton.setEnabled(false);
                meowButton.setBackgroundResource(R.drawable.open);

                int n = (int) (Math.random() * 15 + 1);
                if (n == 1||n==2) sound = MediaPlayer.create(MainActivity.this, R.raw.meowone);
                else if (n == 3 || n==4) sound = MediaPlayer.create(MainActivity.this,R.raw.meowtwo);
                else if (n == 5 || n==6) sound = MediaPlayer.create(MainActivity.this,R.raw.meowthree);
                else if (n == 7 || n==8) sound = MediaPlayer.create(MainActivity.this,R.raw.meowfour);
                else if (n == 9 || n==10) sound = MediaPlayer.create(MainActivity.this,R.raw.meowfive);
                else if (n == 11 || n==12) sound = MediaPlayer.create(MainActivity.this, R.raw.meowsix);
                else if (n == 13 || n==14) sound = MediaPlayer.create(MainActivity.this, R.raw.meowseven);
                else {
                    sound = MediaPlayer.create(MainActivity.this, R.raw.dog);
                    meowButton.setBackgroundResource(R.drawable.pup);
                }

                sound.start();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        goBack(sound);
                    }
                };
                Handler h = new Handler();
                h.postDelayed(r, sound.getDuration());
            }
        });
    }

    private void goBack(MediaPlayer soundToStop) {
        soundToStop.stop();
        soundToStop.release();
        meowButton.setEnabled(true);
        meowButton.setBackgroundResource(R.drawable.closed);
    }
}
