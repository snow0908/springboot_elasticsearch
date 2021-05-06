package com.snow.learn.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtil {

    // 默认字符集
    private static final String ENCODING = "UTF-8";
    private static final CloseableHttpClient client = HttpClients.createDefault();
    /**
     * @Title: sendPost
     * @Description: (发送post请求)
     * @param url 请求地址
     * @param headers 请求头
     * @param data 请求实体
     * @param encoding 字符集
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendPost(String url,Map<String, String> headers, JSONObject data, String encoding) {
        // 请求返回结果
        String resultJson = null;
        // 创建HttpPost对象
        HttpPost httpPost = new HttpPost();

        try {
            // 设置请求地址
            httpPost.setURI(new URI(url));
            // 设置请求头
            if (headers != null) {
                setHeaders(httpPost, new BasicHeader[headers.size()],headers);
            }
            // 设置实体
            httpPost.setEntity(new StringEntity((JSON.toJSONString(data)),encoding));
            // 发送请求,返回响应对象
            CloseableHttpResponse response = client.execute(httpPost);
            // 获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                // 获取响应结果
                resultJson = EntityUtils.toString(response.getEntity(), encoding);
            } else {
            }

        } catch (Exception e) {
        } finally {
            httpPost.releaseConnection();
        }
        return resultJson;
    }

    /**
     * 设置头
     * @param httpPost
     * @param allHeader
     * @param headers
     */
    private static void setHeaders(HttpPost httpPost,Header[] allHeader,Map<String, String> headers){
        int i = 0;
        for (Map.Entry<String, String> entry: headers.entrySet()){
            allHeader[i] = new BasicHeader(entry.getKey(), entry.getValue());
            i++;
        }
        httpPost.setHeaders(allHeader);
    }

    /**
     * @Title: sendPost
     * @Description: (发送post请求，请求数据默认使用json格式，默认使用UTF-8编码)
     * @param url 请求地址
     * @param data 请求实体
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendPost(String url, JSONObject data) {
        // 设置默认请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json;charset=utf-8");

        return sendPost(url, headers, data, ENCODING);
    }

    /**
     * @Title: sendPost
     * @Description: (发送post请求，请求数据默认使用json格式，默认使用UTF-8编码)
     * @param url 请求地址
     * @param params 请求实体
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendPost(String url,Map<String,Object> params){
        // 设置默认请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        // 将map转成json
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(params));
        return sendPost(url,headers,data,ENCODING);
    }

    /**
     * @Title: sendPost
     * @Description: (发送post请求，请求数据默认使用UTF-8编码)
     * @param url 请求地址
     * @param headers 请求头
     * @param data 请求实体
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendPost(String url, Map<String, String> headers, JSONObject data) {
        return sendPost(url, headers, data, ENCODING);
    }

    /**
     * @Title: sendPost
     * @Description:(发送post请求，请求数据默认使用UTF-8编码)
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求实体
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendPost(String url,Map<String,String> headers,Map<String,String> params){
        // 将map转成json
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(params));
        return sendPost(url,headers,data,ENCODING);
    }

    /**
     * @Title: sendGet
     * @Description: (发送get请求)
     * @param url 请求地址
     * @param params 请求参数
     * @param encoding 编码
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendGet(String url,Map<String,Object> params,String encoding){
        // 请求结果
        String resultJson = null;
        // 创建client

        // 创建HttpGet
        HttpGet httpGet = new HttpGet();
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            // 封装参数
            if(params != null){
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key).toString());
                }
            }
            URI uri = builder.build();
            // 设置请求地址
            httpGet.setURI(uri);
            // 发送请求，返回响应对象
            CloseableHttpResponse response = client.execute(httpGet);
            // 获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if(status == HttpStatus.SC_OK){
                // 获取响应数据
                resultJson = EntityUtils.toString(response.getEntity(), encoding);
            }else{
            }
        } catch (Exception e) {
        } finally {
            httpGet.releaseConnection();
        }
        return resultJson;
    }

    /**
     * @Title: sendGet
     * @Description: (发送get请求)
     * @param url 请求地址
     * @param params 请求参数
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendGet(String url,Map<String,Object> params){
        return sendGet(url,params,ENCODING);
    }


    /**
     * @Title: sendGet
     * @Description: (发送get请求)
     * @param url 请求地址
     * @author MrHu
     * @return String
     * @throws
     */
    public static String sendGet(String url){
        return sendGet(url,null,ENCODING);
    }

}
