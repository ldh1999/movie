package com.example.movie.Dao;

import com.example.movie.Pojo.MovieInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieInformationDao {
    public List<MovieInformation> queryAll();
    public void insert(MovieInformation movieInformation);
    public void updateMovieFile(String movie,Integer num);
    public List<MovieInformation> query(Integer num);
    public void delete(Integer num);
    public List<MovieInformation> queryType(String type);
    public List<MovieInformation> querySearch(String keyword);
}
