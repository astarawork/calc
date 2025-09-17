package com.nimaseyfinejad.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText display;
    StringBuilder currentInput = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] buttons = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,
                R.id.btnAdd,R.id.btnSub,R.id.btnMul,R.id.btnDiv,R.id.btnDot};

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                currentInput.append(b.getText().toString());
                display.setText(currentInput.toString());
            }
        };

        for (int id : buttons) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btnEq).setOnClickListener(v -> {
            try {
                double result = eval(currentInput.toString());
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
            } catch (Exception e) {
                display.setText("Error");
                currentInput.setLength(0);
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentInput.setLength(0);
            display.setText("");
        });
    }

    private double eval(String expression) {
        try {
            return new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(expression) instanceof Number ?
                   ((Number) new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(expression)).doubleValue() : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}
