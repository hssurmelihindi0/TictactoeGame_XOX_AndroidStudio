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

import java.util.Random;

public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;

    private boolean winningLine = false;

    public final int min = 1;
    public final int max = 3;

    private final Paint paint = new Paint();

    private final GameLogic game;

    private int cellSize = getWidth()/3;



    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a= context.getTheme().obtainStyledAttributes(attrs, R.styleable.TictactoeTahtasi,0,0);

        try {
            boardColor = a.getInteger(R.styleable.TictactoeTahtasi_boardColor,0);
            XColor = a.getInteger(R.styleable.TictactoeTahtasi_XColor,0);
            OColor = a.getInteger(R.styleable.TictactoeTahtasi_OColor,0);
            winningLineColor = a.getInteger(R.styleable.TictactoeTahtasi_winningColor,0);

        }finally {

        }

    }

    @Override
    protected void onMeasure(int Width , int Height){
        super.onMeasure(Width, Height);

        int dimension= Math.min(getMeasuredWidth(),getMeasuredHeight());
        cellSize = dimension/3;

        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

        if (winningLine){
            paint.setColor(winningLineColor);
            drawKazanancizgi(canvas);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();


        //final int random = new Random().nextInt((max - min) + 1) + min;


        if(action == MotionEvent.ACTION_DOWN){

            if (game.getPlayer() % 2 != 0 ){
                int row = (int) Math.ceil(y/cellSize);
                int col = (int) Math.ceil(x/cellSize);

                if (!winningLine){
                    if (game.updateGameBoard(row, col)){
                        invalidate();

                        if (game.winnerCheck()){
                            winningLine=true;
                            invalidate();
                        }


                        //updating the players turn
                        if (game.getPlayer() % 2 == 0){
                            game.setPlayer(game.getPlayer() - 1);

                        }
                        else{
                            game.setPlayer(game.getPlayer() + 1);


                        }

                }

                }

                invalidate();


            }

            else {

                    int row = new Random().nextInt((max - min) + 1) + min;
                    int col = new Random().nextInt((max - min) + 1) + min;
                    //row= row-1;
                    //col= col-1;
                if (!winningLine) {
                    if (game.updateGameBoard(row, col)) {
                        invalidate();

                        if (game.winnerCheck()){
                            winningLine=true;
                            invalidate();
                        }


                        //updating the players turn
                        if (game.getPlayer() % 2 == 0) {
                            game.setPlayer(game.getPlayer() - 1);


                        } else {
                            game.setPlayer(game.getPlayer() + 1);


                        }
                    }
                }

                    invalidate();

                    return true;

            }


        }





        return false;
    }

    private void drawGameBoard(Canvas canvas){

        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int c=1;c<3;c++){
            canvas.drawLine(cellSize*c,0,cellSize*c,canvas.getWidth(),paint);
        }

        for (int r=1;r<3;r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(),cellSize*r,paint);



        }

    }

    private void drawMarkers(Canvas canvas){
        for (int r=0; r<3 ; r++){
            for (int c=0 ; c<3; c++){
                if (game.getGameBoard()[r][c] != 0){
                    if (game.getGameBoard()[r][c] == 1){
                        drawX(canvas , r, c );
                    }
                    else{

                        drawO(canvas, r, c);
                    }

                }
            }
        }
    }



    private void drawX(Canvas canvas , int row, int col){
        paint.setColor(XColor);

        canvas.drawLine((float) ((col+1)*cellSize - cellSize*0.2),
                        (float)(row*cellSize + cellSize*0.2),
                        (float)(col*cellSize + cellSize*0.2),
                        (float)((row+1)*cellSize - cellSize*0.2),
                        paint);

        canvas.drawLine((float)(col*cellSize+cellSize*0.2),
                        (float)(row*cellSize+cellSize*0.2),
                        (float)((col+1)*cellSize-cellSize*0.2),
                        (float)((row+1)*cellSize-cellSize*0.2),
                        paint);
    }

    private void drawO(Canvas canvas , int row, int col){
        paint.setColor(OColor);

        canvas.drawOval((float) (col*cellSize + cellSize*0.2),
                        (float) (row*cellSize + cellSize*0.2),
                        (float) ((col*cellSize+cellSize) - cellSize*0.2),
                        (float) ((row*cellSize+cellSize) - cellSize*0.2),
                        paint);
    }

    private void drawHorizontalLine(Canvas canvas, int row,int col){
        canvas.drawLine(col, row*cellSize+(float)cellSize/2, cellSize*3,row*cellSize+(float)cellSize/2,paint); //Yatayda kazanan olursa üzerine yatay çizgi çekicek

    }

    private void drawVerticalLine(Canvas canvas , int row , int col){

        canvas.drawLine(col*cellSize+(float)cellSize/2,row, col*cellSize+(float)cellSize/2,cellSize*3,paint);//Dikeyde kazanan olursa dikey çizgi çekmek için
    }

    private void drawDiagonalLinePos(Canvas canvas ){
        canvas.drawLine(0,cellSize*3, cellSize*3,0,paint); //Soldan sağa çaprazda kazanan olursa çizgi çekmek için yani (\) şekil olarak bu
    }

    private void drawDiagonalLineNeg(Canvas canvas ){
        canvas.drawLine(0,0, cellSize*3,cellSize*3,paint); //Sağdan sola çaprazda kazanan olursa çizgi çekmek için yani (/) şekil olarak bu
    }

    private void drawKazanancizgi(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]) {

            case 1:
                drawHorizontalLine(canvas, row, col);
                break;
            case 2:
                drawVerticalLine(canvas, row, col );
                break;
            case 3:
                drawDiagonalLineNeg(canvas);
                break;
            case 4:
                drawDiagonalLinePos(canvas);
                break;

        }
    }

    public void setUpGame(Button playAgain, Button home , TextView playerDisplay){
         game.setPlayAgainButton(playAgain);
         game.setHomeBTN(home);
         game.setPlayerturn(playerDisplay);




    }

    public void resetGame(){
        game.resetGame();
        winningLine=false;
    }
}
