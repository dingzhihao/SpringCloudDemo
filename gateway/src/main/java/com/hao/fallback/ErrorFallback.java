package com.hao.fallback;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zuul路由熔断
 */
@Component
public class ErrorFallback implements FallbackProvider {

    private static final Logger logger = LoggerFactory.getLogger(ErrorFallback.class);

    /**
     * 指定要处理的 service
     *
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        logger.error(route);
        if (cause != null && cause.getCause() != null) {
            String reason = cause.getCause().getMessage();
            logger.error(reason);
        }
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ClientHttpResponse response(HttpStatus httpStatus) {
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return httpStatus;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return httpStatus.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return httpStatus.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                JSONObject json = new JSONObject();
                json.put("message", "服务器忙，请稍候重试~~");
                return new ByteArrayInputStream(json.toJSONString().getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }

        };
    }

}