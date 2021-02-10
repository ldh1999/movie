package com.example.movie.Server;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface Load {
    public String MovieFile(MultipartFile file, Integer num) throws FileNotFoundException;
}
