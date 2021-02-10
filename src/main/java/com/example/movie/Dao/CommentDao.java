package com.example.movie.Dao;

import com.example.movie.Pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.naming.CompositeName;
import java.util.List;

@Mapper
@Repository
public interface CommentDao {
    public void insert(Comment comment);
    public void delete(Integer num);
    public List<Comment> queryMovie(Integer movieNum);
    public List<Comment> queryNum(Integer num);
    public List<Comment> queryAll();
}
