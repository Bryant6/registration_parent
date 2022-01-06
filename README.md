# 医院网上预约挂号系统
- 项目架构：SpringBoot + Maven
- SpringCloud分布式微服务
- 接口测试：Swagger（http://localhost:port/swagger-ui.html）
- 数据库：MySQL、Redis。MongoDB
- gitee版本管理（https://gitee.com/wy666666/registration_parent.git）
- 前端：ECMAScript6（ES6）、Vue、axios、element-ui、node.js、npm、webpack
- 数据字典: EasyExcel

模拟医院系统接口中使用MongoDB数据库。



## 后台系统医院管理

service-hosp调用service-cmn远程调用，使用注册中心与服务调用

**注册中心**

- Eureka
- Zookeeper
- Consul
- Nacos

使用Nacos，Nacos = Spring Cloud Eureka + Spring Cloud Config
nacos(server:1.1.4,springboot:2.2.2;)
@EnableDiscoveryClient
nacos:8848 | service-hosp:8201 | service-cmn:8202



**服务网关**

Spring Cloud Gateway 替代Nginx

![image-20211118150907290](C:\Users\Bryant\AppData\Roaming\Typora\typora-user-images\image-20211118150907290.png)




## service
- service_hosp: 医院设置管理

  | 请求方式 | 路径                                                      | 功能                 |
  | -------- | --------------------------------------------------------- | -------------------- |
  | DELETE   | /admin/hosp/hospitalSet/batchRemove                       | 批量删除医院设置     |
  | DELETE   | /admin/hosp/hospitalSet/delete/{id}                       | 逻辑删除医院设置信息 |
  | GET      | /admin/hosp/hospitalSet/findAll                           | 获取所有医院信息     |
  | POST     | /admin/hosp/hospitalSet/findPageHospSet/{current}/{limit} | 条件查询带分页       |
  | GET      | /admin/hosp/hospitalSet/getHospitalSet/{id}               | 根据id获取医院设置   |
  | PUT      | /admin/hosp/hospitalSet/lockHospitalSet/{id}/{status}     | 锁定解锁             |
  | POST     | /admin/hosp/hospitalSet/saveHospitalSet                   | 添加医院设置         |
  | PUT      | /admin/hosp/hospitalSet/sendKey/{id}                      | 发送签名秘钥         |
  | POST     | /admin/hosp/hospitalSet/updateHospitalSet                 | 修改医院设置         |


- service_cmn: 数据字典服务

  | 请求方式 | 路径                               | 功能                 |
  | -------- | ---------------------------------- | -------------------- |
  | GET      | /admin/cmn/dict/exportData         | 导出数据字典         |
  | GET      | /admin/cmn/dict/findChildData/{id} | 根据id查询子数据列表 |
  | POST     | /admin/cmn/dict/impoerData         | 导入数据字典         |

  

Redis:service redisd start
