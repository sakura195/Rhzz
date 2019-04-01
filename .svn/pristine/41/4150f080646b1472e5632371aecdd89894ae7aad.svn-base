package com.bky.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.bky.model.Add;
@SuppressWarnings("rawtypes")
public interface AddMapper {
	int deleteByPrimaryKey(String id);

	int insert(Add record);

	int insertSelective(Add record);

	Add selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Add record);

	int updateByPrimaryKey(Add record);

	List getAll(String username);

	Map queryID(String name);

	List queryUserStatus(String id);

	Integer updateUserById(String number, String qx, String ID);

	List selectUserByGroup(String num1, String num2, String num3);

	List selectUserALL(Map map);

	Integer updateUserByGroup(String s1, String s2);

	Map queryPasswordByName(String name);

	Map QueryUserInfo(String name);

	List queryPackagePojiByRow(Integer num);

	List queryUNdealPackage(Integer num);

	Integer updateUndealPackage(String id);

	Map queryUnpackageByNo(String no);

	String queryPackageNo();
String selectname();
	String querylastPackageNo(String no);

	List querymaterialquality();

	Integer shitangdiaocha(String myd, String jy, Date time);

	Integer insertpackage(String a, String b, String c, String d, String e, String f, String g, Date h, String i,
			String j, String k, String l, String m, String n, String o, String pString, String q, String r, String s,
			String t, String u, String v, String wString, String x, String yString, String z, String aa, String ab,
			String ac, String ad, String ae);

	Integer insertdetailPackage(String a, String b, String c, String d, String e, String f, String g, String h,
			String i, String j, String k, String l);

	Integer insertjiaokou(String a, String b, String c, String d, String e);

	String selectlastNo();

	Integer deletegyzb(String no);

	Integer deletegygj(String no);

	List queryAlluserinfo();

	List querypackage(String no);

	Integer updatepackage(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8,
			String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16, String a17,
			String a18, String a19, String a20, String a21, String a22, String a23, String a24, String a25, String a26);

	// Integer updatepackage(String a1,String a2,String a3,String a4,String
	// a5,String a6,String a7,String a8,String a9,String a10,String a11,String
	// a12);
	// Integer updatepackage(String a1,String a2,String a3,String a4,String a5);
	List querydetailPackage(String no);

	Integer updateDetailPackage(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8,
			String a9);

	Integer deleteDetailPackage(String no);

	Integer insertDetailPackage(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8,
			String a9, String a10, String a11);

	Integer updatePackageWight(Double a, String b, String c);

	Integer updatePackageWight2(Double a, String b, String c);
	String  queryPicupload(String aString);
	Integer updatePicname(String name,String no);
	Integer updatetcnbbh(String s1,String s2);
	Integer insertRemind (String s1,String s2,String s3);
	Integer begin();
	Integer commit();
	Integer rollback();
	List selectNBBH();
	List queryBoard(String s1,String s2);
	Map queryFaceUserInfo(String s1);
}