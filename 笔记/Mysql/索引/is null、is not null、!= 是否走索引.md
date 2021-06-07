![](..\pic\img.png)

### role in nul  |  role is not null

```sql
explain SELECT * FROM sql_lock l WHERE l.role IS NULL; --ref
EXPLAIN FORMAT=json SELECT * FROM sql_lock l WHERE l.role IS NULL; --"query_cost": "0.35"

explain SELECT * FROM sql_lock l WHERE l.role IS not NULL; --all 全表扫描
EXPLAIN format=json SELECT * FROM sql_lock l WHERE l.role IS not NULL; --"query_cost": "1.35"
```



### phone is null | phone is not null

```sql
explain SELECT * FROM sql_lock l WHERE l.phone IS NULL; --all 全表扫描
EXPLAIN FORMAT=json SELECT * FROM sql_lock l WHERE l.phone IS NULL; --"query_cost": "1.35"

explain SELECT * FROM sql_lock l WHERE l.phone IS not NULL; --range
EXPLAIN format=json SELECT * FROM sql_lock l WHERE l.phone IS not NULL; --"query_cost": "0.71"
```



###  role = m | role <> 'm'

```sql
explain SELECT * FROM sql_lock l WHERE l.role = 'm';  --ref
EXPLAIN format=json SELECT * FROM sql_lock l WHERE l.role = 'm'; --  "query_cost": "1.40"

explain SELECT * FROM sql_lock l WHERE l.role <> 'm'; --range
EXPLAIN format=json SELECT * FROM sql_lock l WHERE l.role <> 'm'; -- "query_cost": "1.41"
```



### role = f | role <> 'f'

```sql
explain SELECT * FROM sql_lock l WHERE l.role = 'f'; --ref
EXPLAIN format=json SELECT * FROM sql_lock l WHERE l.role = 'f'; -- "query_cost": "0.35"

explain SELECT * FROM sql_lock l WHERE l.role <> 'f'; --all 全表扫描
EXPLAIN format=json SELECT * FROM sql_lock l WHERE l.role <> 'f'; -- "query_cost": "1.35"
```



