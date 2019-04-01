package com.bky.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bky.model.Add;
import com.bky.model.Mokuai;
import com.bky.model.PackageDetailPojo;
import com.bky.model.PackagePojo;

public interface BaseService {
	
	String addInfo(Add addInfo);
	
	List getAll(String username);
	
	String delete(String id);
	
	Add findById(String id);
	String queryPasswordByName(String name);
	String update(Add addInfo);
	Map queryModId(String name);
	List queryUserStatus (String ID);
	String UpdateUserByID(String num,String qx,String ID);
	List selectUserByGroup(String num,String num2,String num3);
	List selectUserALL(String num,String num2);
	Integer updateUserByGroup(List list);
	Map queryUserInfo(String name);
	List queryPackagepojo(Integer num);
	List queryUndealPackage(Integer num);
	Integer UpdateUnpackage(String id);
	Map queryUndealPackageByNo(String name,String b);
	String queryPackageNo();
	String querylastPackageNo();
	List querymaterialquality();
	Integer insertStxx(String a,String b,Date cDate);
	Integer insertPackage(List a,List b,PackagePojo p);
	Integer deletegydinfo(String no);
	List queryAllUserInfo();
	List querypackage(String no); 
	Integer updatepackage(PackagePojo packagePojo);
	List querydetailPackage(String no);
	Integer updateDetailpackage(PackageDetailPojo packageDetailPojo);
	Integer deleteDetailPojo(String no);
	Integer insertPacakgedetail(PackageDetailPojo pak);
	Integer updatePackagetimeandweight(int a,double b,String c,String d);
	String queryPicupload(String a);
	Integer updatePicName(String name,String no);
	Integer updatetcnbbh(String s1,String s2);
	Integer insertnbbhbg(String s1,String s2,String s3);
	Integer 	begin();
	Integer commit();
	Integer rollback();
	List selectNBBH();
	List queryBoard(String s1,String s2);
	/*void test1();*/
	Map queryFaceInfo(String s1);
}
