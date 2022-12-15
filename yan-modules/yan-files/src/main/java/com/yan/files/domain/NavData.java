package com.yan.files.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yan.common.core.annotation.Excel;
import com.yan.common.core.web.domain.TreeEntity;

/**
 * 导航树对象 t_nav_data
 * 
 * @author yan
 * @date 2022-12-15
 */
public class NavData extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /**  侧边栏一级名称 */
    @Excel(name = " 侧边栏一级名称")
    private String name;

    /**  侧边栏一级名称 */
    @Excel(name = " 侧边栏一级名称")
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

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setEnName(String enName) 
    {
        this.enName = enName;
    }

    public String getEnName() 
    {
        return enName;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setWebId(Long webId) 
    {
        this.webId = webId;
    }

    public Long getWebId() 
    {
        return webId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("name", getName())
            .append("parentName", getParentName())
            .append("enName", getEnName())
            .append("icon", getIcon())
            .append("orderNum", getOrderNum())
            .append("webId", getWebId())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
