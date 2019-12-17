package com.hao;

import com.hao.controller.UserController;
import com.hao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrentTest {

    @Autowired
    private UserController userController;

    @Test
    public void test() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(9);

            for (int i = 0; i < 10; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (countDownLatch) {
                            countDownLatch.countDown();
                            System.out.println("----------" + countDownLatch.getCount());
                        }
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        User user = userController.getUser(1);
                        System.out.println(user.toString());
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}