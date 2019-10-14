package com.lcq.my.controller;

import com.lcq.my.bean.UserInfo;
import com.lcq.my.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class hello {

    @Autowired
    UserInfoMapper userInfoMapper;

    @ResponseBody
    @RequestMapping("/")
    public String say() {
        return "Hello MY Druid";
    }

    @ResponseBody
    @RequestMapping("/users")
    public List getUserall() {
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        return userInfos;
    }
}
