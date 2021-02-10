package com.example.movie.Server;

import com.example.movie.Pojo.*;

import java.util.List;

public interface MovieServer {
    public List<MovieInformation> MovieInformationQueryAll();
    public State MovieInformationInsert(MovieInformation movieInformation);
    public List<MovieInformation> MovieInformationQuery(Integer num);
    public State MovieInformationDelete(Integer num);
    public List<MovieInformation> MovieInformationQueryType(String type);
    public List<MovieInformation> MovieInformationQuerySearch(String keyword);

    public List<User> UserQueryAll();
    public List<User> UserQuery(Integer num);
    public List<User> UserQueryEmail(String email);
    public State UserInsert(User user);
    public State UserDelete(Integer num);
    public State UserUpdateInformation(Integer num,String email,String name);
    public State UserUpdataPassword(Integer num,String password);

    public List<Manager> ManagerQuery(String username);
    public State Managerup(Manager manager);

    public State CommentInsert(Comment comment);
    public State CommentDelete(Integer num);
    public List<Comment> CommentQuery(Integer num);
    public List<Comment> CommentQueryAll();
    public List<Comment> CommentQueryMovie(Integer num);

    public State SafeInsert(Safe safe);
    public State SafeDelete(Integer num);
    public State SafeUpdata(Safe safe);
    public State SafeRenew(Safe safe);
    public List<Safe> SafeQuery(Integer num);
}
