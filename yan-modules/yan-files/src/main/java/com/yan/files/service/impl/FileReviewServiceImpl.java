package com.yan.files.service.impl;

import java.util.List;
import com.yan.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yan.files.mapper.FileReviewMapper;
import com.yan.files.domain.FileReview;
import com.yan.files.service.IFileReviewService;

/**
 * 文件预览Service业务层处理
 * 
 * @author yan
 * @date 2022-11-20
 */
@Service
public class FileReviewServiceImpl implements IFileReviewService 
{
    @Autowired
    private FileReviewMapper fileReviewMapper;

    /**
     * 查询文件预览
     * 
     * @param id 文件预览主键
     * @return 文件预览
     */
    @Override
    public FileReview selectFileReviewById(Long id)
    {
        return fileReviewMapper.selectFileReviewById(id);
    }

    /**
     * 查询文件预览列表
     * 
     * @param fileReview 文件预览
     * @return 文件预览
     */
    @Override
    public List<FileReview> selectFileReviewList(FileReview fileReview)
    {
        return fileReviewMapper.selectFileReviewList(fileReview);
    }

    /**
     * 新增文件预览
     * 
     * @param fileReview 文件预览
     * @return 结果
     */
    @Override
    public int insertFileReview(FileReview fileReview)
    {
        fileReview.setCreateTime(DateUtils.getNowDate());
        return fileReviewMapper.insertFileReview(fileReview);
    }

    /**
     * 修改文件预览
     * 
     * @param fileReview 文件预览
     * @return 结果
     */
    @Override
    public int updateFileReview(FileReview fileReview)
    {
        fileReview.setUpdateTime(DateUtils.getNowDate());
        return fileReviewMapper.updateFileReview(fileReview);
    }

    /**
     * 批量删除文件预览
     * 
     * @param ids 需要删除的文件预览主键
     * @return 结果
     */
    @Override
    public int deleteFileReviewByIds(Long[] ids)
    {
        return fileReviewMapper.deleteFileReviewByIds(ids);
    }

    /**
     * 删除文件预览信息
     * 
     * @param id 文件预览主键
     * @return 结果
     */
    @Override
    public int deleteFileReviewById(Long id)
    {
        return fileReviewMapper.deleteFileReviewById(id);
    }
}
