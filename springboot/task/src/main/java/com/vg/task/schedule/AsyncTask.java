package com.vg.task.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 1.启动类上添加注解：@EnableAsync
 * 2.任务上添加注解：@Async
 * 结论：任务（task1、task2之间）并行执行。不过task1也发生了并行执行了。由此引发的数据的不一致。
 * @author: xieweij
 * @time: 2021/5/28 11:00
 */
//@Component
@Slf4j
public class AsyncTask {

    @Async
    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("我是task-1，我需要执行 10s 钟的时间，我的线程的 id == > {}，时间 == >{}",
                Thread.currentThread().getId(), new Date());
        Thread.sleep(10000);
        log.error("task-1 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }

    @Async
    @Scheduled(cron = "*/4 * * * * ?")
    public void task2() throws InterruptedException {
        log.error("我是task-2，我需要执行 2s 钟的时间，我的线程的 id == > {}，时间 == >{}",
                Thread.currentThread().getId(), new Date());
        Thread.sleep(2000);
        log.error("task-2 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }
}
