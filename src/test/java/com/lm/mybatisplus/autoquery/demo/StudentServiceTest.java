package com.lm.mybatisplus.autoquery.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lm.mybatisplus.autoquery.MybatisplusAutoqueryApplicationTests;
import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.demo.service.StudentService;
import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 示例：
 * 该种情况是在Entity对应的实体中添加查询的外键表字段（如不想使用该种做法）
 * 支持的功能为：
 * 1.自动多表关联查询
 * 2.可指定主表外键名以及外键表连接字段
 * 3.设置连表类型
 * 4.可指定映射多个外键表字段
 * @author LM
 * @date 2019/3/12 15:52
 */
public class StudentServiceTest extends MybatisplusAutoqueryApplicationTests {

    @Resource
    private StudentService studentService;

    /**
     * 测试不带有分页的查询
     */
    @Test
    public void testBase() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_age", 20);
        queryWrapper.eq("class_id", 1);
        queryWrapper.eq("rel0.id", 1);

        List<StudentVo> students = studentService.autoQuery(queryWrapper);
        System.out.println(students);
    }

    /**
     * 测试带有分页的查询
     */
    @Test
    public void testWithPage() {

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_age", 20);
        queryWrapper.eq("class_id", 1);

        Page<Student> studentPage = new Page<>(1, 1);


        List<StudentVo> students = studentService.autoQuery(studentPage, queryWrapper);
        System.out.println(students);

    }

}