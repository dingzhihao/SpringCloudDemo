package com.hao;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ConcurrentTest {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentTest.class);

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.custom().build();

        try {
            CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

            for (int i = 0; i < THREAD_COUNT; i++) {
                final int a = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (countDownLatch) {
                            countDownLatch.countDown();
//                            logger.info("----------" + countDownLatch.getCount());
//                            System.out.println("----------" + countDownLatch.getCount());
                        }
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try {
                            HttpGet httpGet = new HttpGet("http://192.168.8.9/service1/user/getUser?id=1");
                            CloseableHttpResponse response = httpClient.execute(httpGet);
                            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                            logger.info(result);
//                            System.out.println(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}