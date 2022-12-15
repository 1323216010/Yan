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
import com.yan.files.domain.NavWeb;
import com.yan.files.service.INavWebService;
import com.yan.common.core.web.controller.BaseController;
import com.yan.common.core.web.domain.AjaxResult;
import com.yan.common.core.utils.poi.ExcelUtil;
import com.yan.common.core.web.page.TableDataInfo;

/**
 * 导航网址Controller
 * 
 * @author yan
 * @date 2022-12-15
 */
@RestController
@RequestMapping("/nav")
public class NavWebController extends BaseController
{
    @Autowired
    private INavWebService navWebService;

    /**
     * 查询导航网址列表
     */
    @RequiresPermissions("files:nav:list")
    @GetMapping("/list")
    public TableDataInfo list(NavWeb navWeb)
    {
        startPage();
        List<NavWeb> list = navWebService.selectNavWebList(navWeb);
        return getDataTable(list);
    }

    /**
     * 导出导航网址列表
     */
    @RequiresPermissions("files:nav:export")
    @Log(title = "导航网址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavWeb navWeb)
    {
        List<NavWeb> list = navWebService.selectNavWebList(navWeb);
        ExcelUtil<NavWeb> util = new ExcelUtil<NavWeb>(NavWeb.class);
        util.exportExcel(response, list, "导航网址数据");
    }

    /**
     * 获取导航网址详细信息
     */
    @RequiresPermissions("files:nav:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(navWebService.selectNavWebById(id));
    }

    /**
     * 新增导航网址
     */
    @RequiresPermissions("files:nav:add")
    @Log(title = "导航网址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavWeb navWeb)
    {
        return toAjax(navWebService.insertNavWeb(navWeb));
    }

    /**
     * 修改导航网址
     */
    @RequiresPermissions("files:nav:edit")
    @Log(title = "导航网址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavWeb navWeb)
    {
        return toAjax(navWebService.updateNavWeb(navWeb));
    }

    /**
     * 删除导航网址
     */
    @RequiresPermissions("files:nav:remove")
    @Log(title = "导航网址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navWebService.deleteNavWebByIds(ids));
    }
}
