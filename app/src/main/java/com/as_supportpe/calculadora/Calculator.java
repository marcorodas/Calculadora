package com.as_supportpe.calculadora;

/**
 * Created by Administrator on 13/12/2015.
 */

import org.boris.expr.*;
import org.boris.expr.parser.ExprParser;
import org.boris.expr.util.Exprs;
import org.boris.expr.util.SimpleEvaluationContext;

import java.io.IOException;

public class Calculator {

    public static String eval(String expresion) throws IOException, ExprException {
        SimpleEvaluationContext context = new SimpleEvaluationContext();
        Expr e = ExprParser.parse(expresion);
        Exprs.toUpperCase(e);
        if (e instanceof ExprEvaluatable) {
            e = ((ExprEvaluatable) e).evaluate(context);
        }
        return e.toString();
    }

/*
SimpleEvaluationContext context = new SimpleEvaluationContext();
        System.out.println("Expr Evaluator v1.0");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print(">");
                String line = br.readLine();
                if (line == null)
                    break;
                Expr e = ExprParser.parse(line);
                Exprs.toUpperCase(e);
                if (e instanceof ExprEvaluatable) {
                    e = ((ExprEvaluatable) e).evaluate(context);
                }
                System.out.println(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
*/

}
