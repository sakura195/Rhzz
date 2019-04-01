package com.bky.model;

public class JiaoKou {
    private String 序号;
    private String 类型;
    private String 类别;
    private String 规格;
    private String 数量;
    private String 排箱号;
    private String 订单细则号;
    private String 工艺单编号;
    private String 备注;
	public String get序号() {
		return 序号;
	}
	public void set序号(String 序号) {
		this.序号 = 序号;
	}
	public String get类型() {
		return 类型;
	}
	public void set类型(String 类型) {
		this.类型 = 类型;
	}
	public String get类别() {
		return 类别;
	}
	public void set类别(String 类别) {
		this.类别 = 类别;
	}
	public String get规格() {
		return 规格;
	}
	public void set规格(String 规格) {
		this.规格 = 规格;
	}
	public String get数量() {
		return 数量;
	}
	public void set数量(String 数量) {
		this.数量 = 数量;
	}
	public String get排箱号() {
		return 排箱号;
	}
	public void set排箱号(String 排箱号) {
		this.排箱号 = 排箱号;
	}
	public String get订单细则号() {
		return 订单细则号;
	}
	public void set订单细则号(String 订单细则号) {
		this.订单细则号 = 订单细则号;
	}
	public String get工艺单编号() {
		return 工艺单编号;
	}
	public void set工艺单编号(String 工艺单编号) {
		this.工艺单编号 = 工艺单编号;
	}
	public String get备注() {
		return 备注;
	}
	public void set备注(String 备注) {
		this.备注 = 备注;
	}
	@Override
	public String toString() {
		return "JiaoKou [序号=" + 序号 + ", 类型=" + 类型 + ", 类别=" + 类别 + ", 规格=" + 规格 + ", 数量=" + 数量 + ", 排箱号=" + 排箱号
				+ ", 订单细则号=" + 订单细则号 + ", 工艺单编号=" + 工艺单编号 + ", 备注=" + 备注 + "]";
	}
    

}
