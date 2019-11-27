package com.nf.spring.controller;

import com.github.pagehelper.PageInfo;
import com.nf.spring.entity.UserInfo;
import com.nf.spring.service.UserInfoService;
import com.nf.spring.vo.ResponseVo;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nf.spring.vo.UserInfoVo;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * @author Sam
 */
@Controller
public class IndexController extends BaseController  {
    /**
     * 获取spring mvc生成的容器
     */
    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/index")
    public String indexView(){
        return "index";
    }
    /**
     * RequestMapping表示在url输入的地址。
     * @return
     */
    @PostMapping(value = "/index")
    @ResponseBody
    public PageInfo<UserInfo> index (@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "2") int pageSize){
        List<UserInfo> userInfos = userInfoService.getAll(pageNum,pageSize);
        PageInfo<UserInfo> userInfoPageInfo = new PageInfo<>(userInfos,3);
        return userInfoPageInfo;
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseVo insert(@Valid UserInfoVo userInfoVo,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        ResponseVo responseVo = null;
        UserInfo userInfo = new UserInfo();

        //将校验完成的数据添加到实体类中
        BeanUtils.copyProperties(userInfoVo,userInfo);
        Integer pageNum = userInfoVo.getPageNum()==null?1:userInfoVo.getPageNum();
        if (bindingResult.hasErrors()){
//            this.fillFieldError(bindingResult,redirectAttributes);
//            redirectAttributes.addFlashAttribute("userInfo",userInfo);

//            throw new ValidationException("数据验证失败");
            responseVo = new ResponseVo("500","添加失败",bindingResult.getFieldErrors());
        }else{
            redirectAttributes.addFlashAttribute("userInfo",new UserInfo());
            userInfoService.insert(userInfo);
            responseVo = new ResponseVo("200", "添加成功", pageNum);
        }
        return responseVo;
    }



    @PostMapping("/upload")
    public String upload(MultipartFile[] myFiles, HttpServletRequest request) throws IOException {
        for (MultipartFile myFile : myFiles) {
            if(myFile.getSize()>0){
                //获取保存上传文件的file路径
                String path = request.getServletContext().getRealPath("images");
                //获取上传的文件名
                String name = myFile.getOriginalFilename();
                File file = new File(path,name);
                myFile.transferTo(file);
            }
        }
        return "redirect:index";
    }



    @GetMapping("/uploadView")
    public String  uploadView(){
        return "upload";
    }


    //下载
    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> download(String fileName, HttpServletRequest request) throws IOException {
        //1.读取文件
        String path = request.getServletContext().getRealPath("images")+File.separator+fileName;

        File file = new File(path);
        //2.根据方法得到对应的媒体类型，也就是mime类型
        //比如image/jpeg
        String mediaType = URLConnection.guessContentTypeFromName(fileName);
        if(mediaType==null){
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        //3.设置请求体
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mediaType));
        //文件名
        String name = UUID.randomUUID().toString()+
                fileName.substring(fileName.lastIndexOf("."));
        //4.编码处理
        headers.setContentDispositionFormData("attachment",
                URLEncoder.encode(name,"UTF-8"));

        //5.获得一个输入流
        InputStreamResource isr =
                new InputStreamResource(new FileInputStream(file));
        return new ResponseEntity<>(isr,headers, HttpStatus.OK);

    }
}
