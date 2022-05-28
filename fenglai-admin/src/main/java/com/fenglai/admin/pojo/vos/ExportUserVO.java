package com.fenglai.admin.pojo.vos;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

/**
 * @description: 用户导出对象
 * 
 * @author: TJ
 * @date:  2022-05-28
 **/
@Data
@ContentRowHeight(20)
@ColumnWidth(20)
public class ExportUserVO {

    /**
     * 用户编码
     */
    @ExcelProperty("用户编码")
    private String userCode;
    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String userName;
    /**
     * 组织单位名
     */
    @ExcelProperty("组织单位")
    private String orgName;
    /**
     * 性别
     */
    @ExcelProperty("性别")
    private String sex;
    /**
     * 手机号
     */
    @ExcelProperty("手机号")
    private String phone;
    /**
     * 用户状态
     */
    @ExcelProperty("用户状态")
    private String userStatus;

    @ExcelProperty("创建时间")
    private String createTime;
}
