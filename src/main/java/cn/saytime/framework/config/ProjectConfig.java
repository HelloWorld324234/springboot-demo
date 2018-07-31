package cn.saytime.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 10:48
 * @Description:
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(
        prefix = "project"
)
public class ProjectConfig {

    @Value("${project.debug:false}")
    private Boolean debug;
    @Value("${project.dateFormatPattern:yyyy-MM-dd HH:mm:ss}")
    private String dateFormatPattern;

    public ProjectConfig() {
    }

    public Boolean getDebug() {
        return this.debug == null?Boolean.valueOf(false):this.debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public String getDateFormatPattern() {
        return this.dateFormatPattern == null?"yyyy-MM-dd HH:mm:ss":this.dateFormatPattern;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

}
