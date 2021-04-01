package org.maple.demo01;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.impl.DefaultPooledObjectInfo;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Client {

    public static void main(String[] args) {
        PeonPool peonPool = new PeonPool();
        Peon peon1 = peonPool.getPeon();
        Peon peon2 = peonPool.getPeon();
        Peon peon3 = peonPool.getPeon();
        Peon peon4 = peonPool.getPeon();
        Peon peon5 = peonPool.getPeon();
        log.debug("苦工老家只有 5 个苦工，现在 5 个苦工都出去干活了");
        try {
            Peon peon6 = peonPool.getPeon();
        } catch (Exception ex) {
            log.debug("等待空闲的苦工超时，所有苦工都出门干活了，所以获取空闲苦工失败");
        }
        peonPool.returnPeon(peon5);
        log.debug("苦工 5 号干完，回家了");

        Peon peon = peonPool.getPeon();
        log.debug("苦工 5 号又出门工作了");

        // 获取苦工信息
        log.debug("获取所有苦工的详情");
        Set<DefaultPooledObjectInfo> set = peonPool.getAllObjectInfo();
        for (DefaultPooledObjectInfo defaultPooledObjectInfo : set) {
            log.debug("该苦工的信息：{}", defaultPooledObjectInfo.getPooledObjectToString());
            log.debug("该苦工的生辰：{}", defaultPooledObjectInfo.getCreateTimeFormatted());
            log.debug("该苦工出门干活的次数：{}", defaultPooledObjectInfo.getBorrowedCount());
            log.debug("该苦工出门干活的时间：{}", defaultPooledObjectInfo.getLastBorrowTime());
            // 如果还没归还，借出时间和归还时间一致
            log.debug("该苦工上次回家的时间：{}", defaultPooledObjectInfo.getLastReturnTime());
            log.debug("=========================================================");
        }

        try {
            TimeUnit.SECONDS.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
