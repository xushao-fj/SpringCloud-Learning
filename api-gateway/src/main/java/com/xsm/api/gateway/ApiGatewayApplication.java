package com.xsm.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    /**
     * myRoutes方法RouteLocatorBuilder可以很容易地用于创建路由。除了创建路由之外，
     * RouteLocatorBuilder还允许你在路由中添加各种 predicates(断言) 和 filters，以便根据特定条件更改请求和响应。
     * 上面创建的route可以让请求“/get”请求都转发到“http://httpbin.org/get”。
     * 在route配置上，我们添加了一个filter，该filter会将请求添加一个header,key为hello，value为world
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://httpbin.org:80"))
                .build();
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("http://httpbin.org:80"))
//                .route(p -> p
//                        .host("*.hystrix.com")
//                        .filters(f -> f.hystrix(config -> config
//                                .setName("mycmd")
//                                .setFallbackUri("forward:/fallback")))
//                        .uri("http://httpbin.org:80"))
//                .build();
//    }

    @RestController
    static class TestController{
        @RequestMapping("/fallback")
        public String fallback() {
            return "fallback";
        }
    }
}
