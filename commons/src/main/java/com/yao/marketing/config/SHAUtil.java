package com.yao.marketing.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHAUtil {

		public static String SHA(String inStr) {
				MessageDigest md = null;
				String outStr = null;
				try {
						md = MessageDigest.getInstance("SHA-1");     //ѡ��SHA-1��Ҳ����ѡ��MD5
						byte[] digest = md.digest(inStr.getBytes());       //���ص���byet[]��Ҫת��ΪString�洢�ȽϷ���
						outStr = bytetoString(digest);
				} catch (NoSuchAlgorithmException nsae) {
						nsae.printStackTrace();
				}
				return outStr;
		}


		public static String bytetoString(byte[] digest) {
				String str = "";
				String tempStr = "";

				for (int i = 0; i < digest.length; i++) {
						tempStr = (Integer.toHexString(digest[i] & 0xff));
						if (tempStr.length() == 1) {
								str = str + "0" + tempStr;
						} else {
								str = str + tempStr;
						}
				}
				return str.toLowerCase();
		}
		public static void main(String[] args) {
				String[] params = new String[] {  "1400567112", "269215637", "UDW5pqTDYGmaqhOL0gw", };
				
				String destSign = SHAUtil.SHA(params[0]+params[1]+params[2]);
				System.out.println(destSign);
		}
}
