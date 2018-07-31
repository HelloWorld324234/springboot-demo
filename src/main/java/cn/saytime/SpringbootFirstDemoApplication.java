package cn.saytime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@AutoConfigureAfter
@SpringBootApplication
@MapperScan("cn.saytime.mappers")
@EnableSwagger2
@EnableScheduling
@EnableCaching
public class SpringbootFirstDemoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFirstDemoApplication.class, args);
	}
}
