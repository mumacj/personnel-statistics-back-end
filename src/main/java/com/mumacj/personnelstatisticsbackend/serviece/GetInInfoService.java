package com.mumacj.personnelstatisticsbackend.serviece;

import com.mumacj.personnelstatisticsbackend.dao.GetInInfoMapper;
import com.mumacj.personnelstatisticsbackend.entity.GetInInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        maps = formatInfos(getInInfos);
        if (maps != null && maps.size() > 0){
            return maps;
        }
        return null;
    }

    public List<HashMap<String, Object>> getInfosByTimeOrName(ArrayList<Date> dates,String name) throws ParseException {
        Date start = null;
        Date end = null;
        if (dates != null && dates.size() == 2) {
            String startString = "";
            String endString = "";

            startString = String.valueOf(dates.get(0)).replace("Z", " UTC");
            endString = String.valueOf(dates.get(1)).replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            start = format.parse(startString);
            end = format.parse(endString);
            System.out.println(start);
            System.out.println(end);
        }

        List<GetInInfo> infos =  getInInfoMapper.getInfosByTimeOrName(start, end,name);

        System.out.println(infos);
        List<HashMap<String,Object>> maps = new ArrayList<>();
        maps = formatInfos(infos);
        if (maps != null && maps.size() > 0){
            return maps;
        }
        return null;
    }

    public Boolean updateInfo(GetInInfo getInInfo){
        if (getInInfoMapper.updateByPrimaryKey(getInInfo) > 0){
            return true;
        }
        return false;

    }

    public Boolean deleteInfo(String id){
        if (getInInfoMapper.deleteByPrimaryKey(id) > 0){
            return true;
        }
        return false;
    }

    private List<HashMap<String, Object>> formatInfos(List<GetInInfo> getInInfos){
        List<HashMap<String,Object>> maps = new ArrayList<>();
        if (getInInfos != null && getInInfos.size() > 0){
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
                infoMap.put("id",getInInfo.getId());
                infoMap.put("readonly",true);
                infoMap.put("dateTime",sdf.format(getInInfo.getInTime()));
                infoMap.put("name",getInInfo.getName());
                infoMap.put("address",getInInfo.getAddress());
                infoMap.put("idCard",getInInfo.getIdCard());
                infoMap.put("tempNow",getInInfo.getTemperature());
                infoMap.put("wheLeave","0".equals(getInInfo.getWheLeave())?"否":"是");
                infoMap.put("healthCode","0".equals(getInInfo.getHealthCode())?false:true);

                maps.add(infoMap);
            }
            return maps;
        }
        return null;
    }

    public List<HashMap<String,Object>> getTemps(String idCard){
        return getInInfoMapper.getTemps(idCard);
    }

    public HashMap<String, Object> getDailyTempInfo(){
        List<HashMap<String, Object>> infosByDate = getInInfoMapper.getInfosByDate();
        System.out.println(infosByDate);
        int high = 0;
        int health = 0;
        int low = 0;
        for (HashMap<String, Object> info : infosByDate){
            Double temp = (Double) info.get("temperature");
            if (temp >= 37.3){
                high ++;
            }else if (temp < 37.3 && temp >= 36.0){
                health ++;
            }else {
                low ++;
            }
        }
        HashMap<String,Object> numMap = new HashMap<>();
        numMap.put("high",high);
        numMap.put("health",health);
        numMap.put("low",low);

        HashMap<String,Object> returnMap = new HashMap<>();
        returnMap.put("pieData",numMap);
        returnMap.put("tempData",infosByDate);
        return returnMap;
    }
}
