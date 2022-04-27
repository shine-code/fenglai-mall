package com.shine.common.file.service.impl;

import cn.hutool.core.util.IdUtil;
import com.shine.common.file.service.IFileService;
import com.shine.common.web.exception.FileServiceException;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-28
 **/
@Slf4j
@Service("MinioFileService")
public class MinioFileServiceImpl implements IFileService {

    @Autowired
    private MinioClient minioClient;

    /**
     * 检查存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return boolean
     */
    public boolean bucketExists(String bucketName) {
        boolean found = false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error("{} does not exist; {}", bucketName, e);
        }
        return found;
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    public boolean createBucket(String bucketName) {
        boolean exist = bucketExists(bucketName);
        if (!exist) {
            try {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build());
            } catch (Exception e) {
                log.error("createBucket exception: {}, \n {}", bucketName, e);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 列出所有存储桶名称
     */
    public List<String> listBucketNames() {
        List<Bucket> bucketList = listBuckets();
        List<String> bucketListName = new ArrayList<>();
        bucketList.forEach(bucket -> bucketListName.add(bucket.name()));
        return bucketListName;
    }

    /**
     * 所有存储桶
     */
    private List<Bucket> listBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            log.error("minio 查询存储桶异常: ", e);
        }
        return new ArrayList<>();
    }

    /**
     * 文件上传
     *
     * @param bucketName 桶名称
     * @param multipartFile 文件
     */
    @Override
    public void upload(String bucketName, MultipartFile multipartFile, String fileType) {
        try {
            String fileName = multipartFile.getOriginalFilename();

            String objectName = IdUtil.fastSimpleUUID()
                    + fileName.substring(fileName.lastIndexOf("."));

            InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, -1, -1)
                            .contentType(fileType)
                            .build());
        } catch (Exception e) {
            throw new FileServiceException("文件上传异常: " + e);
        }
    }


    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param fileName 存储桶里的文件名称
     * @return 访问路径
     */
    public String getFileUrl(String bucketName, String fileName) {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            try {
                url = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucketName)
                                .object(fileName)
                                .expiry(2, TimeUnit.MINUTES)
                                .build());
            } catch (Exception e) {
                throw new FileServiceException(String.format("文件路径获取异常, bucketName: %s, fileName: %s; \n %s", bucketName, fileName, e));
            }
        }
        return url;
    }


    /**
     * 删除一个对象
     *
     * @param bucketName 存储桶名称
     * @param fileName 存储桶里的文件名称
     */
    public boolean removeFile(String bucketName, String fileName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            try {
                minioClient.removeObject(
                        RemoveObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .build());
            } catch (Exception e) {
                throw new FileServiceException(String.format("删除文件异常, bucketName: %s, fileName: %s; \n %s", bucketName, fileName, e));
            }
            return true;
        }
        log.warn("file not found when remove, bucketName:{}, fileName:{}", bucketName, fileName);
        return false;
    }

    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param fileName 存储桶里的文件名称
     * @return 文件流
     */
    public InputStream getFileInputStream(String bucketName, String fileName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            try {
                return minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .build());
            } catch (Exception e) {
                throw new FileServiceException(String.format("获取文件对象元数据异常, bucketName: %s, fileName: %s; \n %s", bucketName, fileName, e));
            }
        }
        return null;
    }

    /**
     * 删除指定桶的多个文件对象,返回删除错误的对象列表，全部删除成功，返回空列表
     *
     * @param bucketName  存储桶名称
     * @param fileNames 删除文件集合
     */
    public List<String> removeFile(String bucketName, List<String> fileNames) {
        List<String> res = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            List<DeleteObject> objects = new LinkedList<>();
            for (String fileName : fileNames) {
                objects.add(new DeleteObject(fileName));
            }
            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder()
                                    .bucket(bucketName)
                                    .objects(objects).build());

            for (Result<DeleteError> result : results) {
                try {
                    DeleteError error = result.get();
                    res.add(error.objectName());
                } catch (Exception e) {
                    log.error("文件删除异常, bucketName:{}; \n {}", bucketName, e);
                }
            }
        }
        return res;
    }
}
