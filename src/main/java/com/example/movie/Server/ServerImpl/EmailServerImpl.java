package com.example.movie.Server.ServerImpl;

import com.example.movie.Dao.CodeDao;
import com.example.movie.Pojo.Code;
import com.example.movie.Pojo.State;
import com.example.movie.Server.EmailServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class EmailServerImpl implements EmailServer {

    @Autowired
    private CodeDao codeDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.sender}")
    private String sender;


    private String CreateCode(){
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for (int i=0;i<6;i++){
            sb.append(String.valueOf(random.nextInt(10)));
        }
        return sb.toString();
    }

    private boolean CheckString(String str,char c){
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)==c){
                return true;
            }
        }
        return false;
    }

    @Override
    public State SendCode(String email) {
        String code;
        if (email.equals("")){
            return new State("邮箱不能为空");
        }else if (!(CheckString(email,'@'))){
            return new State("邮箱格式不符");
        }

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);//"1523890353@qq.com"
        message.setSubject("豆扮影视");
        code=CreateCode();

        message.setText("欢迎您的加入，验证码："+code+"，此验证码5分钟之内有效【若非您本人操作,请注意您的隐私安全】");
        System.out.println("欢迎您的加入，验证码："+code+"，此验证码5分钟之内有效"+DigestUtils.md5DigestAsHex(code.getBytes()));
        try {

            javaMailSender.send(message);

            SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar c=Calendar.getInstance();
            c.add(Calendar.MINUTE,5);
            String date=df.format(c.getTime());

            if (codeDao.query(email).isEmpty()){
                codeDao.insert(new Code(email,DigestUtils.md5DigestAsHex(code.getBytes()),date));
            }else {
                codeDao.updata(new Code(email,DigestUtils.md5DigestAsHex(code.getBytes()),date));
            }

            return new State("发送成功");
        }catch (MailSendException e){
            System.out.println(e.getMessage());
            return new State("该邮箱不存在");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new State("邮箱格式都写不对 #= =");
        }
    }

    @Override
    public State re_Code(String email,String code) {

        if (code.equals("")){
            return new State("请填写验证码");
        }
        List<Code> codeList=codeDao.query(email);
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
        String date=df.format(new Date());
        code=DigestUtils.md5DigestAsHex(code.getBytes());

        System.out.println(date);

        if (codeList.isEmpty()){
            return new State("该邮箱未获取验证码(该验证码仅可使用一次，用后即作废)");
        }else if(codeList.get(0).getCode().equals(code)){
            if(codeList.get(0).getTimeout().compareTo(date)<0){
                return new State("验证码超时");
            }else {
                codeDao.delete(email);
                return new State("400");
            }
        }else {
            return new State("验证码不符");
        }
    }
}
