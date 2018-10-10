package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kkc
 * Date: 2018-09-25
 * Time: 下午10:31
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
