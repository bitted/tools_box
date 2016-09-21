package com.cyou.hithere.center.util.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cyou.hithere.center.util.CustomProperty;


public class IPlistUtil {
	
	public static List<IPlistVO> getIPlist() {
		String iplist = String.valueOf(CustomProperty.getContextProperty("chat.mongoDBIP.list"));
		List<IPlistVO> riplist = new ArrayList<IPlistVO>();
		String [] allips = iplist.split(";");
		for (int i = 0; i < allips.length; i++) {
			String allip = allips[i].trim();
			if (StringUtils.isNotEmpty(allip)) {
				String ip = allip.split(":")[0];
				Integer port = Integer.parseInt(allip.split(":")[1]);
				IPlistVO iplistVO = new IPlistVO();
				iplistVO.setIp(ip);
				iplistVO.setPort(port);
				riplist.add(iplistVO);
			}
		}
		return riplist;
	}

}
