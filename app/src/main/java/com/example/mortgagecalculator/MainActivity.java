package com.example.mortgagecalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et1 = (EditText) findViewById(R.id.editText2);
        //et1.setHintTextColor(Color.WHITE);

        List<String> spinnerArray = spinnerArray = new ArrayList<String>();

        for (int i = 1; i <= 30; i++)
        {
            spinnerArray.add(i+" years");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
    }

    public void SummaryPage(View v){
        EditText principal = (EditText)findViewById(R.id.editText4);
        EditText interest = (EditText)findViewById(R.id.editText2);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

        String prin = principal.getText().toString();
        String inter = interest.getText().toString();
        String period = mySpinner.getSelectedItem().toString();

        Intent summary = new Intent (this, SummaryPage.class);

        summary.putExtra("principal", prin);
        summary.putExtra("interest", inter);
        summary.putExtra("period", period);

        startActivity(summary);
    }

    public void Help (View v) {
        Intent details = new Intent (this, Details.class);
        startActivity(details);
    }
}
