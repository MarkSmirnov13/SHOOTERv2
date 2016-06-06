package com.example.user.shooter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartGameButton(View v) {
        /*Toast t = Toast.makeText(this, "Проверка", Toast.LENGTH_SHORT);
        t.show();*/
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
    }
}
