package com.bky.model;

public class User {

	private  String 用户ID;
	private String 登陆名;
	private String 登陆密码;
	private String 对应工号;
	private String 用户权限;
	private String 用户组;
	private String 当前查询权限位;
	public String get当前查询权限位() {
		return 当前查询权限位;
	}
	public void set当前查询权限位(String 当前查询权限位) {
		this.当前查询权限位 = 当前查询权限位;
	}
	public String get用户ID() {
		return 用户ID;
	}
	public void set用户ID(String 用户id) {
		用户ID = 用户id;
	}
	public String get登陆名() {
		return 登陆名;
	}
	public void set登陆名(String 登陆名) {
		this.登陆名 = 登陆名;
	}
	public String get登陆密码() {
		return 登陆密码;
	}
	public void set登陆密码(String 登陆密码) {
		this.登陆密码 = 登陆密码;
	}
	public String get对应工号() {
		return 对应工号;
	}
	public void set对应工号(String 对应工号) {
		this.对应工号 = 对应工号;
	}
	public String get用户权限() {
		return 用户权限;
	}
	public void set用户权限(String 用户权限) {
		this.用户权限 = 用户权限;
	}
	public String get用户组() {
		return 用户组;
	}
	public void set用户组(String 用户组) {
		this.用户组 = 用户组;
	}
	@Override
	public String toString() {
		return "User [用户ID=" + 用户ID + ", 登陆名=" + 登陆名 + ", 登陆密码=" + 登陆密码 + ", 对应工号=" + 对应工号 + ", 当前查询权限位=" + 当前查询权限位 + ", 用户组="
				+ 用户组 + "]";
	}


}
