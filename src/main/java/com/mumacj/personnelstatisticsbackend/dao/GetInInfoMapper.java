package com.mumacj.personnelstatisticsbackend.dao;

import com.mumacj.personnelstatisticsbackend.entity.GetInInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface GetInInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(GetInInfo record);

    int insertSelective(GetInInfo record);

    GetInInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GetInInfo record);

    int updateByPrimaryKey(GetInInfo record);

    List<GetInInfo> getAllInfos();

    List<GetInInfo> getInfosByTimeOrName(Date start, Date end,String name);

    List<HashMap<String, Object>> getTemps(String idCard);
}