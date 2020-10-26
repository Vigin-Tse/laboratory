#repository方法名查询规则:<br/>
    findBy(关键字)+属性名称(属性名称的首字母大写)+查询条件(首字母大写)

|关键字 | 方法命名 |sql where 字句|
| :-----| :-----| :-----|
|And 	|findByNameAndPwd 	|where name= ? and pwd =?
|Or 	|findByNameOrSex 	|where name= ? or sex=?
|Is,Equal 	|findById, 	|findByIdEquals
|Between 	|findByIdBetween 	|where id between ? and ?
|LessThan 	|findByIdLessThan 	|where id < ?
|LessThanEqual 	|findByIdLessThanEquals 	|where id <= ?
|GreaterThan 	|findByIdGreaterThan 	|where id > ?
|GreaterThanEqual 	|findByIdGreaterThanEquals 	|where id > = ?
|After 	|findByIdAfter 	|where id > ?
|Before 	|findByIdBefore 	|where id < ?
|IsNull 	|findByNameIsNull 	|where name is null
|isNotNull,Not Null 	|findByNameNotNull 	|where name is not
|Like 	|findByNameLike 	|where name like ?
|NotLike 	|findByNameNotLike 	|where name not like ?
|StartingWith 	|findByNameStartingWith 	|where name like '?%'
|EndingWith 	|findByNameEndingWith 	|where name like '%?'
|Containing 	|findByNameContaining 	|where name like '%?%'
|OrderBy 	|findByIdOrderByXDesc 	|where id=? order by x desc
|Not 	|findByNameNot 	|where name <> ?
|In 	|findByIdIn(Collection<?> c) 	|where id in (?)
|NotIn 	|findByIdNotIn(Collection<?> c) 	|where id not in (?)
|True 	|findByAaaTue 	|where aaa = true
|False 	|findByAaaFalse 	|where aaa = false
|IgnoreCase 	|findByNameIgnoreCase 	|where UPPER(name)=UPPER(?)


#StringMatcher 参数
|Matching	|生成的语句	|说明
| :-----| :-----| :-----|
|DEFAULT (case-sensitive)	|firstname = ?0	|默认（大小写敏感）
|DEFAULT (case-insensitive)	|LOWER(firstname) = LOWER(?0)	|默认（忽略大小写）
|EXACT (case-sensitive)	|firstname = ?0	|精确匹配（大小写敏感）
|EXACT (case-insensitive)	|LOWER(firstname) = LOWER(?0)	|精确匹配（忽略大小写）
|STARTING (case-sensitive)	|firstname like ?0 + ‘%’	|前缀匹配（大小写敏感）
|STARTING (case-insensitive)	|LOWER(firstname) like LOWER(?0) + ‘%’	|前缀匹配（忽略大小写）
|ENDING (case-sensitive)	|firstname like ‘%’ + ?0	|后缀匹配（大小写敏感）
|ENDING (case-insensitive)	|LOWER(firstname) like ‘%’ + LOWER(?0)	|后缀匹配（忽略大小写）
|CONTAINING (case-sensitive)	|firstname like ‘%’ + ?0 + ‘%’	|模糊查询（大小写敏感）
|CONTAINING (case-insensitive)	|LOWER(firstname) like ‘%’ + LOWER(?0) + ‘%’	|模糊查询（忽略大小写）