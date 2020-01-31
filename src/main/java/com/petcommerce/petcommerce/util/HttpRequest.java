package com.petcommerce.petcommerce.util;

import okhttp3.*;

import java.io.IOException;

public class HttpRequest {

    private final OkHttpClient httpClient = new OkHttpClient();

    public Response sendSoap(String url, String soap, String soapAction) throws IOException{

        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, soap);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "text/xml")
                .addHeader("SOAPAction", soapAction)
                .build();

        return httpClient.newCall(request).execute();
    }
}
