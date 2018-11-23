package br.com.dagnese.plune;
import java.util.Map;

import br.com.dagnese.plune.model.DagneseFabricacao;
import br.com.dagnese.plune.model.DagneseFabricacaoChecagem;
import br.com.dagnese.plune.model.DagneseUsuarioChapa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PluneServices {

    String URL_FABRICACAO_CHECAGEM = "https://dagnese.plune.com.br/JSON/Dagnese.FabricacaoChecagem/";
    String URL_FABRICACAO = "https://dagnese.plune.com.br/JSON/Dagnese.Fabricacao/";

    String AUTH_TOKEN = "Ultra.Users:74-44-4350201";
    //String PASSWORD = "$m4rtph0n3!2018";

    @GET("Browse?_AuthToken=" + AUTH_TOKEN + "&_Dagnese.Fabricacao.BrowseLimit=1&_Dagnese.Fabricacao.BrowseSequence=CompanyId,ObraId,EtapaId,PecaId,Id,DesenhoId,ObraApelido,PecaId,Id,QuantLib")
    Call<DagneseFabricacao> browseFabricacao(@Query("Dagnese.Fabricacao.Id") int fabricacao);

    @GET("Insert?_AuthToken=" + AUTH_TOKEN)
    Call<DagneseFabricacaoChecagem> insertFabricacaoChecagem(@Query("Dagnese.FabricacaoChecagem.CompanyId") int companyId,
                                                             @Query("Dagnese.FabricacaoChecagem.ObraId") int obraId,
                                                             @Query("Dagnese.FabricacaoChecagem.EtapaId") int etapaId,
                                                             @Query("Dagnese.FabricacaoChecagem.DesenhoId") String desenhoId,
                                                             @Query("Dagnese.FabricacaoChecagem.PecaId") String pecaId,
                                                             @Query("Dagnese.FabricacaoChecagem.FabricacaoId") int fabricacaoId,
                                                             @Query("Dagnese.FabricacaoChecagem.QtdPacote") int quantidadePacote,
                                                             @Query("Dagnese.FabricacaoChecagem.SetorId") String setorId,
                                                             @Query("Dagnese.FabricacaoChecagem.CodigoBarras") String codigoBarras);
    @GET("Select?_AuthToken=" + AUTH_TOKEN)
    Call<DagneseUsuarioChapa> selectUsuario(@Query("Dagnese.UsuarioChapa.CompanyId") int companyId,
                                            @Query("Dagnese.UsuarioChapa.Chapa") String chapa);
}
