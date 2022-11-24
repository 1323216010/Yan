package com.yan.files.service;

import java.util.List;
import com.yan.files.domain.FileReview;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件预览Service接口
 * 
 * @author yan
 * @date 2022-11-20
 */
public interface IFileReviewService 
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
     * 批量删除文件预览
     * 
     * @param ids 需要删除的文件预览主键集合
     * @return 结果
     */
    public int deleteFileReviewByIds(Long[] ids);

    /**
     * 删除文件预览信息
     * 
     * @param id 文件预览主键
     * @return 结果
     */
    public int deleteFileReviewById(Long id);

    public int fileUpload(MultipartFile file, HttpServletRequest request);

}
