规则:<br/>
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