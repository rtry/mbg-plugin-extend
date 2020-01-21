## mbg-plugin
> 基于mybatis-generator 的自定义Maven插件,只支持Mysql


### TODO 
* Model 类支持lombok 
* Mapper 类支持生成自定义注解
* 去掉 SelectOptionMapper 中关于selectOptionToMap
* 优化 InsertBatchMapper 批量插入时，bean 不一致的情况（有的 bean 属性多一点，有的少有点）
* 优化 update 必须更新为空的方法
* 无主键的情况
* BUG 主键不叫id的情况
* 生成的其他类 自动生成的注解问题



### version: 0.0.2
* 新增超类 InsertBatchMapper，可勾选，表示批量插入
* 新增超类SelectOptionMapper，可勾选，表示按需查询
* 新增超类InsertIfAbsentMapper，可勾选，表示不存在则插入
* 去掉原来配置文件，改用自定义json 配置文件，可项目内共享
* 新增可视化配置界面，期望一键生成
* 可设置部分 Mysql 字段到 Java 字段的类型映射
* 可设置是否驼峰

### version: 0.0.1 
* 数据库字段注解，自动生成到对应的Model
* Mapper.java 类与方法注解
* Mapper.xml 文件覆盖操作
* 新增一个超类Mapper:BaseMapper.java，将基本操作方法提出到超类
* Mapper.xml->自定义扩展，Mapper.java->自定义扩展 保证自定义与生成数互不干扰

