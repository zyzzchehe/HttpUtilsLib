package com.zc.mylibrary.httpcore;


import com.qingguo.ebook.tools.Preconditions;

import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by Tony on 2016/12/14.
 * SSL创建类证书
 */

public class SSLSocketFactory {

    /**
     * 获取AppHttps的策略
     *
     * @return
     */
    public static SSLContext getSSLstrategy(String host) {
        if (Preconditions.isNullOrEmpty(host)) {
            //过滤不需要 SSL验证的 网址
            return getTrustAllSSLSocketFactory();
        }
        return getTrustAllSSLSocketFactory();
    }


    /**
     * 获取信任所有https网站的证书
     *
     * @return
     */
    public static SSLContext getTrustAllSSLSocketFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
        } catch (Exception e) {
        }
        return sslContext;
    }
}
