package com.fenglai.admin.controller.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.admin.pojo.dtos.QueryUserDTO;
import com.fenglai.admin.pojo.vos.SysUserListVO;
import com.fenglai.common.core.excel.ExcelFailResult;
import com.fenglai.common.web.annotations.Log;
import com.fenglai.common.web.annotations.PostParam;
import com.fenglai.common.web.response.CommonPage;
import com.fenglai.common.web.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fenglai.admin.service.ISysUserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @description: 用户表 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Slf4j(topic = "user-controller")
@Validated
@RestController
@RequestMapping("api/v1/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 用户列表分页查询
     * @param queryUserDTO 查询参数
     * @param page 分页参数
     */
    @GetMapping("queryUserList")
    @Log
    public R queryUserList(QueryUserDTO queryUserDTO, CommonPage page) {
        List<SysUserListVO> listVOS = iSysUserService.queryUserList(queryUserDTO, page);
        return R.ok(listVOS, page);
    }

    /**
     * 新增用户
     * @param userDTO 用户对象
     */
    @PostMapping("addUser")
    public R addUser(@Validated @RequestBody AddUserDTO userDTO) {
        return R.judge(iSysUserService.addUser(userDTO));
    }

    /**
     * 更新用户信息
     * @param userDTO 用户对象
     */
    @PostMapping("updateUser")
    public R updateUser(@Validated @RequestBody AddUserDTO userDTO) {
        return R.judge(iSysUserService.updateUser(userDTO));
    }

    /**
     * 更新用户状态, 启用/停用/删除
     * @param id 用户id
     * @param userStatus 用户状态值, {@link com.fenglai.admin.pojo.enums.SysStatusEnum}
     */
    @PostMapping("changeUserStatus")
    public R changeUserStatus(@PostParam @NotNull(message = "用户id不能为空") Long id,
                              @PostParam @NotNull(message = "用户状态不能为空") Integer userStatus) {
        return R.judge(iSysUserService.changeUserStatus(id, userStatus));
    }

    /**
     * 下载用户导入模板
     */
    @GetMapping("downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws IOException {

        String fileName = "用户导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8"));

        String fileClassPath = "templates" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream)
                .excelType(ExcelTypeEnum.XLSX)
                .withTemplate(inputStream).build();
        excelWriter.finish();
    }

    /**
     * 导入用户数据
     * @param file 文件对象
     */
    @PostMapping("/importUser")
    public R importUser(MultipartFile file) throws IOException {

//        String pathName = "C:\\Users\\TJ\\Desktop\\用户导入模板.xlsx";
//        BufferedInputStream inputStream = FileUtil.getInputStream(pathName);

        List<ExcelFailResult> failResults = iSysUserService.importUser(file.getInputStream());
        return R.ok(failResults);
    }

    /**
     * 导出用户
     * @param queryUserDTO 查询参数
     */
    @GetMapping("/exportUserList")
    public void exportUserList(QueryUserDTO queryUserDTO, HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + URLEncoder.encode("用户列表.xlsx", "UTF-8"));
        iSysUserService.exportUser(queryUserDTO, response.getOutputStream());
    }

}
