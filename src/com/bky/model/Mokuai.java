package com.bky.model;

import org.apache.ibatis.io.ResolverUtil.IsA;

public class Mokuai {

	private String 子模块;
	private String 中级模块;
	private String 顶级模块;
	private String dwobject;
	private String selectsql;
	public String get子模块() {
		return 子模块;
	}
	public void set子模块(String 子模块) {
		this.子模块 = 子模块;
	}
	public String get中级模块() {
		return 中级模块;
	}
	public void set中级模块(String 中级模块) {
		this.中级模块 = 中级模块;
	}
	public String get顶级模块() {
		return 顶级模块;
	}
	public void set顶级模块(String 顶级模块) {
		this.顶级模块 = 顶级模块;
	}
	public String getDwobject() {
		return dwobject;
	}
	public void setDwobject(String dwobject) {
		this.dwobject = dwobject;
	}
	public String getSelectsql() {
		return selectsql;
	}
	public void setSelectsql(String selectsql) {
		this.selectsql = selectsql;
	}
	@Override
	public String toString() {
		return "Mokuai [子模块=" + 子模块 + ", 中级模块=" + 中级模块 + ", 顶级模块=" + 顶级模块 + ", dwobject=" + dwobject + ", selectsql="
				+ selectsql + "]";
	}

	
	
}
