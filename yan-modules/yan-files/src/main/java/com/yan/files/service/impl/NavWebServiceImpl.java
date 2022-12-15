package com.yan.files.service.impl;

import java.util.List;
import com.yan.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yan.files.mapper.NavWebMapper;
import com.yan.files.domain.NavWeb;
import com.yan.files.service.INavWebService;

/**
 * 导航网址Service业务层处理
 * 
 * @author yan
 * @date 2022-12-15
 */
@Service
public class NavWebServiceImpl implements INavWebService 
{
    @Autowired
    private NavWebMapper navWebMapper;

    /**
     * 查询导航网址
     * 
     * @param id 导航网址主键
     * @return 导航网址
     */
    @Override
    public NavWeb selectNavWebById(Long id)
    {
        return navWebMapper.selectNavWebById(id);
    }

    /**
     * 查询导航网址列表
     * 
     * @param navWeb 导航网址
     * @return 导航网址
     */
    @Override
    public List<NavWeb> selectNavWebList(NavWeb navWeb)
    {
        return navWebMapper.selectNavWebList(navWeb);
    }

    /**
     * 新增导航网址
     * 
     * @param navWeb 导航网址
     * @return 结果
     */
    @Override
    public int insertNavWeb(NavWeb navWeb)
    {
        navWeb.setCreateTime(DateUtils.getNowDate());
        return navWebMapper.insertNavWeb(navWeb);
    }

    /**
     * 修改导航网址
     * 
     * @param navWeb 导航网址
     * @return 结果
     */
    @Override
    public int updateNavWeb(NavWeb navWeb)
    {
        navWeb.setUpdateTime(DateUtils.getNowDate());
        return navWebMapper.updateNavWeb(navWeb);
    }

    /**
     * 批量删除导航网址
     * 
     * @param ids 需要删除的导航网址主键
     * @return 结果
     */
    @Override
    public int deleteNavWebByIds(Long[] ids)
    {
        return navWebMapper.deleteNavWebByIds(ids);
    }

    /**
     * 删除导航网址信息
     * 
     * @param id 导航网址主键
     * @return 结果
     */
    @Override
    public int deleteNavWebById(Long id)
    {
        return navWebMapper.deleteNavWebById(id);
    }
}
