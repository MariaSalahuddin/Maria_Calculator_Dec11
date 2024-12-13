package com.example.maria_calculator_dec11;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText display;

    Button historyButton;
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        historyButton = findViewById(R.id.buttonHistory);
        // Number Buttons
        int[] numberIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9};

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(this::numberClicked);
        }

        findViewById(R.id.buttonAdd).setOnClickListener(this::operatorClicked);
        findViewById(R.id.buttonSub).setOnClickListener(this::operatorClicked);
        findViewById(R.id.buttonMulti).setOnClickListener(this::operatorClicked);
        findViewById(R.id.buttonDiv).setOnClickListener(this::operatorClicked);

        findViewById(R.id.buttonClear).setOnClickListener(v -> {
            input = "";
            display.setText("");
        });
        findViewById(R.id.buttonHistory).setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, SecondActivity.class);

         startActivity(intent);

        });

        // Equals Button
        findViewById(R.id.buttonEqual).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                char lastChar = input.charAt(input.length() - 2);
                if (Calculator.isOperator(lastChar)) {
                    return;
                }
                String result = Calculator.calculation(input);
                display.setText(result);
                MyApp.history.add(input +" = " +result);
                input = result;
            }
        });

        findViewById(R.id.buttonBack).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                display.setText(input);
            }
        });
    }

    private void numberClicked(View view) {
        Button button = (Button) view;
        input += button.getText().toString();
        display.setText(input);
    }

    private void operatorClicked(View view) {
        if (!input.isEmpty()) {
            char lastChar;
            if(input.length() > 2)
            lastChar = input.charAt(input.length() - 2);
            else
                lastChar = input.charAt(input.length() - 1);

            if (Calculator.isOperator(lastChar)) {
                return;
            }
            input += " " +((Button) view).getText().toString() + " ";
            display.setText(input);
        }
    }

}