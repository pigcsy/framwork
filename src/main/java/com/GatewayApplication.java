package com;

import com.core.base.DefaultSpringbootInitializer;
import com.core.exception.GatewayExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * SpringBoot方式启动类
 *
 * @author csy
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
@Slf4j
@MapperScan(value = {"com.mapper"})
@EnableAsync
@Import(GatewayExceptionHandler.class)
public class GatewayApplication extends DefaultSpringbootInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("Application is success!");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GatewayApplication.class);
    }

}
