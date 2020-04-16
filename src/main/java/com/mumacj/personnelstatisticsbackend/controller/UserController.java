package com.mumacj.personnelstatisticsbackend.controller;

import com.github.pagehelper.util.StringUtil;
import com.mumacj.personnelstatisticsbackend.entity.User;
import com.mumacj.personnelstatisticsbackend.serviece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/getUserInfo")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getAllUsers")
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("getUserByUsernameOrId")
    private List<Map> getUserByUsernameOrId(@RequestBody  Map<String,String> data){
        System.out.println(data);
        String userName = data.get("username");
        String idCard = data.get("idcard");
        System.out.println(userName);
        if (!StringUtil.isEmpty(userName)) {
            System.out.println("进来");
            return userService.selectByUsername(userName);
        }else if (!StringUtil.isEmpty(idCard)){
            HashMap<String,Object> result = (HashMap<String, Object>) userService.selectById(idCard);
            ArrayList<Map> resultArr = new ArrayList<>();
            if (result != null) {
                resultArr.add(result);
            }
            return resultArr;
        }else {
            return new ArrayList<>();
        }
    }
}
