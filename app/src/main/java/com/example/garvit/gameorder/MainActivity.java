package com.example.garvit.gameorder;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   //0:yellow,1:red;
    int[] gamestate ={2,2,2,2,2,2,2,2,2};
    int activeplayer=0;
    boolean gameactive= true;
    int [][] WinningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedCounter] == 2 && gameactive)
        {
            gamestate[tappedCounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            }
            else
                {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] WinningPositon : WinningPosition) {
                if (gamestate[WinningPositon[0]] == gamestate[WinningPositon[1]] && gamestate[WinningPositon[1]] == gamestate[WinningPositon[2]] && gamestate[WinningPositon[0]] != 2)
                {
                    gameactive=false;
                    String winner = "";
                    if (activeplayer == 1)
                    {
                        winner = "yellow";
                    }
                    else
                        {
                        winner = "red";
                    }
                    Button playagainbutton =(Button)findViewById(R.id.PlayAgain);
                    TextView winnerTextview =(TextView)findViewById(R.id.WinnerTextView);
                    winnerTextview.setText("Winner is " + winner);
                    playagainbutton.setVisibility(View.VISIBLE);
                    winnerTextview.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playagain (View view)
    {
        Button playagainbutton =(Button)findViewById(R.id.PlayAgain);
        TextView winnerTextview =(TextView)findViewById(R.id.WinnerTextView);
        playagainbutton.setVisibility(View.INVISIBLE);
        winnerTextview.setVisibility(View.INVISIBLE);
        GridLayout gridlayout= (GridLayout)findViewById(R.id.gridlayout);
        for(int i=0; i<gridlayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridlayout.getChildAt(i);
            // do stuff with child view
            counter.setImageDrawable(null);
        }
        for (int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }

        activeplayer=0;
        gameactive= true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
