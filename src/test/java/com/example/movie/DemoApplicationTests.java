package com.example.movie;

import com.example.movie.Dao.CodeDao;
import com.example.movie.Dao.RecommendDao;
import com.example.movie.Dao.SafeDao;
import com.example.movie.Pojo.*;
import com.example.movie.Server.MovieServer;
import com.example.movie.Server.RecommendServer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RecommendServer movieServer;
    @Autowired
    private RecommendDao dao;

    @Test
    void contextLoads() {
        //dao.insert(new Recommend(3,0,0,0,0,0,0));
        System.out.println(dao.querytype(3,"suspense"));
        movieServer.addtype(3,"喜剧");
        movieServer.addtype(3,"灾");



    }
}
