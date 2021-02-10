package com.example.movie.Controller;
import com.example.movie.Pojo.*;
import com.example.movie.Server.EmailServer;
import com.example.movie.Server.MovieServer;
import com.example.movie.Server.RecommendServer;
import com.example.movie.Server.ServerImpl.MovieServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path = "/")
public class MovieController {

    @Autowired
    private MovieServer movieServer;

    @Autowired
    private EmailServer emailServer;

    @Autowired
    private RecommendServer recommendServer;

    private void User_cookie(Model model,HttpServletRequest request){
        String user_str=new String();
        user_str="您未登录(请勿点击我，除非您已登录)";
        Cookie[] cookie=request.getCookies();
        if (cookie!=null&&cookie.length>0){
            String user_num = new String();
            for (Cookie c:cookie){
                if (c.getName().equals("user")){
                    user_num=c.getValue();
                    user_str="欢迎您，"+movieServer.UserQuery(Integer.valueOf(user_num)).get(0).getName();
                }
            }
            //System.out.println("user_num++++++++++++++++++"+user_num);
            model.addAttribute("user",user_str);
        }
    }
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
    private String GetCookie(HttpServletRequest request,String name){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie c:cookies){
                if (c.getName().equals(name)){
                    return c.getValue();
                }
            }
            return null;
        }else
            return null;
    }

    @RequestMapping(path = "/")
    public String Home(Model model, HttpServletRequest request){
        List<MovieInformation> movieInformationTopList=new ArrayList<>();
        List<MovieInformation> movieInformationList=movieServer.MovieInformationQueryAll();
        Integer user_num=GetUserCookie(request,"user");
        String type=recommendServer.queryrecommend(user_num);

        if (user_num==null){
            model.addAttribute("prompt","请登录");
        }
        else if (type.equals("")){
            model.addAttribute("prompt","您当前观看电影较少，无法为您推荐");
        }else {
            List<MovieInformation> movieInformationList1=movieServer.MovieInformationQueryType(type);
            model.addAttribute("movie_type",movieInformationList1);
        }



        for(MovieInformation obj:movieInformationList){
            if (Integer.valueOf(obj.getScore())>=5){
                movieInformationTopList.add(obj);
            }
        }
        model.addAttribute("movieTop",movieInformationTopList);

        User_cookie(model,request);

        return "index";
    }

    @RequestMapping(path = "register_Login")
    public @ResponseBody
    State register_Login(User user,String re_email){
        State state=emailServer.re_Code(user.getEmail(),re_email);
        if(state.getState().equals("400")){
          state= movieServer.UserInsert(user);
          return state;
        }else {
            return state;
        }
    }

    @RequestMapping(path = "enroll_login")
    public @ResponseBody State enroll_login(String email, String password, HttpServletResponse response){
        State state=new State();
        System.out.println("email"+email+"password"+password);
        List<User> list= movieServer.UserQueryEmail(email);
        if (list.isEmpty()){
            state.setState("100");
        }
        else if (list.get(0).getPassword().equals(password)){
            Cookie user_num=new Cookie("user",list.get(0).getNum().toString());
            user_num.setMaxAge(-1);
            response.addCookie(user_num);
            System.out.println(list);
            state.setState("400");
        }else {
            state.setState("150");
        }
        return state;
    }

    @RequestMapping(path = "single")
    public String design(Integer uid,Model model,HttpServletRequest request){
        System.out.println(uid);
        List<MovieInformation> list=movieServer.MovieInformationQuery(uid);
        List<Comment> commentList=movieServer.CommentQueryMovie(uid);
        MovieInformation movie=list.get(0);

        Map<Integer,String> map=new HashMap<>();
        if (!(commentList.isEmpty())) {
            for (Comment c : commentList) {
                map.put(c.getCommentator(), movieServer.UserQuery(c.getCommentator()).get(0).getName());
            }
            model.addAttribute("map", map);
        }

        model.addAttribute("movie",movie);
        model.addAttribute("comment",commentList);
        User_cookie(model,request);

        Integer user_num=GetUserCookie(request,"user");
        recommendServer.addtype(user_num,list.get(0).getType());
        return "single";
    }

    @RequestMapping(path = "comment")
    public @ResponseBody State comment(Integer uid, String mes,HttpServletRequest request){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=df.format(new Date());
        String user_num = new String();
        Cookie[] cookie=request.getCookies();
        if (cookie!=null&&cookie.length>0){
            for (Cookie c:cookie){
                if (c.getName().equals("user")){
                    user_num=c.getValue();
                }
            }
        }
        State state=new State();
        if(user_num.equals("")){
            state.setState("800");
        }else
            state= movieServer.CommentInsert(new Comment(Integer.valueOf(user_num),mes,str,uid));
        return state;
    }


    @RequestMapping(path = "list")
    public String list(Model model,HttpServletRequest request){
        User_cookie(model,request);

        List<MovieInformation> movieInformationAllList=movieServer.MovieInformationQueryAll();
        model.addAttribute("MovieAll",movieInformationAllList);

        Map<String,List<MovieInformation>> map=new HashMap<>();
        map.put("动作",movieServer.MovieInformationQueryType("动作"));
        map.put("科幻",movieServer.MovieInformationQueryType("科幻"));
        map.put("喜剧",movieServer.MovieInformationQueryType("喜剧"));
        map.put("悬疑",movieServer.MovieInformationQueryType("悬疑"));
        map.put("恐怖",movieServer.MovieInformationQueryType("恐怖"));
        map.put("灾难",movieServer.MovieInformationQueryType("灾难"));


        model.addAttribute("MovieType",map);

        return "list";
    }

    @RequestMapping(path = "Search")
    public String Search(Model model,String search,HttpServletRequest request){
        System.out.println(search);
        User_cookie(model,request);
        if (search.equals("")){
            model.addAttribute("MovieAll",new ArrayList<MovieInformation>());
        }else{
            List<MovieInformation> movieInformationList=movieServer.MovieInformationQuerySearch(search);
            if (movieInformationList.isEmpty()){
                movieInformationList.add(new MovieInformation(0, "未搜索到", "", "", "", "", "","",""));
            }
            model.addAttribute("MovieAll",movieInformationList);
        }
        return "search";
    }

    @RequestMapping(path = "personal_information")
    public String Person_information(Model model,HttpServletRequest request){

        Cookie[] cookie=request.getCookies();
        String user=new String();

        if (cookie!=null&&cookie.length>0){
            for(Cookie c:cookie){
                if (c.getName().equals("user")){
                    user=c.getValue();
                }
            }
        }

        List<User> movieInformationList=movieServer.UserQuery(Integer.valueOf(user));

        model.addAttribute("userlist",movieInformationList.get(0));

        return "contact";
    }

    @RequestMapping(path = "reinformation")
    public String reinformation(Model model,HttpServletRequest request){

        Cookie[] cookies=request.getCookies();
        String user=new String();
        if (cookies!=null&&cookies.length>0){
            for (Cookie c:cookies){
                if (c.getName().equals("user")){
                    user=c.getValue();
                }
            }
        }
        model.addAttribute("user",movieServer.UserQuery(Integer.valueOf(user)).get(0));
        System.out.println(movieServer.UserQuery(Integer.valueOf(user)).get(0));
        return "reinformation";
    }

    @RequestMapping(path="re_information")
    public @ResponseBody State re_information(HttpServletResponse response,HttpServletRequest request,String name,String email,String code){
        State state=new State();
        String num=new String();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie c:cookies){
                if (c.getName().equals("user")){
                    num=c.getValue();
                }
            }
        }
        if (!(email.equals(movieServer.UserQuery(Integer.valueOf(num)).get(0).getEmail()))){
            if (movieServer.UserQueryEmail(email).isEmpty()){
                State state1=emailServer.re_Code(email,code);
                if (!(state1.getState().equals("400")))
                    return state1;
            }else
                return new State("当前邮箱已被注册");
        }

        state=movieServer.UserUpdateInformation(Integer.valueOf(num),email,name);
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        return state;
    }

    @RequestMapping(path="safe_question")
    public String safe_question(Model model,HttpServletRequest request){
        String num=new String();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie c:cookies){
                if (c.getName().equals("user")){
                    num=c.getValue();
                }
            }
        }
        List<Safe> safeList=movieServer.SafeQuery(Integer.valueOf(num));
        if (safeList.isEmpty()){
            model.addAttribute("safe",new Safe(Integer.valueOf(num),"null","null"));
        }else{
            model.addAttribute("safe",safeList.get(0));
        }
        return "re_safe_question";
    }
    @RequestMapping(path = "re_safe_question")
    @ResponseBody
    public State re_safe_question(String question,String answer,HttpServletRequest request){
        String num=new String();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie c:cookies){
                if (c.getName().equals("user")){
                    num=c.getValue();
                }
            }
        }
        return movieServer.SafeRenew(new Safe(Integer.valueOf(num),question,answer));
    }

    @RequestMapping(path = "forget_password_email")
    public String forget_password_email(){
        return "forget_password_email";
    }

    @RequestMapping(path = "re_forget_password_email")
    public @ResponseBody State re_forget_password_email(String email,HttpServletResponse response){
        List<User> userList=movieServer.UserQueryEmail(email);
        if (email.equals("")){
            return new State("250");
        }
        if (userList.isEmpty()){
            return new State("100");
        }else{
            State state=new State("400");
            Cookie cookie=new Cookie("forget_email",String.valueOf(userList.get(0).getNum()));
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            return new State("400");
        }
    }

    @RequestMapping(path ="forget_password" )
    public String forget_password(){
        return "forget_home";
    }
    @RequestMapping(path = "forget_password_sendEmail")
    public String forget_password_question(){
        return "forget_password_sendEmail";
    }
    @RequestMapping(path = "forget_password_question")
    public String forget_password(Model model,HttpServletResponse response,HttpServletRequest request){
        Integer user_num=GetUserCookie(request,"forget_email");
        List<Safe> safeList=movieServer.SafeQuery(user_num);
        if (safeList.isEmpty()){
            model.addAttribute("question","您未设置安全问题");
        }else {
            model.addAttribute("question",safeList.get(0).getQuestion());
            Cookie cookie=new Cookie("forget_password_num",String.valueOf(user_num));
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
        return "forget_password";
    }

    @RequestMapping(path = "re_password")
    public @ResponseBody State re_password(HttpServletRequest request,String answer,String password){
        Integer user_num=GetUserCookie(request,"forget_password_num");
        if (user_num==null){
            return new State("404");
        }
        List<Safe> safeList=movieServer.SafeQuery(user_num);
        if (safeList.isEmpty()){
            return new State("404");
        }
        if (password.equals("")||answer.equals("")){
            return new State("有空值");
        }
        if (answer.equals(safeList.get(0).getAnswer())){
            return movieServer.UserUpdataPassword(user_num,password);
        }else {
            return new State("答案不符");
        }
    }
    @RequestMapping(path = "re_password_email")
    public @ResponseBody State re_password_email(String password,String code,HttpServletRequest request){
        if (password.equals("")||code.equals("")){
            return new State("有空值");
        }
        List<User> userList=movieServer.UserQuery(GetUserCookie(request,"forget_email"));
        State state=emailServer.re_Code(userList.get(0).getEmail(),code);
        if (!(state.getState().equals("400")))
            return state;
        else
            return movieServer.UserUpdataPassword(userList.get(0).getNum(),password);
    }
}
