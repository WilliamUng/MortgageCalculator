package com.example.mortgagecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.lang.Math;

public class SummaryPage extends AppCompatActivity{

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            refreshCalc();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        List<String> spinnerCurrency = new ArrayList<String>();
        spinnerCurrency.add("Dollar");
        spinnerCurrency.add("Euro");
        spinnerCurrency.add("Pound");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerCurrency);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);

        List<String> spinnerFreq = new ArrayList<String>();
        spinnerFreq.add("Monthly");
        spinnerFreq.add("Bi-weekly");
        spinnerFreq.add("Weekly");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerFreq);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems1 = (Spinner) findViewById(R.id.spinner3);
        sItems1.setAdapter(adapter1);

        sItems.setOnItemSelectedListener(new MyOnItemSelectedListener());
        sItems1.setOnItemSelectedListener(new MyOnItemSelectedListener());

        refreshCalc();
    }

    void refreshCalc(){
        Bundle extras = getIntent().getExtras();

        String principalString = extras.getString("principal");
        String interestString = extras.getString("interest");
        String periodString = extras.getString("period");

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner3);
        String frequencyText = mySpinner.getSelectedItem().toString();

        Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner2);
        String currencyText = mySpinner2.getSelectedItem().toString();

        int principalInt = Integer.valueOf(principalString);
        double interestFloat = Double.parseDouble(interestString);
        int periodInt = Integer.parseInt(periodString.substring(0, periodString.indexOf(" ")));

        int str = Character.getNumericValue(periodString.charAt(0));
        int n = 0;

        interestFloat = interestFloat / 100;

        double interestRate = 0;

        switch (frequencyText) {
            case "Bi-weekly":
                interestRate = interestFloat / 24;
                n = periodInt * 24;
                break;
            case "Weekly":
                interestRate = interestFloat / 48;
                n = periodInt * 48;
                break;
            case "Monthly":
                interestRate = interestFloat / 12;
                n = periodInt * 12;
                break;
        }

        char unit = ' ';

        switch (currencyText) {
            case "Dollar":
                unit = '$';
                break;
            case "Euro":
                unit = '€';
                break;
            case "Pound":
                unit = '£';
                break;
        }

        double amount = principalInt * (interestRate*(Math.pow(1+interestRate, n))/(Math.pow(1+interestRate, n)-1));

        TextView result = (TextView) findViewById(R.id.textView12);
        TextView amountText = (TextView) findViewById(R.id.textView7);
        TextView interestText = (TextView) findViewById(R.id.textView13);
        TextView periodText = (TextView) findViewById(R.id.textView15);

        result.setText(unit+String.format("%.2f", amount));
        amountText.setText(String.valueOf(unit)+principalInt+"");
        interestText.setText(interestFloat+"%");
        periodText.setText(periodString);
    }

    public void Help (View v) {
        Intent details = new Intent (this, Details.class);
        startActivity(details);
    }
}