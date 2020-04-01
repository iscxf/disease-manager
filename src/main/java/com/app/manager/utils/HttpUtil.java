package com.app.manager.utils;

import com.app.common.utils.R;
import com.app.manager.domain.HttpResponseMessage;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求工具类，使用httpclient4.4.x以上版本
 * 跳过所有ssl证书验证
 *
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final CloseableHttpClient httpClient;

    private static final String CHARSET = "UTF-8";

    static {
        httpClient = createSSLClientDefault();
    }

    private HttpUtil() {
    }

    public static CloseableHttpClient createSSLClientDefault() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5*60*1000).setSocketTimeout(1*60*1000).build();
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf =
                    new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
        } catch (Exception e) {
            logger.error("init httpClient error, details:{}", e);
        }

        return HttpClients.createDefault();
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static HttpResponseMessage getResult(HttpRequestBase request) {
        HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            entity = response.getEntity();
            httpResponseMessage.setHttpCode(Integer.toString(statusCode));
            if (statusCode == HttpStatus.OK.value() && entity != null) {
                httpResponseMessage.setContent(EntityUtils.toString(entity, CHARSET));
            }
        } catch (Exception e) {
            logger.error("init getResult error, request:{}, details:{}",request, e);
        } finally {
            try{
                if(entity != null){
                    EntityUtils.consume(entity);
                }
                if(response != null){
                    response.close();
                }
            }catch(IOException e){
                logger.error("init getResult finally error, details:{}", e);
            }
        }
        return httpResponseMessage;
    }

    /**
     * 设置请求头
     * @param http      发送的HTTP请求
     * @param headers   请求头
     * @return          发送的HTTP请求
     */
    private static HttpRequestBase setHeaders(HttpRequestBase http, Map<String, Object> headers) {
        if (null != headers) {
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                http.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
        }
        return http;
    }

    /**
     * 将参数转为特定格式
     * @param params    参数
     * @return
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (null != params) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
            }
        }
        return pairs;
    }

    /**
     * post + header + map 发送请求
     *
     * @param url
     * @param headers
     * @param params
     * @return @see HttpResponseMessage
     */
    public static HttpResponseMessage sendPostRequest(String url, Map<String, Object> headers, Map<String, Object> params) throws BaseException {
        logger.info("sendPostRequest, url={}, params={}",url,params);
        try {
            HttpPost request = new HttpPost(url);
            //设置参数
            ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
            request.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            //设置请求头
            request = (HttpPost) setHeaders(request, headers);
            return getResult(request);
        } catch (Exception e) {
            logger.error("sendPostByJson error, details:", e);
            throw new BaseException(10001, e.getMessage());
        }
    }

    /**
     * post + map 发送请求
     *
     * @param url
     * @param params
     * @return @see HttpResponseMessage
     */
    public static HttpResponseMessage sendPostRequest(String url, Map<String, Object> params) throws BaseException {
      return sendPostRequest(url, null, params);
    }

    /**
     * post 发送请求
     *
     * @param url
     * @return @see HttpResponseMessage
     */
    public static HttpResponseMessage sendPostRequest(String url) throws BaseException {
        return sendPostRequest(url, null);
    }

    /**
     * post + json 发送请求
     *
     * @param url
     * @param parameters
     * @return @see HttpResponseMessage
     */
    public static HttpResponseMessage sendPostByJson(String url, String parameters) throws BaseException {
        logger.info("sendPostByJson,url={}, parameters={}",url, parameters);
        HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            StringEntity params = new StringEntity(parameters, CHARSET);
            HttpPost request = new HttpPost(url);
            //设置请求头
            request.addHeader("Content-type", "application/json");
            request.setEntity(params);
            //执行http请求
            response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            entity = response.getEntity();
            //结果转换
            httpResponseMessage.setHttpCode(Integer.toString(statusCode));
            if (statusCode == HttpStatus.OK.value() && entity != null) {
                httpResponseMessage.setContent(EntityUtils.toString(entity, CHARSET));
            }
        } catch (Exception e) {
            logger.error("sendPostByJson error, details:", e);
            throw new BaseException(10001,e.getMessage());
        }finally{
            try{
                if(entity != null){
                    EntityUtils.consume(entity);
                }
                if(response != null){
                    response.close();
                }
            }catch(Exception e){
                logger.error("sendPostByJson error, details:{}", e);
            }
        }
        return httpResponseMessage;
    }

    /**
     * get +header + map 发送请求
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws BaseException
     */
    public static HttpResponseMessage sendGetRequest(String url, Map<String, Object> headers, Map<String, Object> params) throws BaseException{
        logger.info("sendGetRequest,url={}, parameters={}",url,params);
        try {
            URIBuilder ub = new URIBuilder();
            ub.setPath(url);
            //设置参数
            ub.setParameters(covertParams2NVPS(params));
            HttpGet request = new HttpGet(ub.build());
            logger.info("get url :{}", ub.build());
            if (null == headers){
                //为空的header则置默认值
                request.addHeader("Content-type", "application/json;charset=utf-8");
            }else {
                request = (HttpGet) setHeaders(request, headers);
            }
            return getResult(request);
        } catch (Exception e) {
            logger.error("sendGetRequest fail Exception :{}", e);
            throw new BaseException(10001,e.getMessage());
        }
    }

    /**
     * get + map 发送请求
     * @param url
     * @param params
     * @return
     * @throws BaseException
     */
    public static HttpResponseMessage sendGetRequest(String url, Map<String, Object> params) throws BaseException {
       return sendGetRequest(url, null, params);
    }

    /**
     * get 发送请求
     * @param url
     * @return
     * @throws BaseException
     */
    public static HttpResponseMessage sendGetRequest(String url) throws BaseException{
        return sendGetRequest(url, null);
    }

    public static R doPost(String url, Object arg) {
//        R result = new R();
        Map<String, Object> resultMap = new HashMap<>(16);
        try {
            HttpResponseMessage resp = HttpUtil.sendPostByJson(url, new Gson().toJson(arg));
            logger.info("http doPost request arg:{}, result :{}",arg, resp);
            if ("200".equals(resp.getHttpCode())) {
                resultMap.put("data",resp.getContent());
            } else {
                return R.error(Integer.valueOf(resp.getHttpCode()), resp.getMessage());
            }
        } catch (BaseException e) {
            logger.error("doPost error, details:", e);
            return R.error(e.getCode(), e.getMessage());
        }
        return R.ok(resultMap);
    }

    public static void main(String[] args){
        String url = "https://www.baidu.com";
        Map<String,Object> map =new HashMap<>(16);
        map.put("ea","fs");
        HttpResponseMessage httpResponseMessage = sendGetRequest(url,null, map);
        String result = httpResponseMessage.getContent();
        System.out.println(result);
    }
}
