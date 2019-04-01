package com.bky.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.print.DocFlavor.STRING;

import org.apache.ibatis.jdbc.Null;
import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.bky.dao.AddMapper;
import com.bky.model.Add;
import com.bky.model.JiaoKou;
import com.bky.model.Mokuai;
import com.bky.model.PackageDetailPojo;
import com.bky.model.PackagePojo;
import com.bky.model.User;
import com.bky.service.BaseService;
import com.bky.util.Util;

@Service("baseService")
@EnableScheduling
public class BaseServiceImpl implements BaseService {

	private AddMapper addMapper;

	public AddMapper getAddMapper() {
		return addMapper;
	}

	@Autowired
	public void setAddMapper(AddMapper addMapper) {
		this.addMapper = addMapper;
	}

	@Override
	public String addInfo(Add addInfo) {
		if (addMapper.insertSelective(addInfo) == 1) {
			return "添加成功";
		}
		return "添加失败";
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Add findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Add addInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ----------------------------------------我是分割线----------------------------
	 * ---------
	 */
	@Override
	public List getAll(String username) {
		List alList = new ArrayList();
		alList = addMapper.getAll(username);
		for (int i = 0; i < alList.size(); i++) {
			Mokuai mokuai = (Mokuai) alList.get(i);
			System.out.println(mokuai.toString());

		}
		return alList;
	}

	@Override
	public Map queryModId(String name) {
		Map map = new HashMap();
		map = (Map) addMapper.queryID(name);
		if (Util.isNullOrEmpty(map)) {
			map = new HashMap();
			map.put("flag", true);

		} else {
			map.put("flag", false);
		}
		return map;
	}

	@Override
	public List queryUserStatus(String ID) {
		List alList = new ArrayList();
		alList = addMapper.queryUserStatus(ID);
		/*
		 * for(int i=0;i<alList.size();i++) {User user=(User) alList.get(i);
		 * System.out.println(user.toString()); }
		 */
		return alList;
	}

	@Override
	public String UpdateUserByID(String num, String qx, String ID) {
		String s1 = addMapper.updateUserById(num, qx, ID).toString();
		return s1;
	}

	@Override
	public List selectUserByGroup(String num, String num1, String num2) {
		List alList = new ArrayList();
		alList = addMapper.selectUserByGroup(num, num1, num2);
		/*
		 * for(int i=0;i<alList.size();i++) {
		 * System.out.println(alList.get(i).toString()); Map map =new HashMap();
		 * }
		 */
		return alList;
	}

	@Override
	public List selectUserALL(String num, String num2) {
		List alList = new ArrayList();
		Map map = new HashMap();
		map.put("qx", num2);
		map.put("weizhi", num);
		alList = addMapper.selectUserALL(map);
		return alList;
	}

	@Override
	public Integer updateUserByGroup(List list) {
		int q = 0;
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String s1 = map.get("用户权限").toString();
			String s2 = map.get("用户ID").toString();

			addMapper.updateUserByGroup(s1, s2);
			q++;
		}
		return q;
	}

	@Override
	public String queryPasswordByName(String name) {
		String password = null;
		Map map = addMapper.queryPasswordByName(name);
		if (!Util.isNullOrEmpty(map)) {
			if (!Util.isNullOrEmpty(map.get("登陆密码"))) {
				password = map.get("登陆密码").toString();
			}
		}
		return password;
	}

	@Override
	public Map queryUserInfo(String name) {
		Map map = new HashMap();
		map = addMapper.QueryUserInfo(name);
		return map;
	}

	@Override
	public List queryPackagepojo(Integer num) {
		List alList=null;
		try {
		alList = new ArrayList();
		alList = addMapper.queryPackagePojiByRow(num);
		
	} catch (Exception e) {
		
	}
		return alList;
	}

	@Override
	public List queryUndealPackage(Integer num) {
		List aList = new ArrayList();
		aList = addMapper.queryUNdealPackage(num);
		/*
		 * for(int i=0;i<aList.size();i++) {
		 * System.out.println(aList.toString()); }
		 */
		return aList;
	}

	@Override
	public Integer UpdateUnpackage(String id) {
		Integer aInteger = addMapper.updateUndealPackage(id);
		return aInteger;
	}

	@Override
	public Map queryUndealPackageByNo(String name,String packageNo) {
		Map map = new HashMap();
		map = addMapper.queryUnpackageByNo(name);
		map.put("排箱号细则",packageNo);
		return map;
	}

	@Override
	public String queryPackageNo() {
		String s1 = addMapper.queryPackageNo();
		String s2 = null;
		String s4 = s1.substring(0, 6);
		s1 = s1.substring(6, 8);
		Integer s3 = Integer.parseInt(s1);
		s3++;
		if (s3 < 10) {
			s2 = "0" + s3;
		}else{s2=s3+"";
			}
		String re = s4 + s2 + "";
		return re;
	}
@Override
	public List querymaterialquality(){
	List list=addMapper.querymaterialquality();
	List list2=new ArrayList();
for(int i=0;i<list.size();i++){
	if(!Util.isNullOrEmpty(list.get(i)))
	{
		list2.add(list.get(i).toString().trim());
	}
}
		return list2;
	}
	
	
	@Override
	public String querylastPackageNo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date day = new Date();
		Integer month = Integer.parseInt(df.format(day).substring(4, 6));
		Integer year = Integer.parseInt(df.format(day).substring(0, 4));
		Integer date = Integer.parseInt(df.format(day).substring(6, 8));
		if (date > 25) {
			month = month + 1;
		}
		if (month > 12) {
			month = 1;
			year++;
		}
		String s3 = null;
		switch (month) {
		case 1:
			s3 = year + "A";
			break;
		case 2:
			s3 = year + "B";
			break;
		case 3:
			s3 = year + "C";
			break;
		case 4:
			s3 = year + "D";
			break;
		case 5:
			s3 = year + "E";
			break;
		case 6:
			s3 = year + "F";
			break;
		case 7:
			s3 = year + "G";
			break;
		case 8:
			s3 = year + "H";
			break;
		case 9:
			s3 = year + "J";
			break;
		case 10:
			s3 = year + "K";
			break;
		case 11:
			s3 = year + "L";
			break;
		case 12:
			s3 = year + "M";
			break;

		}
		String re = addMapper.querylastPackageNo(s3);
		Integer number;
		String result = null;
		if (!Util.isNullOrEmpty(re)) {
			number = Integer.parseInt(re.substring(5)) + 1;
		} else {
			number = 1;
		}
		if (number < 10) {
			result = s3 + "00" + number;
		}
		if (number < 100 && number >= 10) {
			result = s3 + "0" + number;
		}
		if (number >= 100) {
			result = s3 + number;
		}
		return result;
	}
	@Override
	public Integer insertStxx(String a,String b,Date cDate) {
		Integer s=addMapper.shitangdiaocha(a, b, cDate);
		System.out.println(s);
	return s;
	
	}

	@Override
	public Integer insertPackage(List a, List b, PackagePojo p) {
		//a ,订单子表        b浇注子表       p 排箱主表    顺序  p-a-b
		System.out.println(p.get浇注时间1());
		System.out.println(p.get浇注时间());
		Integer sInteger=addMapper.insertpackage(p.get工艺单编号(),p.get浇注温度(),p.get备注(),p.get浇注时间(),p.get是否入库质检(),p.get材质(),p.get技术员(),p.get制定时间(),p.get是否合箱检验(),p.get砂箱(),p.get图片(),p.get浇注总重(),p.get材质1(),p.get浇注总重1(),p.get浇注系统比例1(),p.get浇注系统比例(),p.get浇注系统方式1(),p.get出气口(),p.get出气口1(),p.get锅台(),p.get锅台1(),p.get浇注系统方式(),p.get排箱号1(),p.get排箱号(),p.get砂箱1(),p.get浇注温度1(),p.get浇注时间1(),p.get拉延模(),p.get拉延模1(),p.get浇注系统说明(),p.get浇注系统说明1());
		Integer sinteger2=0;
		Integer sInteger3=0;
		PackageDetailPojo pdp;
		for(int i=0;i<a.size();i++){
			pdp=(PackageDetailPojo) a.get(i);
			sinteger2=sinteger2+addMapper.insertdetailPackage(pdp.get工艺单细则号(),pdp.get铸件编号(),pdp.get工艺单编号(),pdp.get材质(),pdp.get单重(),pdp.get内浇口数量(),pdp.get订单细则号(),pdp.get排箱号(),pdp.get备注(),pdp.get密度比(),pdp.get内部编号(),pdp.get客户简称());
		}
		//Integer number=Integer.parseInt(addMapper.selectlastNo());
		for(int i=0;i<b.size();i++){
			JiaoKou j=(JiaoKou) b.get(i);
			sInteger3=addMapper.insertjiaokou(j.get类型(),j.get规格(),j.get数量(),j.get排箱号(),j.get工艺单编号());				
	}	
		return sInteger+sinteger2+sInteger3;
}

	@Override
	public Integer deletegydinfo(String no) {
		if(!Util.isNullOrEmpty(no)){
			Integer s1=addMapper.deletegyzb(no.trim());
			Integer s2=addMapper.deletegygj(no.trim());	
		return s1+s2;
		}else{	
			return 0;
		}		
	}
	@Override
	public List queryAllUserInfo() {
		List list=new ArrayList();
		list=addMapper.queryAlluserinfo();
		return list;
	}

	@Override
	public List querypackage(String no) {
		List list=new ArrayList();
		list=addMapper.querypackage(no);
		return list;
	}

	@Override
	public Integer updatepackage(PackagePojo p) {
	Integer aInteger=addMapper.updatepackage(p.get备注(),p.get排箱号(),p.get材质(),p.get砂箱(),p.get浇注温度(),p.get浇注时间(),p.get浇注总重(),p.get浇注系统方式(),p.get锅台(),p.get出气口(),p.get浇注系统说明1(),p.get拉延模(),p.get排箱号1(),p.get材质1(),p.get砂箱1(),p.get浇注温度1(),p.get浇注时间1(),p.get浇注总重1(),p.get浇注系统方式1(),p.get锅台1(),p.get出气口1(),p.get拉延模1(), p.get浇注系统比例(),p.get浇注系统比例1(),p.get浇注系统说明(),p.get工艺单编号());
	

		return aInteger;
	}
	@Override
	public List querydetailPackage(String  no) {
	List ArrayList=addMapper.querydetailPackage(no);
		return ArrayList;
	}

	@Override
	public Integer updateDetailpackage(PackageDetailPojo p) {
		Integer integer=addMapper.updateDetailPackage(p.get材质(),p.get单重(),p.get内浇口数量(),p.get排箱号(),p.get备注(),p.get密度比(),p.get内部编号(),p.get客户简称(),p.get工艺单细则号());
		return integer;
	}
	@Override
	public Integer deleteDetailPojo(String p) {
		Integer s1=addMapper.deleteDetailPackage(p);
		return s1;
}
	
	@Override
	public Integer insertPacakgedetail(PackageDetailPojo p) {
		Integer integer=addMapper.insertDetailPackage(p.get材质(),p.get单重(),p.get内浇口数量(),p.get排箱号(),p.get备注(),p.get密度比(),p.get内部编号(),p.get客户简称(),p.get工艺单细则号(),p.get铸件编号(),p.get工艺单编号());
		return integer;
}
	
	@Override
	public Integer updatePackagetimeandweight(int a,double b,String c,String d) {
		Integer integer=null;
		if(a==1){
			integer=addMapper.updatePackageWight(b, c,d);
		}else{
			integer=addMapper.updatePackageWight2(b, c,d);
		}
		return integer;
}
	@Override
	public String queryPicupload(String a){
		String  upload=addMapper.queryPicupload(a);
		
		return upload;
	}

	@Override
	public Integer updatePicName(String name, String no) {
		return addMapper.updatePicname(name, no);
		
	}

	@Override
	public Integer updatetcnbbh(String s1, String s2) {
		return addMapper.updatetcnbbh(s1, s2);
	}

	@Override
	public Integer insertnbbhbg(String s1, String s2, String s3) {
		return addMapper.insertRemind(s1, s2, s3);
	}

	@Override
	public Integer begin() {
		addMapper.begin();
		return null;
	}

	@Override
	public Integer commit() {
		// TODO Auto-generated method stub
		addMapper.commit();
		return null;
	}

	@Override
	public Integer rollback() {
		// TODO Auto-generated method stub
		addMapper.rollback();
		return null;
	}

	@Override
	public List selectNBBH() {
		// TODO Auto-generated method stub
		return	addMapper.selectNBBH();
		
	}
	@Override
	public List queryBoard(String s1,String s2){
		String time[]=s1.split("-");
		String year=time[0];
		String month=time[1];
		String day=time[2];	
		return addMapper.queryBoard( s1, s2);
		
	}

	@Override
	public Map queryFaceInfo(String s1) {
		Map map=null;
		map=addMapper.queryFaceUserInfo(s1);
		return map;
	}
	
	
/*	@Override
	@Scheduled(cron="0/40 * * * * ?")
	public void test1(){


	
	
	
	
	
	}*/


}
