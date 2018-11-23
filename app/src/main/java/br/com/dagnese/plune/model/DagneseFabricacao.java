package br.com.dagnese.plune.model;

import java.util.ArrayList;

import br.com.dagnese.plune.model.types.IntegerPair;
import br.com.dagnese.plune.model.types.StringPair;

public class DagneseFabricacao {
    private int allRows;
    private String Method;
    private int rows;
    private String ErrorStatus;
    private String ClassId;
    private String Description;
    private DagneseFabricacaoData data;

    public class DagneseFabricacaoData {
        ArrayList<DagneseFabricacaoRow> row;
        public DagneseFabricacaoData() {}
        public ArrayList<DagneseFabricacaoRow> getRow() {
            return row;
        }
        public void setRow(ArrayList<DagneseFabricacaoRow> row) {
            this.row = row;
        }
    }

    public class DagneseFabricacaoRow {
        private IntegerPair CompanyId;
        private IntegerPair ObraId;
        private IntegerPair EtapaId;
        private StringPair DesenhoId;
        private StringPair PecaId;
        private StringPair ObraApelido;
        private IntegerPair Id;
        private IntegerPair QuantLib;

        public DagneseFabricacaoRow() {}
        public IntegerPair getCompanyId() {
            return CompanyId;
        }
        public void setCompanyId(IntegerPair companyId) {
            CompanyId = companyId;
        }
        public IntegerPair getObraId() {
            return ObraId;
        }
        public void setObraId(IntegerPair obraId) {
            ObraId = obraId;
        }
        public IntegerPair getEtapaId() { return EtapaId; }
        public void setEtapaId(IntegerPair etapaId) { EtapaId = etapaId; }
        public StringPair getDesenhoId() { return DesenhoId; }
        public void setDesenhoId(StringPair desenhoId) { DesenhoId = desenhoId; }
        public StringPair getObraApelido() {
            return ObraApelido;
        }
        public void setObraApelido(StringPair obraApelido) {
            ObraApelido = obraApelido;
        }
        public StringPair getPecaId() {
            return PecaId;
        }
        public void setPecaId(StringPair pecaId) {
            PecaId = pecaId;
        }
        public IntegerPair getId() { return Id; }
        public void setId(IntegerPair id) { Id = id; }
        public IntegerPair getQuantLib() { return QuantLib; }
        public void setQuantLib(IntegerPair quantLib) { QuantLib = quantLib; }
    }
    public DagneseFabricacao() {}
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
    public DagneseFabricacaoData getData() {
        return data;
    }
    public void setData(DagneseFabricacaoData data) {
        this.data = data;
    }
}