package com.iclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@MapperScan("com.iclass.excel.mybatis.mapper")
@ImportResource(locations = "classpath:spring/*.xml")
@SpringBootApplication
public class ExcelHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelHandlerApplication.class, args);
	}
}
