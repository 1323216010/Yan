package com.yan.files.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yan.common.core.annotation.Excel;
import com.yan.common.core.web.domain.BaseEntity;

/**
 * 文件预览对象 file_review
 * 
 * @author yan
 * @date 2022-11-20
 */
public class FileReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 路径 */
    @Excel(name = "路径")
    private String path;

    /** 文件名 */
    @Excel(name = "文件名")
    private String name;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String type;

    /** 访问路径 */
    @Excel(name = "访问路径")
    private String url;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private String userName;

    /** 不为null时不在首页展示 */
    @Excel(name = "不为null时不在首页展示")
    private String secret;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 星级 */
    @Excel(name = "星级")
    private String star;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 详细信息 */
    @Excel(name = "详细信息")
    private String info;

    /** 封面 */
    @Excel(name = "封面")
    private String avatar;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private String volume;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setSecret(String secret) 
    {
        this.secret = secret;
    }

    public String getSecret() 
    {
        return secret;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setStar(String star) 
    {
        this.star = star;
    }

    public String getStar() 
    {
        return star;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setInfo(String info) 
    {
        this.info = info;
    }

    public String getInfo() 
    {
        return info;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setVolume(String volume) 
    {
        this.volume = volume;
    }

    public String getVolume() 
    {
        return volume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("path", getPath())
            .append("name", getName())
            .append("type", getType())
            .append("url", getUrl())
            .append("userName", getUserName())
            .append("secret", getSecret())
            .append("title", getTitle())
            .append("star", getStar())
            .append("author", getAuthor())
            .append("info", getInfo())
            .append("avatar", getAvatar())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("volume", getVolume())
            .toString();
    }
}
