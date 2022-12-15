package com.yan.files.service.impl;

import java.util.List;
import com.yan.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yan.files.mapper.NavDataMapper;
import com.yan.files.domain.NavData;
import com.yan.files.service.INavDataService;

/**
 * 导航树Service业务层处理
 * 
 * @author yan
 * @date 2022-12-15
 */
@Service
public class NavDataServiceImpl implements INavDataService 
{
    @Autowired
    private NavDataMapper navDataMapper;

    /**
     * 查询导航树
     * 
     * @param id 导航树主键
     * @return 导航树
     */
    @Override
    public NavData selectNavDataById(Long id)
    {
        return navDataMapper.selectNavDataById(id);
    }

    /**
     * 查询导航树列表
     * 
     * @param navData 导航树
     * @return 导航树
     */
    @Override
    public List<NavData> selectNavDataList(NavData navData)
    {
        return navDataMapper.selectNavDataList(navData);
    }

    /**
     * 新增导航树
     * 
     * @param navData 导航树
     * @return 结果
     */
    @Override
    public int insertNavData(NavData navData)
    {
        navData.setCreateTime(DateUtils.getNowDate());
        return navDataMapper.insertNavData(navData);
    }

    /**
     * 修改导航树
     * 
     * @param navData 导航树
     * @return 结果
     */
    @Override
    public int updateNavData(NavData navData)
    {
        navData.setUpdateTime(DateUtils.getNowDate());
        return navDataMapper.updateNavData(navData);
    }

    /**
     * 批量删除导航树
     * 
     * @param ids 需要删除的导航树主键
     * @return 结果
     */
    @Override
    public int deleteNavDataByIds(Long[] ids)
    {
        return navDataMapper.deleteNavDataByIds(ids);
    }

    /**
     * 删除导航树信息
     * 
     * @param id 导航树主键
     * @return 结果
     */
    @Override
    public int deleteNavDataById(Long id)
    {
        return navDataMapper.deleteNavDataById(id);
    }
}
