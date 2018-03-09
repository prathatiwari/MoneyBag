package com.pratha.moneybag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class InputOutputActivity extends AppCompatActivity {

    private TextView output;
    private EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_output);

        amount = findViewById(R.id.amount);
        output = findViewById(R.id.output);

        amount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    processAmount();
                }
                return false;
            }
        });
    }

    private void processAmount() {
        MoneyBag moneyBag = new MoneyBag();
        Double inputAmount = Double.parseDouble(amount.getText().toString());
        HashMap cash = moneyBag.getCashForAmount(inputAmount);
        if (cash == null) {
            output.setText(R.string.amount_error);
        } else {
            output.setText(moneyBag.getCashInPrintableFormat(cash, getString(R.string.total_)));
        }
    }
}
