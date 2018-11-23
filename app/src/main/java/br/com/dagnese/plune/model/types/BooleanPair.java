package br.com.dagnese.plune.model.types;

public class BooleanPair {
    Integer value;
    String resolved;

    public BooleanPair() {
    }

    public boolean isValue() {
        return value.equals(1);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }
}
