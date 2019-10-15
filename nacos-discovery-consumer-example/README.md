# nacos作为配置中心

* 依赖  
```xml
<dependency>
   <groupId>com.alibaba.cloud</groupId>
   <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```  
* 配置文件配置nacos-config 信息  
详见配置文件  

## nacos 配置自动刷新  
* 在需要自动刷新的配置类, 方法中添加注解 `@RefreshScope` 就能打开动态刷新功能  
```java
@RestController
@RefreshScope // 打开动态刷新功能
public class TestRefreshScopeController {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private Integer age;

    @GetMapping(value = "/test/{param}")
    public String test(@PathVariable("param") String param){
        return userName + "-" + param + "-" + age;
    }

}
```  
>* 动态刷新原理  
Nacos Config Starter默认为所有获取数据成功的Nacos的配置项添加了监听功能, 在监听到服务端配置发生变化时会实时触发`org.springframework.cloud.context.refersh.ContextRefresher`的refresh方法.  
如果需要对Bean进行动态刷新, 请参照Spring和Spring Cloud的规范. 推荐给类添加`@RefreshScope` 或 `@ConfigurationProperties`注解  
>>* 根据上述刷新原理, 可以采用`@ConfigurationProperties`的方式  
```java
@ConfigurationProperties(prefix = "user")
@Component
public class TestProperties {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```  
```java
@RestController
public class TestRefreshPropertiesController {

    @Autowired
    private TestProperties testProperties;

    @GetMapping(value = "/test2/{param}")
    public String test(@PathVariable("param") String param){
        return testProperties.getName() + "-" + param + "-" + testProperties.getAge();
    }

}
```  

## actuator 自定义健康状态指示器  
>* 编写一个指示器, 实现HealthIndicator接口  
>* 指示器的名字 xxxHealthIndicator  
>* 加入容器中  
```java

/**
 * @author xsm
 * @date 2019/10/15 9:07
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // 自定义的检查方法
        // Health.down().build(); // 代表状态不健康
        // Health.up().build(); // 代表状态健康
        return Health.down().withDetail("msg", "服务异常").build();
    }
}

```
