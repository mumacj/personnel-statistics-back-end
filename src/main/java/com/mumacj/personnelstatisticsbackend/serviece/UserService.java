package com.mumacj.personnelstatisticsbackend.serviece;

import com.mumacj.personnelstatisticsbackend.dao.UserMapper;
import com.mumacj.personnelstatisticsbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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


    public Boolean addUser(User user){
        if (userMapper.insert(user) > 0){
            return true;
        }
        return false;
    }

    public List<HashMap<String,Object>> selectLikeId(String idCard){
        List<HashMap<String,Object>> infos = userMapper.selectLikeId(idCard);
        if (infos != null && infos.size() > 0){
            return infos;
        }else {
            return new ArrayList<>();
        }
    }

    public User getInfoById(String id){
        User infoById = userMapper.getInfoById(id);
        return infoById;
    }

    public Boolean updateInfo(User user){
        if (userMapper.updateByPrimaryKey(user) > 0){
            return true;
        }
        return false;

    }
}
