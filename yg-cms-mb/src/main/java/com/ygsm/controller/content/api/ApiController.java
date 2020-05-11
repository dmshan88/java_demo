package com.ygsm.controller.content.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.ygsm.common.ExcelAnalysisEventListener;
import com.ygsm.dao.CategoryDAO;
import com.ygsm.dao.MenuDAO;
import com.ygsm.dao.PostDAO;
import com.ygsm.dao.WebPageDAO;
import com.ygsm.model.excel.CategoryExcel;
import com.ygsm.model.excel.MenuExcel;
import com.ygsm.model.excel.PostExcel;
import com.ygsm.model.excel.WebPageExcel;
import com.ygsm.model.pojo.Category;
import com.ygsm.model.pojo.Menu;
import com.ygsm.model.pojo.Post;
import com.ygsm.model.pojo.WebPage;
import com.ygsm.util.BeanUtil;

@RequestMapping("/api")
@RestController
public class ApiController {
    
    @Autowired
    private PostDAO postDAO;
    
    @Autowired
    private WebPageDAO webPageDAO;
    
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Autowired
    private MenuDAO menuDAO;
    
    @GetMapping(path = "/get-post/{id}", produces = "application/json;charset=UTF-8")
    public Post getPost(@PathVariable Long id) {
        Post post = postDAO.findById(id);
        return post;
    }
    
//    @PostMapping("/create-post")
//    public void createPost(PostCreateForm postCreateForm) {
//        Post post = new Post();
//        BeanUtil.copyProperties(postCreateForm, post);
//        postDAO.insert(post);
//    }
    
//    @PostMapping("/create-category")
//    public void createCategory(CategoryCreateForm categoryCreateForm) {
//        Category category = new Category();
//        BeanUtil.copyProperties(categoryCreateForm, category);
//        categoryDAO.insert(category);
//    }
    
//    @PostMapping("/create-menu")
//    public void createMenu(MenuAddForm categoryCreateForm) {
//        Menu menu = new Menu();
//        BeanUtil.copyProperties(categoryCreateForm, menu);
//        menuDAO.insert(menu);
//    }
    
    @PostMapping(value = "/import-category")
    public String importCategory(@RequestParam("file") MultipartFile file) {
        ExcelAnalysisEventListener<CategoryExcel> listener = new ExcelAnalysisEventListener<>();
        try {
            EasyExcel.read(file.getInputStream(), CategoryExcel.class, listener).sheet().doRead();
        } catch (IOException e) {
            return e.getMessage();
        }
        List<CategoryExcel> categoryExcellist = listener.getList();
        List<Category> categoryList = new ArrayList<>();
        categoryExcellist.forEach(obj -> {
            Category category = new Category();
            BeanUtil.copyProperties(obj, category);
            if (category.getTemplate() == null) {
                category.setTemplate("");
            }
            categoryList.add(category);
        });
        categoryList.sort(new Comparator<Category>() {

            @Override
            public int compare(Category o1, Category o2) {
                int parentCompare = o1.getParentId() - o2.getParentId();
                return parentCompare == 0 ? o1.getId() - o2.getId() : parentCompare;
            }
        });
        categoryDAO.batchInsert(categoryList);
        return "ok";

    }
    
    @PostMapping(value = "/import-menu")
    public String importMenu(@RequestParam("file") MultipartFile file) {
        ExcelAnalysisEventListener<MenuExcel> listener = new ExcelAnalysisEventListener<>();
        try {
            EasyExcel.read(file.getInputStream(), MenuExcel.class, listener).sheet().doRead();
        } catch (IOException e) {
            return e.getMessage();
        }
        List<MenuExcel> menuExcellist = listener.getList();
        List<Menu> menuList = new ArrayList<>();
        menuExcellist.forEach(obj -> {
            Menu menu = new Menu();
            BeanUtil.copyProperties(obj, menu);
            if (menu.getPriority() == null) {
                menu.setPriority(0);
            }
            menuList.add(menu);
        });
        menuList.sort(new Comparator<Menu>() {

            @Override
            public int compare(Menu o1, Menu o2) {
                int parentCompare = o1.getParentId() - o2.getParentId();
                return parentCompare == 0 ? o1.getId() - o2.getId() : parentCompare;
            }
        });
        menuDAO.batchInsert(menuList);
        return "ok";
    }
    
    @PostMapping(value = "/import-post")
    public String importPost(@RequestParam("file") MultipartFile file) {
        ExcelAnalysisEventListener<PostExcel> listener = new ExcelAnalysisEventListener<>();
        try {
            EasyExcel.read(file.getInputStream(), PostExcel.class, listener).sheet().doRead();
        } catch (IOException e) {
            return e.getMessage();
        }
        List<PostExcel> postExcellist = listener.getList();
        List<Post> postList = new ArrayList<>();
        postExcellist.forEach(obj -> {
            Post post = new Post();
            BeanUtil.copyProperties(obj, post);
            if(post.getSummary() == null) {
                post.setSummary("");
            }
            if(post.getThumbnail() == null) {
                post.setThumbnail("");
            }
            postList.add(post);
        });
        postDAO.batchInsert(postList);
        return "ok";
    }
    
    @PostMapping(value = "/import-page")
    public String importPage(@RequestParam("file") MultipartFile file) {
        ExcelAnalysisEventListener<WebPageExcel> listener = new ExcelAnalysisEventListener<>();
        try {
            EasyExcel.read(file.getInputStream(), WebPageExcel.class, listener).sheet().doRead();
        } catch (IOException e) {
            return e.getMessage();
        }
        List<WebPageExcel> postExcellist = listener.getList();
        List<WebPage> pageList = new ArrayList<>();
        postExcellist.forEach(obj -> {
            WebPage post = new WebPage();
            BeanUtil.copyProperties(obj, post);
            pageList.add(post);
        });
        webPageDAO.batchInsert(pageList);
        return "ok";
    }
}
