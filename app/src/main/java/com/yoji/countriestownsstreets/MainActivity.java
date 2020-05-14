package com.yoji.countriestownsstreets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner countriesSpinner;
    private Spinner citiesSpinner;
    private Spinner housesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initCountriesSpinnerAdapter();
        initHouseNumberSpinnerAdapter();
    }

    private void initViews() {
        countriesSpinner = findViewById(R.id.countriesSpinnerId);
        citiesSpinner = findViewById(R.id.citiesSpinnerId);
        housesSpinner = findViewById(R.id.housesSpinnerId);
        Button okButton = findViewById(R.id.okButtonId);

        okButton.setOnClickListener(onClickListener);
    }

    private void initCountriesSpinnerAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getResources().getStringArray(R.array.countries);
                initCitiesSpinnerAdapter(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initCitiesSpinnerAdapter(int countryIndex) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (countryIndex) {
            case 0:
                adapter = ArrayAdapter.createFromResource(this, R.array.russian_cities, android.R.layout.simple_spinner_item);
                break;
            case 1:
                adapter = ArrayAdapter.createFromResource(this, R.array.ukrainian_cities, android.R.layout.simple_spinner_item);
                break;
            case 2:
                adapter = ArrayAdapter.createFromResource(this, R.array.belorussian_cities, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null){
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citiesSpinner.setAdapter(adapter);
        }
    }

    private void initHouseNumberSpinnerAdapter() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 0; i < 50; i++) {
            houseNumbers[i] = i + 1;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, houseNumbers);
        housesSpinner.setAdapter(adapter);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String toastMessage = getString(R.string.toast_message, countriesSpinner.getSelectedItem().toString(),
                    citiesSpinner.getSelectedItem().toString(), housesSpinner.getSelectedItem().toString());
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
        }
    };
}
