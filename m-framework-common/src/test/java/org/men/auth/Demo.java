package org.men.auth;


import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;

import java.io.IOException;

public class Demo {

    static {
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        //线上环境
//        httpsParam.setAppKey("203848930");
//        httpsParam.setAppSecret("OYm7UyCbdNvyDG7XjlQP5J4gVbXLdZL0");
        HttpsApiClient.getInstance().init(httpsParam);
    }

    public static void main(String[] args) {
        HttpSyncTest();
    }

    public static void HttpSyncTest() {
        ApiResponse response = HttpsApiClient.getInstance().syncMode("门超峰", "61042919920606339X");
        try {
            String resultString = getResultString(response);
            System.out.println("result:"+resultString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        if (response.getCode() != 200) {
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }
        result.append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody(), SdkConstant.CLOUDAPI_ENCODING));
        return result.toString();
    }





}
