package de.feichhorn.booltr.booltr;

import java.util.HashMap;
import java.util.Map;

public class Normalformen {

    // attributes
    private String expression;
    private String preparedExpression;
    private String konjunktiveNF;
    private String kanonischeKNF;
    private String disjunktiveNF;
    private String kanonischeDNF;

    // methods
    public Normalformen(String expression)  {
        this.setExpression(expression);
    }

    private void setExpression(String expression) {
        this.expression = expression;
    }

    private void computeAllNF() {
        this.preperation();
        this.computeKonjunktiveNF();
        this.computeKanonischeKNF();
        this.computeDisjunktiveNF();
        this.computeKanonischeDNF();
    }

    public HashMap<String,String> getAllNF() {
        HashMap<String,String> allNF = new HashMap<String,String>();
        allNF.put("konjunktiveNF",this.konjunktiveNF);
        allNF.put("kanonischeKNF", this.kanonischeKNF);
        allNF.put("disjunktiveNF", this.disjunktiveNF);
        allNF.put("kanonischeDNF", this.kanonischeDNF);
        return allNF;
    }

    private void preperation() {
        this.applyDeMorganRule(this.replaceImplicationEquivalence());
    }

    private String replaceImplicationEquivalence() {
        if ( expression.contains("→") || expression.contains("↔")) {

            return "return";
        } else {
            return expression;
        }
    }

    private void applyDeMorganRule(String expressionWithoutImplicationEquivalence) {

    }

    private void computeKonjunktiveNF() {

    }

    private void computeKanonischeKNF() {

    }

    private void computeDisjunktiveNF() {

    }

    private void computeKanonischeDNF() {

    }
}
