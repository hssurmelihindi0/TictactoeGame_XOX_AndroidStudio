package com.hssurmelihindi.tic_tac_toe_xox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

        Button playAgainButton = findViewById(R.id.playAgainButton);
        Button homeButton= findViewById(R.id.homeButton);
        TextView playerDisplay = findViewById(R.id.playerDisplay);

        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);






        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgainButton, homeButton , playerDisplay);
    }

    public void playAgainButtonClick(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();

    }

    public void homeButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}