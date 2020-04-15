package com.mumacj.personnelstatisticsbackend.serviece;

import com.mumacj.personnelstatisticsbackend.dao.GetInInfoMapper;
import com.mumacj.personnelstatisticsbackend.entity.GetInInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetInInfoService {
    @Autowired
    private GetInInfoMapper getInInfoMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Boolean addInfo(GetInInfo getInInfo){
         if (getInInfoMapper.insert(getInInfo) > 0){
             return true;
         }
         return false;
    }

    public List<HashMap<String,Object>> getAllInfos(){
        List<GetInInfo> getInInfos =  getInInfoMapper.getAllInfos();
        List<HashMap<String,Object>> maps = new ArrayList<>();
        for (GetInInfo getInInfo : getInInfos){
            HashMap<String,Object> infoMap = new HashMap<>();
//          readonly: true,
//          dateTime: "",
//          time: "8:20:30",
//          name: "王小虎",
//          address: "上海市普陀区金沙江路 1516 弄",
//          idCard: "339005123456789123",
//          tempNow: "37.5",
//          wheLeave: "否",
//          healthCode: true
            infoMap.put("readonly",true);
            infoMap.put("dateTime","");
            infoMap.put("time",sdf.format(getInInfo.getInTime()));
            infoMap.put("name",getInInfo.getName());
            infoMap.put("address",getInInfo.getAddress());
            infoMap.put("idCard",getInInfo.getIdCard());
            infoMap.put("tempNow",getInInfo.getTemperature());
            infoMap.put("wheLeave","0".equals(getInInfo.getWheLeave())?"否":"是");
            infoMap.put("healthCode","0".equals(getInInfo.getHealthCode())?false:true);

            maps.add(infoMap);
        }
        if (maps != null && maps.size() > 0){
            System.out.println(maps);
            return maps;
        }
        return null;
    }
}
