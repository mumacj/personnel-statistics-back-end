package com.mumacj.personnelstatisticsbackend.serviece;

import com.mumacj.personnelstatisticsbackend.dao.UserMapper;
import com.mumacj.personnelstatisticsbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> selectById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<Map> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public List<Map> selectByUsername(String userName){
        return userMapper.selectByUsername(userName);
    }
}
