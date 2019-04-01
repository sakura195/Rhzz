package com.bky.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Util {
	
	/**
	48      * 汉字转为拼音
	49      * @param chinese
	50      * @return
	51      */
	     public static String ToPinyin(String chinese){          
	         String pinyinStr = "";  
	         char[] newChar = chinese.toCharArray();  
	         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
	         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	         for (int i = 0; i < newChar.length; i++) {  
	             if (newChar[i] > 128) {  
	                 try {  
	                     pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];  
	                 } catch (BadHanyuPinyinOutputFormatCombination e) {  
	                     e.printStackTrace();  
	                 }  
	             }else{  
	                 pinyinStr += newChar[i];  
	             }  
	         }  
	         return pinyinStr;  
	     }  

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		if (obj instanceof Object[]) {
			Object[] o = (Object[]) obj;
			if (o == null || o.length == 0) {
				return true;
			}
			return false;
		} else {
			if (obj instanceof String) {
				if ((obj == null) || (("").equals(((String) obj).trim()))) {
					return true;
				}
				return false;
			}
			if (obj instanceof List) {
				List objList = (List) obj;
				if (objList == null || objList.isEmpty()) {
					return true;
				}
				return false;
			}
			if (obj instanceof Map) {
				Map objMap = (Map) obj;
				if (objMap == null || objMap.isEmpty()) {
					return true;
				}
				return false;
			}
			if ((obj == null) || (("").equals(obj))) {
				return true;
			}
		}
		return false;
	}

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes(); // 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5"); // 使用指定的字节更新摘要
			mdInst.update(btInput); // 获得密文
			byte[] md = mdInst.digest(); // 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * unicode编码转中文
	 */
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static String Unicode2GBK(String str) {
		if ("".equals(str)) {
			return str;
		}
		if (str == null) {
			return str;
		}
		if (!str.contains("&#")) {
			return str;
		}
		List list = new ArrayList();
		String[] fields = str.split(";");
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				list.add((char) Integer.parseInt(fields[i].substring(fields[i].indexOf("#") + 1)));
			}
		}
		StringBuffer result = new StringBuffer();
		if (list.size() > 0 && list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				result.append(String.valueOf(it.next()));
			}
		}
		return result.toString();
	}

	/*
	 * 签名转换（主要签名转换，次要签名先查找MAP是否有无数据，若没有，则查找数据库）
	 * */
	public String signName(String name){
		Map map=new HashMap();
		map.put("ZZ001","芸峰");map.put("247","左从军");map.put("28","张宏伟");map.put("7","查磊");map.put("4","高梦迪");map.put("37","刘平");map.put("298","钟徽");
		map.put("230","李万梅");map.put("5","彭小春");map.put("650","周龙");map.put("83","王磊");map.put("61","王皖");map.put("395","曹正飞");map.put("681","程云");
		map.put("6","黄鹂");map.put("215","赵齐");map.put("346","李莹");map.put("3","吴萍");map.put("608","邓华松");map.put("213","盛杰");map.put("648","汪海余");
		map.put("667","韦开保");map.put("176","李芳芳");map.put("22","徐清清");map.put("590","吕振海");map.put("271","徐达义");map.put("587","钱飞");map.put("24","汪继松");
		if(Util.isNullOrEmpty(map.get(name)))
		{
			
		}		
		
		return null;
	}
	
	
	public static String trimString(String str) {
		return str == null ? "" : str.trim();
	}
	
	public static String getAlpha(String chines) {
		          chines = cleanChar(chines);
		          String pinyinName = "";
		          char[] nameChar = chines.toCharArray();
		          HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		          defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		          defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		          for (int i = 0; i < nameChar.length; i++) {
		              if (nameChar[i] > 128) {
		                  try {
		                      pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
		                  } catch (BadHanyuPinyinOutputFormatCombination e) {
		                      e.printStackTrace();
		                  }
		              } else {
		                  pinyinName += nameChar[i];
		              }
		          }
		          return pinyinName;
		      }
	
	     /**
	      * 清理特殊字符以便得到
	      * @param chines
	      * @return
      */
	     public static String cleanChar(String chines) {
	         chines = chines.replaceAll("[\\p{Punct}\\p{Space}]+", ""); // 正则去掉所有字符操作
	         // 正则表达式去掉所有中文的特殊符号
	         String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}<>《》【】‘；：”“’。，、？]";
	         Pattern pattern = Pattern.compile(regEx);
	         Matcher matcher = pattern.matcher(chines);
	         chines = matcher.replaceAll("").trim();
	         return chines;
	     }
	 
}