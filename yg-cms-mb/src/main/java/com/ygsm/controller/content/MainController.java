package com.ygsm.controller.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.ygsm.model.pojo.PageRelate;
import com.ygsm.model.pojo.Post;
import com.ygsm.model.pojo.WebPage;
import com.ygsm.service.CategoryService;
import com.ygsm.service.MenuService;
import com.ygsm.service.PostService;
import com.ygsm.service.WebPageService;

@Controller
public class MainController {

    private static final Integer categoryPostLimit1 = 5;
    private static final Integer categoryPostLimit2 = 20;
    private static final Integer relatePostLimit1 = 10;
    private static final Long homePageIndex = 1L;

    @Value("${app.theme}")
    private String theme;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private WebPageService webPageService;

    @ModelAttribute
    public void head(Model model) {
        model.addAttribute(TemplateConstant.MENU_LIST, menuService.findMenuNestList());// 目录列表
    }

    @GetMapping({ "/" })
    public String index(Model model) {
        return this.pageDetail(homePageIndex, model);
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        Post postDetail = postService.findOne(id);
        if (postDetail == null) {
            return theme + "/404";
        }
        Category categoryDetail = categoryService.findOne(postDetail.getCategoryId());
        this.addParentCategoryAndCategoryList(categoryDetail, model);
        model.addAttribute(TemplateConstant.POST_DETAIL, postDetail);// 文章详情
        return theme + "/post";
    }

    @GetMapping("/page/{id}")
    public String pageDetail(@PathVariable Long id, Model model) {
        WebPage webPage = webPageService.findOne(id);
        if (webPage == null) {
            return theme + "/404";
        }
        List<PageRelate> pageRelateList = webPageService.findRelates(id);
        for (PageRelate pageRelate : pageRelateList) {
            // 关联分类
            if (PageRelate.RelateType.CATEGORY.getType() == pageRelate.getRelateType()) {
                Integer categoryId = pageRelate.getRelateId().intValue();
                CategroyPostDTO categoryPostDTO = postService.findCategoryPostList(categoryId, relatePostLimit1, false);
                model.addAttribute(TemplateConstant.RELATE_CATEGORY + categoryId, categoryPostDTO);// 相关分类文章列表
            }
        }
        model.addAttribute(TemplateConstant.WEBPAGE_DETAIL, webPage); // 页面详情
        if (webPage.getTemplate() == null || webPage.getTemplate().isEmpty()) {
            return theme + "/page";
        } else {
            return theme + "/page_" + webPage.getTemplate();
        }
    }

    @GetMapping("/category/{id}")
    public String categoryDetail(@PathVariable Integer id, Model model, Integer page) {
        Category categoryDetail = categoryService.findOne(id);
        if (categoryDetail == null) {
            return theme + "/404";
        }
        List<Category> categoryList = categoryService.findChildrenList(id);
        if (categoryList != null && !categoryList.isEmpty()) {
            //有子分类
            List<CategroyPostDTO> categroyPostDTOList = new ArrayList<>();
            categoryList.forEach(category -> {
                CategroyPostDTO categroyPostDTO = postService.findCategoryPostList(category.getId(), categoryPostLimit1,
                        false);
                categroyPostDTOList.add(categroyPostDTO);
            });
            model.addAttribute(TemplateConstant.CATEGORY_POST_LIST, categroyPostDTOList);// 子分类文章列表
            this.addParentCategoryAndCategoryList(categoryDetail, model);
            return theme + "/category";
        }
        //无子分类
        if (page == null) {
            page = 1;
        }
        PageInfo<Post> postPage = postService.findCategoryPostPage(id, new PageObject(page, categoryPostLimit2));
        model.addAttribute(TemplateConstant.POST_PAGE, postPage);// 分类文章列表
        this.addParentCategoryAndCategoryList(categoryDetail, model);
        return theme + "/category2";

    }

    // 添加各级分类列表,同级分类列表
    private void addParentCategoryAndCategoryList(Category category, Model model) {
        List<Category> categoryList = null;
        List<Category> parentCategoryList = null;
        if (category != null) {
            Integer parentId = category.getParentId();
            parentCategoryList = categoryService.findParentList(parentId);
            categoryList = categoryService.findChildrenList(parentId);
        }
        model.addAttribute(TemplateConstant.CATEGORY_DETAIL, category);// 分类详情
        model.addAttribute(TemplateConstant.CATEGORY_PARENT_LIST, parentCategoryList);// 各级分类列表
        model.addAttribute(TemplateConstant.CATEGORY_LIST, categoryList);// 同级分类列表
    }

}