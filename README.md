Logback Spring Config
---------------------
Extends [`Logback Extensions :: Spring`][1] to allow moving appender XML configuration from `logback.xml` to Spring XML without much change.

```xml
<!-- logback.xml -->
<configuration>

    <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%date %-5level [%thread] %logger{36} %m%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="consoleAppender"/>
    </root>

</configuration>
```

to

```xml
<!-- logback.xml -->
<configuration>

    <appender name="consoleAppender" class="ch.qos.logback.ext.spring.DelegatingLogbackAppender"/>

    <root level="INFO">
        <appender-ref ref="consoleAppender"/>
    </root>

</configuration>
```
```xml
<!-- Spring XML -->
<beans ... xsi:schemaLocation="... http://logback.qos.ch logback-lenient.xsd">

    <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%date %-5level [%thread] %logger{36} %m%n</pattern>
        </encoder>
    </appender>

</beans>
```

[1] https://github.com/qos-ch/logback-extensions/wiki/Spring
