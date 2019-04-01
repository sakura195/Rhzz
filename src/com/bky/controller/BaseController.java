package com.bky.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.ibatis.mapping.ResultMap;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.junit.runner.Request;
import org.opencv.osgi.OpenCVNativeLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import com.alibaba.druid.pool.GetConnectionTimeoutException;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.druid.sql.visitor.functions.Trim;
import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bky.Dict.Dict;
import com.bky.dao.AddMapper;
import com.bky.model.Add;
import com.bky.model.JiaoKou;
import com.bky.model.Mokuai;
import com.bky.model.PackageDetailPojo;
import com.bky.model.PackagePojo;
import com.bky.model.User;
import com.bky.service.BaseService;
import com.bky.service.impl.BaseServiceImpl;
import com.bky.util.Util;
import com.bky.util.GsonUtils;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.hp.hpl.sparta.Document;
import com.jspsmart.upload.SmartUpload;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.UnconditionalBranch;
import com.sun.tools.internal.xjc.api.Property;

import sun.awt.image.FileImageSource;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServlet;

@Controller
public class BaseController extends HttpServlet {

	/*
	 * 含有AJAX的需要返回的在HTTP中加上response，跳转的只需要request
	 * 
	 */
	private AddMapper addMapper;

	public AddMapper getAddMapper() {
		return addMapper;
	}

	private BaseService baseService;

	public BaseService getBaseService() {
		return baseService;
	}

	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	@SuppressWarnings("finally")
	@RequestMapping("addInfo")
	public String add(Add add, HttpServletRequest request) {
		try {
			add.setId(UUID.randomUUID().toString());
			System.out.println(add.getId() + ":::::" + add.getTname() + ":::::" + add.getTpwd());
			String str = baseService.addInfo(add);
			System.out.println(str);
			request.setAttribute("InfoMessage", str);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "添加信息失败！具体异常信息：" + e.getMessage());
		} finally {
			return "result";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("del")
	public String del(String tid, HttpServletRequest request) {
		try {
			String str = baseService.delete(tid);
			request.setAttribute("InfoMessage", str);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "删除信息失败！具体异常信息：" + e.getMessage());
		} finally {
			return "result";
		}
	}

	@RequestMapping("modify")
	public String modify(String tid, HttpServletRequest request) {
		try {
			Add add = baseService.findById(tid);
			request.setAttribute("add", add);
			return "modify";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("update")
	public String update(Add add, HttpServletRequest request) {
		try {
			String str = baseService.update(add);
			request.setAttribute("InfoMessage", str);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "更新信息失败！具体异常信息：" + e.getMessage());
		} finally {
			return "result";
		}
	}
	/*-----------------------------------------------------------------------------------------------
	*/

	@SuppressWarnings("finally")
	@RequestMapping("mokuai")
	public String querymokuai(HttpServletRequest request) {
		try {
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return "WEB-INF/zh_CN/mokuai";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("jurisdiction")
	public String jurisdiction(HttpServletRequest request) {
		try {
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return "WEB-INF/zh_CN/Showjurisdiction";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/mokuaiquery", produces = "application/json")
	@ResponseBody
	public String mokuaiquery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List date = new ArrayList();
		response.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("queryname");
			date = baseService.getAll(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			String s1 = JSON.toJSONString(date, true);
			// 解决json前端乱码问题
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(s1);
			return null;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/ChangJurisdic", produces = "application/json")
	public String jurisdiction1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ID = "未查询到相关信息";
		boolean flag = false;
		Map map = null;
		try {
			String name = request.getParameter("Mname");
			map = baseService.queryModId(name);
			flag = (Boolean) map.get("flag");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ID = JSON.toJSONString(map, true);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(ID);

			return null;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/queryAllUserStatus", produces = "application/json")
	public String queryAllUserStatus(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("changeID");
		List date = new ArrayList();
		date = baseService.queryUserStatus(ID);
		request.setAttribute("LoopResult", date);
		request.setAttribute("number", ID);
		return "WEB-INF/zh_CN/ShowUserInfo";

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/UpdateUserByID", produces = "application/json")
	public String UpdateUserById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ID = request.getParameter("用户编号");// 用户ID
		String qx = request.getParameter("变更权限位");// 变更权限 1：管理 2：录入：3 ：浏览 0：无权限
		String number = request.getParameter("number");// 变更位置
		String result = null;
		result = baseService.UpdateUserByID(number, qx, ID);
		result = JSON.toJSONString(result, true);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
		return null;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/UpdateUserByGroup", produces = "application/json")
	public String UpdateUserByGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String Character = request.getParameter("Character");
		String Group = request.getParameter("Group");
		String number = request.getParameter("number");
		List list = null;
		list = baseService.selectUserByGroup(number, Character, Group);
		int q = baseService.updateUserByGroup(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSON.toJSONString(q));
		return null;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/UpdateAllUser", produces = "application/json")
	public String UpdateAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String Character = request.getParameter("Character");
		String number = request.getParameter("number");
		List list = null;
		list = baseService.selectUserALL(number, Character);
		int q = baseService.updateUserByGroup(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSON.toJSONString(q));
		return null;
	}

	/*
	 * 登出
	 * 
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/Loginout", produces = "application/json")

	public String Loginout(HttpServletRequest request, HttpServletResponse response,
			@CookieValue("userid") Cookie cookie) throws IOException {
		cookie.setMaxAge(0);
		cookie.setPath("/"); // 路径一定要写上，不然干不掉的
		response.addCookie(cookie);
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}

	/*
	 * 登陆
	 * 
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/login", produces = "application/json")
	public String Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String Username = request.getParameter("Username");
		String password = request.getParameter("Password");
		Username = Util.Unicode2GBK(Username);
		if (!Util.isNullOrEmpty(password)) {
			if (!password.equals("123")) {
				password = Util.MD5(password);
			}
			if (password.equals(baseService.queryPasswordByName(Username))) {
				Map map = baseService.queryUserInfo(Username);
				System.out.println(map);

				// 设置格式
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods", "POST");
				response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				User user = new User();
				user.set登陆名(map.get("登陆名").toString().trim());
				user.set对应工号(map.get("对应工号").toString());/* 签名使用保存 */
				user.set用户ID(map.get("用户ID").toString());
				user.set用户组(map.get("用户组").toString());
				HttpSession Session = request.getSession();
				Session.setAttribute(map.get("对应工号").toString(), user);
				Session.setMaxInactiveInterval(30 * 60);
				Cookie uid = new Cookie("userid", map.get("对应工号").toString());
				response.addCookie(uid);
				// 读取用户所有对应用户名/签名数据

				return "index";
			} else {
				request.setAttribute("ERRORLOG", "用户名密码不匹配，请重新输入");
				return "login";
			}
		} else {
			return "login";
		}

	}
/**
 * 人脸识别登陆
 * */
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/login2", produces = "application/json")
	public String Login2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user_id = request.getParameter("user_id");
		Map map2=baseService.queryFaceInfo(user_id);
		String Username=map2.get("登陆名").toString();
				Map map = baseService.queryUserInfo(Username);
				System.out.println(map);

				// 设置格式
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods", "POST");
				response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				User user = new User();
				user.set登陆名(map.get("登陆名").toString().trim());
				user.set对应工号(map.get("对应工号").toString());/* 签名使用保存 */
				user.set用户ID(map.get("用户ID").toString());
				user.set用户组(map.get("用户组").toString());
				HttpSession Session = request.getSession();
				Session.setAttribute(map.get("对应工号").toString(), user);
				Session.setMaxInactiveInterval(30 * 60);
				Cookie uid = new Cookie("userid", map.get("对应工号").toString());
				response.addCookie(uid);
				// 读取用户所有对应用户名/签名数据

				return "index";
			
		

	}
	/*
	 * 登陆检查
	 */
	private boolean isLogin(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("utf-8");
		boolean flag = false;
		// 获取request里面的cookie cookie里面存值方式也是 键值对的方式
		Cookie[] cookie = request.getCookies();
		String uid = null;
		if (!Util.isNullOrEmpty(cookie)) {
			for (int i = 0; i < cookie.length; i++) {
				Cookie cook = cookie[i];
				if (cook.getName().equalsIgnoreCase("userid")) { // 获取键
					uid = cook.getValue().toString(); // 获取值
					break;
				}
			}
		}
		if (Util.isNullOrEmpty(uid)) {
			return false;
		} else {
			HttpSession Session = request.getSession(true);
			if (!Util.isNullOrEmpty(Session.getAttribute(uid))) {
				User user = (User) Session.getAttribute(uid);
				request.setAttribute("uid", uid);
				request.setAttribute("uname", user.get登陆名());
				System.out.println(user.get登陆名());
				return true;
			} else {
				return false;
			}
		}

	}

	/* 工艺排箱跳转界面整合 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/Packingprocess", produces = "application/json")
	public String Packingprocess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (isLogin(request)) {
			String Flag = request.getParameter("Flag");
			ResultMap map = null;
			if (Util.isNullOrEmpty(Flag)) {
				Flag = "1";
			}

			Integer pageNum = turnPage(request);

			/**
			 * Flag1 查询（分页）
			 */
			if (Flag.equals("1")) {

				List date = new ArrayList();
				try {
					date = baseService.queryPackagepojo(pageNum);
					checkEndPage(pageNum, date, request);

				} catch (ParserException e) {
				}

				request.setAttribute("LoopResult", date);
			}

			/**
			 * Flag2 查询（单条）
			 */

			/**
			 * Flag3查询（单条）
			 */

			/**
			 * Flag3查询（新建）
			 */

			/**
			 * Flag3查询（删除）
			 */
			return "WEB-INF/zh_CN/PackageMENU";
		} else {
			return "login";
		}
	}

	/* 工艺排箱代办区数据 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/dealwithPackage", produces = "application/json")
	public String dealwithPackage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (isLogin(request)) {
			request.setAttribute("packageUnwait", request.getParameter("packageUnwait"));
			request.setAttribute("packageUnwait2", request.getParameter("packageUnwait2"));
			Integer pageNum = turnPage(request);
			List loopresult = baseService.queryUndealPackage(pageNum);
			checkEndPage(pageNum, loopresult, request);
			request.setAttribute("LoopResult", loopresult);
			return "WEB-INF/zh_CN/UndealPackage";
		} else {
			return "login";
		}
	}

	/*
	 * request,翻页数值
	 */
	private Integer turnPage(HttpServletRequest request) {
		Integer coverPage;
		/* 从界面获取翻页数量，如果数量不存在默认15，放入request中(放入后无法拿到？) */
		if (!Util.isNullOrEmpty(request.getParameter("coverPage"))) {
			coverPage = Integer.parseInt(request.getParameter("coverPage").toString());
		} else {
			coverPage = 15;
		}
		Integer pageNum;
		String nextorback = request.getParameter("nextorback");
		if (Util.isNullOrEmpty(request.getParameter("page"))) {
			pageNum = 0;
		} else {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		if (!Util.isNullOrEmpty(nextorback)) {
			if (nextorback.equals("1")) {
				pageNum = pageNum + coverPage;
			} else {
				if (pageNum >= coverPage) {
					pageNum = pageNum - coverPage;
				}
			}
		}
		request.setAttribute("pageNum", pageNum);
		return pageNum;
	}

	/* 检查是否翻页到末页 */
	private void checkEndPage(Integer a, List list, HttpServletRequest request) {
		Integer coverPage = null;
		if (!Util.isNullOrEmpty(request.getParameter("coverPage"))) {
			coverPage = Integer.parseInt(request.getParameter("coverPage"));
		} else {
			coverPage = 15;
		}
		if (list.size() < coverPage) {
			if (a - coverPage > 0)/** 最后一页的情况 **/
			{
				request.setAttribute("pageNum", a - coverPage);
			} else {/** 第一页的情况 **/
				request.setAttribute("pageNum", 0);
			}
		}
	}

	/*
	 * 设置工艺排向无效数据
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/changeUnPackageDetail", produces = "application/json")
	private String changeUnPackage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Boolean flag = true;
		if (Util.isNullOrEmpty(request.getParameter("id"))) {
			flag = false;
		} else {
			String Id = request.getParameter("id").toString().trim();
			Integer result = baseService.UpdateUnpackage(Id);
			if (!result.equals(0)) {
				flag = true;
				Map map = new HashMap();
				map.put("Flag", flag);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(JSON.toJSONString(flag, true));
			}
		}

		return null;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/Packageable", produces = "application/json")
	private String Packageable(HttpServletRequest request) throws IOException {
		if (isLogin(request)) {
			if (Util.isNullOrEmpty(request.getParameter("packageUnwait"))
					&& Util.isNullOrEmpty(request.getParameter("packageUnwait2"))) {
				return "index";
			}
			String unpackage = request.getParameter("packageUnwait").toString().trim();
			String[] aStrings = unpackage.split(";");
			String unpackage2 = request.getParameter("packageUnwait2").toString().trim();
			String[] bStrings = unpackage2.split(";");
			List list = new ArrayList<String>();
			List list2 = new ArrayList();
			List list3 = new ArrayList();
			List list4 = new ArrayList();
			List list5 = new ArrayList();
			List list6 = new ArrayList();
			list3.add("Φ50");
			list3.add("Φ70");
			list3.add("Φ100");
			list3.add("Φ110");
			list3.add("Φ120");
			list3.add("Φ130");
			list3.add("Φ140");
			list3.add("Φ150");
			list3.add("Φ160");
			list4.add("Φ70");
			list4.add("80*40");
			list4.add("80*60");
			list4.add("80*80");
			list4.add("80*100");
			list4.add("80*120");
			list5.add("Φ50");
			list5.add("80*25");
			list5.add("80*30");
			String packageNo = baseService.querylastPackageNo();
			Set set = new HashSet();
			Set set2 = new HashSet();
			// 界面读取需要排箱的订单细则号
			for (int i = 0; i < aStrings.length; i++) {
				if (!Util.isNullOrEmpty(aStrings[i])) {
					set.add(aStrings[i].trim());
				}
			}
			for (int i = 0; i < bStrings.length; i++) {
				if (!Util.isNullOrEmpty(bStrings[i])) {
					set2.add(bStrings[i].trim());
				}
			}
			list6.addAll(set2);
			list.addAll(set);
			for (int i = 0; i < list.size(); i++) {
				list2.add(baseService.queryUndealPackageByNo(list.get(i).toString(), packageNo));
			}
			for (int i = 0; i < list6.size(); i++) {
				list2.add(baseService.queryUndealPackageByNo(list6.get(i).toString(), packageNo + "-1"));
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date day = new Date();
			String PackagelistNo = baseService.queryPackageNo();
			PackagelistNo = Util.getAlpha(request.getAttribute("uname").toString()) + PackagelistNo;

			request.setAttribute("PackagelistNo", PackagelistNo);// 编号
			request.setAttribute("PackageNo", packageNo);// 排箱号
			request.setAttribute("PackageNo1", packageNo + "-1");// 排箱号

			request.setAttribute("LoopResult", list2);// 查询到的子键信息
			request.setAttribute("listsize", list.size());// 查询到的子键信息
			request.setAttribute("qualitylist", baseService.querymaterialquality());
			request.setAttribute("nowtime", df.format(day).toString());
			request.setAttribute("guige1", list3);
			request.setAttribute("guige2", list4);
			request.setAttribute("guige3", list5);
			return "WEB-INF/zh_CN/Package";
		} else {
			return "login";
		}

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/stdc", produces = "application/json")
	private String stdc(HttpServletRequest request) throws IOException, ParseException {
		String 意见 = null;
		if (!Util.isNullOrEmpty(request.getParameter("jy"))) {
			意见 = request.getParameter("jy").toString();
		}
		String st = request.getParameter("nsq").toString();
		if (st.equals("1")) {
			st = "满意";
		} else {
			st = "不满意";
		}
		Date time = new java.sql.Date(new java.util.Date().getTime());
		baseService.insertStxx(st, 意见, time);
		return "result";
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/px", produces = "application/json")
	private String px(HttpServletRequest request) throws IOException, ParseException {
		if (isLogin(request)) {
			Double TotalWight = 0.0;
			String totaltime = null;
			Double TotalWight1 = 0.0;
			String totaltime1 = null;
			String 铸件编号[] = request.getParameterValues("铸件编号");
			String 材质[] = request.getParameterValues("材质3");
			String 单重[] = request.getParameterValues("单重");
			String 内浇口数量[] = request.getParameterValues("内浇口数量");
			String 订单细则号[] = request.getParameterValues("订单细则号");
			String 密度比[] = request.getParameterValues("密度比");
			String 内部编号[] = request.getParameterValues("内部编号");
			String 客户简称[] = request.getParameterValues("客户简称");
			String 工艺单编号 = request.getParameter("工艺单编号");
			String 排箱号细则[] = request.getParameterValues("排箱号细则");
			String 备注[] = request.getParameterValues("备注");
			List list = new ArrayList();
			// 是否所有的数据都要TRIM还有待协商(子表数据)
			for (int i = 0; i < 铸件编号.length; i++) {
				PackageDetailPojo pdp = new PackageDetailPojo();
				String dz = 单重[i];
				if (Util.isNullOrEmpty(dz)) {
					dz = "0";
				}
				String njksl = 内浇口数量[i];
				if (Util.isNullOrEmpty(njksl)) {
					njksl = "0";
				}
				pdp.set备注(备注[i]);
				pdp.set排箱号(排箱号细则[i]);
				pdp.set铸件编号(铸件编号[i].trim());
				pdp.set材质(材质[i].trim());
				pdp.set单重(dz.trim());
				pdp.set内浇口数量(njksl.trim());
				pdp.set订单细则号(订单细则号[i].trim());
				pdp.set密度比(密度比[i].trim());
				pdp.set内部编号(内部编号[i].trim());
				pdp.set客户简称(客户简称[i].trim());
				pdp.set工艺单编号(工艺单编号.trim());
				pdp.set工艺单细则号(request.getParameter("工艺单编号") + (i + 1));// 直接读取条数后+1
				int q = 排箱号细则[i].indexOf("-");
				if (q > 0) {
					TotalWight1 = TotalWight1 + Double.valueOf(密度比[i].trim()) * Double.valueOf(dz.trim());
				} else {
					TotalWight = TotalWight + Double.valueOf(密度比[i].trim()) * Double.valueOf(dz.trim());
				}
				list.add(pdp);
				// 订单子键
			}
			totaltime = calculatetime(TotalWight, totaltime);
			totaltime1 = calculatetime(TotalWight1, totaltime1);
			@SuppressWarnings("unused")
			Date dateTime;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			PackagePojo ppj = new PackagePojo();
			ppj.set是否入库质检("否");
			ppj.set是否合箱检验("N");
			ppj.set出气口(request.getParameter("出气口"));
			ppj.set制定时间(formatter.parse(request.getParameter("制定时间")));
			ppj.set技术员((String) request.getAttribute("uid"));
			ppj.set工艺单编号(request.getParameter("工艺单编号"));
			ppj.set评审员(request.getParameter("评审员"));
			// 评审时间
			ppj.set排箱号(request.getParameter("排箱号"));
			ppj.set拉延模(request.getParameter("拉延模"));
			ppj.set材质(request.getParameter("材质"));
			ppj.set浇注总重(TotalWight.toString());
			ppj.set砂箱(request.getParameter("沙箱"));
			ppj.set出气口(request.getParameter("出气口"));
			System.out.println(totaltime);
			ppj.set浇注时间(totaltime);
			ppj.set浇注温度(request.getParameter("浇注温度"));
			ppj.set浇注系统方式(request.getParameter("浇注系统方式"));
			ppj.set锅台(request.getParameter("锅台"));
			ppj.set浇注系统比例(request.getParameter("浇注系统比例"));
			ppj.set浇注系统说明(request.getParameter("浇注系统说明"));
			ppj.set图片(request.getParameter("图片"));
			ppj.set备注(request.getParameter("备注"));

			if (!Util.isNullOrEmpty(request.getParameter("排箱号1"))) {

				ppj.set排箱号1(request.getParameter("排箱号1"));
				ppj.set拉延模1(request.getParameter("拉延模1"));
				ppj.set材质1(request.getParameter("材质1"));
				ppj.set浇注总重1(TotalWight1.toString());
				ppj.set砂箱1(request.getParameter("沙箱1"));
				ppj.set出气口1(request.getParameter("出气口1"));
				System.out.println(totaltime1);
				ppj.set浇注时间1(totaltime1);
				ppj.set浇注温度1(request.getParameter("浇注温度1"));
				ppj.set浇注系统方式1(request.getParameter("浇注系统方式1"));
				ppj.set锅台1(request.getParameter("锅台1"));
				ppj.set浇注系统比例1(request.getParameter("浇注系统比例1"));
				ppj.set浇注系统说明1(request.getParameter("浇注系统说明1"));
				ppj.set出气口1(request.getParameter("出气口1"));

			}

			// 主表PPJ
			/*
			 * 浇口入库
			 */
			JiaoKou jiaoKou = null;
			JiaoKou jiaoKou2 = null;
			JiaoKou jiaoKou3 = null;
			JiaoKou jiaoKou1 = null;
			JiaoKou jiaoKou12 = null;
			JiaoKou jiaoKou13 = null;
			List list2 = new ArrayList();

			jiaoKou = new JiaoKou();
			jiaoKou.set工艺单编号(工艺单编号.trim());
			jiaoKou.set排箱号(request.getParameter("排箱号"));
			jiaoKou.set数量(request.getParameter("s1"));
			jiaoKou.set类型("直浇道");
			jiaoKou.set规格(request.getParameter("gg1"));
			list2.add(jiaoKou);
			jiaoKou2 = new JiaoKou();
			jiaoKou2.set工艺单编号(工艺单编号.trim());
			jiaoKou2.set排箱号(request.getParameter("排箱号"));
			jiaoKou2.set类型("横浇道");
			jiaoKou2.set规格(request.getParameter("gg2"));
			jiaoKou2.set数量(request.getParameter("s2"));
			list2.add(jiaoKou2);
			jiaoKou3 = new JiaoKou();
			jiaoKou3.set工艺单编号(工艺单编号.trim());
			jiaoKou3.set排箱号(request.getParameter("排箱号"));
			jiaoKou3.set类型("内浇口");
			jiaoKou3.set规格(request.getParameter("gg3"));
			jiaoKou3.set数量(request.getParameter("s3"));
			list2.add(jiaoKou3);
			if (!Util.isNullOrEmpty(request.getParameter("s4")) && (!request.getParameter("s4").equals("0"))) {
				JiaoKou jiaokou4 = new JiaoKou();
				jiaokou4.set工艺单编号(工艺单编号.trim());
				jiaokou4.set排箱号(request.getParameter("排箱号"));
				jiaokou4.set类型("内浇口");
				jiaokou4.set规格(request.getParameter("gg4"));
				jiaokou4.set数量(request.getParameter("s4"));
				list2.add(jiaokou4);
			}
			if (!Util.isNullOrEmpty(request.getParameter("sl11"))) {

				jiaoKou1 = new JiaoKou();
				jiaoKou1.set工艺单编号(工艺单编号.trim());
				jiaoKou1.set排箱号(request.getParameter("排箱号1"));
				jiaoKou1.set数量(request.getParameter("sl11"));
				jiaoKou1.set类型("直浇道");
				jiaoKou1.set规格(request.getParameter("gg11"));
				list2.add(jiaoKou1);
				jiaoKou12 = new JiaoKou();
				jiaoKou12.set工艺单编号(工艺单编号.trim());
				jiaoKou12.set排箱号(request.getParameter("排箱号1"));
				jiaoKou12.set类型("横浇道");
				jiaoKou12.set规格(request.getParameter("gg12"));
				jiaoKou12.set数量(request.getParameter("sl12"));
				list2.add(jiaoKou12);
				jiaoKou13 = new JiaoKou();
				jiaoKou13.set工艺单编号(工艺单编号.trim());
				jiaoKou13.set排箱号(request.getParameter("排箱号1"));
				jiaoKou13.set类型("内浇口");
				jiaoKou13.set规格(request.getParameter("gg13"));
				jiaoKou13.set数量(request.getParameter("sl13"));
				list2.add(jiaoKou13);
				if (!Util.isNullOrEmpty(request.getParameter("sl14")) && (!request.getParameter("sl14").equals("0"))) {
					JiaoKou jiaokou14 = new JiaoKou();
					jiaokou14.set工艺单编号(工艺单编号.trim());
					jiaokou14.set排箱号(request.getParameter("排箱号1"));
					jiaokou14.set类型("内浇口");
					jiaokou14.set规格(request.getParameter("gg14"));
					jiaokou14.set数量(request.getParameter("sl14"));
					list2.add(jiaokou14);
				}
				// 子键浇注接口list2

			}
			System.out.println(list2.size());
			/*
			 * for(int i=0;i<list.size();i++) { PackageDetailPojo
			 * packageDetailPojo=(PackageDetailPojo) list.get(i);
			 * System.out.println(packageDetailPojo.toString()); }
			 */
			baseService.insertPackage(list, list2, ppj);
			// 订单子表,浇注接口表,主表
			return "index";
		} else {
			return "login";
		}

	}

	// 浇注时长计算
	private String calculatetime(Double TotalWight, String totaltime) {
		if (TotalWight <= 2000 && TotalWight > 0) {
			totaltime = "25±10S";
		} else if (TotalWight > 2000 && TotalWight <= 3000) {
			totaltime = "30±10S";
		} else if (TotalWight > 3000 && TotalWight <= 5000) {
			totaltime = (int) (TotalWight / 70) + "±10S";
		} else if (TotalWight > 5000 && TotalWight <= 6000) {
			totaltime = (int) (TotalWight / 80) + "±10S";
		} else if (TotalWight > 6000 && TotalWight <= 11000) {
			totaltime = (int) (TotalWight / 100) + "±10S";
		} else if (TotalWight > 11000 && TotalWight <= 12000) {
			totaltime = (int) (TotalWight / 105) + "±10S";
		} else if (TotalWight > 12000 && TotalWight <= 13000) {
			totaltime = (int) (TotalWight / 110) + "±10S";
		} else if (TotalWight > 13000 && TotalWight <= 15000) {
			totaltime = (int) (TotalWight / 120) + "±10S";
		} else {
			totaltime = (int) (TotalWight / 180) + "±10S";
		}
		return totaltime;
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/deleteDetail", produces = "application/json")
	private void deleteDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		// 根据工艺单编号删除3张表单
		Integer s = baseService.deletegydinfo(request.getParameter("工艺单编号"));
		String s2;
		if (s.equals("0")) {
			s2 = "删除失败";
		} else {
			s2 = "删除成功";
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSON.toJSONString(s2, true));
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/choicedelete", produces = "application/json")
	private String choicedelete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {

		String no1 = request.getParameter("no");
		request.setAttribute("no", no1);
		return "WEB-INF/zh_CN/choicedelete";
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/changemainpackage", produces = "application/json")
	private String changemainpackage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		if (Util.isNullOrEmpty(request.getParameter("update"))) {
			String no1 = request.getParameter("no");
			List list = baseService.querypackage(no1);
			System.out.println(list);
			PackagePojo p = (PackagePojo) list.get(0);
			request.setAttribute("技术员", p.get技术员());
			request.setAttribute("制定时间", p.get制定时间());
			request.setAttribute("工艺单编号", p.get工艺单编号());
			request.setAttribute("评审员", p.get评审员());
			request.setAttribute("评审时间", p.get评审时间());
			request.setAttribute("排箱号", p.get排箱号());
			request.setAttribute("排箱号1", p.get排箱号1());
			request.setAttribute("拉延模", p.get拉延模());
			request.setAttribute("拉延模1", p.get拉延模1());
			request.setAttribute("材质", p.get材质());
			request.setAttribute("材质1", p.get材质1());
			request.setAttribute("浇注总重", p.get浇注总重());
			request.setAttribute("浇注总重1", p.get浇注总重1());
			request.setAttribute("砂箱", p.get砂箱());
			request.setAttribute("砂箱1", p.get砂箱1());
			if (!Util.isNullOrEmpty(p.get出气口())) {
				request.setAttribute("出气口", p.get出气口().trim());
			}
			if (!Util.isNullOrEmpty(p.get出气口1())) {
				request.setAttribute("出气口1", p.get出气口1().trim());
			}
			request.setAttribute("浇注时间", p.get浇注时间());
			request.setAttribute("浇注时间1", p.get浇注时间1());
			request.setAttribute("浇注温度", p.get浇注温度());
			request.setAttribute("浇注温度1", p.get浇注温度1());
			request.setAttribute("浇注系统方式", p.get浇注系统方式());
			request.setAttribute("浇注系统方式1", p.get浇注系统方式1());
			if (!Util.isNullOrEmpty(p.get锅台())) {
				request.setAttribute("锅台", p.get锅台().toString());
			} else {
				request.setAttribute("锅台", "否");
			}
			if (!Util.isNullOrEmpty(p.get锅台1())) {
				request.setAttribute("锅台1", p.get锅台1().toString());
			} else {
				request.setAttribute("锅台1", "否");
			}
			request.setAttribute("浇注系统比例", p.get浇注系统比例());
			request.setAttribute("浇注系统比例1", p.get浇注系统比例1());
			request.setAttribute("浇注系统说明", p.get浇注系统说明());
			request.setAttribute("浇注系统说明1", p.get浇注系统说明1());
			request.setAttribute("图片", p.get图片());
			request.setAttribute("备注", p.get备注());

			return "WEB-INF/zh_CN/showpackage";
		} else {
			PackagePojo p = new PackagePojo();
			p.set技术员(request.getParameter("技术员"));
			p.set工艺单编号(request.getParameter("工艺单编号").toString().trim());
			p.set评审员(request.getParameter("评审员"));
			p.set排箱号(request.getParameter("排箱号"));
			p.set排箱号1(request.getParameter("排箱号1"));
			p.set拉延模(request.getParameter("拉延模"));
			p.set拉延模1(request.getParameter("拉延模1"));
			p.set材质(request.getParameter("材质"));
			p.set材质1(request.getParameter("材质1"));
			p.set浇注总重(request.getParameter("浇注总重"));
			if (Util.isNullOrEmpty(request.getParameter("浇注总重1"))) {
				p.set浇注总重1("0");
			} else {
				p.set浇注总重1(request.getParameter("浇注总重1"));
			}
			p.set砂箱(request.getParameter("砂箱"));
			p.set砂箱1(request.getParameter("砂箱1"));
			p.set出气口(request.getParameter("出气口"));
			p.set出气口1(request.getParameter("出气口1"));
			p.set浇注时间(request.getParameter("浇注时间"));
			p.set浇注时间1(request.getParameter("浇注时间1"));
			p.set浇注温度(request.getParameter("浇注温度"));
			p.set浇注温度1(request.getParameter("浇注温度1"));
			p.set浇注系统方式(request.getParameter("浇注系统方式"));
			p.set浇注系统方式1(request.getParameter("浇注系统方式1"));
			p.set浇注系统比例(request.getParameter("浇注系统比例"));
			p.set浇注系统比例1(request.getParameter("浇注系统比例1"));
			p.set浇注系统说明(request.getParameter("浇注系统说明"));
			p.set浇注系统说明1(request.getParameter("浇注系统说明1"));
			p.set备注(request.getParameter("备注"));
			p.set锅台(request.getParameter("锅台"));
			p.set锅台1(request.getParameter("锅台1"));
			Integer aInteger = baseService.updatepackage(p);
			return "WEB-INF/zh_CN/success";
		}

	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/changedetailpackage", produces = "application/json")
	private String changedetailpackage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		// 需要查询两个条件 1、工艺单细则的数量（用户增加操作）
		// 2、工艺单的数据（展示）
		String packageNo = request.getParameter("no").toString();
		List LoopResult = baseService.querydetailPackage(packageNo);
		Integer listsize = LoopResult.size();
		request.setAttribute("LoopResult", LoopResult);
		request.setAttribute("listsize", listsize);
		return "WEB-INF/zh_CN/DetailPackage";

	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/detailPackagepool", produces = "application/json")
	private String detailPackagepool(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		// 需要查询两个条件 1、根据反映的标志位来判断是否进行删除\更新\插入操作，完成操作后重新刷新界面
		// 通过传入的编号来区别是去取哪一个的值 更新操作不对铸件编号进行更改
		String 工艺单编号[] = request.getParameterValues("工艺单编号");
		String 工艺细则编号[] = request.getParameterValues("工艺细则编号");
		String 材质[] = request.getParameterValues("材质");
		String 单重[] = request.getParameterValues("单重");
		String 内浇口数量[] = request.getParameterValues("内浇口数量");
		String 订单细则号[] = request.getParameterValues("订单细则号");
		String 密度比[] = request.getParameterValues("密度比");
		String 内部编号[] = request.getParameterValues("内部编号");
		String 客户简称[] = request.getParameterValues("客户简称");
		String 排箱号[] = request.getParameterValues("排箱号");
		String 备注[] = request.getParameterValues("备注");
		PackageDetailPojo pojo = new PackageDetailPojo();
		int n = Integer.parseInt(request.getParameter("number").toString());
		String a2 = null;
		if (request.getParameter("funct").equals("update")) {

			pojo.set材质(材质[n]);
			pojo.set单重(单重[n]);
			pojo.set内浇口数量(内浇口数量[n]);
			pojo.set订单细则号(订单细则号[n]);
			pojo.set密度比(密度比[n]);
			pojo.set内部编号(内部编号[n]);
			pojo.set客户简称(客户简称[n]);
			pojo.set工艺单编号(工艺单编号[n]);
			pojo.set工艺单细则号(工艺细则编号[n]);
			pojo.set排箱号(排箱号[n]);
			pojo.set备注(备注[n]);
			a2 = 工艺单编号[n];
			baseService.updateDetailpackage(pojo);
			String flag = request.getParameter("exece");
			if (flag.equals("1")) {
				String totaltime = null;
				String totaltime2 = null;
				Double totalWeight = 0.00;
				Double totalWeight2 = 0.00;
				for (int i = 0; i < 工艺单编号.length; i++) {
					if ((排箱号[i].indexOf("-")) > 0) {// -1箱
													// 等于-1意味着没有改值，不等于-1说明存在该值
						totalWeight = Double.valueOf(单重[i]) * Double.valueOf(密度比[i]) + totalWeight;
					} else {
						totalWeight2 = Double.valueOf(单重[i]) * Double.valueOf(密度比[i]) + totalWeight2;
					}
				}
				totaltime2 = calculatetime(totalWeight2, totaltime2);
				baseService.updatePackagetimeandweight(1, totalWeight2, totaltime2, 工艺单编号[0]);
				if (totalWeight != 0.00) {
					totaltime = calculatetime(totalWeight, totaltime);
					baseService.updatePackagetimeandweight(0, totalWeight, totaltime, 工艺单编号[0]);
				}
			}
		} else if (request.getParameter("funct").equals("delete")) {

			baseService.deleteDetailPojo(工艺细则编号[n]);
			a2 = 工艺单编号[n];

			String totaltime = null;
			String totaltime2 = null;
			Double totalWeight = 0.00;
			Double totalWeight2 = 0.00;
			for (int i = 0; i < 工艺单编号.length; i++) {
				if ((排箱号[i].indexOf("-")) > 0) {// -1箱
					totalWeight = Double.valueOf(单重[i]) * Double.valueOf(密度比[i]);
				} else {
					totalWeight2 = Double.valueOf(单重[i]) * Double.valueOf(密度比[i]);
				}
			}
			totaltime2 = calculatetime(totalWeight2, totaltime2);
			baseService.updatePackagetimeandweight(0, totalWeight2, totaltime2, 工艺单编号[0]);
			if (totalWeight != 0.00) {
				totaltime = calculatetime(totalWeight, totaltime);
				baseService.updatePackagetimeandweight(0, totalWeight, totaltime, 工艺单编号[0]);
			}

		} else if (request.getParameter("funct").equals("insert")) {

			pojo.set材质(材质[n]);
			pojo.set单重(单重[n]);
			pojo.set内浇口数量(内浇口数量[n]);
			pojo.set订单细则号(订单细则号[n]);
			pojo.set密度比(密度比[n]);
			pojo.set内部编号(内部编号[n]);
			pojo.set客户简称(客户简称[n]);
			pojo.set工艺单编号(工艺单编号[n]);
			pojo.set工艺单细则号(工艺细则编号[n]);
			pojo.set排箱号(排箱号[n]);
			pojo.set备注(备注[n]);
			pojo.set铸件编号(客户简称[n] + 工艺单编号[n] + 订单细则号[n]);
			baseService.insertPacakgedetail(pojo);
			a2 = 工艺单编号[n];
			String flag = request.getParameter("exece");
			if (flag.equals("1")) {
				String totaltime = null;
				String totaltime2 = null;
				Double totalWeight = 0.00;
				Double totalWeight2 = 0.00;
				for (int i = 0; i < 工艺单编号.length; i++) {
					if ((排箱号[i].indexOf("-")) > 0) {// -1箱
						totalWeight = Double.valueOf(单重[i]) * Double.valueOf(密度比[i]);
					} else {
						totalWeight2 = Double.valueOf(单重[i]) * Double.valueOf(密度比[i]);
					}
				}
				totaltime2 = calculatetime(totalWeight2, totaltime2);
				baseService.updatePackagetimeandweight(0, totalWeight2, totaltime2, 工艺单编号[0]);
				if (totalWeight != 0.00) {
					totaltime = calculatetime(totalWeight, totaltime);
					baseService.updatePackagetimeandweight(0, totalWeight, totaltime, 工艺单编号[0]);
				}
			}
		}

		List LoopResult = baseService.querydetailPackage(a2);
		Integer listsize = LoopResult.size();
		request.setAttribute("LoopResult", LoopResult);
		request.setAttribute("listsize", listsize);
		return "WEB-INF/zh_CN/DetailPackage";
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/upload", produces = "application/json")
	private String upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String upload = request.getParameter("no");
		request.setAttribute("no", upload);
		upload = baseService.queryPicupload(upload);
		String picload = null;

		if (!Util.isNullOrEmpty(upload)) {
			String[] sblit = upload.split("\\\\");
			picload = sblit[3];
		}
		request.setAttribute("Pic", picload);

		return "WEB-INF/zh_CN/uploadPic";

	}

	private String uploadstring = "D:\\upload_test";

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/uploadPic", produces = "application/json")
	private String uploadPic(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map map = new HashMap();
		File imgFile = new File(uploadstring);
		String[] list = imgFile.list(); // 获取文件夹内的所有文件名
		int b = 0;
		String picname = null;
		for (int i = 0; i < list.length; i++) {
			String a2[] = list[i].split("\\.");
			if (b < Integer.parseInt(a2[0])) {
				b = Integer.parseInt(a2[0]);
			}
		}
		b++;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request); // 获得前台提交过来的信息，两类：文件、form表单

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator<FileItem> itr = items.iterator();

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				// 是表单数据
				map.put(item.getFieldName(), item.getString("utf-8"));
			} else if (!"".equals(item.getName())) {// 不是form表单数据而是文件上传
				try {
					String filename = item.getName();
					String name[] = filename.split("\\.");

					// 上传到硬盘的一个绝对路径
					String filePath = uploadstring + "\\" + b + "." + name[1];
					picname = filePath;
					item.write(new File(filePath));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String number = map.get("no").toString();
		baseService.updatePicName(picname, number);
		return "WEB-INF/zh_CN/success";
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/nbbhbg", produces = "application/json")
	private String nbbhbg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isLogin(request)) {
			return "WEB-INF/zh_CN/nbbhbg";
		} else {

			return "login";
		}
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/jh", produces = "application/json")
	private String jh(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isLogin(request)) {
			return "WEB-INF/zh_CN/jh";
		} else {

			return "login";
		}
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/nbbhbgcz", produces = "application/json")
	private String nbbhbgcz(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isLogin(request)) {
			if (request.getParameter("type").toString().equals("0")) {
				String 内部编号 = request.getParameter("nbbh");
				String 订单细则号 = request.getParameter("xzh");
				String 内部编号2 = request.getParameter("nbbh2");
			} else {
				String 内部编号[] = request.getParameterValues("bh");
				String 订单细则号[] = request.getParameterValues("xz");
				String 内部编号2[] = request.getParameterValues("bh2");
				System.out.println("1");
				// 两部操作，现将所有内部编号更新为一个唯一不被占用的编号，在将编号一次更新为目标编号
				Integer p = 0;
				Integer q = 0;
				try {
					for (int i = 0; i < 内部编号.length; i++) {
						baseService.insertnbbhbg(内部编号[i], 订单细则号[i], 内部编号2[i]);
						Date date = new Date();
						String s = "LSBH000" + i;
						baseService.updatetcnbbh(s, 订单细则号[i]);
						p++;
					}
					for (int i = 0; i < 内部编号.length; i++) {

						baseService.updatetcnbbh(内部编号2[i], 订单细则号[i]);
						q++;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				List list = baseService.selectNBBH();
				request.setAttribute("LoopResult", list);
				if (!Util.isNullOrEmpty(list)) {
					return "WEB-INF/zh_CN/nbbhbgshow";
				}

			}
			return "WEB-INF/zh_CN/nbbhbg";
		} else {

			return "login";
		}
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/gszh", produces = "application/json")
	private void gszh(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = null;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request); // 获得前台提交过来的信息，两类：文件、form表单

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		File file = new File("D://item.htm");
		if (!Util.isNullOrEmpty(items)) {

			Iterator<FileItem> itr = items.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					// 是表单数据
					map.put(item.getFieldName(), item.getString("utf-8"));
				} else if (!"".equals(item.getName())) {// 不是form表单数据而是文件上传
					File fil = new File(item.getName());
					map.put("name", fil.getName());
					item.write(file);
				}
			}
			if (!Util.isNullOrEmpty(map.get("time"))) {
				date = df2.parse(map.get("time").toString());
				date1 = df.format(date);
			} else {
				date1 = df.format(date);
			}
			response.setHeader("content-disposition", "attachment;filename=" + map.get("name"));

			dealwithhtm(file, date1, map.get("name").toString());

			/*
			 * //将图片读入内存 FileInputStream fis = new
			 * FileInputStream("D://"+map.get("name"));
			 * 
			 * //将图片写出到网络 ServletOutputStream sos = response.getOutputStream();
			 * 
			 * 
			 * byte[] arr = new byte[1024];
			 * 
			 * int num = -1;
			 * 
			 * while((num = fis.read(arr)) != -1){ sos.write(arr, 0, num);
			 * sos.flush(); }
			 * 
			 * fis.close(); sos.close(); File fileend=new
			 * File("D://"+map.get("name")); fileend.delete();
			 */
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(JSON.toJSONString(map.get("name")));

		}
		/* return "gszh"; */
	}

	private void dealwithhtm(File file, String date, String name) throws IOException {

		InputStreamReader inStream = null;
		inStream = new InputStreamReader(new FileInputStream("D://item.htm"), "GBK");
		BufferedReader bReader = new BufferedReader(inStream);

		StringBuffer sb = new StringBuffer("");
		// FileReader reader =new FileReader(file);
		// BufferedReader bReader=new BufferedReader(reader);
		String string = null;
		String s1 = "<td width=\"710\" height=\"45\" colspan=\"6\" bordercolordark=\"#FFFFFF\">";
		String s2 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>件&nbsp; 号</b></font></td>";
		String s3 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><b><font size=\"6\">";
		String s4 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>日&nbsp; 期</b></font></td>";
		String s5 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>审核</b></font></td><td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"3\"><b>&nbsp</b></font></td>";
		String s6 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"3\"><b>201";
		String s7 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>编程员</b></font></td>";
		String s8 = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>校对者</b></font></td>";
		while ((string = bReader.readLine()) != null) {

			if (string.equals(s1)) {
				string = "<td width=\"710\" height=\"45\" colspan=\"8\" bordercolordark=\"#FFFFFF\">";
			}
			if (string.equals(s2)) {
				string = "<td width=\"118\" height=\"26\" colspan=\"2\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>件&nbsp; 号</b></font></td>";
			}
			if (string.indexOf(s3) >= 0) {
				string = "<td width=\"118\" height=\"26\" colspan=\"2\" align=\"center\" bordercolordark=\"#FFFFFF\"><b><font size=\"6\">"
						+ string.substring(99, 101) + "</font></b></td>";
				System.out.println(string);
			}
			if (string.equals(s4)) {
				string = s5 + s4;
			}
			if (string.indexOf(s6) >= 0) {
				string = "<td width=\"118\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"3\"><b>"
						+ date + "</b></font></td>";
			}
			if (string.equals(s7)) {
				string = "<td width=\"150\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>编程员</b></font></td>";
			}
			if (string.equals(s8)) {
				string = "<td width=\"150\" height=\"26\" colspan=\"1\" align=\"center\" bordercolordark=\"#FFFFFF\"><font size=\"6\"><b>校对者</b></font></td>";
			}
			sb.append(string);
			// System.out.println(string);

		}

		bReader.close();
		// reader.close();

		OutputStreamWriter outStream = null;
		outStream = new OutputStreamWriter(new FileOutputStream("D://" + name), "GBK");
		BufferedWriter writer = new BufferedWriter(outStream);
		writer.write(sb.toString());
		writer.close();
		/*
		 * FileWriter writer=new FileWriter("D://"+name); BufferedWriter
		 * bWriter=new BufferedWriter(writer);
		 * 
		 * bWriter.write(sb.toString()); bWriter.close();
		 */

		file.delete();
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/downloading", produces = "application/json")
	private String downloading(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String name = null;
		if (!Util.isNullOrEmpty(request.getParameter("realtext"))) {
			name = request.getParameter("realtext").replace("\"", "").trim();
		}
		if (!Util.isNullOrEmpty(name)) {
			response.setHeader("content-disposition", "attachment;filename=" + name);
			// 将图片读入内存
			FileInputStream fis = new FileInputStream("D://" + name);

			// 将图片写出到网络
			ServletOutputStream sos = response.getOutputStream();

			byte[] arr = new byte[1024];

			int num = -1;

			while ((num = fis.read(arr)) != -1) {
				sos.write(arr, 0, num);
				sos.flush();
			}

			fis.close();
			sos.close();
			File fileend = new File("D://" + name);
			fileend.delete();

		}
		return "gszh";
	}

	@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
	@RequestMapping(value = "/queryBoard", produces = "application/json")
	private List queryBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kind = request.getParameter("看板类型");
		String date = request.getParameter("时间");
		List ArrayList =null; 
		if(!Util.isNullOrEmpty(kind)&&!Util.isNullOrEmpty(date)){
			ArrayList=baseService.queryBoard(date,kind);			
		}
		String s1 = JSON.toJSONString(ArrayList, true);
		// 解决json前端乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(s1);
		return null;
	}

@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
@RequestMapping(value = "/jhxz", produces = "application/json")
private List jhxz(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	Map map = new HashMap();
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setHeaderEncoding("UTF-8");
	File fil=null;
	List<FileItem> items = null;
	try {
		items = upload.parseRequest(request); // 获得前台提交过来的信息，两类：文件、form表单

	} catch (FileUploadException e) {
		e.printStackTrace();
	}
	File file = new File("D://2.dat");
	if (!Util.isNullOrEmpty(items)) {
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				// 是表单数据
				map.put(item.getFieldName(), item.getString("utf-8"));
			} else if (!"".equals(item.getName())) {// 不是form表单数据而是文件上传
				fil = new File(item.getName());
				map.put("name", fil.getName());
				item.write(file);
			}
		}
	
	}
	BufferedReader br = null;
    StringBuffer sb = new StringBuffer();
    try {
        br = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = br.readLine())!=null){
            sb.append(line);                
        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }finally{
        try {
            if(br!=null){
                br.close();
            }
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }
    }
    fil.delete();
   // System.out.println(sb.toString());
    String sbb=sb.toString();
    String q1="";
    for(int i=0 ; i<sbb.length();i++){
    		if(Character.isDigit(sbb.charAt(i))){ //用char包装类中的判断数字的方法判断每一个字符
    		q1=q1+sbb.charAt(i);
    		}
    		if(Character.isLetter(sbb.charAt(i))){ //用char包装类中的判断字母的方法判断每一个字符
    		q1=q1+sbb.charAt(i);
    		}
}
	String s1=q1;
	String s15=(String) s1.subSequence(0, 15);
	String s2425=(String)s1.subSequence(23,25);
	String s1728=(String)s1.subSequence(15,28);	
	String s28=(String)s1.subSequence(27,28);
	String s2930=(String)s1.subSequence(28,30);
	String total=s15+s2425+s1728+s28+s2930+"99999999";
	final BASE64Encoder encoder = new BASE64Encoder();
	final byte[] textByte = total.getBytes("UTF-8");
	//编码加密
	final String encodedText = encoder.encode(textByte);
	//System.out.println(encodedText);
	String head="0001000000FFFFFFFF0100000000000000060100000038";
	String end ="0B";
	String str = "";
	for (int i = 0; i < encodedText.length(); i++) {
		int ch = (int) encodedText.charAt(i);
		String s4 = Integer.toHexString(ch);
		str = str + s4;
	}
	str = head + str + end;
	str = str.toUpperCase();
	ServletOutputStream sos = response.getOutputStream();
    //设置客户端下载
    response.setHeader("content-disposition", "attachment;filename=1.dat");


    
    File f = new File("D:\\1.dat");
	if (!f.exists()) {
		f.createNewFile();
	}
	FileOutputStream fout = new FileOutputStream(f);
	for (int i = 0; i < str.length(); i = i + 2) {
		fout.write(Integer.parseInt(str.substring(i, i + 2), 16));
	}
	fout.close();
	FileInputStream fis = new FileInputStream("D:\\1.dat");
    //将图片写出到网络
    ServletOutputStream sos1 = response.getOutputStream();
    byte[] arr = new byte[1024];
    int num = -1;
    while((num = fis.read(arr)) != -1){
        sos1.write(arr, 0, num);
        sos1.flush();
    }

    fis.close();
    sos1.close();
    f.delete();
return null;
}

@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
@RequestMapping(value = "/kbcx", produces = "application/json")
private String kbcx(HttpServletRequest request, HttpServletResponse response) throws Exception {
	if (isLogin(request)) {
		Date time = new java.sql.Date(new java.util.Date().getTime());
		request.setAttribute("Date",time);
		return  "WEB-INF/zh_CN/queryBoard";	
		
	}else{
		return "login";
	}
}

@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
@RequestMapping(value = "/faceadd", produces = "application/json")
private String faceadd(HttpServletRequest request, HttpServletResponse response) throws Exception {
	if (isLogin(request)) {
             return  "WEB-INF/zh_CN/Faceadd";
	}else{
		return "login";
	}
}

@ModelAttribute
private void firstrun(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("11122");
}

@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
@RequestMapping(value = "/facedistinguish", produces = "application/json")
private String facedistinguish(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String token=gettoken();
	String url="https://aip.baidubce.com/rest/2.0/face/v3/search?access_token="+token;
	HttpURLConnection conn=null;
	URL url2=new URL(url);
	conn=(HttpURLConnection) url2.openConnection();
	conn.setDoInput(true);
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type","application/json");
	conn.setRequestProperty("Connection", "Keep-Alive");
	List list=new ArrayList();
	Map map=new HashMap();
	String img = request.getParameter("base");
	map.put("image",img);
	map.put("group_id_list","Manage,guanliyonghu");
	map.put("image_type", "BASE64");
	map.put("face_type","LIVE");
	map.put("quality_control","LOW");
	map.put("liveness_control","LOW");
/*	list.add(map);*/
	String param=GsonUtils.toJson(map);
	DataOutputStream dStream=new DataOutputStream(conn.getOutputStream());
	dStream.write(param.getBytes("UTF-8"));
	dStream.flush();
	dStream.close();
	conn.connect();
	InputStream iStream=conn.getInputStream();
	BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(iStream));
	String string="";
	String total="";
	if((string=bufferedReader.readLine())!=null){
		System.out.println(string);
		total=total+string;
	}
	InputStream in=this.getClass().getResourceAsStream("/error.properties");
	Properties p=new Properties();
	p.load(in);
	map=(Map)JSON.parse(total);
	if(map.get("error_code").toString().equals("0")){
		Map map2=(Map) map.get("result");
		List list2=(List) map2.get("user_list");
		Map map3=new HashMap();
		map3.put("error_code",map.get("error_code").toString());
		map3.put("error_msg",map.get("error_msg").toString());
		map=(Map) list2.get(0);
		String user_id=(String) map.get("user_id");
		map3.put("user_id", user_id);
		total=GsonUtils.toJson(map3);
	}else{Map map3=new HashMap();
		if(!Util.isNullOrEmpty(p.get(map.get("error_code").toString()))){
			map3.put("error_msg",p.get(map.get("error_code").toString()));
			map3.put("error_code",map.get("error_code").toString());
		}else{			
			map3.put("error_msg",map.get("error_msg").toString());
			map3.put("error_code",map.get("error_code").toString());
		}
		total=GsonUtils.toJson(map3);
	}
	in.close();
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().print(total);
   return null;
}
@SuppressWarnings({ "finally", "unchecked", "null", "rawtypes" })
@RequestMapping(value = "/faceaddaction", produces = "application/json")
private String faceaddaction(HttpServletRequest request, HttpServletResponse response) throws Exception {
	if (isLogin(request)) {
           String token=gettoken(); 
           String url="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add?access_token="+token;
			HttpURLConnection conn1=null;
			URL url2=new URL(url);
			conn1=(HttpURLConnection) url2.openConnection();
			conn1.setRequestMethod("POST");
			conn1.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn1.setRequestProperty("Connection", "Keep-Alive");
			conn1.setUseCaches(false);
			conn1.setDoOutput(true);
			conn1.setDoInput(true);
			Map map=new HashMap();
			Map FaceInfo=baseService.queryFaceInfo(request.getAttribute("uid").toString());
			map.put("user_id",FaceInfo.get("对应工号").toString().trim());
			map.put("group_id",Util.ToPinyin(FaceInfo.get("用户组").toString().trim()));
			map.put("user_info",Util.ToPinyin(FaceInfo.get("登陆名").toString().trim()));
           map.put("liveness_control", "NORMAL");
           map.put("image_type", "BASE64");
           map.put("quality_control", "LOW");
			
           String img = request.getParameter("base");
           map.put("image", img);
           String param=GsonUtils.toJson(map);    
           DataOutputStream out2 = new DataOutputStream(conn1.getOutputStream());
           out2.write(param.getBytes("UTF-8"));
           out2.flush();
           out2.close();
           conn1.connect();
           InputStream iss=conn1.getInputStream();
           BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(iss));
           String total="";
           String string="";
			if((string=bufferedReader.readLine())!=null)
			{
				System.out.println(string);
				total=total+string;
				
			}
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(total);
           return null;
	}else{
		return "need login";
	}
}
private  String  gettoken(){
	String token="";
	try {
		InputStream in=this.getClass().getResourceAsStream("/face.properties");
		Properties p=new Properties();
		p.load(in);
		String day=p.getProperty("tokenday");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date fDate=sdf.parse(day);
		Date lDate=new Date();
		long daysBetween=(fDate.getTime()-lDate.getTime())/(60*60*24*1000);
		if(daysBetween<=0){
			String string="";
			String url=p.getProperty("urltoken");
			HttpURLConnection conn=null;
			URL url1=new URL(url);
			conn=(HttpURLConnection) url1.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			InputStream is=conn.getInputStream();
			BufferedReader bf=new BufferedReader(new InputStreamReader(is));
			String total="";
			if((string=bf.readLine())!=null)
			{
				//System.out.println(string);
				total=total+string;
				
			}
			in.close();
			System.out.println(total);
			JSONObject js=JSON.parseObject(total);
			token=js.getString("access_token");			
			/*String profilepath = BaseController.class.getResource("/").getPath()+"face.properties";//我的配置文件在src根目录下
			PropertiesConfiguration config  = new PropertiesConfiguration(profilepath);
            config.setAutoSave(true);
            config.setProperty("tokenday",sdf.format(lDate));
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            config.setProperty("access_token",token);*/
		}else{
			
			token=p.getProperty("access_token");
		}
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.err.println("文件未读取到");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}/* catch (ConfigurationException e) {
		// TODO Auto-generated catch block
		System.err.println("配置读取错误");
	}*/
	return token;
	
 
}
}
