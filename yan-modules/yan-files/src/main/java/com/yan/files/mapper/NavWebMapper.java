package com.yan.files.mapper;

import java.util.List;
import com.yan.files.domain.NavWeb;

/**
 * 导航网址Mapper接口
 * 
 * @author yan
 * @date 2022-12-15
 */
public interface NavWebMapper 
{
    /**
     * 查询导航网址
     * 
     * @param id 导航网址主键
     * @return 导航网址
     */
    public NavWeb selectNavWebById(Long id);

    /**
     * 查询导航网址列表
     * 
     * @param navWeb 导航网址
     * @return 导航网址集合
     */
    public List<NavWeb> selectNavWebList(NavWeb navWeb);

    /**
     * 新增导航网址
     * 
     * @param navWeb 导航网址
     * @return 结果
     */
    public int insertNavWeb(NavWeb navWeb);

    /**
     * 修改导航网址
     * 
     * @param navWeb 导航网址
     * @return 结果
     */
    public int updateNavWeb(NavWeb navWeb);

    /**
     * 删除导航网址
     * 
     * @param id 导航网址主键
     * @return 结果
     */
    public int deleteNavWebById(Long id);

    /**
     * 批量删除导航网址
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNavWebByIds(Long[] ids);
}
