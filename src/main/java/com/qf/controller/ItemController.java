package com.qf.controller;


import com.qf.pojo.Item;
import com.qf.pojo.PageInfo;
import com.qf.service.ItemService;
import com.qf.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.qf.constant.SsmConstant.*;

/**
 * Administrator 2019/7/16 0016 11:12
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
//    http://localhost/item/list

    @GetMapping("list")
    public String list(String name,
                       @RequestParam(value = "page",defaultValue = "1")Integer page,
                       @RequestParam(value = "size",defaultValue = "5")Integer size,
                       Model model){
      PageInfo<Item> pageInfo= itemService.findItremByNameAndLimit(name,page,size);
      model.addAttribute("pageInfo",pageInfo);
      model.addAttribute("name",name);
      return "item/item_list";
    }

    @GetMapping("/add-ui")
    public String addUI(String addInfo,Model model){
        model.addAttribute("addInfo",addInfo);
        return "item/item_add";
    }

    @Value("${pic_type}")
    public String picType;

    @PostMapping("/add")
    public String add(@Valid Item item,
                      BindingResult bindingResult,
                      MultipartFile picFile,
                      HttpServletRequest request,
                      RedirectAttributes redirectAttributes) throws IOException {
        //校验参数
        if(bindingResult.hasErrors()){
            String msg=bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("addInfo",msg);
            return REDIRECT+ITEM_ADD_UI;
        }
        //保存商品信息


        //对图片大小左校验
        Long size=picFile.getSize();
        if (size>(5*1024*1024L)){
            redirectAttributes.addAttribute("addInfo","图片过大,要求小于5M");
            return REDIRECT+ITEM_ADD_UI;
        }
        boolean flag=false;
        //对图片的类型做校验
        String[] types=picType.split(",");
        for (String type : types) {
            if(StringUtils.endsWithIgnoreCase(picFile.getOriginalFilename(),type)){
                //图片类型正确
                flag=true;
                break;
            }
        }
        if (!flag){
            redirectAttributes.addAttribute("addInfo","图片类型不正确,要求"+picType);
            return REDIRECT+ITEM_ADD_UI;
        }
        //检验图片是否损坏
        BufferedImage image= ImageIO.read(picFile.getInputStream());
        if(image==null){
            //图片已经损坏
            redirectAttributes.addAttribute("addInfo","图片已经损坏");
            return REDIRECT+ITEM_ADD_UI;
        }
        //将图片保存到本地
        //起个新名字
        String prefix= UUID.randomUUID().toString();
        String suffix= StringUtils.substringAfterLast(picFile.getOriginalFilename(),".");
        String newName=prefix+"."+suffix;
        //保存到本地
        String path=request.getServletContext().getRealPath("")+"\\static\\img\\"+newName;
        File file = new File(path);
        if(file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        picFile.transferTo(file);
        //封装图片的访问路径
        String pic="http://localhost/static/img/" + newName;
        item.setPic(pic);
        Integer count=itemService.save(item);

        if(count==1){
            return REDIRECT+"/item/list";
        }else {
            redirectAttributes.addAttribute("addInfo","添加商品信息失败!!!");
            return REDIRECT+ITEM_ADD_UI;
        }
    }
    /*  Request URL: http://localhost/item/13
        Request Method: DELETE
    */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultVo del(@PathVariable Long id){
        Integer count= itemService.del(id);

        if(count==1){
            return new ResultVo(0,"msg",null);
        }else {
            return new ResultVo(5,"msg",null);
        }
    }
    @GetMapping("/update-ui")
    public String updateUI(Long id,Model model){
        model.addAttribute( "id",id);
        return "item/item_update";
    }
    @PostMapping("/update")
    public String update(@Valid Item item,
                         Long id,
                         BindingResult bindingResult,
                         MultipartFile picFile,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes) throws IOException {
        //校验参数
        if(bindingResult.hasErrors()){
            String msg=bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("addInfo",msg);
            return REDIRECT+ITEM_UPDATE_UI;
        }
        //对图片大小校验
        Long size=picFile.getSize();
        if (size>(5*1024*1024L)){
            redirectAttributes.addAttribute("addInfo","图片过大,要求小于5M");
            return REDIRECT+ITEM_UPDATE_UI;
        }
        boolean flag=false;
        //对图片的类型做校验
        String[] types=picType.split(",");
        for (String type : types) {
            if(StringUtils.endsWithIgnoreCase(picFile.getOriginalFilename(),type)){
                //图片类型正确
                flag=true;
                break;
            }
        }
        if (!flag){
            redirectAttributes.addAttribute("addInfo","图片类型不正确,要求"+picType);
            return REDIRECT+ITEM_UPDATE_UI;
        }
        //检验图片是否损坏
        BufferedImage image= ImageIO.read(picFile.getInputStream());
        if(image==null){
            //图片已经损坏
            redirectAttributes.addAttribute("addInfo","图片已经损坏");
            return REDIRECT+ITEM_UPDATE_UI;
        }
        //将图片保存到本地
        //起个新名字
        String prefix= UUID.randomUUID().toString();
        String suffix= StringUtils.substringAfterLast(picFile.getOriginalFilename(),".");
        String newName=prefix+"."+suffix;
        //保存到本地
        String path=request.getServletContext().getRealPath("")+"\\static\\img\\"+newName;
        File file = new File(path);
        if(file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        picFile.transferTo(file);
        //封装图片的访问路径
        String pic="http://localhost/static/img/" + newName;
        item.setPic(pic);
        Integer count=itemService.update(item);

        if(count==1){
            return REDIRECT+ITEM_LIST;
        }else {
            redirectAttributes.addAttribute("addInfo","修改商品信息失败!!!");
            return REDIRECT+ITEM_UPDATE_UI;
        }

    }
}
