package com.XO.odeyfox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.XO.R;

import java.util.Arrays;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button B1 = (Button) findViewById(R.id.button);
        Button B2 = (Button) findViewById(R.id.button2);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, VsAI.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(this, TwoPlayers.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
