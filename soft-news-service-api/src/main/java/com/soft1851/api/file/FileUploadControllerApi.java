package com.soft1851.api.file;

import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileUploadControllerApi
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/19
 **/
@Api(value = "文件上传Controller",tags = "{文件上传Controller}")
@RequestMapping("fs")
public interface FileUploadControllerApi {
    /**
     * 上传用户头像
     * @param userId 用户Id
     * @param file 文件对象
     * @return 封装结果
     * @throws Exception 异常
     */
    @ApiOperation(value = "上传用户头像",notes = "上传用户头像",httpMethod = "POST")
    @PostMapping("uploadFace")
    GraceResult uploadFace(@RequestParam String userId, MultipartFile file) throws  Exception;

    /**
     * 上传多个文件夹
     * @param userId 用户id
     * @param files 文件数组
     * @return 返回
     * @throws Exception 异常
     */
    @PostMapping("/uploadSomeFiles")
    GraceResult uploadSomeFiles(@RequestParam String userId,
                                MultipartFile[] files) throws Exception;
}
