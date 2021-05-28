package com.vg.task.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 不同任务之间并行，相同任务串行
 * 配置类集成 SchedulingConfigurer，实现 configureTasks设置
 * @author: xieweij
 * @time: 2021/5/28 11:18
 */
@Component
@Slf4j
public class ThreadTask {

    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("ThreadTask-1，我需要执行 10s 钟的时间，我的线程的 id == > {}，时间 == >{}",
                Thread.currentThread().getId(), new Date());
        Thread.sleep(10000);
        log.error("ThreadTask-1 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }

    @Scheduled(cron = "*/4 * * * * ?")
    public void task2() throws InterruptedException {
        log.error("ThreadTask-2，我需要执行 2s 钟的时间，我的线程的 id == > {}，时间 == >{}",
                Thread.currentThread().getId(), new Date());
        Thread.sleep(2000);
        log.error("ThreadTask-2 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }
}
