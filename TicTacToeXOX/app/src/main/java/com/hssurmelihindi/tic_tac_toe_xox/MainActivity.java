package com.hssurmelihindi.tic_tac_toe_xox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }




    //Oynaya tıkladığımda beni isim kaydetme sayfasına yönlendiricek
    public void oynabutontikla(View view){
        Intent intent = new Intent(this, Oyunsayfasi1.class);
        startActivity(intent);
    }


    public void hakkindatikla(View view){
        Intent intent = new Intent(this, HakkindaKismi.class);
        startActivity(intent);
    }

    public void pvpoynabutontikla(View view){
        Intent intent = new Intent(this, GameDisplay.class);


        startActivity(intent);
    }

}