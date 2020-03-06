#### This project will show you how create an external configuration file for logs using **slf4j** and **logback**.

##### First, you should import libraries from *Maven* project :
```
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
```

```
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.2.3</version>
</dependency>
```

##### Then, create your *logback.xml* configuration file where you want :
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <timestamp key="byDay" datePattern="yyyyMMdd"/>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <layout class="ch.qos.logback.classic.PatternLayout">
      <Pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</Pattern>
    </layout>
  </appender>
  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      <level>INFO</level>
    </filter>
    <file>C:\Path\where\will\be\saved\loggers\${byDay}.log</file>
    <append>true</append>
    <layout class="ch.qos.logback.classic.PatternLayout">
      <Pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</Pattern>
    </layout>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
    <appender-ref ref="FILE" />
  </root>
</configuration>
```
This code above will show up **DEBUG level** logs into console; and will also show up **INFO level** logs into a file saved per day in `C:\Path\where\will\be\saved\loggers\ ` folder.

You have to add *VM options* from *Run/Debug Configurations* using **Intellij**.
For this, press `ALT` + `SHIFT` + `F10` and past

`-Dlogback.configurationFile=file:C:\Path\where\you\created\and\saved\logback.xml` into **VM Options** input.
> Of course you have to change example path by real path

##### Now, you can use logs from you project :

```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("We can see me from console output only");
        log.info("We can see me from console output and logback.xml file");
    }
}
```

##### Finally, run java application and enjoy result ! :thumbsup: