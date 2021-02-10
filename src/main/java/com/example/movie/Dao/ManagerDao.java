package com.example.movie.Dao;

import com.example.movie.Pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerDao {
    public List<Manager> query(String username);
}
