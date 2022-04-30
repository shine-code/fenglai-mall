package com.fenglai.common.file;

import cn.hutool.core.lang.Pair;
import com.fenglai.common.file.service.ShineFileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-28
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j(topic = "common-file-test")
public class FileServiceTest {

    @Autowired
    ShineFileService fileService;

    @Test
    public void uploadTest() {

        Pair<File, String> pair;
        // 表单
//        pair = Pair.of(new File("C:\\Users\\TJ\\Desktop\\plot.log"), "multipart/form-data");

        // 图片
        pair = Pair.of(new File("C:\\Users\\TJ\\Pictures\\719a.PNG"), "image/PNG");
        try {
            MultipartFile multipartFile = new MockMultipartFile("file",
                    pair.getKey().getName(),
                    pair.getValue(),
                    new FileInputStream(pair.getKey()));
            String url = fileService.upload(multipartFile);
            System.out.println(url);
            Assertions.assertNotNull(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteFileTest() {

        boolean deleteFile = fileService.deleteFile("127d6dec149c482c91bc8d67e15a3aec.PNG");
        log.info("文件删除: {}", deleteFile);
    }
}
