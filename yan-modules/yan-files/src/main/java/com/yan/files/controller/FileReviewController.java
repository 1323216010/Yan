package com.yan.files.controller;


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.yan.common.core.utils.ServletUtils;
import com.yan.common.core.utils.ip.IpUtils;
import com.yan.files.utils.StaticGetPrivate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.yan.common.log.annotation.Log;
import com.yan.common.log.enums.BusinessType;
import com.yan.common.security.annotation.RequiresPermissions;
import com.yan.files.domain.FileReview;
import com.yan.files.service.IFileReviewService;
import com.yan.common.core.web.controller.BaseController;
import com.yan.common.core.web.domain.AjaxResult;
import com.yan.common.core.utils.poi.ExcelUtil;
import com.yan.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${amap.Key}")
    private String key;

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

    @ApiOperation("文件上传")
    @PostMapping("/fileUpload")
    public AjaxResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        return toAjax(fileReviewService.fileUpload(file, httpServletRequest));
    }


    @GetMapping(value = "/weather")
    public AjaxResult getWeather(HttpServletRequest request)
    {
        Map<String,String> map = new HashMap();
        map.put("Key",key);
        map.put("ip", IpUtils.getIpAddr(ServletUtils.getRequest()));
        JSONObject jsonObject = StaticGetPrivate.getResTemplate().getForObject("https://restapi.amap.com/v3/ip?ip={ip}&Key={Key}", JSONObject.class, map);
        String adcode = jsonObject.getString("adcode");

        map.put("city",adcode);
        jsonObject = StaticGetPrivate.getResTemplate().getForObject("https://restapi.amap.com/v3/weather/weatherInfo?city={city}&Key={Key}", JSONObject.class, map);
        JSONArray lives = jsonObject.getJSONArray("lives");
        return success(lives);
    }

    /**
     * 通用下载请求
     *
     * @param filePath 文件路径
     */
    @GetMapping("/download")
    public void fileDownload(@RequestParam("fileName")String fileName, @RequestParam("filePath")String filePath, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            filePath = filePath.replace("_", File.separator) + fileName;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            StringBuilder contentDispositionValue = new StringBuilder();
            contentDispositionValue.append("attachment; filename=")
                    .append(fileName)
                    .append(";")
                    .append("filename*=")
                    .append("utf-8''")
                    .append(fileName);

            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
            response.setHeader("Content-disposition", contentDispositionValue.toString());
            response.setHeader("download-filename", fileName);
            writeBytes(filePath, response.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os 输出流
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }
}
