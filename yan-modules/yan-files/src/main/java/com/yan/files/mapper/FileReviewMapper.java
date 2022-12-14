package com.yan.files.mapper;

import java.util.List;
import com.yan.files.domain.FileReview;

/**
 * 文件预览Mapper接口
 * 
 * @author yan
 * @date 2022-11-20
 */
public interface FileReviewMapper 
{
    /**
     * 查询文件预览
     * 
     * @param id 文件预览主键
     * @return 文件预览
     */
    public FileReview selectFileReviewById(Long id);

    /**
     * 查询文件预览列表
     * 
     * @param fileReview 文件预览
     * @return 文件预览集合
     */
    public List<FileReview> selectFileReviewList(FileReview fileReview);

    /**
     * 新增文件预览
     * 
     * @param fileReview 文件预览
     * @return 结果
     */
    public int insertFileReview(FileReview fileReview);

    /**
     * 修改文件预览
     * 
     * @param fileReview 文件预览
     * @return 结果
     */
    public int updateFileReview(FileReview fileReview);

    /**
     * 删除文件预览
     * 
     * @param id 文件预览主键
     * @return 结果
     */
    public int deleteFileReviewById(Long id);

    /**
     * 批量删除文件预览
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFileReviewByIds(Long[] ids);
}
