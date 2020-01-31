package com.petcommerce.petcommerce.frete;

import com.google.gson.Gson;
import com.petcommerce.petcommerce.util.HttpRequest;
import okhttp3.Response;
import org.json.JSONObject;
import org.json.XML;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FreteService {

    private final String CEP_ORIGEM = "13288130";
    private final String CORREIOS_URL_CALCPRECOPRAZO = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx";
    private final String CORREIOS_SOAPACTION = "http://tempuri.org/CalcPrecoPrazo";
    private final HttpRequest httpClient;

    public FreteService(){
        httpClient = new HttpRequest();
    }



    public FreteDto getShipInfo(String cepDestino, Double peso, FreteTipo codigoServico){
        try{
            String soapRequest = String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">" +
                            "<soapenv:Header/>" +
                            "<soapenv:Body>" +
                            "<tem:CalcPrecoPrazo>" +
                            "<tem:nCdEmpresa>%s</tem:nCdEmpresa>" +
                            "<tem:sDsSenha>%s</tem:sDsSenha>" +
                            "<tem:nCdServico>%d</tem:nCdServico>" +
                            "<tem:sCepOrigem>%s</tem:sCepOrigem>" +
                            "<tem:sCepDestino>%s</tem:sCepDestino>" +
                            "<tem:nVlPeso>%s</tem:nVlPeso>" +
                            "<tem:nCdFormato>%s</tem:nCdFormato>" +
                            "<tem:nVlComprimento>%s</tem:nVlComprimento>" +
                            "<tem:nVlAltura>%s</tem:nVlAltura>" +
                            "<tem:nVlLargura>%s</tem:nVlLargura>" +
                            "<tem:nVlDiametro>%s</tem:nVlDiametro>" +
                            "<tem:sCdMaoPropria>%s</tem:sCdMaoPropria>" +
                            "<tem:nVlValorDeclarado>%s</tem:nVlValorDeclarado>" +
                            "<tem:sCdAvisoRecebimento>%s</tem:sCdAvisoRecebimento>" +
                            "</tem:CalcPrecoPrazo>" +
                            "</soapenv:Body>" +
                            "</soapenv:Envelope>",
                    "", "", codigoServico.tipo, CEP_ORIGEM, cepDestino, peso.toString(), "1", "16", "2", "11", "1", "s", "0", "s");
            Response response = httpClient.sendSoap(CORREIOS_URL_CALCPRECOPRAZO, soapRequest, CORREIOS_SOAPACTION);

            if(!response.isSuccessful())
                throw new Exception("Falha no servico dos correios");

            JSONObject xmlJSONObj = XML.toJSONObject(response.body().string());
            return new Gson().fromJson(xmlJSONObj.getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("CalcPrecoPrazoResponse")
                    .getJSONObject("CalcPrecoPrazoResult")
                    .getJSONObject("Servicos")
                    .getJSONObject("cServico").toString(), FreteDto.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
