package br.com.dagnese.plune.model.types;

import java.util.Date;

public class DatePair {
    Date value;
    String resolved;

    public DatePair() {
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }
}
