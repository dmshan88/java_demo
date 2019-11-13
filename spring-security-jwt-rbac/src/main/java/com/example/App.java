package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.utils.TreeBuilder;
import com.example.utils.TreeBuilder.TreeNode;

import lombok.Data;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class App {
    

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        @Data
        class MyNode implements TreeNode<MyNode, Integer> {
            Integer id;
            Integer parentId;
            private Collection<MyNode> children;
            
            public MyNode(Integer id, Integer parentId) {
                this.id = id; this.parentId = parentId;
            }
            @Override
            public void setChildren(Collection<MyNode> children) {
                this.children = children;
            }

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public Integer getParentId() {
                return parentId;
            }
            
        }
        List<MyNode> list = new ArrayList<MyNode>();
        list.add(new MyNode(1,null));
        list.add(new MyNode(2,null));
        list.add(new MyNode(3,1));
        TreeBuilder<MyNode, Integer> builder = new TreeBuilder<MyNode, Integer>(list);
        System.out.println(list);
        System.out.println(builder.buildTree());
    }

}