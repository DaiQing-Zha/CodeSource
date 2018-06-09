package com.jxnu.zha;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.extract.YJOperate;
import com.jxnu.zha.extract.ZDOperate;
import com.jxnu.zha.utils.ReportUtil;

public class MainOperate {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReportUtil.getInstance().collectGoodProvince(Config.STYLE_THREE);
//		ZDOperate.getInstance().classifyProvince();;
	}

	

//	JMOperate.getInstance().classifyProvince();
//	PZOperate.getInstance().classifyProvince();
//	QPOperate.getInstance().classifyProvince();
//	SAOperate.getInstance().classifyProvince();
//	SFOperate.getInstance().classifyProvince();
//	TKOperate.getInstance().classifyProvince();
//	WYOperate.getInstance().classifyProvince();
//	XMOperate.getInstance().classifyProvince();
//	YFOperate.getInstance().classifyProvince();
//	MGOperate.getInstance().classifyProvince();
}
