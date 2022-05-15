package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 菜单表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenuDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 类型,0:菜单,1:目录,2:外链
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 菜单路径
     */
    @TableField("menu_path")
    private String menuPath;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 状态,0:启用,1:停用
     */
    @TableField("menu_status")
    private Integer menuStatus;

    /**
     * 层级路径
     */
    @TableField("hierarchy_path")
    private String hierarchyPath;


}
