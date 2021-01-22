# 1. INSERT相关锁

-- session 1 插入记录 插入后id自增号为19
--INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (19, 'insert-1', '插入-1', '1', '2021-01-22 15:00:03');

DELETE from sql_lock l WHERE l.id = 19; ---执行失败，session1 未提交 （主键）id= 19的记录处于行锁
DELETE from sql_lock l WHERE l.code = 'insert-1'; ---执行失败，session1 未提交 （唯一索引）code = 'insert-1'的记录处于行锁
DELETE from sql_lock l WHERE l.name = '插入-1'; ---执行失败，session1 未提交 （普通索引）name = '插入-1'的记录处于行锁
DELETE from sql_lock l WHERE l.age <= 2; --执行失败，session1 未提交 删除结果集合中存在 行锁记录 无法执行删除事务
DELETE from sql_lock l WHERE l.create_time >= '2021-01-22 15:00:03'; --执行失败，session1 未提交 删除结果集合中存在 行锁记录 无法执行删除事务
DELETE from sql_lock l WHERE l.id = 22;  ---执行成功


UPDATE sql_lock SET age = 80 WHERE id = 19; ---执行失败，session1 未提交 （主键）id= 19的记录触发行锁
UPDATE sql_lock SET age = 80 where code = 'insert-1'; ---执行失败，session1 未提交 （唯一索引）code = 'insert-1'的记录处于行锁
UPDATE sql_lock SET age = 80 where name = '插入-1'; ---执行失败，session1 未提交 （普通索引）name = '插入-1'的记录处于行锁
UPDATE sql_lock SET age = 80 where age <= 2; --执行失败，session1 未提交 删除结果集合中存在 行锁记录 无法执行删除事务
UPDATE sql_lock SET age = 80 where create_time >= '2021-01-22 15:00:03'; --执行失败，session1 未提交 删除结果集合中存在 行锁记录 无法执行删除事务
UPDATE sql_lock SET age = 0 WHERE id = 22;  ---执行成功

SELECT * FROM sql_lock WHERE id = 19; ---执行成功，查询不受影响

INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (19, 'insert-2', '插入相同id(主键=19)', '1', '2021-01-22 15:00:03');---执行失败，session1 未提交 （主键）id= 19的记录处于行锁
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (18, 'insert-18', '主键=18', '1', '2021-01-22 15:00:03'); ---执行成功
INSERT INTO sql_lock (`code`, `name`, `age`, `create_time`) VALUES ('insert-auto', '主键自增', '1', '2021-01-22 15:00:03'); ---执行成功
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (17, 'insert-17', '主键=17', '1', '2021-01-22 15:00:03'); ---执行成功
INSERT INTO sql_lock (`id`, `code`, `name`, `age`, `create_time`) VALUES (20, 'insert-20', '主键=20', '1', '2021-01-22 15:00:03'); ---执行成功