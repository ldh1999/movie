package com.example.movie.Controller;

import com.example.movie.Pojo.Manager;
import com.example.movie.Pojo.MovieInformation;
import com.example.movie.Pojo.State;
import com.example.movie.Pojo.User;
import com.example.movie.Server.Load;
import com.example.movie.Server.MovieServer;
import org.apache.catalina.manager.ManagerServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("_manager")
public class BackgroundController {

    @Autowired
    private MovieServer movieServer;
    @Autowired
    private Load load;

    @RequestMapping("/")
    public String ReHome(){
        return "background";
    }

    @RequestMapping("login")
    public @ResponseBody State login(Manager manager){
        return movieServer.Managerup(manager);
    }

    @RequestMapping(path = "jukdowkjaldciqn^ddqwfsdqfdqf")
    public String Home(Model model){
        List<MovieInformation> list=movieServer.MovieInformationQueryAll();
        List<User> list1=movieServer.UserQueryAll();

        model.addAttribute("movie",list);
        model.addAttribute("user",list1);

        return "backgroundHome";
    }

    @RequestMapping(path = "insert")
    public @ResponseBody State insert(MovieInformation movieInformation){
        System.out.println(movieInformation);
        return movieServer.MovieInformationInsert(movieInformation);
    }

    @RequestMapping(path = "delete")
    public @ResponseBody State delete(Integer num){
        System.out.println(num);
        return movieServer.MovieInformationDelete(num);
    }

    @RequestMapping(path = "delete_user")
    public @ResponseBody State delete_user(Integer num){
        System.out.println(num);
        return movieServer.UserDelete(num);
    }

    @RequestMapping(path = "upmovie")
    public @ResponseBody String upmovie(@RequestParam("video")MultipartFile file,Integer num) throws IOException {
        return load.MovieFile(file,num);
    }


}
