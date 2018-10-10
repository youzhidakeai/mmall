package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.conmon.ServerResponse;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kkc
 * Date: 2018-09-25
 * Time: 下午10:33
 */
@Slf4j
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    /**
     * 把上传的文件名返回回去
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename(); //上传文件名
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}\",fileName,path,uploadFileNam");

        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件上传成功
            FTPUtil.uploadFile(Lists.newArrayList());
            //上传成功,删除文件夹下的文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("文件上传异常",e);
            return null;
        }
        return targetFile.getName();//目标文件的文件名
    }

//    public static void main(String[] args) {
//        String fileName = "kkc.jpg";
//        System.out.println(fileName.substring(fileName.lastIndexOf(".")+1));
//    }
}
