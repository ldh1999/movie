package com.example.movie.Controller;

import com.example.movie.Pojo.State;
import com.example.movie.Pojo.User;
import com.example.movie.Server.EmailServer;
import com.example.movie.Server.MovieServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "email")
public class EmailController {

    @Autowired
    private EmailServer emailServer;

    @Autowired
    private MovieServer movieServer;

    private Integer GetUserCookie(HttpServletRequest request,String user){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie c:cookies){
                if (c.getName().equals(user)){
                    return Integer.valueOf(c.getValue());
                }
            }
            return null;
        }else
            return null;
    }
    @RequestMapping(path = "send")
    public @ResponseBody
    State email_send(String email_send){
        return emailServer.SendCode(email_send);
    }
    @RequestMapping(path = "send_cookie")
    public @ResponseBody State  email_send_cookie(HttpServletRequest request){
        Integer user_num=GetUserCookie(request,"forget_email");
        if (user_num==null)
            return new State("404");
        List<User> userList=movieServer.UserQuery(user_num);
        if (userList.isEmpty()){
            return new State("404");
        }
        return emailServer.SendCode(userList.get(0).getEmail());
    }

}
