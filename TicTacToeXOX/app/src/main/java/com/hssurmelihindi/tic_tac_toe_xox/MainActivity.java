package com.hssurmelihindi.tic_tac_toe_xox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    //Oynaya tıkladığımda beni isim kaydetme sayfasına yönlendiricek
    public void oynabutontikla(View view){
        Intent intent = new Intent(this, Oyunsayfasi1.class);
        startActivity(intent);
    }


}