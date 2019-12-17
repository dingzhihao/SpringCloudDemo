package com.hao.service.impl;

import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁实例
     *
     * @param lockKey
     * @return
     */
    public RLock getLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }

    /**
     * 获取锁（阻塞）
     * 如果锁不可用，出于线程调度目的，将禁用当前线程，并且在获得锁之前，该线程将一直处于休眠状态
     *
     * @param lockKey
     */
    public void lock(String lockKey) {
        getLock(lockKey).lock();
    }

    /**
     * 获取时锁（阻塞）
     * 如果锁不可用，出于线程调度目的，将禁用当前线程，并且在获得锁之前，该线程将一直处于休眠状态
     *
     * @param lockKey
     * @param leaseTime 获取锁后最长持有时间，-1无限持有
     * @param unit      参数的时间单位
     */
    public void lock(String lockKey, long leaseTime, TimeUnit unit) {
        getLock(lockKey).lock(leaseTime, unit);
    }

    /**
     * 尝试获取锁（非阻塞）
     * 如果锁可用，则获取锁，并立即返回值true，如果锁不可用，则此方法将立即返回false
     *
     * @param lockKey
     * @return
     */
    public boolean tryLock(String lockKey) {
        return getLock(lockKey).tryLock();
    }

    /**
     * 尝试获取锁（等待时间内阻塞）
     *
     * @param lockKey
     * @param time    等待锁的最长时间
     * @param unit    参数的时间单位
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(String lockKey, long time, TimeUnit unit) throws InterruptedException {
        return getLock(lockKey).tryLock(time, unit);
    }

    /**
     * 尝试获取锁（等待时间内阻塞）
     *
     * @param lockKey
     * @param waitTime  等待锁的最长时间
     * @param leaseTime 获取锁后最长持有时间，-1无限持有
     * @param unit      参数的时间单位
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        return getLock(lockKey).tryLock(waitTime, leaseTime, unit);
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    public void unLock(String lockKey) {
        getLock(lockKey).unlock();
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public void unLock(RLock lock) {
        lock.unlock();
    }

    /**
     * 添加List元素
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean addList(String key, T value) {
        RList<T> rList = redissonClient.getList(key);
        return rList.add(value);
    }

    /**
     * 获取List
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String key) {
        RList<T> rList = redissonClient.getList(key);
        return rList.readAll();
    }

}