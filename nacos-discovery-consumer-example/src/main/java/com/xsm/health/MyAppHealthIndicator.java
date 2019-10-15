package com.xsm.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

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
