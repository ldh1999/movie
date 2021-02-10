package com.example.movie.Server.ServerImpl;

import com.example.movie.Dao.*;
import com.example.movie.Pojo.*;
import com.example.movie.Server.MovieServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class MovieServerImpl implements MovieServer {
    @Autowired
    private MovieInformationDao movieInformationDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private SafeDao safeDao;
    @Autowired
    private RecommendDao recommendDao;

    @Override
    public List<MovieInformation> MovieInformationQuery(Integer num) {
        return movieInformationDao.query(num);
    }

    @Override
    public List<MovieInformation> MovieInformationQueryAll() {
        return movieInformationDao.queryAll();
    }

    @Override
    public State MovieInformationInsert(MovieInformation movieInformation) {
        State state=new State();
        if (movieInformation.isempty()){
            state.setState("有空值");
        }else if (movieInformationDao.query(movieInformation.getNum()).isEmpty()){
            movieInformationDao.insert(movieInformation);
           // System.out.println(movieInformation);
            state.setState("注册成功");
        }else {
            state.setState("该邮箱已被注册，若忘记密码请找回");
        }
        return state;
    }

    @Override
    public State MovieInformationDelete(Integer num) {
        State state=new State();
        String path=null;
        File file=null;
        if (num==null){
            state.setState("250");
        }
        else if (movieInformationDao.query(num).isEmpty()){
            state.setState("100");
        }else {
            try {
              //  path = ResourceUtils.getURL("classpath:").getPath() + "static";
                file=new File(ResourceUtils.getURL("classpath*:").getPath());
                if (!(file.exists())){
                    file=new File("");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
           //String realPath = path.replace('/', '\\').substring(1, path.length());

            File realfile=new File(file.getAbsolutePath(),"static/"+movieInformationDao.query(num).get(0).getVideo());
            System.out.println(file);
            //File file=new File(realPath,movieInformationDao.query(num).get(0).getVideo());
            if (realfile.exists()){
                realfile.delete();
                System.out.println("delete !!1");
            }else{
                System.out.println(realfile.getAbsoluteFile());
                System.out.println("delete false");
            }
            movieInformationDao.delete(num);
            state.setState("400");
        }
        return state;
    }

    @Override
    public List<User> UserQueryAll() {
        return userDao.queryAll();
    }

    @Override
    public List<User> UserQuery(Integer num) {
        return userDao.query(num);
    }

    @Override
    public List<User> UserQueryEmail(String email) {
        return userDao.queryemail(email);
    }

    @Override
    public State UserInsert(User user) {
        State state=new State();
        if (user.isempty()){
            state.setState("250");
        }else if (userDao.queryemail(user.getEmail()).isEmpty()){
            userDao.insert(user);
            System.out.println("user_num"+user);
            recommendDao.insert(new Recommend(userDao.queryemail(user.getEmail()).get(0).getNum(),0,0,0,0,0,0));
            state.setState("400");
        }else {
            state.setState("200");
        }
        return state;

    }

    @Override
    public State UserDelete(Integer num) {
        State state=new State();
        if(userDao.query(num).isEmpty()){
            state.setState("100");
        }else {
            userDao.delete(num);
            state.setState("400");
        }
        return state;
    }

    @Override
    public State UserUpdateInformation(Integer num,String email,String name) {
        State state=new State();
        if (num==null||email.equals("")||name.equals("")){
            state.setState("250");
        }else {
            state.setState("400");
            userDao.updatePersonInformation(num,email,name);

        }
        return state;
    }

    @Override
    public List<Manager> ManagerQuery(String username) {
        return managerDao.query(username);
    }

    public State Managerup(Manager manager){
        State state=new State();
        List<Manager> list=  managerDao.query(manager.getUsername());
        if(list.isEmpty()){
            state.setState("100");
        }else if (!(list.get(0).getPassword().equals(manager.getPassword()))){
            state.setState("150");
        }else {
            state.setState("400");
        }
        return state;
    }

    @Override
    public State CommentInsert(Comment comment) {
        State state=new State();
        if (comment.getContent().equals(""))
            state.setState("250");
        else {
            state.setState("400");
            commentDao.insert(comment);
        }
        return state;
    }

    @Override
    public State CommentDelete(Integer num) {
        State state=new State();
        List<Comment> list=commentDao.queryNum(num);
        if(list.isEmpty()){
            state.setState("100");
        }else {
            commentDao.delete(num);
            state.setState("400");
        }
        return state;
    }

    @Override
    public List<Comment> CommentQuery(Integer num) {
        return commentDao.queryNum(num);
    }

    @Override
    public List<Comment> CommentQueryAll() {
        return commentDao.queryAll();
    }

    @Override
    public List<Comment> CommentQueryMovie(Integer num) {
        return commentDao.queryMovie(num);
    }

    @Override
    public List<MovieInformation> MovieInformationQueryType(String type) {
        return movieInformationDao.queryType(type);
    }

    @Override
    public List<MovieInformation> MovieInformationQuerySearch(String keyword) {
        return movieInformationDao.querySearch(keyword);
    }


    @Override
    public State SafeInsert(Safe safe) {
        State state=new State();
        if (safe.isemtpy()){
            state.setState("250");
        }else if(!(safeDao.query(safe.getNum()).isEmpty())){
            state.setState("200");
        }else {
            safeDao.insert(safe);
            state.setState("400");
        }
        return state;
    }

    @Override
    public State SafeDelete(Integer num) {
        State state=new State();
        if (num==null){
            state.setState("250");
        }else if (safeDao.query(num).isEmpty()){
            state.setState("100");
        }else {
            safeDao.delete(num);
            state.setState("400");
        }
        return state;
    }

    @Override
    public State SafeUpdata(Safe safe) {
        State state=new State();
        if (safe.isemtpy()){
            state.setState("250");
        }else if (safeDao.query(safe.getNum()).isEmpty()){
            state.setState("100");
        }else {
            safeDao.updata(safe);
            state.setState("400");
        }
        return state;
    }

    @Override
    public List<Safe> SafeQuery(Integer num) {
        return safeDao.query(num);
    }

    @Override
    public State SafeRenew(Safe safe) {
        State state=new State();
        if (safe.isemtpy()){
            state.setState("250");
        }
        else if (safeDao.query(safe.getNum()).isEmpty()){
            safeDao.insert(safe);
            state.setState("400");
        }else{
            safeDao.updata(safe);
            state.setState("400");
        }
        return state;
    }

    @Override
    public State UserUpdataPassword(Integer num, String password) {
        if (password.equals("")){
            return new State("250");
        }
        if (userDao.query(num).isEmpty()){
            return new State("100");
        }
        userDao.updataPassword(password,num);
        return new State("密码更改成功");
    }
}
