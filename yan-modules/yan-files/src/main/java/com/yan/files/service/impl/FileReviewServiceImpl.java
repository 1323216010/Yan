package com.yan.files.service.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yan.common.core.utils.DateUtils;
import com.yan.common.core.utils.MethodUtils;
import com.yan.common.security.service.TokenService;
import com.yan.common.security.utils.SecurityUtils;
import com.yan.files.utils.StaticGetPrivate;
import com.yan.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.yan.files.mapper.FileReviewMapper;
import com.yan.files.domain.FileReview;
import com.yan.files.service.IFileReviewService;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private TokenService tokenService;


    private static final Charset DEFAULT_CHARSET;

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }

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

    @Value("${kkFile.address}")
    private String kkAddres;
    /**
     * 查询文件预览列表
     * 
     * @param fileReview 文件预览
     * @return 文件预览
     */
    @Override
    public List<FileReview> selectFileReviewList(FileReview fileReview)
    {
        fileReview.setUserName(SecurityUtils.getUsername());
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
        for (int i = 0; i < ids.length; i++) {
            FileReview fileReview = fileReviewMapper.selectFileReviewById(ids[i]);
            Map<String,Object> map = new HashMap<>();
            map.put("fileName",fileReview.getName());
            StaticGetPrivate.getResTemplate().getForObject(kkAddres + "/deleteFile" + "?fileName={fileName}",String.class,map);
        }
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

    @Override
    public int fileUpload(MultipartFile file, HttpServletRequest httpServletRequest) {
        LoginUser loginUser = tokenService.getLoginUser(MethodUtils.getToken(httpServletRequest.getHeader("Authorization")));

        String url = kkAddres + "/fileUpload";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        StaticGetPrivate.getResTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);

        FileReview fileReview = new FileReview();
        fileReview.setUserName(loginUser.getUsername());
        fileReview.setTitle(MethodUtils.getFileTitle(file.getOriginalFilename()));
        fileReview.setName(file.getOriginalFilename());
        fileReview.setVolume(MethodUtils.getFileSize(String.valueOf(file.getSize())));
        fileReview.setType(MethodUtils.getFileType(file.getOriginalFilename()));
        String url0 = kkAddres + "/demo/" + file.getOriginalFilename();
        url0 = new String(Base64Utils.encode(url0.getBytes(DEFAULT_CHARSET)), StandardCharsets.UTF_8);
        fileReview.setUrl(MethodUtils.stringToUrl(url0));

        return fileReviewMapper.insertFileReview(fileReview);
    }
}
