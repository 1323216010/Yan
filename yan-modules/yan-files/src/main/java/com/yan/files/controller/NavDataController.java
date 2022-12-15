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
import com.yan.files.domain.NavData;
import com.yan.files.service.INavDataService;
import com.yan.common.core.web.controller.BaseController;
import com.yan.common.core.web.domain.AjaxResult;
import com.yan.common.core.utils.poi.ExcelUtil;

/**
 * 导航树Controller
 * 
 * @author yan
 * @date 2022-12-15
 */
@RestController
@RequestMapping("/nav")
public class NavDataController extends BaseController
{
    @Autowired
    private INavDataService navDataService;

    /**
     * 查询导航树列表
     */
    @RequiresPermissions("files:nav:list")
    @GetMapping("/list")
    public AjaxResult list(NavData navData)
    {
        List<NavData> list = navDataService.selectNavDataList(navData);
        return success(list);
    }

    /**
     * 导出导航树列表
     */
    @RequiresPermissions("files:nav:export")
    @Log(title = "导航树", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavData navData)
    {
        List<NavData> list = navDataService.selectNavDataList(navData);
        ExcelUtil<NavData> util = new ExcelUtil<NavData>(NavData.class);
        util.exportExcel(response, list, "导航树数据");
    }

    /**
     * 获取导航树详细信息
     */
    @RequiresPermissions("files:nav:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(navDataService.selectNavDataById(id));
    }

    /**
     * 新增导航树
     */
    @RequiresPermissions("files:nav:add")
    @Log(title = "导航树", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavData navData)
    {
        return toAjax(navDataService.insertNavData(navData));
    }

    /**
     * 修改导航树
     */
    @RequiresPermissions("files:nav:edit")
    @Log(title = "导航树", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavData navData)
    {
        return toAjax(navDataService.updateNavData(navData));
    }

    /**
     * 删除导航树
     */
    @RequiresPermissions("files:nav:remove")
    @Log(title = "导航树", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navDataService.deleteNavDataByIds(ids));
    }
}
