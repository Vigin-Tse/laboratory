package com.vg.task.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 普通定时任务：
 *        schedule是默认一单线程（所有schedule在同一线程串行）的情况执行的
 * @author: xieweij
 * @time: 2021/5/28 10:46
 */
//@Component
@Slf4j
public class CommonTask {

    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("我是task-1，我需要执行 10s 钟的时间，我的线程的 id == > {}，时间 == >{}",
                Thread.currentThread().getId(), new Date());
        Thread.sleep(10000);
        log.error("task-1 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }

    @Scheduled(cron = "*/4 * * * * ?")
    public void task2() throws InterruptedException {
        log.error("我是task-2，我需要执行 2s 钟的时间，我的线程的 id == > {}，时间 == >{}",
                Thread.currentThread().getId(), new Date());
        Thread.sleep(2000);
        log.error("task-2 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }
}
