package org.maple.demo01;

import org.apache.commons.pool2.impl.DefaultPooledObjectInfo;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.Set;
import java.util.UUID;

public class PeonPool {

    private final GenericObjectPool<Peon> objectPool;

    public PeonPool() {
        String id = UUID.randomUUID().toString();
        PeonFactory peonFactory = new PeonFactory(id);
        // 池子配置文件
        GenericObjectPoolConfig<Peon> config = new GenericObjectPoolConfig<>();
        // 整个池最大值 5
        config.setMaxTotal(5);
        // 最大空闲 2
        config.setMaxIdle(2);
        // 最小空闲
        config.setMinIdle(0);
        // 最大等待时间，-1表示一直等
        config.setMaxWaitMillis(5 * 1000);
        // 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true
        config.setBlockWhenExhausted(true);
        // 在从对象池获取对象时是否检测对象有效，true是；默认值是false
        config.setTestOnBorrow(false);
        // 在向对象池中归还对象时是否检测对象有效，true是，默认值是false
        config.setTestOnReturn(false);
        // 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。true是，默认值是false
        config.setTestWhileIdle(false);
        // 可发呆的时间,1 min
        config.setMinEvictableIdleTimeMillis(60 * 1000);
        // 发呆过长移除的时候是否 test 一下先
        config.setTestWhileIdle(true);
        // 回收资源线程的执行周期 3s
        config.setTimeBetweenEvictionRunsMillis(3 * 1000);
        config.setNumTestsPerEvictionRun(10);
        objectPool = new GenericObjectPool<>(peonFactory, config);
    }

    public Peon getPeon() {
        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException("苦工出门异常", e);
        }
    }

    public void returnPeon(Peon peon) {
        try {
            objectPool.returnObject(peon);
        } catch (Exception e) {
            throw new RuntimeException("苦工回家异常", e);
        }
    }

    public Set<DefaultPooledObjectInfo> getAllObjectInfo(){
        return objectPool.listAllObjects();
    }

}
