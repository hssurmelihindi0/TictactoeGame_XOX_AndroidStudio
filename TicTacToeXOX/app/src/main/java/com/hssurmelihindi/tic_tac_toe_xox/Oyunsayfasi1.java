package com.hssurmelihindi.tic_tac_toe_xox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Oyunsayfasi1 extends AppCompatActivity {


    private EditText oyuncu1;
    private EditText oyuncu2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oyunsayfasi1);

        oyuncu1 =findViewById(R.id.oyuncu1);
        oyuncu2 =findViewById(R.id.oyuncu2);


    }


    public void kaydetbutonutikla(View vier){

        String oyuncu1isim = oyuncu1.getText().toString();
        String oyuncu2isim= oyuncu2.getText().toString();


        Intent intent= new Intent(this, OyunEkrani.class);
        intent.putExtra("Oyuncu_isimleri" , new String[] {oyuncu1isim, oyuncu2isim});
        startActivity(intent);


    }
}