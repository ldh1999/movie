package com.example.movie.Dao;

import com.example.movie.Pojo.Code;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CodeDao {
    public void insert(Code code);
    public void delete(String email);
    public void updata(Code code);
    public List<Code> query(String email);
}
