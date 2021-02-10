package com.example.movie.Dao;

import com.example.movie.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    public void insert(User user);
    public void delete(Integer num);
    public List<User> queryAll();
    public List<User> query(Integer num);
    public List<User> queryemail(String email);
    public void updatePersonInformation(Integer num,String email,String name);
    public void updataPassword(String password,Integer user_num);
}
