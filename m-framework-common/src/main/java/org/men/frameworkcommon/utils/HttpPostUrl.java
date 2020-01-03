package org.men.frameworkcommon.utils;



import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpPostUrl {

        /**
         * 向指定URL发送POST请求
         * @param url
         * @param paramMap
         * @return 响应结果
         */
        public static String sendPost(String url, Map<String, Object> paramMap) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("content-Type","application/json;charset=UTF-8");
                conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // conn.setRequestProperty("Charset", "UTF-8");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());

                // 设置请求属性
                String param = "";
                if (paramMap != null && paramMap.size() > 0) {
                    Iterator<String> ite = paramMap.keySet().iterator();
                    while (ite.hasNext()) {
                        String key = ite.next();// key
                        Object value = paramMap.get(key);
                        param += key + "=" + value + "&";
                    }
                    param = param.substring(0, param.length() - 1);
                }

                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.err.println("发送 POST 请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输出流、输入流
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return result;
        }

        /**
         * 数据流post请求
         * @param urlStr
         * @param
         */
        public static String doPost(String urlStr, String strInfo) {
            String reStr="";
            try {
                URL url = new URL(urlStr);
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                con.setRequestProperty("Pragma:", "no-cache");
                con.setRequestProperty("Cache-Control", "no-cache");
                con.setRequestProperty("Content-Type", "text/xml");
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(new String(strInfo.getBytes("utf-8")));
                out.flush();
                out.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = "";
                for (line = br.readLine(); line != null; line = br.readLine()) {
                    reStr += line;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return reStr;
        }


    public static void doGet(String urlParam, Map<String, Object> paramMap) {
        try {
            StringBuffer paramStr = new StringBuffer();
            for(String key: paramMap.keySet()){
                paramStr.append(key).append("=").append(paramMap.get(key)).append("&");
            }
            String urlStr = urlParam+"?"+paramStr.toString();
            urlStr=urlStr.substring(0,urlStr.length()-1);
            URL url = new URL(urlStr);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置该连接是可以输出的
            connection.setDoOutput(true);
            // 设置请求方式
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            // 读取数据
            while ((line = br.readLine()) != null) {
                result.append(line + "\n");
            }
            connection.disconnect();
            System.out.println(result.toString());
            if(result.toString().contains("999999")){
                File file =new File("C:\\Users\\lw\\Desktop\\云积分\\拉单1002811.txt");
                Writer out =new FileWriter(file,true);
                out.write(url.toString()+System.getProperty("line.separator"));
                out.close();
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doGetUrl(String urlStr) {
        try {
            URL url = new URL(urlStr);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置该连接是可以输出的
            connection.setDoOutput(true);
            // 设置请求方式
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            // 读取数据
            while ((line = br.readLine()) != null) {
                result.append(line + "\n");
            }
            connection.disconnect();
            System.out.println(result.toString());
            if(result.toString().contains("999999")){
                File file =new File("C:\\Users\\lw\\Desktop\\云积分\\拉单9999.txt");
                Writer out =new FileWriter(file,true);
                out.write(url.toString()+System.getProperty("line.separator"));
                out.close();
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /**
         * {
         * 	"activityId":0,
         * 	"score": 100,
         * 	"activityType": 0,
         * 	"remark": "手动调整",
         * 	"msgId": "9c4eb516f2d9434ffsv916h6r887gh26",
         * 	"ope": "add",
         * 	"merchantNum": 1006259,
         * 	"ip": "113.97.31.12",
         * 	"sessionKey": "6bdfe57261e1941ce7a41cb3998ca280",
         * 	"timestamp": 1546847373138,
         * 	"account": "虫儿飞110110",
         * 	"accountType": 2
         * }
         * @param args
         */
        public static void main(String[] args) {
            Map<String, Object> mapParam = new HashMap<String, Object>();
            mapParam.put("activityId", 0);
            mapParam.put("score",100);
            mapParam.put("activityType",0);
            mapParam.put("remark","手动调整");
            mapParam.put("msgId","9c4eb516f2d9434ffsv916h6r887gh26");
            mapParam.put("ope","add");
            mapParam.put("merchantNum",1006259);
            mapParam.put("ip","113.97.31.12");
            mapParam.put("sessionKey","6bdfe57261e1941ce7a41cb3998ca280");
            mapParam.put("timestamp",1546847373138L);
            mapParam.put("account","");
            mapParam.put("accountType",1);
            String pathUrl = "127.0.0.1:30001/dockplat/adjustPoint.do";
            String result = sendPost(pathUrl, mapParam);
            System.out.println(result);

        }

}
