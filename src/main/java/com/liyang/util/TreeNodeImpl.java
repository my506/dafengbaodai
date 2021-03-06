package com.liyang.util;

import java.util.List;

/**
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class TreeNodeImpl<T extends TreeNode> implements TreeNode<T>, Comparable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	String label;
	String href;
	Integer sort = 0;
	T parent;
	String iconClass;
	String router;
	List<T> children;
	Integer parentId;
	String entityName;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getRouter() {
		return router;
	}

	public void setRouter(String router) {
		this.router = router;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public Integer getSort() {
		return sort;
	}

	@Override
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public T getParent() {
		return parent;
	}

	@Override
	public void setParent(T parent) {
		this.parent = parent;
	}

	@Override
	public List<T> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List<T> children) {
		this.children = children;
	}

	@Override
	public Integer getParentId() {
		return parentId == null ? 0 : parentId;
	}

	@Override
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public int compareTo(T o) {
		if (getSort() > o.getSort()) {
			return 1;
		} else if (getSort().equals(o.getSort())) {
			return 0;
		} else {
			return -1;
		}
	}

}
