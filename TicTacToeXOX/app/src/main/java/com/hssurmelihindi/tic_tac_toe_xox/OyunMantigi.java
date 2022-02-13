package com.hssurmelihindi.tic_tac_toe_xox;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class OyunMantigi {

    private int[][] oyuntahtasi;

    private Button tekrarOynaBtn;
    private Button geriDonbtn;
    private TextView oyuncusirasi;
    private String[] oyuncuisimler= {"Oyuncu 1", "Oyuncu 2"};


    private int [] kazanantipi = {-1, -1, -1}; // burada 1.-1 sıra için 2. -1 sütun için 3. -1 çizgi için (yani 1. değer satırın değeri 2. değer sütunun değeri 3. değer ise çizilecek çizginin tipi)

    private int oyuncu =1;


    OyunMantigi(){
        oyuntahtasi = new int[3][3];

        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){

                oyuntahtasi[r][c] = 0;
            }
        }
    }

    public boolean updateOyunTahtasi(int row, int col) {
        if (oyuntahtasi[row-1][col-1]==0){

            oyuntahtasi[row-1][col-1]=oyuncu;

            if (oyuncu ==1 ){
                oyuncusirasi.setText((oyuncuisimler[1] + " adlı oyuncunun sırası"));

            }

            else {
                oyuncusirasi.setText((oyuncuisimler[0] + " adlı oyuncunun sırası"));
            }

            return true;


        }
        else{
            return false;
        }
    }


    //Burada kazananın kontrolü yapılıyor aynı çizgi üzerinde x ler veya o lar oluştuğunda kazanan belli olacak
    public boolean kazananKontrol(){

        boolean kazanan = false;


        //burası yatay kazananı görmek için
        for (int r=0;r<3;r++){
            if (oyuntahtasi[r][0] == oyuntahtasi[r][1] && oyuntahtasi[r][0]== oyuntahtasi[r][2] && oyuntahtasi[r][0] != 0 ){

                kazanantipi= new int[] {r,0,1};
                kazanan=true;


            }
        }
        //burası dikeyde kazananları saptıyor
        for (int c=0;c<3;c++){
            if (oyuntahtasi[0][c]== oyuntahtasi[1][c] && oyuntahtasi[2][c]== oyuntahtasi[0][c] && oyuntahtasi[0][c] != 0 ){

                kazanantipi= new int[] {0,c,2};
                kazanan=true;

            }
        }

        //bu iki if de çaprazda kazananları saptıyor
        if (oyuntahtasi[0][0]== oyuntahtasi[1][1] && oyuntahtasi[0][0]== oyuntahtasi[2][2] && oyuntahtasi[0][0] != 0 ){
            //burası sağdan sola çapraz
            kazanantipi= new int[] {0,2,3};
            kazanan=true;

        }
        if (oyuntahtasi[2][0]== oyuntahtasi[1][1] && oyuntahtasi[2][0]== oyuntahtasi[0][2] && oyuntahtasi[2][0] != 0 ){
            //burası soldan sağa çapraz
            kazanantipi= new int[] {2,2,4};
            kazanan=true;

        }


        //buradan başlayarak kazanan veya beraberlik durumunu belirleyen kod
        int boardFilled =0;

        for (int r=0;r<3;r ++){
            for (int c=0; c<3;c++){
                if (oyuntahtasi[r][c] != 0){
                    boardFilled +=1;
                }
            }
        }

        if (kazanan){
            tekrarOynaBtn.setVisibility(View.VISIBLE);
            geriDonbtn.setVisibility(View.VISIBLE);
            oyuncusirasi.setText((oyuncuisimler[oyuncu-1] + " Kazandıı!!!!"));
            return true;
        }

        else if (boardFilled == 9 ){
            tekrarOynaBtn.setVisibility(View.VISIBLE);
            geriDonbtn.setVisibility(View.VISIBLE);
            oyuncusirasi.setText("Beraberlik!!!");
            return true;

        }
        else{
            return false;
        }
    }

    //yukarısı kazanan ve beraberlik durumunu belirleyen kod


    //burası oyunu resetleyen kod oyun
    public void resetOyun(){
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){

                oyuntahtasi[r][c] = 0;
            }
        }

        oyuncu = 1;

        tekrarOynaBtn.setVisibility(View.GONE);
        geriDonbtn.setVisibility(View.GONE);

        oyuncusirasi.setText((oyuncuisimler[0] +" sırası"));
    }


    //oyun ekranındaki butonlar ve textview tanımlanıyor böylece yukarıda kullanmamızı sağlayacak
    public void setTekrarOynaBtn(Button tekrarOynaBtn) {
        this.tekrarOynaBtn = tekrarOynaBtn;
    }

    public void setGeriDonbtn(Button geriDonbtn) {
        this.geriDonbtn = geriDonbtn;
    }

    public void setOyuncusirasi(TextView oyuncusirasi) {
        this.oyuncusirasi = oyuncusirasi;
    }

    public void setOyuncuisimler(String[] oyuncuisimler){
        this.oyuncuisimler = oyuncuisimler;

    }

    public int[][] getOyuntahtasi() {
        return oyuntahtasi;
    }

    public void setOyuncu(int oyuncu){
        this.oyuncu = oyuncu;
    }


    public int getOyuncu() {
        return oyuncu;
    }

    public int[] getKazanantipi() {
        return kazanantipi;
    }
}
