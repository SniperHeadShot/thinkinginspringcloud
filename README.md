[TOC]

# 1. Ribbon

## 1.1 指定单个服务的路由规则

### 1.1.1 使用

```yaml
SERVICE-PROVIDER:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.WeightedResponseTimeRule
```

### 1.1.2 分析

核心配置类: org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration

```java
@Bean
@ConditionalOnMissingBean
public IRule ribbonRule(IClientConfig config) {
    if (this.propertiesFactory.isSet(IRule.class, name)) {
        return this.propertiesFactory.get(IRule.class, config, name);
    }
    ZoneAvoidanceRule rule = new ZoneAvoidanceRule();
    rule.initWithNiwsConfig(config);
    return rule;
}
```

项目配置文件工厂类: org.springframework.cloud.netflix.ribbon.PropertiesFactory

```java
public boolean isSet(Class clazz, String name) {
    return StringUtils.hasText(getClassName(clazz, name));
}
```

