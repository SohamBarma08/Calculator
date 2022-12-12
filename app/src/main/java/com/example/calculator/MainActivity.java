package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,equation;
    MaterialButton buttonClear, buttonAllClear,buttonBracketOpen,buttonBracketClose,buttonDecimal;
    MaterialButton buttonAdd,buttonSubstract,buttonMultiply,buttonDivide,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        equation = findViewById(R.id.equation);

        assingId(buttonClear,R.id.buttonClear);
        assingId(buttonAllClear,R.id.buttonAllClear);
        assingId(buttonBracketOpen,R.id.buttonBracketOpen);
        assingId(buttonBracketClose,R.id.buttonBracketClose);
        assingId(buttonDecimal,R.id.buttonDecimal);
        assingId(buttonAdd,R.id.buttonadd);
        assingId(buttonSubstract,R.id.buttonsub);
        assingId(buttonMultiply,R.id.buttonmultiply);
        assingId(buttonDivide,R.id.buttondivide);
        assingId(buttonEquals,R.id.buttonequals);
        assingId(button0,R.id.button0);
        assingId(button1,R.id.button1);
        assingId(button2,R.id.button2);
        assingId(button3,R.id.button3);
        assingId(button4,R.id.button4);
        assingId(button5,R.id.button5);
        assingId(button6,R.id.button6);
        assingId(button7,R.id.button7);
        assingId(button8,R.id.button8);
        assingId(button9,R.id.button9);

    }

    void assingId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = equation.getText().toString();

        if(buttonText.equals("AC")){
            equation.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            equation.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        }
        else{
            dataToCalculate = dataToCalculate + buttonText;
        }
        equation.setText(dataToCalculate);

        String finalResult = getReasult(dataToCalculate);

        if(!finalResult.equals("ERROR")){
            result.setText(finalResult);
        }
    }

    String getReasult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "ERROR";
        }

    }
}