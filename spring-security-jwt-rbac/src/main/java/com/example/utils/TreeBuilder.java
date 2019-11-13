package com.example.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class TreeBuilder<T1 extends TreeBuilder.TreeNode<T1, T2>, T2 extends Serializable> {
    
    public interface TreeNode<T1, T2 extends Serializable> {
        void setChildren(Collection<T1> children);
        T2 getId();
        T2 getParentId();
    }

    private Collection<T1> nodes = new ArrayList<T1>();

    public TreeBuilder(Collection<T1> nodes) {
        this.nodes= nodes;
    }

    /**构建树形结构 */
    public Collection<T1> buildTree() {
        Collection<T1> treeNodes = new ArrayList<T1>();
        Collection<T1> rootNodes = getRootNodes();
        for (T1 rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**获取父节点下所有的子节点*/
    public Collection<T1> getChildNodes(T1 pnode) {
        Collection<T1> childNodes = new ArrayList<T1>();
        for (T1 n : nodes){
            if (pnode.getId().equals(n.getParentId())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /** 判断是否为根节点 */
    public boolean rootNode(T1 node) {
        boolean isRootNode = true;
        for (T1 n : nodes){
            if (n.getId().equals(node.getParentId())) {
                isRootNode= false;
                break;
            }
        }
        return isRootNode;
    }

    /** 获取集合中所有的根节点 */
    public Collection<T1> getRootNodes() {
        Collection<T1> rootNodes = new ArrayList<T1>();
        for (T1 n : nodes){
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

    /** 递归子节点 */
    public void buildChildNodes(T1 node) {
        Collection<T1> children = getChildNodes(node); 
        if (!children.isEmpty()) {
            for(T1 child : children) {
                buildChildNodes(child);
            } 
            node.setChildren(children);
        }
    }

}
