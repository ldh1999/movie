package com.example.movie.Dao;

import com.example.movie.Pojo.Safe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SafeDao {
    public void insert(Safe safe);
    public void delete(Integer num);
    public void updata(Safe safe);
    public List<Safe> query(Integer num);
}
