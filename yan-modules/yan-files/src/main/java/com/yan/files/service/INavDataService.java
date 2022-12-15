package com.yan.files.service;

import java.util.List;
import com.yan.files.domain.NavData;

/**
 * 导航树Service接口
 * 
 * @author yan
 * @date 2022-12-15
 */
public interface INavDataService 
{
    /**
     * 查询导航树
     * 
     * @param id 导航树主键
     * @return 导航树
     */
    public NavData selectNavDataById(Long id);

    /**
     * 查询导航树列表
     * 
     * @param navData 导航树
     * @return 导航树集合
     */
    public List<NavData> selectNavDataList(NavData navData);

    /**
     * 新增导航树
     * 
     * @param navData 导航树
     * @return 结果
     */
    public int insertNavData(NavData navData);

    /**
     * 修改导航树
     * 
     * @param navData 导航树
     * @return 结果
     */
    public int updateNavData(NavData navData);

    /**
     * 批量删除导航树
     * 
     * @param ids 需要删除的导航树主键集合
     * @return 结果
     */
    public int deleteNavDataByIds(Long[] ids);

    /**
     * 删除导航树信息
     * 
     * @param id 导航树主键
     * @return 结果
     */
    public int deleteNavDataById(Long id);
}
