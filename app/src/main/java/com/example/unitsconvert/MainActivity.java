package com.example.unitsconvert;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerInputUnit;
    private Spinner spinnerOutputUnit;
    private EditText inputvalue;
    private TextView outputvalue;
    private Button convertbutton;
    private FrameLayout frame;
    private Button tempbutton, lengthbutton,timebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerInputUnit = findViewById(R.id.spinnerInputUnit);
        spinnerOutputUnit = findViewById(R.id.spinnerOutputUnit);
        inputvalue = findViewById(R.id.inputvalue);
        outputvalue = findViewById(R.id.outputvalue);
        convertbutton = findViewById(R.id.convertbutton);
        frame = findViewById(R.id.frame);
        tempbutton = findViewById(R.id.tempbutton);
        lengthbutton = findViewById(R.id.lengthbutton);
        timebutton = findViewById(R.id.timebutton);

        

        
        String[] tempUnits = {"Select unit","Celsius", "Fahrenheit", "Kelvin"};
        String[] lengthUnits = {"km", "m", "miles"};
        String[] timeUnits = {"Seconds", "Minutes", "Hours"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tempUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInputUnit.setAdapter(adapter);
        spinnerOutputUnit.setAdapter(adapter);

        tempbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, tempUnits);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerInputUnit.setAdapter(adapter);
                spinnerOutputUnit.setAdapter(adapter);
            }
        });

        lengthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, lengthUnits);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerInputUnit.setAdapter(adapter);
                spinnerOutputUnit.setAdapter(adapter);
            }
        });

        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, timeUnits);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerInputUnit.setAdapter(adapter);
                spinnerOutputUnit.setAdapter(adapter);
            }
        });


        
        convertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputText = inputvalue.getText().toString();

        if (inputText.isEmpty()) {
            outputvalue.setText("Please input a valid number");
            outputvalue.setTextColor(Color.RED);
            return;
        }
        outputvalue.setTextColor(Color.BLACK);


        Double inputValue;
        try {
            inputValue = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            outputvalue.setTextColor(Color.RED);
            outputvalue.setText("Invalid input. Please enter a valid number.");
            return;
        }

        String inputUnit = spinnerInputUnit.getSelectedItem().toString();
        String outputUnit = spinnerOutputUnit.getSelectedItem().toString();
        double result;


            if (inputUnit.equals("Select unit") || outputUnit.equals("Select unit")) {
                outputvalue.setText("Please select valid units");
                outputvalue.setTextColor(Color.RED);
                return;
            }
        outputvalue.setTextColor(Color.BLACK);

        
        if (inputUnit.equals("km") && outputUnit.equals("m")) {
            result = inputValue * 1000; 
        } else if (inputUnit.equals("m") && outputUnit.equals("km")) {
            result = inputValue / 1000; 
        } else if (inputUnit.equals("miles") && outputUnit.equals("km")) {
            result = inputValue * 1.60934f; 
        } else if (inputUnit.equals("km") && outputUnit.equals("miles")) {
            result = inputValue / 1.60934f; 
        }else if (inputUnit.equals("miles") && outputUnit.equals("m")) {
            result = inputValue *  1609.34f; 
        }else if (inputUnit.equals("m") && outputUnit.equals("miles")) {
            result = inputValue / 1609.34f;
        }

        else if (inputUnit.equals("Celsius") && outputUnit.equals("Fahrenheit")) {
            result = (inputValue * 9/5) + 32;
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Kelvin")) {
            result = inputValue + 273.15; 
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Celsius")) {
            result = (inputValue - 32) * 5/9; 
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Kelvin")) {
            result = (inputValue - 32) * 5/9 + 273.15; 
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Celsius")) {
            result = inputValue - 273.15; 
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Fahrenheit")) {
            result = (inputValue - 273.15) * 9/5 + 32; 
        }

        else if (inputUnit.equals("Seconds") && outputUnit.equals("Minutes")) {
            result = inputValue / 60; 
        } else if (inputUnit.equals("Seconds") && outputUnit.equals("Hours")) {
            result = inputValue / 3600; 
        } else if (inputUnit.equals("Minutes") && outputUnit.equals("Seconds")) {
            result = inputValue * 60;
        } else if (inputUnit.equals("Minutes") && outputUnit.equals("Hours")) {
            result = inputValue / 60; 
        } else if (inputUnit.equals("Hours") && outputUnit.equals("Seconds")) {
            result = inputValue * 3600;
        } else if (inputUnit.equals("Hours") && outputUnit.equals("Minutes")) {
            result = inputValue * 60; 
        }else {
            result = inputValue; 
        }

        
        outputvalue.setText(String.format("= %.5f %s",result,outputUnit));
    }
}
