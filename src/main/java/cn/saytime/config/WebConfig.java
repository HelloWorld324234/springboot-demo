package cn.saytime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 11:22
 * @Description:
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"cn.saytime.controller"},useDefaultFilters = true)
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FormatJsonReturnValueHandler JsonReturnHandler(){
        FormatJsonReturnValueHandler formatJsonReturnValueHandler=new FormatJsonReturnValueHandler();
        return formatJsonReturnValueHandler;
    }
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(JsonReturnHandler());
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).
                addResourceLocations(new String[]{"classpath:/META-INF/resources/"});

        /*registry.addResourceHandler(new String[]{"/webjars/**"})
                .addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});*/
    }

}
