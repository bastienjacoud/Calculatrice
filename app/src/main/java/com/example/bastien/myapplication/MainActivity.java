package com.example.bastien.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView_result);
        tv.setText("");
    }

    @Override
    public void onClick(View v) {
        TextView tv = findViewById(R.id.textView_result);
        int length = tv.getText().length();
        switch (((Button) v).getText().toString()) {
            case "=":
                if(length > 0){
                    tv.setText(computeValue(tv.getText().toString()));
                }
                break;
            case "C":
                if(length > 0){
                    tv.setText(tv.getText().toString().substring(0, length - 1));
                }
                break;
            default:
                tv.setText(tv.getText().toString().concat(((Button) v).getText().toString()));
                break;
        }
    }

    private String computeValue(String stringValue){
        try{
            Expression e = new ExpressionBuilder(stringValue)
                    .build();
            return String.valueOf(e.evaluate());
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return stringValue;
        }
    }
}
