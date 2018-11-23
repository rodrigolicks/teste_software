package br.com.dagnese.plune.model.types;

public class NumericPair {
    double value;
    String resolved;

    public NumericPair() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }
}
