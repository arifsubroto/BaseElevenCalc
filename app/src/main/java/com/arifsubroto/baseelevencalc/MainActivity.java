package com.arifsubroto.baseelevencalc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
        if(savedInstanceState != null){
            calculator_result.setText(savedInstanceState.getString("last_screen_result"));
        }

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
                Log.d("a", "asdasdasdasdasd");
                break;
            case R.id.btn_multiply:
                updateResultScreen("x");
                break;
            case R.id.btn_mod:
                updateResultScreen("%");
                break;
            case R.id.btn_equal:
                String lastScreen = calculator_result.getText().toString();
                if(issetOperator(lastScreen) && !isOperator(lastScreen.substring(lastScreen.length()-1, lastScreen.length()))){
                    String op = validExpression(calculator_result.getText().toString());
                    String hasil = doCalc(calculator_result.getText().toString(), op);
                    calculator_result.setText(hasil);
                }
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
            if(issetOperator(lastScreenResult)){
                if(isOperator(lastScreenResult.substring(lastScreenResult.length()-1, lastScreenResult.length()))){
                    String changeOperator = lastScreenResult.substring(0, lastScreenResult.length()-1)+text;
                    calculator_result.setText(changeOperator);
                }else{
                    String op = validExpression(lastScreenResult);
                    String hasil = doCalc(lastScreenResult, op);
                    calculator_result.setText(hasil+text);
                }
                /*if(!validExpression(lastScreenResult).equals(null)){
                    String op = validExpression(lastScreenResult);
                    String hasil = "";
                    calculator_result.setText("hasil"+text);
                }else{
                    //String changeOperator = lastScreenResult.substring(0, lastScreenResult.length()-1);
                    //calculator_result.setText("aaaa");
                }*/
            }else if(!((calculator_result == null || lastScreenResult.equals("")))) {
                calculator_result.setText(lastScreenResult + text);
            }
        }else{
            if(text.equals("0")){
                if(calculator_result == null || lastScreenResult.equals("")){
                    //stuck
                }else if(issetOperator(lastScreenResult) && isOperator(lastScreenResult.substring(lastScreenResult.length()-1, lastScreenResult.length()))){
                    //stuck
                }else{
                    calculator_result.setText(lastScreenResult + text);
                }
            }else{
                calculator_result.setText(lastScreenResult + text);
            }
        }
    }

    private boolean isOperator(String x){
        if(x.equals("+") || x.equals("-") || x.equals("x") || x.equals("%"))
            return true;
        return false;
    }

    private boolean issetOperator(String exp) {
        if ((exp.indexOf('+') > 0) || (exp.indexOf('-') > 0) || (exp.indexOf('x') > 0) || (exp.indexOf('%') > 0)){
            return true;
        }
        return false;
    }

    private String validExpression(String exp){
        if (exp.split("\\+").length == 2) {
            return "+";
        }else if (exp.split("\\-").length == 2) {
            return "-";
        }else if (exp.split("x").length == 2) {
            return "x";
        }else if (exp.split("%").length == 2) {
            return "%";
        }
        return null;
    }

    private String doCalc(String exp, String opt){
        String result = "";
        int bil_1, bil_2;
        String bil[];
        switch (opt){
            case "+":
                bil = exp.split("\\+");
                result = do_base11_calc(bil[0], bil[1], "+");
                break;
            case "-":
                bil = exp.split("\\-");
                result = do_base11_calc(bil[0], bil[1], "-");
                break;
            case "x":
                bil = exp.split("x");
                result = do_base11_calc(bil[0], bil[1], "x");
                break;
            case "%":
                bil = exp.split("%");
                result = do_base11_calc(bil[0], bil[1], "%");
                break;
            default:
                result = "0";
        }
        return result;
    }

    private String do_base11_calc(String bil_1, String bil_2, String opt){
        String result = "";
        int temp = 0;
        int b1 = Integer.parseInt(BaseConverter.convertToBase(bil_1, 11, 10));
        int b2 = Integer.parseInt(BaseConverter.convertToBase(bil_2, 11, 10));
        switch (opt){
            case "+":
                temp = b1+b2;
                result = BaseConverter.convertToBase(temp+"", 10, 11);
                break;
            case "-":
                temp = b1-b2;
                //result = temp+"";
                result = BaseConverter.convertToBase(temp+"", 10, 11);
                break;
            case "x":
                temp = b1*b2;
                result = BaseConverter.convertToBase(temp+"", 10, 11);
                break;
            case "%":
                temp = b1%b2;
                result = BaseConverter.convertToBase(temp+"", 10, 11);
                break;
            default:
                result = "0";
        }
        return result;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("last_screen_result", calculator_result.getText().toString());
    }
}
