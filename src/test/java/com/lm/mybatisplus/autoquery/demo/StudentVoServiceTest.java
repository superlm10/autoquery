package com.lm.mybatisplus.autoquery.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lm.mybatisplus.autoquery.MybatisplusAutoqueryApplicationTests;
import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.demo.mapper.StudentVoMapper;
import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 示例：
 * 如果不想在Entity写与表结构无关的字段, 实际场景：vo类返回数据给前端页面
 * 第一步: 创建StudentVo类 {@link StudentVo}, 注意：这时Entity中本不应包含与表结构无关的数据，为了其他测试演示, 保留 {@link Student}
 * 第二步: 创建StudentVoMapper类 {@link StudentVoMapper}
 * 第三步: 在需要映射外键表字段时，注入StudentVoMapper, 调用相应的autoQuery方法即可
 * @author LM
 * @date 2019/3/13 11:55
 */
public class StudentVoServiceTest extends MybatisplusAutoqueryApplicationTests {

    @Resource
    StudentVoMapper studentVoMapper;

    @Test
    public void testVoBase() {

        QueryWrapper<StudentVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_age", 20);
        queryWrapper.eq("class_id", 1);

        List<StudentVo> studentVos = studentVoMapper.autoQuery(queryWrapper);
        System.out.println(studentVos);

    }


}
