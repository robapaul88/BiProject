package pack.bi.activities;

import pack.bi.R;
import pack.bi.model.Category;
import pack.bi.storage.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    Button showStatisticsBtn;
    Spinner productsSpinner;
    List<Category> categories;
    RadioGroup optionsRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        showStatisticsBtn = (Button) findViewById(R.id.showStatisticsBtn);
        showStatisticsBtn.setOnClickListener(this);

        productsSpinner = (Spinner) findViewById(R.id.productsSpinner);
        productsSpinner.setOnItemSelectedListener(this);
        putCategoriesOnSpinner();

        optionsRadio = (RadioGroup) findViewById(R.id.optionsRadioGroup);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // add items into spinner dynamically
    public void putCategoriesOnSpinner() {
        DatabaseHandler handler = new DatabaseHandler(this);
        categories = handler.getAllCategories();
        List<String> categoryNames = new ArrayList<String>();
        for (Category cat : categories) {
            categoryNames.add(cat.get_categoryName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productsSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showStatisticsBtn:
                Intent intent = new Intent(new Intent(OptionsActivity.this, StatisticsActivity.class));
                intent.putExtra(StatisticsActivity.CATOGORY_KEY, categories.get(productsSpinner.getSelectedItemPosition()));
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
