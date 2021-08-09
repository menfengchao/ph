package org.men.auth;


import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;

public class HttpsApiClient extends ApacheHttpClient {

    public final static String HOST = "safrvcert.market.alicloudapi.com";
    static HttpsApiClient instance = new HttpsApiClient();

    public static HttpsApiClient getInstance() {
        return instance;
    }

    public void init(HttpClientBuilderParams httpClientBuilderParams) {
        httpClientBuilderParams.setScheme(Scheme.HTTPS);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }


    public ApiResponse syncMode(String realname, String idcard) {
        String path = "/safrv_2meta_id_name/";
        ApiRequest request = new ApiRequest(HttpMethod.GET, path);
        request.addParam("__userId", "1950100446581819", ParamPosition.QUERY, true);
        request.addParam("verifyKey", "IVDB3vOmKY0CFl", ParamPosition.QUERY, true);
        request.addParam("identifyNum", idcard, ParamPosition.QUERY, true);
        request.addParam("userName", realname, ParamPosition.QUERY, true);
        return sendSyncRequest(request);
    }

}