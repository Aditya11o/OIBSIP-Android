package com.aditya.unitconverter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.aditya.unitconverter.R;
import com.aditya.unitconverter.helper.UnitConverterLogic;
import com.aditya.unitconverter.helper.ValidationHelper;
import com.aditya.unitconverter.helper.ValidationResult;
import com.aditya.unitconverter.model.Category;
import com.aditya.unitconverter.utils.FormatterUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private Spinner spinnerSourceUnit;
    private Spinner spinnerTargetUnit;
    private TextInputEditText etInputValue;
    private MaterialButton btnConvert;
    private MaterialTextView tvResultOutput;

    private Category selectedCategory = Category.LENGTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupCategorySpinner();
        setupConvertButtonListener();
    }

    private void initViews() {
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerSourceUnit = findViewById(R.id.spinner_source_unit);
        spinnerTargetUnit = findViewById(R.id.spinner_target_unit);
        etInputValue = findViewById(R.id.et_input_value);
        btnConvert = findViewById(R.id.btn_convert);
        tvResultOutput = findViewById(R.id.tv_result_output);
    }

    private void setupCategorySpinner() {
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories_array,
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = Category.fromPosition(position);
                updateUnitSpinners(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default behavior
            }
        });
    }

    private void updateUnitSpinners(Category category) {
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(
                this,
                category.getStringArrayResId(),
                android.R.layout.simple_spinner_item
        );
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSourceUnit.setAdapter(unitAdapter);
        spinnerTargetUnit.setAdapter(unitAdapter);
    }

    private void setupConvertButtonListener() {
        btnConvert.setOnClickListener(v -> performConversion());
    }

    private void performConversion() {
        String inputStr = etInputValue.getText() != null ? etInputValue.getText().toString() : "";
        String sourceUnit = spinnerSourceUnit.getSelectedItem() != null ? spinnerSourceUnit.getSelectedItem().toString() : "";
        String targetUnit = spinnerTargetUnit.getSelectedItem() != null ? spinnerTargetUnit.getSelectedItem().toString() : "";

        ValidationResult validationResult = ValidationHelper.validate(inputStr, selectedCategory, sourceUnit);

        if (!validationResult.isValid()) {
            Toast.makeText(this, validationResult.getErrorMessageResId(), Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue = Double.parseDouble(inputStr.trim());
        double convertedValue = UnitConverterLogic.convert(inputValue, selectedCategory, sourceUnit, targetUnit);

        String resultText = FormatterUtils.formatResult(convertedValue, targetUnit);
        tvResultOutput.setText(resultText);
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }
}
