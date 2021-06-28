## 事务

```sql
-- 查看当前所有事务
select * from information_schema.innodb_trx;
-- 查看正在锁的事务，8.0.13版本，由performance_schema.data_locks表所代替
select * from information_schema.innodb_locks;
-- 查看等待锁的事务，8.0.13版本，由performance_schema.data_lock_waits表代替
select * from information_schema.innodb_lock_waits;
```



## 锁

```sql
-- 查看当前有那些表是打开的
show open tables;
-- In_use：有多少线程正在使用某张表
show OPEN TABLES where In_use > 0;

-- 解锁
-- 显示连接列表（查询 ID）
show full processlist;
-- 结束指定（ID）连接
kill id;

-- 解锁
UNLOCK TABLES;

show status like '%lock%';

-- 加锁
LOCK TABLES tbl_name READ;
LOCK TABLES tbl_name WRITE;
```



## 线程

```sql
-- 查看当前连接
-- Threads_connected：打开的连接数
-- Threads_running：激活的连接数(并发数，一般远低于connected)
-- Threads_created：表示创建过的线程数
show status like 'Threads%';

-- 如果 Threads_created 值过大的话，表明 MySQL 服务器一直在创建线程，可以适当增加 thread_cache_size 值
show variables like 'thread_cache_size';
```



## 连接

```sql
-- 查询最大连接数
show variables like '%max_connections%';
-- 设置最大连接数
set global max_connections=1000;
-- 在/etc/my.cnf里面设置数据库的最大连接数
-- [mysqld]
-- max_connections = 1000
```



## 其它查询

```sql
-- 显示连接状态，可以把 connect 换成其它参数
show STATUS LIKE '%connect%';

-- Aborted_clients：由于客户没有正确关闭连接已经死掉，已经放弃的连接数量。
-- Aborted_connects：尝试已经失败的 MySQL 服务器的连接的次数。
-- Connections：试图连接 MySQL 服务器的次数。
-- Created_tmp_tables：当执行语句时，已经被创造了的隐含临时表的数量。
-- Delayed_insert_threads：正在使用的延迟插入处理器线程的数量。
-- Delayed_writes：用 INSERT：DELAYED 写入的行数。
-- Delayed_errors：用 INSERT：DELAYED 写入的发生某些错误(可能重复键值)的行数。
-- Flush_commands：执行 FLUSH 命令的次数。
-- Handler_delete：请求从一张表中删除行的次数。
-- Handler_read_first：请求读入表中第一行的次数。
-- Handler_read_key：请求数字基于键读行。
-- Handler_read_next：请求读入基于一个键的一行的次数。
-- Handler_read_rnd：请求读入基于一个固定位置的一行的次数。
-- Handler_update：请求更新表中一行的次数。
-- Handler_write：请求向表中插入一行的次数。
-- Key_blocks_used：用于关键字缓存的块的数量。
-- Key_read_requests：请求从缓存读入一个键值的次数。
-- Key_reads：从磁盘物理读入一个键值的次数。
-- Key_write_requests：请求将一个关键字块写入缓存次数。
-- Key_writes：将一个键值块物理写入磁盘的次数。
-- Max_used_connections：同时使用的连接的最大数目。
-- Not_flushed_key_blocks：在键缓存中已经改变但是还没被清空到磁盘上的键块。
-- Not_flushed_delayed_rows：在 INSERT：DELAY 队列中等待写入的行的数量。
-- Open_tables：打开表的数量。
-- Open_files：打开文件的数量。
-- Open_streams：打开流的数量(主要用于日志记载）
-- Opened_tables：已经打开的表的数量。
-- Questions：发往服务器的查询的数量。
-- Slow_queries：要花超过 long_query_time 时间的查询数量。
-- Threads_connected：当前打开的连接的数量。
-- Threads_running：不在睡眠的线程数量。
-- Uptime：服务器工作了多长时间，单位秒。
```

