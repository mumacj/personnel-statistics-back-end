package com.mumacj.personnelstatisticsbackend.controller;

import com.github.pagehelper.util.StringUtil;
import com.mumacj.personnelstatisticsbackend.entity.User;
import com.mumacj.personnelstatisticsbackend.serviece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/getUserInfo")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getAllUsers")
    private List<Map> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("getUserByUsernameOrId")
    private List<Map> getUserByUsernameOrId(@RequestBody  Map<String,String> data){
        String userName = data.get("username");
        String idCard = data.get("idcard");
        if (!StringUtil.isEmpty(userName)) {
            return userService.selectByUsername(userName);
        }else if (!StringUtil.isEmpty(idCard)){
            HashMap<String,Object> result = (HashMap<String, Object>) userService.selectById(idCard);
            ArrayList<Map> resultArr = new ArrayList<>();
            if (result != null) {
                resultArr.add(result);
            }
            return resultArr;
        }else {
            return userService.getAllUsers();
        }
    }

    @RequestMapping("addUser")
    private Boolean addUser(@RequestBody Map<String,Object> data) throws ParseException {
        System.out.println(data);

        String name = (String) data.get("name");
        String idCard = (String) data.get("idCard");
        String date = ((String) data.get("date")).replace("Z", " UTC");

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//转换时区格式
        Date time = format1.parse(date);
        String address = (String) data.get("address");

        User user = new User();
        if (!StringUtil.isEmpty(name)){
            user.setUsername(name);
        }

        if (!StringUtil.isEmpty(idCard)){
            user.setIdCard(idCard);
        }

        if (time != null){
            user.setRegisteDate(time);
        }

        if (!StringUtil.isEmpty(address)){
            user.setAddress(address);
        }
        user.setPassword("000000");
        user.setInTimes(0);

        return userService.addUser(user);

    }
}
