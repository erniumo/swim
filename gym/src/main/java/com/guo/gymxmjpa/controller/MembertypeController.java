package com.guo.gymxmjpa.controller;


import com.guo.gymxmjpa.service.MembertypeDaoImpl;
import com.guo.gymxmjpa.entity.Membertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/ktype")
public class MembertypeController {

    @Autowired
    private MembertypeDaoImpl membertypeDaoImpl;

    @RequestMapping("/jin5")
    public String jin5(){

        return "WEB-INF/jsp/Membertype";
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<Membertype> query(){

        return membertypeDaoImpl.cha();
    }

    @RequestMapping("/queryq")
    @ResponseBody
    public Map<String,Object> query(String typeName, int pageSize, int pageNumber){
        Map<String,Object>  map1=new HashMap<String,Object>();
        map1.put("typeName",typeName);
        map1.put("qi",(pageNumber-1)*pageSize);
        map1.put("shi",pageSize);
        return membertypeDaoImpl.query(map1);
    }


    @RequestMapping("/query2")
    @ResponseBody
    public Membertype query2(int xztype){

        return membertypeDaoImpl.cha2(xztype);
    }
}
