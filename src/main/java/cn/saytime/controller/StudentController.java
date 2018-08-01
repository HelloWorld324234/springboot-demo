package cn.saytime.controller;

import cn.saytime.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: yule
 * @Date: 2018/8/1 0001 14:11
 * @Description:
 */
@Controller
@Api(description = "Student")
public class StudentController {

    @ApiOperation("getInfo")
    @GetMapping("/student")
    public String getInfo(Model model) {
        Student student = new Student();
        student.setAge(10);
        student.setName("Tomcat");
        model.addAttribute("student",student);
        return "student";
    }

}
