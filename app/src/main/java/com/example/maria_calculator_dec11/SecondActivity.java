package com.example.maria_calculator_dec11;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
   TextView historyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        historyTextView =  findViewById(R.id.history);
        if (!MyApp.history.isEmpty()) {
            String result = TextUtils.join("\n", MyApp.history);
            historyTextView.setText(result);
        }
        else
            historyTextView.setText(R.string.no_history_to_show);

    }
}
