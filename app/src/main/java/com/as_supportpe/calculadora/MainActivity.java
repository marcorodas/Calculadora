package com.as_supportpe.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String btns = "C,Open,Close,Back,Div,Multi,Minus,Plus,Equal,Point";
        for (int i = 0; i < 10; i++) { btns = btns + ",Num" + i; }
        for(String btn:btns.split(",")){
            int resID = getResources().getIdentifier("btn" + btn,"id",getPackageName());
            findViewById(resID).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        TextView txtExpresion = (TextView) findViewById(R.id.txtExpresion);
        TextView txtResult = (TextView) findViewById(R.id.txtResult);
        String btnTxt = ((Button)v).getText().toString();

        switch (v.getId()){
            case R.id.btnC:
                txtExpresion.setText("");
                txtResult.setText("0");
                break;
            case R.id.btnBack:
                CharSequence txt = txtExpresion.getText();
                txt = txt.subSequence(0,txt.length()-1);
                txtExpresion.setText(txt);
                break;
            case R.id.btnOpen:
                txtExpresion.append(btnTxt);
                break;
            case R.id.btnClose:
                txtExpresion.append(btnTxt);
                break;
            case R.id.btnDiv:
                txtExpresion.append(btnTxt);
                break;
            case R.id.btnMulti:
                txtExpresion.append(btnTxt);
                break;
            case R.id.btnMinus:
                txtExpresion.append(btnTxt);
                break;
            case R.id.btnPlus:
                txtExpresion.append(btnTxt);
                break;
            case R.id.btnEqual:

                break;
            case R.id.btnPoint:
                txtExpresion.append(btnTxt);
                break;
            default:
                txtExpresion.append(btnTxt);
        }
    }
}
