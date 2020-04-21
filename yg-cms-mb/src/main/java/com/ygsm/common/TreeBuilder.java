package com.ygsm.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**构造树形结构*/
public class TreeBuilder<T extends TreeBuilder.TreeNode<T, ID>, ID extends Serializable> {
    
    /**树形结构*/
    public interface TreeNode<T, ID extends Serializable> extends Comparable<TreeNode<T, ID>>{
        
        /**设置子节点*/
        void setChildren(Collection<T> children);
        /**节点id*/
        ID getId();
        /**父节点id*/
        ID getParentId();
        /**显示顺序*/
        ID getOrder();
    }

    private Collection<T> nodes = new ArrayList<T>();

    public TreeBuilder(Collection<T> nodes) {
        this.nodes= nodes;
    }

    /**构建树形结构 */
    public Collection<T> buildTree() {
        Collection<T> treeNodes = new ArrayList<T>();
        Collection<T> rootNodes = getRootNodes();
        for (T rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**获取父节点下所有的子节点*/
    private Collection<T> getChildNodes(T pnode) {
        Collection<T> childNodes = new ArrayList<T>();
        for (T n : nodes){
            if (pnode.getId().equals(n.getParentId())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /** 判断是否为根节点 */
    private boolean rootNode(T node) {
        boolean isRootNode = true;
        for (T n : nodes){
            if (n.getId().equals(node.getParentId())) {
                isRootNode= false;
                break;
            }
        }
        return isRootNode;
    }

    /** 获取集合中所有的根节点 */
    private Collection<T> getRootNodes() {
        List<T> rootNodes = new ArrayList<T>();
        for (T n : nodes){
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        Collections.sort(rootNodes);
        return rootNodes;
    }

    /** 递归子节点 */
    private void buildChildNodes(T node) {
        List<T> children =  new ArrayList<>(getChildNodes(node));
        Collections.sort(children);
        if (!children.isEmpty()) {
            for(T child : children) {
                buildChildNodes(child);
            } 
            node.setChildren(children);
        }
    }

}