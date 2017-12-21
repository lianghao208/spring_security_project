package com.imooc.dto;


/**
 * Created by Administrator on 2017/12/18.
 */
public class FileInfo {
    private String path;

    public FileInfo(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
