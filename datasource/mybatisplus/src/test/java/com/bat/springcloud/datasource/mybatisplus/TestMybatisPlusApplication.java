package com.bat.springcloud.datasource.mybatisplus;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.datasource.mybatisplus.entity.User;
import com.bat.springcloud.datasource.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * MybatisPlus Test
 *
 * @author ZhengYu
 * @version 1.0 2020/6/7 14:59
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatisPlusApplication {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(JSONObject.toJSONString(userList));
    }
}
