package com.star.sharding;

import com.star.sharding.entity.Course;
import com.star.sharding.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingjdbcdemoApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void contextLoads() {
        Course course = new Course();
        course.setCname("java");
        course.setUserId(100l);
        course.setCstatus("Navite");
        int insert = courseMapper.insert(course);
    }

}
