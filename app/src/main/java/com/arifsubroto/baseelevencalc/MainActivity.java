package com.arifsubroto.baseelevencalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView calculator_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator_result = (TextView) findViewById(R.id.calculator_result);

        Button btn_0 = (Button) findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        Button btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        Button btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        Button btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        Button btn_A = (Button) findViewById(R.id.btn_A);
        btn_A.setOnClickListener(this);

        Button btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(this);
        Button btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(this);
        Button btn_mod = (Button) findViewById(R.id.btn_mod);
        btn_mod.setOnClickListener(this);
        Button btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_multiply.setOnClickListener(this);
        Button btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_equal.setOnClickListener(this);

        Button btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                updateResultScreen("0");
                break;
            case R.id.btn_1:
                updateResultScreen("1");
                break;
            case R.id.btn_2:
                updateResultScreen("2");
                break;
            case R.id.btn_3:
                updateResultScreen("3");
                break;
            case R.id.btn_4:
                updateResultScreen("4");
                break;
            case R.id.btn_5:
                updateResultScreen("5");
                break;
            case R.id.btn_6:
                updateResultScreen("6");
                break;
            case R.id.btn_7:
                updateResultScreen("7");
                break;
            case R.id.btn_8:
                updateResultScreen("8");
                break;
            case R.id.btn_9:
                updateResultScreen("9");
                break;
            case R.id.btn_A:
                updateResultScreen("A");
                break;
            case R.id.btn_plus:
                updateResultScreen("+");
                break;
            case R.id.btn_minus:
                updateResultScreen("-");
                break;
            case R.id.btn_multiply:
                updateResultScreen("x");
                break;
            case R.id.btn_mod:
                updateResultScreen("%");
                break;
            case R.id.btn_equal:
                //calculator_result.setText((calculator_result.getText().toString()).charAt((calculator_result.getText().toString()).length()-1));
                String a = makeString(calculator_result.getText());
                calculator_result.setText(a.length());
                break;
            case R.id.btn_clear:
                calculator_result.setText("");
                break;
            default:
                break;
        }
    }

    private void updateResultScreen(String text){
        String lastScreenResult = calculator_result.getText().toString();
        if(isOperator(text)){
            if(!((calculator_result == null || lastScreenResult.equals("")))) {
                calculator_result.setText(lastScreenResult + text);
            }
        }else{
            if(!((calculator_result == null || lastScreenResult.equals("")) && text.equals("0"))) {
                calculator_result.setText(lastScreenResult + text);
            }
        }

        /*if(calculator_result == null || calculator_result.getText().equals("")){
            calculator_result.setText("Arif");
            return;
        }*/

        //String lastScreenResult = ((calculator_result.getText().toString() == null) ? "" : calculator_result.getText().toString());
        //calculator_result.setText(lastScreenResult + text);
    }

    private boolean isOperator(String x){
        if(x.equals("+") || x.equals("-") || x.equals("x") || x.equals("%"))
            return true;
        return false;
    }

    private boolean adaOperator(String exp) {
        if ((exp.indexOf('+') > 0) || (exp.indexOf('-') > 0) || (exp.indexOf('x') > 0) || (exp.indexOf('%') > 0)){
            calculator_result.setText("ada operator");
            return true;
        }
        calculator_result.setText("tidak ada operator");
        return false;
    }

    private boolean canEvaluate(String exp){
        String regex = "(?<=op)|(?=op)".replace("op", "[-+*/()]");
        // actual regex becomes (?<=[-+*/()])|(?=[-+*/()])

        calculator_result.setText(java.util.Arrays.toString(
                exp.split(regex)
        ));
        return true;
    }

    private String bisaDiHitung(String exp){
        if (exp.split("+").length == 2) {
            return "+";
        }else if (exp.split("-").length == 2) {
            return "-";
        }else if (exp.split("x").length == 2) {
            return "x";
        }else if (exp.split("%").length == 2) {
            return "%";
        }
        return "Tidak";
    }

    private String makeString(CharSequence charSequence){
        final StringBuilder sb = new StringBuilder(charSequence.length());
        sb.append(charSequence);
        return sb.toString();
    }
}
