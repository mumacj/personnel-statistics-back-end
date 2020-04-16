package com.mumacj.personnelstatisticsbackend.controller;

import com.github.pagehelper.util.StringUtil;
import com.mumacj.personnelstatisticsbackend.entity.GetInInfo;
import com.mumacj.personnelstatisticsbackend.serviece.GetInInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/getInInfo")
public class GetInInfoController {

    @Autowired
    private GetInInfoService getInInfoService;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    @RequestMapping("addInfo")
    private Boolean addInfo(@RequestBody Map<String,Object> data) throws ParseException {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(data);

        String name = (String) data.get("name");
        String idCard = (String) data.get("idCard");
        String time1 = ((String) data.get("time")).replace("Z", " UTC");

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//转换时区格式
        Date time = format1 .parse(time1);


        String address = (String) data.get("address");
        Double tempNow = Double.parseDouble((String) data.get("tempNow"));
        String wheLeave = (String) data.get("wheLeave");
        Boolean healthCode = (Boolean) data.get("healthCode");

        GetInInfo getInInfo = new GetInInfo();
        System.out.println(uuid);
        getInInfo.setId(uuid);
        if (!StringUtil.isEmpty(name)){
            getInInfo.setName(name);
        }
        if (!StringUtil.isEmpty(idCard)){
            getInInfo.setIdCard(idCard);
        }
        if (time != null){
            getInInfo.setInTime(time);
        }
        if (!StringUtil.isEmpty(address)){
            getInInfo.setAddress(address);
        }
        if (tempNow != null){
            getInInfo.setTemperature(tempNow);
        }
        if (!StringUtil.isEmpty(wheLeave)){
            if ("是".equals(wheLeave)){
                getInInfo.setWheLeave("1");
            }else {
                getInInfo.setWheLeave("0");
            }
        }
        if (healthCode != null){
            getInInfo.setHealthCode(healthCode ? "1" : "0");
        }
        getInInfo.setRegistTime(new Date());
        getInInfo.setRegistePeople("aaa");

        return getInInfoService.addInfo(getInInfo);

    }

    @RequestMapping("getInfos")
    private List<HashMap<String, Object>> getInfos(){
        List<HashMap<String, Object>> getInInfos = new ArrayList<>();
        getInInfos = getInInfoService.getAllInfos();
        if (getInInfos != null && getInInfos.size() > 0){
            return getInInfos;
        }
        return null;
    }

    @RequestMapping("getInfosByTimeOrName")
    private List<HashMap<String,Object>> getInfosByTimeOrName(@RequestBody Map<String,Object> data) throws ParseException {
        System.out.println(data);
        String datesString = String.valueOf(data.get("date"));
        String name = (String) data.get("name");
        List<HashMap<String, Object>> resultInfos = new ArrayList<>();
        ArrayList<Date> dates = new ArrayList<>();
        if (datesString != null && !StringUtil.isEmpty(datesString)){
            datesString = datesString.replace("[","").replace("]","");
            dates = new ArrayList(Arrays.asList(datesString.split(",")));
        }

        if ((dates != null && dates.size() == 2) || !StringUtil.isEmpty(name)){
            resultInfos = getInInfoService.getInfosByTimeOrName(dates, name);
        }

        if ( resultInfos != null && resultInfos.size() > 0) {
            return resultInfos;
        }
        return new ArrayList<>();
    }

    @RequestMapping("updateInfo")
    private Boolean updateInfoById(@RequestBody Map<String,Object> data) throws ParseException {
        System.out.println(data);
        GetInInfo getInInfo = new GetInInfo();
        String id = (String) data.get("id");
        String name = (String) data.get("name");
        String address = (String) data.get("address");
        String idCard = (String) data.get("idCard");
        Date inTime =sdf.parse(String.valueOf(data.get("dateTime")));
        Double tempNow = (Double) data.get("tempNow");
        String wheLeave = (String) data.get("wheLeave");
        Boolean healthCode = (Boolean) data.get("healthCode");

         getInInfo.setId(id);
         getInInfo.setName(name);
         getInInfo.setIdCard(idCard);
         getInInfo.setAddress(address);
         getInInfo.setInTime(inTime);
         getInInfo.setTemperature(tempNow);
         getInInfo.setWheLeave("否".equals(wheLeave)?"0":"1");
         getInInfo.setHealthCode(healthCode?"1":"0");

         if (getInInfoService.updateInfo(getInInfo)) {
             return true;
         }
         return false;

    }

    @RequestMapping("deleteInfo/{id}")
    private Boolean deleteInfoById(@PathVariable String id) {
        if (id != null && !StringUtil.isEmpty(id)) {
            return getInInfoService.deleteInfo(id);
        }
        return false;
    }

}
