package cn.saytime.controller;

import cn.saytime.utils.EmailUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: yule
 * @Date: 2018/7/30 0030 16:05
 * @Description:
 */
@RestController
@Api(description = "邮件")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @ApiOperation("发送邮件")
    @GetMapping("sendEmail")
    public String sendEmail() throws JsonProcessingException {
        boolean isSend = EmailUtils.sendEmail("这是一封测试邮件", new String[]{"920908621@qq.com"}, null, "<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>", null);
        return "发送邮件:" + isSend;
    }

}
