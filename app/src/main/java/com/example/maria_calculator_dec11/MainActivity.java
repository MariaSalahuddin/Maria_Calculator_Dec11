package com.example.maria_calculator_dec11;

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
    TextView historyTextView;
    Button historyButton;
    String input = "";
    ArrayList<String> history = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        historyTextView = findViewById(R.id.history);
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
            if (historyTextView.getVisibility() == View.VISIBLE) {
                historyButton.setText(R.string.history);
                historyTextView.setVisibility(View.GONE);

            }
            else {
                String result = TextUtils.join("\n", history);
                historyTextView.setVisibility(View.VISIBLE);
               if(!result.isEmpty())
                historyTextView.setText(result);
               else
                   historyTextView.setText(R.string.no_history_to_show);
                historyButton.setText(R.string.standard_no_history);
            }
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
                history.add(input +" = " +result);
                input = result;
                if (historyTextView.getVisibility() == View.VISIBLE) {
                    String historyResult = TextUtils.join("\n", history);
                    historyTextView.setText(historyResult);
                }
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