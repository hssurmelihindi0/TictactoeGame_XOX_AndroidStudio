package com.hssurmelihindi.tic_tac_toe_xox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OyunEkrani extends AppCompatActivity {

    private TictactoeTahtasi tictactoeTahtasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oyun_ekrani);

        Button tekrarOynabtn = findViewById(R.id.button2);
        Button geriDonbtn = findViewById(R.id.button3);
        TextView oyuncusirasi= findViewById(R.id.textView4);

        tekrarOynabtn.setVisibility(View.GONE);
        geriDonbtn.setVisibility(View.GONE);

        String [] oyuncuisimler = getIntent(). getStringArrayExtra("Oyuncu_isimleri");

        if (oyuncuisimler !=null){

            oyuncusirasi.setText((oyuncuisimler[0] + " sırası"));
        }




        tictactoeTahtasi = findViewById(R.id.tictactoeTahtasi);

        tictactoeTahtasi.setUpGame(tekrarOynabtn,geriDonbtn,oyuncusirasi,oyuncuisimler);

    }


    public void tekrarOynabutonu(View view){
        tictactoeTahtasi.resetOyun();
        tictactoeTahtasi.invalidate();


    }


    public void geridonusbutonu(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}