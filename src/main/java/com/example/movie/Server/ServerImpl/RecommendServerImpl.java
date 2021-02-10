package com.example.movie.Server.ServerImpl;

import com.example.movie.Dao.RecommendDao;
import com.example.movie.Pojo.Recommend;
import com.example.movie.Server.RecommendServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServerImpl implements RecommendServer {

    @Autowired
    private RecommendDao recommendDao;

    private String strchange(String type){
        if (type.equals("动作"))
            type="action";
        else if(type.equals("科幻"))
            type="science";
        else if(type.equals("喜剧"))
            type="comedy";
        else if(type.equals("悬疑"))
            type="suspense";
        else if(type.equals("恐怖"))
            type="dracula";
        else if(type.equals("灾难"))
            type="disaster";
        else
            type=null;
        return type;
    }

    private String restrchange(String type){
        if (type.equals("action"))
            type="动作";
        else if(type.equals("science"))
            type="科幻";
        else if(type.equals("comedy"))
            type="喜剧";
        else if(type.equals("suspense"))
            type="悬疑";
        else if(type.equals("dracula"))
            type="恐怖";
        else if(type.equals("disaster"))
            type="灾难";
        else
            type=null;
        return type;
    }

    @Override
    public void addtype(Integer num, String type) {
        type=strchange(type);
        if (num!=null){
            Integer add=recommendDao.querytype(num,type)+1;
            recommendDao.updata(num,type,add);
        }
    }

    @Override
    public String queryrecommend(Integer num) {
        List<Recommend> recommendList=recommendDao.query(num);
        if (recommendList.isEmpty()){
            return "";
        }
        Recommend recommend=recommendList.get(0);
        String str=new String();
        Integer max=recommend.getAction();
        str="action";

        if (max<recommend.getComedy()){
            max=recommend.getComedy();
            str="comedy";
        }
        if (max<recommend.getScience()){
            max=recommend.getScience();
            str="science";
        }
        if (max<recommend.getSuspense()){
            max=recommend.getSuspense();
            str="suspense";
        }
        if (max<recommend.getDracula()){
            max=recommend.getDracula();
            str="dracula";
        }
        if (max<recommend.getDisaster()){
            max=recommend.getDisaster();
            str="disaster";
        }
        if (max==0)
            return "";
        return restrchange(str);
    }
}
