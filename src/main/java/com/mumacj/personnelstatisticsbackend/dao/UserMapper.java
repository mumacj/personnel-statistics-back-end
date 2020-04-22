package com.mumacj.personnelstatisticsbackend.dao;

import com.mumacj.personnelstatisticsbackend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String idCard);

    int insert(User record);

    int insertSelective(User record);

    Map<String,Object> selectByPrimaryKey(String idCard);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<Map> getAllUsers();

    List<Map> selectByUsername(String userName);

    List<HashMap<String,Object>> selectLikeId(String idCard);

    User getInfoById(String id);
}