package br.com.dagnese.plune.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.dagnese.plune.model.types.BooleanPair;
import br.com.dagnese.plune.model.types.DatePair;
import br.com.dagnese.plune.model.types.IntegerPair;
import br.com.dagnese.plune.model.types.StringPair;

public class DagneseFabricacaoChecagem {
    private int allRows;
    private String Method;
    private int rows;
    private String ErrorStatus;
    private String ClassId;
    private String Description;
    private DagneseFabricacaoChecagemData data;

    public class DagneseFabricacaoChecagemData {
        ArrayList<DagneseFabricacaoChecagemRow> row;
        public DagneseFabricacaoChecagemData() {}
        public ArrayList<DagneseFabricacaoChecagemRow> getRow() {
            return row;
        }
        public void setRow(ArrayList<DagneseFabricacaoChecagemRow> row) {
            this.row = row;
        }
    }

    public class DagneseFabricacaoChecagemRow {
        private IntegerPair CompanyId;
        private IntegerPair ObraId;
        private IntegerPair EtapaId;
        private StringPair DesenhoId;
        private StringPair PecaId;
        private IntegerPair FabricacaoId;
        private IntegerPair LeituraId;
        private IntegerPair QtdPacote;
        private StringPair SetorId;
        private BooleanPair FinalProducao;
        private StringPair CodigoBarras;
        private DatePair DataProducao;

        public DagneseFabricacaoChecagemRow() {}
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
        public IntegerPair getEtapaId() {
            return EtapaId;
        }
        public void setEtapaId(IntegerPair etapaId) {
            EtapaId = etapaId;
        }
        public StringPair getDesenhoId() {
            return DesenhoId;
        }
        public void setDesenhoId(StringPair desenhoId) {
            DesenhoId = desenhoId;
        }
        public StringPair getPecaId() {
            return PecaId;
        }
        public void setPecaId(StringPair pecaId) {
            PecaId = pecaId;
        }
        public IntegerPair getFabricacaoId() {
            return FabricacaoId;
        }
        public void setFabricacaoId(IntegerPair fabricacaoId) {
            FabricacaoId = fabricacaoId;
        }
        public IntegerPair getLeituraId() {
            return LeituraId;
        }
        public void setLeituraId(IntegerPair leituraId) {
            LeituraId = leituraId;
        }
        public IntegerPair getQtdPacote() {
            return QtdPacote;
        }
        public void setQtdPacote(IntegerPair qtdPacote) {
            QtdPacote = qtdPacote;
        }
        public StringPair getSetorId() {
            return SetorId;
        }
        public void setSetorId(StringPair setorId) {
            SetorId = setorId;
        }
        public BooleanPair getFinalProducao() {
            return FinalProducao;
        }
        public void setFinalProducao(BooleanPair finalProducao) {
            FinalProducao = finalProducao;
        }
        public StringPair getCodigoBarras() {
            return CodigoBarras;
        }
        public void setCodigoBarras(StringPair codigoBarras) {
            CodigoBarras = codigoBarras;
        }

        public DatePair getDataProducao() {
            return DataProducao;
        }
        public void setDataProducao(DatePair dataProducao) {
            DataProducao = dataProducao;
        }
    }
    public DagneseFabricacaoChecagem() {}
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
    public DagneseFabricacaoChecagemData getData() {
        return data;
    }
    public void setData(DagneseFabricacaoChecagemData data) {
        this.data = data;
    }
}