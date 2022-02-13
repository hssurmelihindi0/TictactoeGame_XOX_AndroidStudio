package com.hssurmelihindi.tic_tac_toe_xox;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {

    private int [][] gameBoard;

    private String[] playernames = {"Kazandın :) Oyun bitti!", "Berabere", "Kaybettin"};

    private int [] winType = {-1 ,-1 , -1};

    private Button playAgainButton;
    private Button homeBTN;
    private TextView playerturn;



    private  int player = 1;

    GameLogic(){
        gameBoard = new int[3][3];

        for (int r=0; r<3 ; r++){
            for (int c=0 ; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;


            return true;
        }
        else{
           return false;
        }
    }


    public boolean winnerCheck(){
        boolean iswinner = false;


        //burası yatay kazananı görmek için
        for (int r=0;r<3;r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0]== gameBoard[r][2] && gameBoard[r][0] != 0 ){

                winType= new int[] {r,0,1};
                iswinner=true;


            }
        }
        //burası dikeyde kazananları saptıyor
        for (int c=0;c<3;c++){
            if (gameBoard[0][c]== gameBoard[1][c] && gameBoard[2][c]== gameBoard[0][c] && gameBoard[0][c] != 0 ){

                winType= new int[] {0,c,2};
                iswinner=true;

            }
        }

        //bu iki if de çaprazda kazananları saptıyor
        if (gameBoard[0][0]== gameBoard[1][1] && gameBoard[0][0]== gameBoard[2][2] && gameBoard[0][0] != 0 ){
            //burası sağdan sola çapraz
            winType= new int[] {0,2,3};
            iswinner=true;

        }
        if (gameBoard[2][0]== gameBoard[1][1] && gameBoard[2][0]== gameBoard[0][2] && gameBoard[2][0] != 0 ){
            //burası soldan sağa çapraz
            winType= new int[] {2,2,4};
            iswinner=true;
        }

        int boardFilled =0;

        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
                if (gameBoard[r][c] != 0 ){
                    boardFilled += 1;
                }
            }
        }

        if (iswinner){
            playAgainButton.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerturn.setVisibility(View.VISIBLE);
            if (player ==1) {

                playerturn.setText(playernames[0]);
            }else{
                playerturn.setText(playernames[2]);
            }
            return true;
        }
        else if (boardFilled == 9){
            playAgainButton.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerturn.setVisibility(View.VISIBLE);
            playerturn.setText(playernames[1]);
            return true;
        }else{
            return false;
        }
    }

    public void resetGame(){
        for (int r=0; r<3 ; r++){
            for (int c=0 ; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }

        player=1;

        playAgainButton.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);
        playerturn.setVisibility(View.GONE);



    }

    public void setPlayAgainButton(Button playAgainButton) {
        this.playAgainButton = playAgainButton;
    }

    public void setHomeBTN(Button homeBTN) {
        this.homeBTN = homeBTN;
    }

    public void setPlayerturn(TextView playerturn) {
        this.playerturn = playerturn;
    }


    public void setPlayernames(String[] playernames) {
        this.playernames = playernames;
    }

    public int [][] getGameBoard(){
        return gameBoard;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }

    public int[] getWinType() {
        return winType;
    }
}
