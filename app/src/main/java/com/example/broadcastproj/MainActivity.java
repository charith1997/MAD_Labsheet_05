package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView br1;
Button btn;

public  static final String BROADCAST_ACTION = "this is message";

Receiver localListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        br1 = findViewById(R.id.textView4);

        btn = findViewById(R.id.button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        localListner =new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListner,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListner);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundService.startAction(getApplicationContext());
            }
        });
    }

    public class Receiver extends BroadcastReceiver{

        public void onReceive(Context context, Intent intent){
            String currentText =br1.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            br1.setText(currentText);
        }
    }

}
