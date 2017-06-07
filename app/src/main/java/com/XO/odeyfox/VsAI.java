package com.XO.odeyfox;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.XO.R;

import java.util.Arrays;

public class VsAI extends Activity {

    private boolean turn = false;
    private char table[][] = new char[3][3];
    int win[] = new int[3];
    int count = 0;
    int winc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_player);
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        newGame(findViewById(R.id.tableLayout));

        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow r = (TableRow) T.getChildAt(y);
                for (int x = 0; x < r.getChildCount(); x++) {
                    View V = r.getChildAt(x);
                    V.setOnClickListener(new play(x, y));
                }
            }
        }
        Button c = (Button) findViewById(R.id.newgame);
        c.setOnClickListener(new OnClickListener() {
            public void onClick(View n) {
                newGame(n);
            }

        });
    }

    private class play implements View.OnClickListener {

        private int x = 0;
        private int y = 0;

        public play(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                Button B = (Button) view;
                table[x][y] = 'X';
                B.setText("X");
                B.setEnabled(false);
                turn = !turn;
                count++;
                if (count == 1) {
                    firstmove();
                }
                if (count == 3) {
                    defend(view);
                }
                checkwin(view);

                if (count > 4) {
                    goforwin(view);
                    checkwin(view);
                }

            }
        }
    }


    public void newGame(View view) {
        count = 0;
        turn = false;
        table = new char[3][3];
        resetButtons();
    }

    private void resetButtons() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow Re = (TableRow) T.getChildAt(y);
                for (int x = 0; x < Re.getChildCount(); x++) {
                    if (Re.getChildAt(x) instanceof Button) {
                        Button B = (Button) Re.getChildAt(x);
                        B.setText("");
                        B.setEnabled(true);
                        B.setTextColor(Color.parseColor("#000000"));
                        count = 0;
                        winc = 0;

                    }
                }
            }
        }
    }

    public void firstmove() {
        if (table[1][1] != 'X') {
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[1][1] = 'O';
        } else {
            Button B = (Button) findViewById(R.id.button8);
            B.setText("O");
            table[0][2] = 'O';
            count++;

        }

    }
    public void firstAImove(){
        if (table[1][1] == 'X'){
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[0][0] = 'O';

        }else if(table[0][0] == 'X' || table[2][0] == 'X' ||table[0][2] == 'X' ||table[2][2] == 'X' ){
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[1][1] = 'O';

        }else if(table[1][0] == 'X'){
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[0][0] = 'O';

        }else if(table[2][1] == 'X'){
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[2][0] = 'O';

        }else if(table[1][2] == 'X'){
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[1][0] = 'O';

        }else if(table[0][1] == 'X'){
            Button B = (Button) findViewById(R.id.button6);
            B.setText("O");
            count++;
            table[0][0] = 'O';

        }
    }

    private void defend(View view) {
        Button B;

        //checking for X
        //diagonal '\'
        //xx-
        if (table[0][0] == 'X') {
            if ((table[1][1] == 'X') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][0] == 'X') {
            if ((table[2][2] == 'X') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[1][1] == 'X') {
            if ((table[2][2] == 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //diagonal '/'
        //xx-
        if (table[2][0] == 'X') {
            if ((table[1][1] == 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[2][0] == 'X') {
            if ((table[0][2] == 'X') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[0][2] == 'X') {
            if ((table[1][1] == 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //horizontal1
        //condition1 xx-
        if (table[0][0] == 'X') {
            if ((table[1][0] == 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }

        //condition1 x-x
        if (table[0][0] == 'X') {
            if ((table[2][0] == 'X') && (table[1][0] != 'X' && table[1][0] != 'O')) {
                B = (Button) findViewById(R.id.button3);
                B.setText("O");
                table[1][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //condition1 -xx
        if (table[2][0] == 'X') {
            if ((table[1][0] == 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }

        //horizontal2
        //xx-
        if (table[0][1] == 'X') {
            if ((table[1][1] == 'X') && (table[2][1] != 'X' && table[2][1] != 'O')) {
                B = (Button) findViewById(R.id.button7);
                B.setText("O");
                table[2][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][1] == 'X') {
            if ((table[2][1] == 'X') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[2][1] == 'X') {
            if ((table[1][1] == 'X') && (table[0][1] != 'X' && table[0][1] != 'O')) {
                B = (Button) findViewById(R.id.button5);
                B.setText("O");
                table[0][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //horizontal3
        //xx-
        if (table[0][2] == 'X') {
            if ((table[1][2] == 'X') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][2] == 'X') {
            if ((table[2][2] == 'X') && (table[1][2] != 'X' && table[1][2] != 'O')) {
                B = (Button) findViewById(R.id.button9);
                B.setText("O");
                table[1][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[2][2] == 'X') {
            if ((table[1][2] == 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //vertical1
        //xx-
        if (table[0][0] == 'X') {
            if ((table[0][1] == 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][0] == 'X') {
            if ((table[0][2] == 'X') && (table[0][1] != 'X' && table[0][1] != 'O')) {
                B = (Button) findViewById(R.id.button5);
                B.setText("O");
                table[0][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[0][2] == 'X') {
            if ((table[0][1] == 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //vertical2
        //xx-
        if (table[1][0] == 'X') {
            if ((table[1][1] == 'X') && (table[1][2] != 'X' && table[1][2] != 'O')) {
                B = (Button) findViewById(R.id.button9);
                B.setText("O");
                table[1][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[1][0] == 'X') {
            if ((table[1][2] == 'X') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[1][2] == 'X') {
            if ((table[1][1] == 'X') && (table[1][0] != 'X' && table[1][0] != 'O')) {
                B = (Button) findViewById(R.id.button3);
                B.setText("O");
                table[1][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //vertical3
        //xx-
        if (table[2][0] == 'X') {
            if ((table[2][1] == 'X') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[2][0] == 'X') {
            if ((table[2][2] == 'X') && (table[2][1] != 'X' && table[2][1] != 'O')) {
                B = (Button) findViewById(R.id.button7);
                B.setText("O");
                table[2][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[2][2] == 'X') {
            if ((table[2][1] == 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        tryforwin(view);


    }

    private void goforwin(View view) {
        Button B;


        //diagonal '\'
        //xx-
        if (table[0][0] == 'O') {
            if ((table[1][1] == 'O') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][0] == 'O') {
            if ((table[2][2] == 'O') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[1][1] == 'O') {
            if ((table[2][2] == 'O') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //diagonal '/'
        //xx-
        if (table[2][0] == 'O') {
            if ((table[1][1] == 'O') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[2][0] == 'O') {
            if ((table[0][2] == 'O') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[0][2] == 'O') {
            if ((table[1][1] == 'O') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //horizontal1
        //condition1 xx-
        if (table[0][0] == 'O') {
            if ((table[1][0] == 'O') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }

        //condition1 x-x
        if (table[0][0] == 'O') {
            if ((table[2][0] == 'O') && (table[1][0] != 'X' && table[1][0] != 'O')) {
                B = (Button) findViewById(R.id.button3);
                B.setText("O");
                table[1][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //condition1 -xx
        if (table[2][0] == 'O') {
            if ((table[1][0] == 'O') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }

        //horizontal2
        //xx-
        if (table[0][1] == 'O') {
            if ((table[1][1] == 'O') && (table[2][1] != 'X' && table[2][1] != 'O')) {
                B = (Button) findViewById(R.id.button7);
                B.setText("O");
                table[2][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][1] == 'O') {
            if ((table[2][1] == 'O') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[2][1] == 'O') {
            if ((table[1][1] == 'O') && (table[0][1] != 'X' && table[0][1] != 'O')) {
                B = (Button) findViewById(R.id.button5);
                B.setText("O");
                table[0][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //horizontal3
        //xx-
        if (table[0][2] == 'O') {
            if ((table[1][2] == 'O') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][2] == 'O') {
            if ((table[2][2] == 'O') && (table[1][2] != 'X' && table[1][2] != 'O')) {
                B = (Button) findViewById(R.id.button9);
                B.setText("O");
                table[1][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[2][2] == 'O') {
            if ((table[1][2] == 'O') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //vertical1
        //xx-
        if (table[0][0] == 'O') {
            if ((table[0][1] == 'O') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[0][0] == 'O') {
            if ((table[0][2] == 'O') && (table[0][1] != 'X' && table[0][1] != 'O')) {
                B = (Button) findViewById(R.id.button5);
                B.setText("O");
                table[0][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[0][2] == 'O') {
            if ((table[0][1] == 'O') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //vertical2
        //xx-
        if (table[1][0] == 'O') {
            if ((table[1][1] == 'O') && (table[1][2] != 'X' && table[1][2] != 'O')) {
                B = (Button) findViewById(R.id.button9);
                B.setText("O");
                table[1][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[1][0] == 'O') {
            if ((table[1][2] == 'O') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[1][2] == 'O') {
            if ((table[1][1] == 'O') && (table[1][0] != 'X' && table[1][0] != 'O')) {
                B = (Button) findViewById(R.id.button3);
                B.setText("O");
                table[1][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //vertical3
        //xx-
        if (table[2][0] == 'O') {
            if ((table[2][1] == 'O') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //x-x
        if (table[2][0] == 'O') {
            if ((table[2][2] == 'O') && (table[2][1] != 'X' && table[2][1] != 'O')) {
                B = (Button) findViewById(R.id.button7);
                B.setText("O");
                table[2][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //-xx
        if (table[2][2] == 'O') {
            if ((table[2][1] == 'O') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        defend(view);


    }

    private void tryforwin(View view) {
        Button B;
        //00
        //diagonal /
        if (table[0][0] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[0][0] == 'O') {
            if ((table[1][0] != 'O' && table[1][0] != 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[0][0] == 'O') {
            if ((table[0][1] != 'O' && table[0][1] != 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //22
        if (table[2][2] == 'O') {
            if ((table[0][0] != 'O' && table[0][0] != 'X') && (table[1][1] != 'X' && table[1][1] != 'O')) {
                B = (Button) findViewById(R.id.button6);
                B.setText("O");
                table[1][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[2][2] == 'O') {
            if ((table[1][2] != 'O' && table[1][2] != 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[2][2] == 'O') {
            if ((table[2][1] != 'O' && table[2][1] != 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }


        //11
        if (table[1][1] == 'O') {
            if ((table[2][2] != 'O' && table[2][2] != 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[1][1] == 'O') {
            if ((table[0][2] != 'O' && table[0][2] != 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[1][1] == 'O') {
            if ((table[2][1] != 'O' && table[2][1] != 'X') && (table[0][1] != 'X' && table[0][1] != 'O')) {
                B = (Button) findViewById(R.id.button7);
                B.setText("O");
                table[2][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[1][1] == 'O') {
            if ((table[1][2] != 'O' && table[1][2] != 'O') && (table[1][0] != 'X' && table[1][0] != 'O')) {
                B = (Button) findViewById(R.id.button3);
                B.setText("O");
                table[1][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //20
        if (table[2][0] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[2][0] == 'O') {
            if ((table[1][0] != 'O' && table[1][0] != 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[2][0] == 'O') {
            if ((table[2][1] != 'O' && table[2][1] != 'X') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //02
        if (table[0][2] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button4);
                B.setText("O");
                table[2][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[0][2] == 'O') {
            if ((table[1][2] != 'O' && table[1][2] != 'X') && (table[2][2] != 'X' && table[2][2] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[0][2] == 'O') {
            if ((table[0][1] != 'O' && table[0][1] != 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button5);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //10
        if (table[1][0] == 'O') {
            if ((table[2][0] != 'O' && table[2][0] != 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[1][0] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[1][2] != 'X' && table[1][2] != 'O')) {
                B = (Button) findViewById(R.id.button9);
                B.setText("O");
                table[1][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //01
        if (table[0][1] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[2][1] != 'X' && table[2][1] != 'O')) {
                B = (Button) findViewById(R.id.button7);
                B.setText("O");
                table[2][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[0][1] == 'O') {
            if ((table[0][2] != 'O' && table[0][2] != 'X') && (table[0][0] != 'X' && table[0][0] != 'O')) {
                B = (Button) findViewById(R.id.button2);
                B.setText("O");
                table[0][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //21
        if (table[2][1] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[0][1] != 'X' && table[0][1] != 'O')) {
                B = (Button) findViewById(R.id.button5);
                B.setText("O");
                table[0][1] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[2][1] == 'O') {
            if ((table[2][2] != 'O' && table[2][2] != 'X') && (table[2][0] != 'X' && table[2][0] != 'O')) {
                B = (Button) findViewById(R.id.button10);
                B.setText("O");
                table[2][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        //12
        if (table[1][2] == 'O') {
            if ((table[2][2] != 'O' && table[2][2] != 'X') && (table[0][2] != 'X' && table[0][2] != 'O')) {
                B = (Button) findViewById(R.id.button8);
                B.setText("O");
                table[0][2] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
        if (table[1][2] == 'O') {
            if ((table[1][1] != 'O' && table[1][1] != 'X') && (table[1][0] != 'X' && table[1][0] != 'O')) {
                B = (Button) findViewById(R.id.button3);
                B.setText("O");
                table[1][0] = 'O';
                turn = !turn;
                count++;
                B.setEnabled(false);
                return;
            }
        }
    }

    private void checkwin(View view) {
        //checking for X
        //diagonal '\'
        if (table[0][0] == 'X') {
            if ((table[1][1] == 'X') && (table[2][2] == 'X')) {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(1, 5, 9);
                count = 0;
            }
        }
        //diagonal '/'
        if (table[2][0] == 'X') {
            if ((table[1][1] == 'X') && (table[0][2] == 'X')) {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(3, 5, 7);
                count = 0;
            }
        }
        //horizontal1
        if (table[0][0] == 'X') {
            if (table[1][0] == 'X' && table[2][0] == 'X') {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(1, 2, 3);
                count = 0;
            }
        }
        //horizontal2
        if (table[0][1] == 'X') {
            if (table[1][1] == 'X' && table[2][1] == 'X') {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(4, 5, 6);
                count = 0;
            }
        }
        //horizontal3
        if (table[0][2] == 'X') {
            if (table[1][2] == 'X' && table[2][2] == 'X') {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(7, 8, 9);
                count = 0;
            }
        }
        //vertical1
        if (table[0][0] == 'X') {
            if (table[0][1] == 'X' && table[0][2] == 'X') {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(1, 4, 7);
                count = 0;
            }
        }
        //vertical2
        if (table[1][0] == 'X') {
            if (table[1][1] == 'X' && table[1][2] == 'X') {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(2, 5, 8);
                count = 0;
            }
        }
        //vertical3
        if (table[2][0] == 'X') {
            if (table[2][1] == 'X' && table[2][2] == 'X') {
                Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(3, 6, 9);
                count = 0;
            }
        }
        //checking 'O'
        //diagonal '\'
        if (table[0][0] == 'O') {
            if ((table[1][1] == 'O') && (table[2][2] == 'O')) {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(1, 5, 9);
                count = 0;
            }
        }
        //diagonal '/'
        if (table[2][0] == 'O') {
            if ((table[1][1] == 'O') && (table[0][2] == 'O')) {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(3, 5, 7);
                count = 0;
            }
        }
        //horizontal1
        if (table[0][0] == 'O') {
            if (table[1][0] == 'O' && table[2][0] == 'O') {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(1, 2, 3);
                count = 0;
            }
        }
        //horizontal2
        if (table[0][1] == 'O') {
            if (table[1][1] == 'O' && table[2][1] == 'O') {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(4, 5, 6);
                count = 0;
            }
        }
        //horizontal3
        if (table[0][2] == 'O') {
            if (table[1][2] == 'O' && table[2][2] == 'O') {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(7, 8, 9);
                count = 0;
            }
        }
        //vertical1
        if (table[0][0] == 'O') {
            if (table[0][1] == 'O' && table[0][2] == 'O') {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(1, 4, 7);
                count = 0;
            }
        }
        //vertical2
        if (table[1][0] == 'O') {
            if (table[1][1] == 'O' && table[1][2] == 'O') {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(2, 5, 8);
                count = 0;
            }
        }
        //vertical3
        if (table[2][0] == 'O') {
            if (table[2][1] == 'O' && table[2][2] == 'O') {
                Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_LONG).show();
                freezeGame();
                colorchange(3, 6, 9);
                count = 0;
            }
        }
        if (count == 9 && winc == 0) {
            Toast.makeText(getApplicationContext(), "Match Draw", Toast.LENGTH_LONG).show();
            count = 0;
        }


    }

    private void colorchange(int x, int y, int z) {
        win[0] = x;
        win[1] = y;
        win[2] = z;
        Button l = (Button) findViewById(R.id.newgame);

        for (int i = 0; i < 3; i++) {
            switch (win[i]) {
                case 1:
                    l = (Button) findViewById(R.id.button2);
                    break;
                case 2:
                    l = (Button) findViewById(R.id.button3);
                    break;
                case 3:
                    l = (Button) findViewById(R.id.button4);
                    break;
                case 4:
                    l = (Button) findViewById(R.id.button5);
                    break;
                case 5:
                    l = (Button) findViewById(R.id.button6);
                    break;
                case 6:
                    l = (Button) findViewById(R.id.button7);
                    break;
                case 7:
                    l = (Button) findViewById(R.id.button8);
                    break;
                case 8:
                    l = (Button) findViewById(R.id.button9);
                    break;
                case 9:
                    l = (Button) findViewById(R.id.button10);
                    break;
            }
            l.setTextColor(Color.parseColor("#669933"));
            winc = 1;

        }
    }

    private void freezeGame() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(false);
                    }
                }
            }
        }
    }


}