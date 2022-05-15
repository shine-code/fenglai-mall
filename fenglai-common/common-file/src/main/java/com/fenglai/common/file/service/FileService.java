package com.fenglai.common.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 文件服务
 * 
 * @author: TJ
 * @date:  2022-04-28
 **/
public interface FileService {

    /**
     * 上传文件
     * @param multipartFile 文件对象
     * @return 文件访问路径
     */
    String upload(MultipartFile multipartFile);

    /**
     * 上传文件
     * @param bucketName 桶名称
     * @param multipartFile 文件对象
     * @return 文件访问路径
     */
    String upload(String bucketName, MultipartFile multipartFile);

    /**
     * 删除单个文件, 取默认桶名称
     * @param fileName 文件名
     */
    boolean deleteFile(String fileName);

    /**
     * 删除单个文件
     * @param bucketName 桶
     * @param fileName 文件名
     */
    boolean deleteFile(String bucketName, String fileName);

    /**
     * 删除多个文件
     * @param fileNames 文件名集合
     * @return 删除错误的对象列表; 全部删除成功，返回空列表
     */
    List<String> deleteFile(List<String> fileNames);

    /**
     * 删除多个文件
     * @param bucketName 桶
     * @param fileNames 文件名集合
     * @return 删除错误的对象列表; 全部删除成功，返回空列表
     */
    List<String> deleteFile(String bucketName, List<String> fileNames);
}
