package br.com.dagnese.plune.model;

import java.util.ArrayList;

import br.com.dagnese.plune.model.types.IntegerPair;
import br.com.dagnese.plune.model.types.StringPair;

public class DagneseUsuarioChapa {
    private int allRows;
    private String Method;
    private int rows;
    private String ErrorStatus;
    private String ClassId;
    private String Description;
    private DagneseUsuarioChapaData data;

    public class DagneseUsuarioChapaData {
        ArrayList<DagneseUsuarioChapaRow> row;
        public DagneseUsuarioChapaData() {}
        public ArrayList<DagneseUsuarioChapaRow> getRow() {
            return row;
        }
        public void setRow(ArrayList<DagneseUsuarioChapaRow> row) {
            this.row = row;
        }
    }

    public class DagneseUsuarioChapaRow {
        private IntegerPair CompanyId;
        private StringPair UserId;
        private StringPair Chapa;

        public DagneseUsuarioChapaRow() {}
        public IntegerPair getCompanyId() {
            return CompanyId;
        }
        public void setCompanyId(IntegerPair companyId) {
            CompanyId = companyId;
        }
        public StringPair getUserId() { return UserId; }
        public void setUserId(StringPair userId) { UserId = userId; }
        public StringPair getChapa() { return Chapa; }
        public void setChapa(StringPair chapa) { Chapa = chapa; }
    }

    public DagneseUsuarioChapa() {}
    public int getAllRows() {
        return allRows;
    }
    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }
    public String getMethod() {
        return Method;
    }
    public void setMethod(String method) {
        Method = method;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public String getErrorStatus() {
        return ErrorStatus;
    }
    public void setErrorStatus(String errorStatus) {
        ErrorStatus = errorStatus;
    }
    public String getClassId() {
        return ClassId;
    }
    public void setClassId(String classId) {
        ClassId = classId;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public DagneseUsuarioChapaData getData() {
        return data;
    }
    public void setData(DagneseUsuarioChapaData data) {
        this.data = data;
    }
}