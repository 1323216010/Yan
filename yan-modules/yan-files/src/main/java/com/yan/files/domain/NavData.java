package com.yan.files.domain;

import lombok.Data;
import com.yan.common.core.annotation.Excel;
import com.yan.common.core.web.domain.TreeEntity;

/**
 * 导航树对象 t_nav_data
 * 
 * @author yan
 * @date 2022-12-15
 */
@Data
public class NavData extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /**  侧边栏一级名称 */
    @Excel(name = " 导航树节点名称")
    private String name;

    /**  侧边栏一级名称 */
    @Excel(name = " 导航树节点名称")
    private String enName;

    /**  侧边栏一级图标 */
    @Excel(name = " 侧边栏一级图标")
    private String icon;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long webId;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    private String isUrl;

    private String brief;

    private String url;

    private String classification;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


}
