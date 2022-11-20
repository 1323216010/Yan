package com.yan.files.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yan.common.log.annotation.Log;
import com.yan.common.log.enums.BusinessType;
import com.yan.common.security.annotation.RequiresPermissions;
import com.yan.files.domain.FileReview;
import com.yan.files.service.IFileReviewService;
import com.yan.common.core.web.controller.BaseController;
import com.yan.common.core.web.domain.AjaxResult;
import com.yan.common.core.utils.poi.ExcelUtil;
import com.yan.common.core.web.page.TableDataInfo;

/**
 * 文件预览Controller
 * 
 * @author yan
 * @date 2022-11-20
 */
@RestController
@RequestMapping("/review")
public class FileReviewController extends BaseController
{
    @Autowired
    private IFileReviewService fileReviewService;

    /**
     * 查询文件预览列表
     */
    @RequiresPermissions("files:review:list")
    @GetMapping("/list")
    public TableDataInfo list(FileReview fileReview)
    {
        startPage();
        List<FileReview> list = fileReviewService.selectFileReviewList(fileReview);
        return getDataTable(list);
    }

    /**
     * 导出文件预览列表
     */
    @RequiresPermissions("files:review:export")
    @Log(title = "文件预览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FileReview fileReview)
    {
        List<FileReview> list = fileReviewService.selectFileReviewList(fileReview);
        ExcelUtil<FileReview> util = new ExcelUtil<FileReview>(FileReview.class);
        util.exportExcel(response, list, "文件预览数据");
    }

    /**
     * 获取文件预览详细信息
     */
    @RequiresPermissions("files:review:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fileReviewService.selectFileReviewById(id));
    }

    /**
     * 新增文件预览
     */
    @RequiresPermissions("files:review:add")
    @Log(title = "文件预览", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FileReview fileReview)
    {
        return toAjax(fileReviewService.insertFileReview(fileReview));
    }

    /**
     * 修改文件预览
     */
    @RequiresPermissions("files:review:edit")
    @Log(title = "文件预览", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FileReview fileReview)
    {
        return toAjax(fileReviewService.updateFileReview(fileReview));
    }

    /**
     * 删除文件预览
     */
    @RequiresPermissions("files:review:remove")
    @Log(title = "文件预览", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fileReviewService.deleteFileReviewByIds(ids));
    }
}