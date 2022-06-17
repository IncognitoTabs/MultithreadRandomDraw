package com.hoangminhtai.multithreadrandomdraw;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnDrawView;
    EditText edtCell;

    Handler handler = new Handler();
    Random random = new Random();
    Integer number, checker, surplus;

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200);

    LinearLayout layout, mainLayout;

    Runnable UIThread = new Runnable() {
        @Override
        public void run() {

            TextView txt = new TextView(MainActivity.this);
            TextView txt2 = new TextView(MainActivity.this);
            TextView txt3 = new TextView(MainActivity.this);

            if (checker % 3 == 0){
                layout = new LinearLayout(MainActivity.this);
                params.setMargins(15,5,15,5);
                layout.setLayoutParams(params);
                layout.setWeightSum(3);
                number = random.nextInt(9);
                createTextView(txt,number,layout);
                number = random.nextInt(9);
                createTextView(txt2,number,layout);
                number = random.nextInt(9);
                createTextView(txt3,number,layout);

                mainLayout.addView(layout);
            }
        }
    };

    Runnable UIThread2 = new Runnable() {
        @Override
        public void run() {
            layout = new LinearLayout(MainActivity.this);
            params.setMargins(15,5,15,5);
            layout.setLayoutParams(params);
            layout.setWeightSum(3);
            for (int i=1;i<=surplus;i++){
                TextView txt1 = new TextView(MainActivity.this);
                number = random.nextInt(9);
                createTextView(txt1,number,layout);
            }
            mainLayout.addView(layout);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        btnDrawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtCell.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Nhap so o", Toast.LENGTH_LONG).show();
                }else {
                    int numCell = Integer.parseInt(edtCell.getText().toString());
                    runInBackground(numCell);
                }
            }
        });
    }

    private void runInBackground(int numCell) {
        mainLayout.removeAllViews();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (numCell % 3 !=0){
                    surplus = numCell - 3*(numCell/3);
                    for (int i = 1; i <= numCell - surplus; i++){
                        checker = i;
                        handler.post(UIThread);
                        SystemClock.sleep(50);
                    }
                    handler.post(UIThread2);
                }else{
                    for (int i = 1; i <= numCell; i++){
                        checker = i;
                        handler.post(UIThread);
                        SystemClock.sleep(50);
                    }
                }
            }
        });
        thread.start();
    }

    private void linkViews() {
        btnDrawView = findViewById(R.id.btnDrawView);
        edtCell = findViewById(R.id.edtCell);
        mainLayout = findViewById(R.id.mainLayout);
    }

    private void createTextView(TextView txt, Integer number, LinearLayout layout){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(3,3,3,3);
        params.weight = 1;
        txt.setLayoutParams(params);
        String text = number.toString();
        txt.setText(text);
        txt.setTextSize(20);
        txt.setGravity(Gravity.CENTER);
        if (number % 2 == 0)
            txt.setBackgroundColor(Color.BLUE);
        else
            txt.setBackgroundColor(Color.GRAY);
        layout.addView(txt);
    }
}