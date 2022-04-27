package com.shine.common.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-28
 **/
public interface IFileService {

    void upload(String bucketName, MultipartFile multipartFile, String fileType);
}
