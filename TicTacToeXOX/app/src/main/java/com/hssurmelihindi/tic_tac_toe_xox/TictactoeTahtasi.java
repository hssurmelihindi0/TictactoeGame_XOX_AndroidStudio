package com.hssurmelihindi.tic_tac_toe_xox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TictactoeTahtasi extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;


    private boolean kazanancizgisi =false;


    private final Paint paint =new Paint();

    private final OyunMantigi game;

    private int cellSize = getWidth()/3;



    public TictactoeTahtasi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new OyunMantigi();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TictactoeTahtasi, 0 , 0);



        try {
            boardColor = a.getInteger(R.styleable.TictactoeTahtasi_boardColor, 0);
            XColor = a.getInteger(R.styleable.TictactoeTahtasi_XColor, 0);
            OColor = a.getInteger(R.styleable.TictactoeTahtasi_OColor, 0);
            winningLineColor = a.getInteger(R.styleable.TictactoeTahtasi_winningColor, 0);




        } finally {

            a.recycle();
        }


    }

    // tahtayı oluşturmak için cihazın ölçülerini ayarlıyor
    @Override
    protected void onMeasure(int width, int height){

        super.onMeasure(width,height);

        int dimensions = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimensions/3;

        setMeasuredDimension(dimensions,dimensions);

    }

    @Override

    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);


        drawGameBoard(canvas);

        drawMarkers(canvas);


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y= event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if (!kazanancizgisi){

                if (game.updateOyunTahtasi(row,col)){
                    invalidate();

                    if (game.kazananKontrol()){
                        kazanancizgisi=true;
                        invalidate();
                    }

                    //Oyuncu sırasını ayarlayan bölge
                    if (game.getOyuncu() % 2 == 0){
                        game.setOyuncu(game.getOyuncu()-1);
                    }
                    else{
                        game.setOyuncu(game.getOyuncu()+1);
                    }
                }

            }



            invalidate();

            return true;

        }

        return false;


    }

    private void drawGameBoard(Canvas canvas){

        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int c=1;c<3;c++){
            canvas.drawLine(cellSize*c, 0 , cellSize*c,canvas.getWidth(),paint); // sütunlar için çizdirme



        }

        for (int r=1;r<3;r++){
            canvas.drawLine(0,cellSize*r, canvas.getWidth(),cellSize*r,paint); //satırlar için çizdirme

        }



    }

    private void drawMarkers (Canvas canvas){
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){

               if (game.getOyuntahtasi()[r][c] != 0){
                   if (game.getOyuntahtasi()[r][c] == 1){
                       drawX(canvas,r,c);

                   }
                   else {
                       drawO(canvas,r,c);
                   }

               }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);

        canvas.drawLine((float)((col+1)*cellSize-cellSize*0.2),(float)(row*cellSize+cellSize*0.2),(float)(col*cellSize+cellSize*0.2),(float)((row+1)*cellSize-cellSize*0.2),paint);

        canvas.drawLine((float)(col*cellSize+cellSize*0.2),(float)(row*cellSize+cellSize*0.2),(float)((col+1)*cellSize-cellSize*0.2),(float)((row+1)*cellSize-cellSize*0.2),paint);

    }

    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);

        canvas.drawOval((float)(col*cellSize +cellSize*0.2),(float)(row*cellSize+cellSize*0.2), (float)((col*cellSize+cellSize)-cellSize*0.2),(float)((row*cellSize+cellSize)-cellSize*0.2),paint);



    }

    public void setUpGame(Button tekrarOyna, Button Geridon, TextView Oyuncusirasi, String[] isimler){

        game.setTekrarOynaBtn(tekrarOyna);
        game.setGeriDonbtn(Geridon);
        game.setOyuncusirasi(Oyuncusirasi);
        game.setOyuncuisimler(isimler);




    }

    public void resetOyun(){
        game.resetOyun();
        kazanancizgisi = false;
    }
}
