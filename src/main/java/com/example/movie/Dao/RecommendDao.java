package com.example.movie.Dao;

import com.example.movie.Pojo.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecommendDao {
    public void insert(Recommend recommend);
    public void delete(Integer num);
    public void updata(Integer num,String type,Integer retype);
    public List<Recommend> query(Integer num);
    public Integer querytype(Integer num,String type);
}
