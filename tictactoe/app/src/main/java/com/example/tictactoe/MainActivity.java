package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean click = true; // true is x, false is y
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private TextView textView;
    private Button buttonnewgame;
    private View view;
    private int count;
    int dem = 0;

    private int check1 = 0;
    private int check2 = 0;
    private int check3 = 0;
    private int check4 = 0;
    private int check5 = 0;
    private int check6 = 0;
    private int check7 = 0;
    private int check8 = 0;
    private int check9 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
//        buttonnewgame.setVisibility(view.INVISIBLE);
    }

    private void connectView() {
        button1 = findViewById(R.id.but1);
        button2 = findViewById(R.id.but2);
        button3 = findViewById(R.id.but3);
        button4 = findViewById(R.id.but4);
        button5 = findViewById(R.id.but5);
        button6 = findViewById(R.id.but6);
        button7 = findViewById(R.id.but7);
        button8 = findViewById(R.id.but8);
        button9 = findViewById(R.id.but9);
        textView = findViewById(R.id.win);
        buttonnewgame = findViewById(R.id.newgame);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.but1) {
            if (click) {
                button1.setText("X");
                click = false;
                check1 = 1;
            } else {
                button1.setText("O");
                click = true;
                check1 = 2;
            }
            button1.setEnabled(false);
        } else if (view.getId() == R.id.but2) {
            if (click) {
                button2.setText("X");
                click = false;
                check2 = 1;
            } else {
                button2.setText("O");
                click = true;
                check2 = 2;
            }
            button2.setEnabled(false);
        } else if (view.getId() == R.id.but3) {
            if (click) {
                button3.setText("X");
                click = false;
                check3 = 1;
            } else {
                button3.setText("O");
                click = true;
                check3 = 2;
            }
            button3.setEnabled(false);
        } else if (view.getId() == R.id.but4) {
            if (click) {
                button4.setText("X");
                click = false;
                check4 = 1;
            } else {
                button4.setText("O");
                click = true;
                check4 = 2;
            }
            button4.setEnabled(false);
        } else if (view.getId() == R.id.but5) {
            if (click) {
                button5.setText("X");
                click = false;
                check5 = 1;
            } else {
                button5.setText("O");
                click = true;
                check5 = 2;
            }
            button5.setEnabled(false);
        } else if (view.getId() == R.id.but6) {
            if (click) {
                button6.setText("X");
                click = false;
                check6 = 1;
            } else {
                button6.setText("O");
                click = true;
                check6 = 2;
            }
            button6.setEnabled(false);
        } else if (view.getId() == R.id.but7) {
            if (click) {
                button7.setText("X");
                click = false;
                check7 = 1;
            } else {
                button7.setText("O");
                click = true;
                check7 = 2;
            }
            button7.setEnabled(false);
        } else if (view.getId() == R.id.but8) {
            if (click) {
                button8.setText("X");
                click = false;
                check8 = 1;
            } else {
                button8.setText("O");
                click = true;
                check8 = 2;
            }
            button8.setEnabled(false);
        } else if (view.getId() == R.id.but9) {
            if (click) {
                button9.setText("X");
                click = false;
                check9 = 1;
            } else {
                button9.setText("O");
                click = true;
                check9 = 2;
            }
            button9.setEnabled(false);
        }
        count += 1;
        if (count > 4) {
            checkWIN();
        }
        dem ++;
        if (dem == 9) {
            checkWIN(); // xem laij

            textView.setText("HOA");
            textView.setVisibility(view.VISIBLE);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
            button9.setEnabled(false);
        }
    }

    private void checkWIN() {
        if (check1 == check2 && check1 == check3 && check1 > 0 && check4 > 0) {
            // hàng ngang thứ 1 win
            textWin();
        } else if (check4 == check5 && check4 == check6 && check4 > 0 && check5 > 0) {
            // hàng ngang thứ 2 win
            textWin();
        } else if (check7 == check8 && check7 == check9 && check7 > 0 && check9 > 0) {
            // hàng ngang thứ 3 win
            textWin();
        } else if (check1 == check4 && check1 == check7 && check1 > 0 && check7 > 0) {
            // hàng dọc thứ 1 win
            textWin();
        } else if (check2 == check5 && check2 == check8 && check2 > 0 && check8 > 0) {
            // hàng dọc thứ 2 win
            textWin();
        } else if (check3 == check6 && check3 == check9 && check3 > 0 && check9 > 0) {
            // hàng dọc thứ 3 win
            textWin();
        } else if (check1 == check5 && check1 == check9 && check1 > 0 && check9 > 0) {
            // hàng chéo thứ 1 win
            textWin();
        } else if (check3 == check5 && check3 == check7 && check3 > 0 && check7 > 0) {
            // hàng chéo thứ 2 win
            textWin();
        }
    }

    private void textWin() {
        textView.setText("YOU WIN");
        textView.setVisibility(view.VISIBLE);
        buttonnewgame.setVisibility(view.VISIBLE);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
    }

    public void newGame(View view) {
        textView.setVisibility(view.INVISIBLE);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

        check1 = 0;
        check2 = 0;
        check3 = 0;
        check4 = 0;
        check5 = 0;
        check6 = 0;
        check7 = 0;
        check8 = 0;
        check9 = 0;
        count = 0;
        dem = 0;
    }
}
