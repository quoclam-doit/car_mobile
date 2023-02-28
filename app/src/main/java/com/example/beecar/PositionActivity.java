package com.example.beecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class PositionActivity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout btn_client;
    LinearLayout btn_driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        toolbar = findViewById(R.id.tool_bar_pa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_client = findViewById(R.id.btn_client);
        btn_driver = findViewById(R.id.btn_driver);

        //effect
    btn_client.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            btn_client.setBackgroundResource(R.drawable.bg_click_position_pa);
                }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP){
            btn_client.setBackgroundResource(R.drawable.bg_position_pa);
                }
            return false;
    }
});
        btn_driver.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_driver.setBackgroundResource(R.drawable.bg_click_position_pa);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btn_driver.setBackgroundResource(R.drawable.bg_position_pa);
                }
                return false;
            }
        });

        //start activity

    btn_client.setOnClickListener(view -> {
        Intent i_client = new Intent(this, RegisterClientActivity.class);
        i_client.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i_client);
    });
        btn_driver.setOnClickListener(view -> {
            Intent i_driver =new Intent(this,RegisterDriverActivity.class );
            i_driver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i_driver);
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}