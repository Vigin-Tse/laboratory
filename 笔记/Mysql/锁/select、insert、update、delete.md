

参考文章：https://www.cnblogs.com/xmanblue/archive/2004/01/13/9482636.html

# 1. 行锁

加锁的方式：

1. 对于**UPDATE**、**DELETE**和**INSERT**语句，InnoDB会**自动**给涉及数据集加**排他锁**；
2. 对于普通**SELECT**语句，InnoDB不会加任何锁；当然我们也可以显示的加锁：

- 共享锁：select * from tableName where ... + lock in share more
- 排他锁：select * from tableName where ... + for update 



### MySQL 事务属性

事务是由一组SQL语句组成的逻辑处理单元，事务具有ACID属性。
**原子性**（Atomicity）：事务是一个原子操作单元。在当时原子是不可分割的最小元素，其对数据的修改，要么全部成功，要么全部都不成功。
**一致性**（Consistent）：事务开始到结束的时间段内，数据都必须保持一致状态。
**隔离性**（Isolation）：数据库系统提供一定的隔离机制，保证事务在不受外部并发操作影响的"独立"环境执行。
**持久性**（Durable）：事务完成后，它对于数据的修改是永久性的，即使出现系统故障也能够保持。

### 事务常见问题

**更新丢失**（Lost Update）
原因：当多个事务选择同一行操作，并且都是基于最初选定的值，由于每个事务都不知道其他事务的存在，就会发生更新覆盖的问题。类比github提交冲突。

**脏读**（Dirty Reads）
原因：事务A读取了事务B已经修改但尚未提交的数据。若事务B回滚数据，事务A的数据存在不一致性的问题。

**不可重复读**（Non-Repeatable Reads）
原因：事务A第一次读取最初数据，第二次读取事务B已经提交的修改或删除数据。导致两次读取数据不一致。不符合事务的隔离性。

**幻读**（Phantom Reads）
原因：事务A根据相同条件第二次查询到事务B提交的新增数据，两次数据结果集不一致。不符合事务的隔离性。

幻读和脏读有点类似
脏读是事务B里面修改了数据，
幻读是事务B里面新增了数据。



## 总结

1 InnoDB 支持表锁和行锁，使用索引作为检索条件修改数据时采用行锁，否则采用表锁。
2 InnoDB 自动给修改操作加锁，给查询操作不自动加锁
3 行锁可能因为未使用索引而升级为表锁，所以除了检查索引是否创建的同时，也需要通过explain执行计划查询索引是否被实际使用。
4 行锁相对于表锁来说，优势在于高并发场景下表现更突出，毕竟锁的粒度小。
5 当表的大部分数据需要被修改，或者是多表复杂关联查询时，建议使用表锁优于行锁。
6 为了保证数据的一致完整性，任何一个数据库都存在锁定机制。锁定机制的优劣直接影响到一个数据库的并发处理能力和性能。



# 2. 操作验证

## 2.1 - INSERT

-- session 1 插入记录 插入后id自增号为19

```sql
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (19, 'insert-1', '插入-1', '1', '2021-01-22 15:00:03');
```



--session 2 进行select、insert、update、delete操作

```sql
--===============================delete==============================
---执行失败，session1 未提交 （主键）id= 19的记录处于行锁
DELETE from sql_lock l WHERE l.id = 19; 

---执行失败，session1 未提交 （唯一索引）code = 'insert-1'的记录处于行锁
DELETE from sql_lock l WHERE l.code = 'insert-1'; 

---执行失败，session1 未提交 （普通索引）name = '插入-1'的记录处于行锁
DELETE from sql_lock l WHERE l.name = '插入-1'; 

--执行失败，session1 未提交 删除结果集合中存在 行锁记录 无法执行删除事务
DELETE from sql_lock l WHERE l.age <= 2; 

--执行失败，session1 未提交 删除结果集合中存在 行锁记录 无法执行删除事务
DELETE from sql_lock l WHERE l.create_time >= '2021-01-22 15:00:03'; 

---执行成功
DELETE from sql_lock l WHERE l.id = 22;  

--===============================update==============================
--操作结果同delete


--===============================select==============================
---执行成功（无结果行），查询不受影响
SELECT * FROM sql_lock WHERE id = 19; 


--===============================insert==============================
---执行失败，session1 未提交 （主键）id= 19的记录处于行锁
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (19, 'insert-2', '插入相同id(主键=19)', '1', '2021-01-22 15:00:03');

--执行失败，插入相同唯一索引
INSERT INTO sql_lock (`code`, `name`, `age`, `create_time`) VALUES ('insert-1', '插入-1', '1', '2021-01-22 15:00:03'); 

---执行成功
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (18, 'insert-18', '主键=18', '1', '2021-01-22 15:00:03'); 

---执行成功
INSERT INTO sql_lock (`code`, `name`, `age`, `create_time`) VALUES ('insert-auto', '主键自增', '1', '2021-01-22 15:00:03'); 

---执行成功
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (17, 'insert-17', '主键=17', '1', '2021-01-22 15:00:03'); 

---执行成功
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (20, 'insert-20', '主键=20', '1', '2021-01-22 15:00:03'); 


=======================================================================
-- INSERT INTO sql_lock (`id`, `code`, `name`, role, phone, `age`, `create_time`) VALUES (26, 'insert-06251041', '插入-06251041', 'm', '456', '15', '2021-06-25 10:41:03');
--执行失败
UPDATE sql_lock SET age = 21 WHERE age < 16;

--执行失败
UPDATE sql_lock SET age = 21 WHERE age = 16;

--执行失败
UPDATE sql_lock SET role = 'm' WHERE role = 'm';

--成功
UPDATE sql_lock SET role = 'f' WHERE role = 'f';


-- UPDATE sql_lock SET role = 'f' WHERE role = 'f';
--执行失败
UPDATE sql_lock SET role = 'f' WHERE role = 'f';

--执行失败
explain
UPDATE sql_lock SET role = 'm' WHERE role <> 'f';

--执行失败
UPDATE sql_lock SET role = 'm' WHERE role IS NOT NULL;

--成功
explain
UPDATE sql_lock SET role = 'm' WHERE role = 'm';

--成功
UPDATE sql_lock SET role = 'm' WHERE role IS NULL;
```

**总结：**

当insert的记录未提交时：

- **update\delete：**当按条件（where）查询出来符合更新\删除的结果集中含有未提交的数据（行锁）时，更新\删除操作失败。如果结果集不含行锁数据则能执行成功。
- **select：**无影响
- **insert**：无法插入相同的id或唯一索引的数据。



