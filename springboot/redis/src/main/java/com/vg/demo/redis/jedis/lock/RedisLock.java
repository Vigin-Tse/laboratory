package com.vg.demo.redis.jedis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @Description
 * @Author xieweij
 * @create 2020/7/9 13:43
 */
@Service
public class RedisLock {

    @Autowired
    private JedisPool jedisPool;

    private String redisLockKey = "rkey2020";

    private ThreadLocal<String> tl = new ThreadLocal<>();


    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 阻塞式加锁
     * 要点：
     * 1. 使用SetNX 保证redis的数据插入和设置缓存失效时间是原子操作
     * 2. value 为随机唯一的标识，解锁时候认证，以防解错锁
     * 3. 必须加上缓存失效时间防止死锁
     *
     * @return 加锁成功返回ture
     */
    public boolean lock() throws InterruptedException {
        boolean islock = false;
        //阻塞式方法获取锁
        while (!islock) {
            System.out.println(Thread.currentThread() + "尝试加锁");
            //加锁
            islock = tryLock();
            if (islock) {
                System.out.println(Thread.currentThread() + "加锁成功");
            }
            //加锁失败，休眠1s，再尝试获取锁
            if (!islock) {
                System.out.println(Thread.currentThread() + "加锁失败");
            }
        }
        return true;
    }

    /**
     * 非阻塞式锁
     *
     * @return
     */
    private boolean tryLock() {
        Jedis jedis = this.getJedis();
        String value = UUID.randomUUID().toString();
        // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
        String result = jedis.set(redisLockKey, value, "NX", "PX", 500);
        if (!StringUtils.isEmpty(result) && "OK".equals(result)) {
            //加锁成功，保存线程副本value值，方便同一线程解锁时认证锁的持有人（value）
            tl.set(value);
            return true;
        }
        return false;
    }


    /***
     * 使用lua脚本解锁
     * GET、判断、del
     * 把这三个操作打包给redis去执行，使用Lua脚本去执行。(保证这三步操作的原子性)
     * @return
     */
    public void unlock() {
        Jedis jedis = this.getJedis();
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        List<String> keys = new ArrayList<>(1);
        keys.add(redisLockKey);

        List<String> argv = new ArrayList<>(1);
        argv.add(tl.get());

        Long result = (Long) jedis.eval(luaScript, keys, argv);
        System.out.println(Thread.currentThread() + "解锁结果：" + result);
    }

    //TODO 持有锁的客户端应该检查锁是否过期，保证锁在业务执行完释放之前不会过期
}
