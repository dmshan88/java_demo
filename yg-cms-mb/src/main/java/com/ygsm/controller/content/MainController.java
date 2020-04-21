package com.ygsm.controller.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.pagehelper.PageInfo;
import com.ygsm.common.PageObject;
import com.ygsm.constants.TemplateConstant;
import com.ygsm.model.dto.CategroyPostDTO;
import com.ygsm.model.pojo.Category;
import com.ygsm.model.pojo.Post;
import com.ygsm.service.CategoryService;
import com.ygsm.service.MenuService;
import com.ygsm.service.PostService;

@Controller
public class MainController {
    
    private static final Integer categoryPostLimit1 = 5;
    private static final Integer categoryPostLimit2= 20;
    
    @Value("${app.theme}")
    private String theme;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private MenuService menuService;
    
    @ModelAttribute
    public void head(Model model) {
        model.addAttribute(TemplateConstant.MENU_LIST, menuService.findMenuNestList());
    }
    
    @GetMapping({"/"})
    public String index(Model model) {
        List<Category> categoryList = categoryService.findAll();
        
        Map<String, CategroyPostDTO> categroyPostDTOMap = new HashMap<>();
        categoryList.forEach(category -> {
            CategroyPostDTO categroyPostDTO = postService.findCategoryPostList(category.getId(), categoryPostLimit1, true);
            categroyPostDTOMap.put(String.valueOf(category.getId()) , categroyPostDTO);
        });
//        categroyPostDTOMap.forEach(( key, value) -> {System.out.print(key);System.out.println(value);});
//        System.out.println("size:" + categroyPostDTOMap.size());
        model.addAttribute(TemplateConstant.CATEGORY_POST_MAP, categroyPostDTOMap);
        return theme + "/index";
    }
    
    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        Post postDetail = postService.findOne(id);
        if (postDetail == null) {
            return theme + "/404";
        }
        Category categoryDetail = categoryService.findOne(postDetail.getCategoryId());
        if (categoryDetail == null) {
            return theme + "/404";
        }
        if (categoryDetail.getParentId() != 0) {
            Category parentCategory = categoryService.findOne(categoryDetail.getParentId());
            if (parentCategory == null) {
                return theme + "/404";
            }
            List<Category> categoryList = categoryService.findChildrenList(categoryDetail.getParentId());
            model.addAttribute(TemplateConstant.CATEGORY_PARENT_DETAIL, parentCategory);
            model.addAttribute(TemplateConstant.CATEGORY_LIST, categoryList);
        }
        model.addAttribute(TemplateConstant.CATEGORY_DETAIL, categoryDetail);
        model.addAttribute(TemplateConstant.POST_DETAIL, postDetail);
        return theme + "/post";
    }
    
    @GetMapping("/category/{id}")
    public String categoryDetail(@PathVariable Integer id, Model model, Integer page) {
        Category categoryDetail = categoryService.findOne(id);
        if (categoryDetail == null) {
            return theme + "/404";
        }
        if (categoryDetail.getParentId() == 0) {
            //一级分类
            List<Category> categoryList = categoryService.findChildrenList(id);
            List<CategroyPostDTO> categroyPostDTOList = new ArrayList<>();
            categoryList.forEach(category -> {
                CategroyPostDTO categroyPostDTO = postService.findCategoryPostList(category.getId(), categoryPostLimit1, false);
                categroyPostDTOList.add(categroyPostDTO);
            });
            
//            model.addAttribute(TemplateConstant.CATEGORY_LIST, categoryList);
            model.addAttribute(TemplateConstant.CATEGORY_POST_LIST, categroyPostDTOList);
            model.addAttribute(TemplateConstant.CATEGORY_DETAIL, categoryDetail);
            return theme + "/category";
        } else {
            //二级分类
            Category parentCategory = categoryService.findOne(categoryDetail.getParentId());
            if (parentCategory == null) {
                return theme + "/404";
            }
            List<Category> categoryList = categoryService.findChildrenList(categoryDetail.getParentId());
            if (page == null) {
                page = 1;
            }
            PageInfo<Post> postPage = postService.findCategoryPostPage(id, new PageObject(page, categoryPostLimit2));
            model.addAttribute(TemplateConstant.CATEGORY_DETAIL, categoryDetail);
            model.addAttribute(TemplateConstant.CATEGORY_PARENT_DETAIL, parentCategory);
            model.addAttribute(TemplateConstant.CATEGORY_LIST, categoryList);
            model.addAttribute(TemplateConstant.POST_PAGE, postPage);
            return theme + "/category2";
        }
    }
    
    
}