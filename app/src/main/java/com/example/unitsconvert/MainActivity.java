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

        

        // Set up spinner with options
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


        // Set up the convert button action
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


        float inputValue;
        try {
            inputValue = Float.parseFloat(inputText);
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
            result = inputValue * 1000; // km to m
        } else if (inputUnit.equals("m") && outputUnit.equals("km")) {
            result = inputValue / 1000; // m to km
        } else if (inputUnit.equals("miles") && outputUnit.equals("km")) {
            result = inputValue * 1.60934f; // miles to km
        } else if (inputUnit.equals("km") && outputUnit.equals("miles")) {
            result = inputValue / 1.60934f; // km to miles
        }

        else if (inputUnit.equals("Celsius") && outputUnit.equals("Fahrenheit")) {
            result = (inputValue * 9/5) + 32; // Celsius to Fahrenheit
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Kelvin")) {
            result = inputValue + 273.15; // Celsius to Kelvin
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Celsius")) {
            result = (inputValue - 32) * 5/9; // Fahrenheit to Celsius
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Kelvin")) {
            result = (inputValue - 32) * 5/9 + 273.15; // Fahrenheit to Kelvin
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Celsius")) {
            result = inputValue - 273.15; // Kelvin to Celsius
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Fahrenheit")) {
            result = (inputValue - 273.15) * 9/5 + 32; // Kelvin to Fahrenheit
        }

        else if (inputUnit.equals("Seconds") && outputUnit.equals("Minutes")) {
            result = inputValue / 60; // Seconds to Minutes
        } else if (inputUnit.equals("Seconds") && outputUnit.equals("Hours")) {
            result = inputValue / 3600; // Seconds to Hours
        } else if (inputUnit.equals("Minutes") && outputUnit.equals("Seconds")) {
            result = inputValue * 60; // Minutes to Seconds
        } else if (inputUnit.equals("Minutes") && outputUnit.equals("Hours")) {
            result = inputValue / 60; // Minutes to Hours
        } else if (inputUnit.equals("Hours") && outputUnit.equals("Seconds")) {
            result = inputValue * 3600; // Hours to Seconds
        } else if (inputUnit.equals("Hours") && outputUnit.equals("Minutes")) {
            result = inputValue * 60; // Hours to Minutes
        }else {
            result = inputValue; // Same units, no conversion needed
        }

        // Display the result
        outputvalue.setText(String.format("= %.5f %s",result,outputUnit));
    }
}
