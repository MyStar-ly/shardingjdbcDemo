package com.star.sharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.star.sharding.entity.*;
import com.star.sharding.mapper.CourseMapper;
import com.star.sharding.mapper.UdictMapper;
import com.star.sharding.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class ShardingjdbcdemoApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;

    /**
     * =============================公共表测试=====================================
     */
    /**
     * 新增
     */
    @Test
    public void addUdictDb() {
        Udict udict = new Udict();
        udict.setUstatus("Y");
        udict.setUvalue("已启用");
        int insert = udictMapper.insert(udict);
    }


    /**
     * 查询
     * @return
     */
    @Test
    public void queryUdict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        wrapper.eq("dictid", 710447462493454337l);
        Udict udict = udictMapper.selectOne(wrapper);
        System.out.println("=================================>" + udict);
    }


    /**
     * 修改
     */
    @Test
    public void updateUdict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        wrapper.eq("dictid", 710447462493454337l);
        Udict udict = udictMapper.selectOne(wrapper);


        udict.setUstatus("N");
        udict.setUvalue("已失效");
        int updateById = udictMapper.update(udict, wrapper);
    }


    /**
     * 删除
     */
    @Test
    public void deleteUdict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        wrapper.eq("dictid", 710447462493454337l);
        int delete = udictMapper.delete(wrapper);
    }




    /**
     * =============================垂直分库测试=====================================
     */
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setUstatus("Y");
        int insert = userMapper.insert(user);
    }


    @Test
    public void queryUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 710440587647516673l);
        User user = userMapper.selectOne(wrapper);
        System.out.println("=================================>" + user);
    }


    /**
     * =============================水平分库测试=====================================
     */
    @Test
    public void addCourseDb() {
        for (int i = 1; i < 20; i++) {
            Random random = new Random();
            long l = random.nextInt(9);
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100l + l);
            course.setCstatus("Normal" + i);
            int insert = courseMapper.insert(course);
        }
    }


    @Test
    public void queryCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 108l);
        wrapper.eq("cid", 710428208486416384l);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println("=================================>" + course);
    }


    /**
     * =============================水平分表测试=====================================
     */

    /**
     * 测试新增数据
     */
    @Test
    void contextLoads() {
        for (int i = 1; i <= 20; i++) {
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100l);
            course.setCstatus("Normal" + i);
            int insert = courseMapper.insert(course);
        }
    }


    /**
     * 测试查询数据
     */
    @Test
    public void testQuery() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 710410077172924416l);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println("========>" + course);
    }

}
