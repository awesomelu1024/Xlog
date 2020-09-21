package com.lx.blog.web.admin;

import com.lx.blog.po.Tag;
import com.lx.blog.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author: Lux
 * @Date 2020-06-16 22:24
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    //显示tag管理页面
    @GetMapping("/tags")
    public String tags(@PageableDefault(direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){

        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    //显示tag添加/编辑页面
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    //在新增页面中新增tag
    @PostMapping("/tags")
    public String post(@Valid Tag tag,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
        if(tagService.getTagByName(tag.getName())!=null){
            bindingResult.rejectValue("name","nameError","不能重复添加标签");
        }

        if(bindingResult.hasErrors()){
            return "admin/tags-input";
        }

        Tag t=tagService.saveTag(tag);
        if(t==null){
            redirectAttributes.addFlashAttribute("message","添加失败");
        }else{
            redirectAttributes.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/tags";
    }

    //显示tag编辑页面
    @GetMapping("/tags/{id}/input")
    public  String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    //在编辑页面中编辑标签
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,
                       BindingResult bindingResult,
                       @PathVariable Long id,
                       RedirectAttributes redirectAttributes){
        if(tagService.getTagByName(tag.getName())!=null){
            bindingResult.rejectValue("name","nameError","不能重复添加标签");
        }

        if(bindingResult.hasErrors()){
            return "admin/tags-input";
        }

        Tag t=tagService.updateTag(id,tag);
        if(t==null){
            redirectAttributes.addFlashAttribute("message","更新成功");
        }else{
            redirectAttributes.addFlashAttribute("message","更新失败");
        }

        return "redirect:/admin/tags";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        tagService.deletTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
