package com.example.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SummaryPage extends AppCompatActivity {

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
        spinnerFreq.add("Bi-weekly");
        spinnerFreq.add("Weekly");
        spinnerFreq.add("Monthly");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerFreq);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems1 = (Spinner) findViewById(R.id.spinner3);
        sItems1.setAdapter(adapter1);

        Bundle extras = getIntent().getExtras();

        String principalString = extras.getString("principal");
        String interestString = extras.getString("interest");
        String periodString = extras.getString("period");

        int principalInt = Integer.valueOf(principalString);
        float interestInt = Float.parseFloat(interestString);
        int periodInt = Integer.valueOf(periodString.charAt(0));

        int str = Character.getNumericValue(periodString.charAt(0));

        TextView result = (TextView) findViewById(R.id.textView12);
        TextView amountText = (TextView) findViewById(R.id.textView7);
        TextView interestText = (TextView) findViewById(R.id.textView13);
        TextView periodText = (TextView) findViewById(R.id.textView15);

        result.setText(periodString.charAt(0)+"");
        amountText.setText("$"+principalInt);
        interestText.setText(interestInt+"%");
        periodText.setText(periodString);
    }
}
