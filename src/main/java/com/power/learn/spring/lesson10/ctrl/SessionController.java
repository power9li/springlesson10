package com.power.learn.spring.lesson10.ctrl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.power.learn.spring.lesson10.domain.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by shenli on 2017/3/10.
 */
@Controller
@RequestMapping(value = "/distSession/")
public class SessionController {

    @RequestMapping(value = "/")
    public String distSession(HttpServletRequest request) throws UnknownHostException {
        System.out.println("SessionController.distSession");
        HttpSession session = request.getSession();
        System.out.println("session = " + session);
        if (session != null) {
            if (session.getAttribute("userInfo") != null) {
                request.setAttribute("ip", InetAddress.getLocalHost().getHostAddress());
                return "listMyInfo";
            }
        }
        return "input";
    }

    @RequestMapping(value = "saveUserInfo")
    @ResponseBody
    public JSONObject saveUserInfo(@Valid UserInfo userInfo,
                                   BindingResult bindingResult,
                                   HttpServletRequest request) {
        System.out.println("SessionController.saveUserInfo");
        JSONObject resp = new JSONObject();
        if (bindingResult.hasErrors()) {
            JSONArray errAry = new JSONArray();
            resp.put("reasons", errAry);
            resp.put("success", false);
            bindingResult.getAllErrors().stream().forEach(o ->{
                String filedName = o.getCodes()[0];
                filedName = filedName.substring(filedName.indexOf(".") + 1);
                errAry.add(filedName +"."+ o.getDefaultMessage());
            });
        }
        else {
            System.out.println("add to session => userInfo = " + userInfo);
            request.getSession().setAttribute("userInfo", userInfo);
            resp.put("success", true);
            resp.put("userInfo", userInfo.toString());
        }
        return resp;
    }

}
