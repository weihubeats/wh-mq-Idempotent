package com.mq.idempotent.core.strategy.impl;

import com.mq.idempotent.core.model.IdempotentConfig;
import com.mq.idempotent.core.strategy.AbstractIdempotentStrategy;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @author : wh
 * @date : 2022/3/12 18:05
 * @description:
 */
public class RedisIdempotentStrategy extends AbstractIdempotentStrategy {

    private final RedissonClient redissonClient;

    public RedisIdempotentStrategy(IdempotentConfig idempotentConfig, RedissonClient redissonClient) {
        super(idempotentConfig);
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean lock(String lockName) {
        RLock stockLock = redissonClient.getLock(lockName);
        try {
            return stockLock.tryLock(getTryLockTime(), getTryLockTimeUnit());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(String uniqeKey) {

    }
}