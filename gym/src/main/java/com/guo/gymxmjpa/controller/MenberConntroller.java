package com.guo.gymxmjpa.controller;



import com.guo.gymxmjpa.dao.MenberDao;
import com.guo.gymxmjpa.dao.chongzhiDao;
import com.guo.gymxmjpa.service.MenberDaoImpl;
import com.guo.gymxmjpa.entity.Chongzhi;
import com.guo.gymxmjpa.entity.Member;
import com.guo.gymxmjpa.entity.Membertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 会员管理Controller控制层
 * @Author: LiuJian
 * @Date: 2020/4/6
 */
@Controller
@RequestMapping("/menber")
public class MenberConntroller {

    @Autowired
    private  chongzhiDao chongzhiDao;

    @Autowired
    private MenberDao menberDao;
    @Autowired
    private MenberDaoImpl menberDaoiImpl;

    @RequestMapping("/jin2")
    public String jin2(){
        return "WEB-INF/jsp/HYMemberDaoQi";
    }

    @RequestMapping("/jin3")
    public String jin3(){
        return "WEB-INF/jsp/HYMemberChongZhi";
    }

    @RequestMapping("/jin11")
    public String jin11(){
        return "WEB-INF/jsp/HYMemberyeChongZhi";
    }


    @RequestMapping("/jin")
    public String jin(){

        return "WEB-INF/jsp/HYMember";
    }

    @RequestMapping("/userinfo")
    public String userinfo(){

        return "WEB-INF/jsp/HYMember1";
    }

    @RequestMapping("/jin4")
    public String jin4(){

        return "WEB-INF/jsp/privatecoachinfo";
    }

    @RequestMapping("/query")
    @ResponseBody
    public Map<String,Object> query(int ktype,String hyname, int pageSize, int pageNumber){
        Map<String,Object>  map1=new HashMap<String,Object>();
        map1.put("hyname",hyname);
        map1.put("ktype",ktype);
        map1.put("qi",(pageNumber-1)*pageSize);
        map1.put("shi",pageSize);
        return menberDaoiImpl.query(map1);
    }

    @RequestMapping("/userquery")
    @ResponseBody
    public Map<String,Object> userquery(int ktype,String hyname, int pageSize, int pageNumber,HttpSession httpSession){
        Member m=(Member)httpSession.getAttribute("user");
        Map<String,Object>  map1=new HashMap<String,Object>();
        map1.put("hyname",hyname);
        map1.put("ktype",ktype);
        map1.put("qi",(pageNumber-1)*pageSize);
        map1.put("shi",pageSize);
        return menberDaoiImpl.userquery(map1,m.getMemberId());
    }

    @RequestMapping("/query2")
    @ResponseBody
    public Map<String,Object> query2(int ktype,String hyname, int pageSize, int pageNumber){
        Map<String,Object>  map1=new HashMap<String,Object>();
        map1.put("hyname",hyname);
        map1.put("ktype",ktype);
        map1.put("qi",(pageNumber-1)*pageSize);
        map1.put("shi",pageSize);
        return menberDaoiImpl.query2(map1);
    }

    @RequestMapping("/cha")
    @ResponseBody
    public Member query2(int id){

        return menberDaoiImpl.cha(id);
    }

    @RequestMapping("/cha1")
    public Member show(HttpServletRequest request){
        HttpSession session=request.getSession();
        Member m=(Member)session.getAttribute("user");
        return menberDaoiImpl.cha(Integer.parseInt(String.valueOf(m.getMemberId())));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> del(int memid){

        menberDaoiImpl.del(memid);
        return query(0,"",5,1);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(Member member){
        menberDaoiImpl.insert(member);
        return query(0,"",5,1);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Member member){
        menberDaoiImpl.update(member);
        return query(0,"",5,1);
    }

    @RequestMapping("/userupdate")
    @ResponseBody
    public Map<String,Object> userupdate(Member member,HttpSession httpSession){
        menberDaoiImpl.update(member);
        return userquery(0,"",5,1,httpSession);
    }


    @RequestMapping("/ins")
    @ResponseBody
    public Map<String, Object> insert(Chongzhi chongzhi, String daoqi){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.sql.Timestamp dat=java.sql.Timestamp.valueOf(df.format(new Date()));

        chongzhi.setDate(dat);
        chongzhi.setCzStatic(2L);
        chongzhiDao.save(chongzhi);
        Membertype membertype=new Membertype();
        membertype.setTypeId(chongzhi.getMembertype().getTypeId());

        Member member=menberDao.findById(chongzhi.getMember().getMemberId()).get();
        member.setMemberId(chongzhi.getMember().getMemberId());
        member.setMemberStatic(1L);
        member.setMembertypes(membertype);

        java.sql.Date date=java.sql.Date.valueOf(daoqi);

        member.setMemberxufei(date);
        menberDao.save(member);

        return query(0,null,5,1);
    }

}
