package com.example.movie.Server.ServerImpl;

import com.example.movie.Dao.MovieInformationDao;
import com.example.movie.Server.Load;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class LoadImpl implements Load {
    @Autowired
    private MovieInformationDao movieInformationDao;

    @Override
    public String MovieFile(MultipartFile file,Integer num) throws FileNotFoundException {
        if (num==null){
            return "请输入num";
        }
        else if (movieInformationDao.query(num).isEmpty()){
            return "该编号不存在";
        }
        else if (!file.isEmpty()) {
            //文件上传的地址

            File path = new File(ResourceUtils.getURL("classpath*:").getPath());
            //String realPath = path.replace('/', '\\').substring(1, path.length());
            if (!(path.exists())){
                path=new File("");
            }

            File realpath=new File(path.getAbsolutePath(),"static/movie");
            //用于查看路径是否正确
            System.out.println(path);

            //获取文件的名称
            final String fileName = file.getOriginalFilename();
            //限制文件上传的类型
            String contentType = file.getContentType();
            if (true) {
                File dest = new File(realpath, fileName);

                if (!(dest.exists())){
                    dest.mkdirs();
                }

                //完成文件的上传
                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("图片上传成功!");
                String path01 = "../upload/" + fileName;
                //存放在sql中的路径
                String sqlPath="movie/"+fileName;
                movieInformationDao.updateMovieFile(sqlPath,num);

                return "success";
            } else {
                System.out.println("上传失败！");
                return "false";
            }
        } else {
            System.out.println("上传失败！");
            return "请选择文件";
        }
    }
}
