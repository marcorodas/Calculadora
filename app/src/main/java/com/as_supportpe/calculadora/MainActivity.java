package com.as_supportpe.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.boris.expr.ExprException;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtExpresion;
    private TextView txtResult;
    private int parenthesisCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtExpresion = (TextView) findViewById(R.id.txtExpresion);
        txtResult = (TextView) findViewById(R.id.txtResult);
        String btns = "C,Open,Close,Back,Div,Multi,Minus,Plus,Equal,Point";
        for (int i = 0; i < 10; i++) { btns = btns + ",Num" + i; }
        for(String btn:btns.split(",")){
            int resID = getResources().getIdentifier("btn" + btn,"id",getPackageName());
            findViewById(resID).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String btnTxt = ((Button)v).getText().toString();
        String currTxt = txtExpresion.getText().toString();
        boolean currTxtIsNotEmpty = currTxt.length() != 0;
        char lastCharTxt = currTxtIsNotEmpty ? currTxt.charAt(currTxt.length() - 1) : '?';
        String msj = "";

        switch (v.getId()){
            case R.id.btnC:
                txtExpresion.setText("");
                txtResult.setText("");
                parenthesisCounter = 0;
                break;
            case R.id.btnBack:
                if (currTxtIsNotEmpty) {
                    if (currTxt.endsWith("(")){parenthesisCounter--;}
                    if (currTxt.endsWith(")")){parenthesisCounter++;}
                    currTxt = currTxt.substring(0, currTxt.length() - 1);
                    txtExpresion.setText(currTxt);
                }
                break;
            case R.id.btnOpen:
                if("+-*/(?".contains(Character.toString(lastCharTxt))){
                    txtExpresion.append(btnTxt);
                    parenthesisCounter++;
                }
                break;
            case R.id.btnClose:
                if (parenthesisCounter > 0){
                    if (Character.isDigit(lastCharTxt)) {
                        txtExpresion.append(btnTxt);
                        parenthesisCounter--;
                    }
                }
                break;
            case R.id.btnDiv:
                if (Character.isDigit(lastCharTxt) || lastCharTxt == ')') {
                    txtExpresion.append(btnTxt);
                }                                                
                break;
            case R.id.btnMulti:
                if (Character.isDigit(lastCharTxt) || lastCharTxt == ')') {
                    txtExpresion.append(btnTxt);
                }
                break;
            case R.id.btnMinus:
                if(lastCharTxt != '-'){
                    txtExpresion.append(btnTxt);    
                }
                break;
            case R.id.btnPlus:
                if (Character.isDigit(lastCharTxt) || lastCharTxt == ')') {
                    txtExpresion.append(btnTxt);
                }
                break;
            case R.id.btnEqual:
                if (parenthesisCounter == 0){
                    if (!"+-*/(".contains(Character.toString(lastCharTxt))){
                        try {
                            txtResult.setText(Calculator.eval(currTxt));
                        } catch (IOException e) {
                            msj = e.toString();
                        } catch (ExprException e) {
                            msj = e.toString();
                        }
                    }
                    else{
                        msj = Character.toString(lastCharTxt);
                        msj = "Expresión incompleta: No debe terminar en '{simb}'".replace("{simb}",msj);
                    }
                }
                else{
                    msj = Integer.toString(parenthesisCounter);
                    msj = "Debe cerrar {num} paréntesis".replace("{num}", msj);
                }
                break;
            case R.id.btnPoint:
                if (Character.isDigit(lastCharTxt)) {
                    if (!currTxt.replaceAll("[0-9]", "").endsWith(".")){
                        txtExpresion.append(btnTxt);
                    }
                }                
                break;
            default:
                if(!currTxt.endsWith(")")){
                    txtExpresion.append(btnTxt);
                }
        }
        if (msj.length() > 0) {
            Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
        }
    }
}
