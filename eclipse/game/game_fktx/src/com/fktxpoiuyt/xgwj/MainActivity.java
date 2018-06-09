package com.fktxpoiuyt.xgwj;

import org.cocos2dx.lib.Cocos2dxActivity;

import yct.game.pay.GamePay;
import android.os.Bundle;

public class MainActivity extends Cocos2dxActivity
{
	public static final String[] PAY_DESP = new String[] { "解锁疾风迅雷与猩红死神，体验更强战机，还能获赠超值礼包，内涵精彩道具，仅需N.NN元，是否继续？",
		"领取超级僚机“惊天雷”，免费获得无敌护盾*3，原地复活*3。超值礼包，仅需N.NN元，是否继续？",
		"究极超值礼包，包含海量珍贵道具，护盾复活连环冲刺！助您爽快闯关，一往无前，仅需N.NN元，是否继续？",
		"购买5000金币仅需N.NN元！首次充值多送一倍！吐血优惠，机不可失！", 
		"购买12000金币仅需N.NN元！首次充值多送一倍！吐血优惠，机不可失！", 
		"购买21000金币仅需N.NN元！首次充值多送一倍！吐血优惠，机不可失！",
		"购买32000金币仅需N.NN元！首次充值多送一倍！吐血优惠，机不可失！",
		"你已经无路可逃了！急速火炮，自动追踪，无懈可击，你值得拥有！仅需N.NN元。",
		"感受来自地狱的怒火！史上最强僚机，一发入魂，一旦拥有，别无所求！仅需N.NN元。",
		"立刻获得3次终极核弹使用机会！仅需N.NN元。",
		"立刻获得3次无敌冲刺使用机会！仅需N.NN元",
		"距离破纪录一步之遥！是否使用原地复活？仅需N.NN元！",
		"距离破纪录一步之遥！是否使用亡灵冲刺？仅需N.NN元！",
		"立刻获得金币*20000，随后每天再送金币*5000，持续7天！超值优惠，过时不候！仅需N.NN元！",
		"", "",
		"解锁究极僚机“死神之眼”，再送海量20000金币，无敌护盾*3，原地复活*3，吐血优惠，仅需N.NN元，是否继续？" };
	public static final String[] PAY_NAME = new String[] { "战神礼包", "新兵礼包", "超神礼包", "购买金币1", "购买金币2", "购买金币3", "购买金币4", "自由之翼", "死神之眼", "终极核弹", "无敌冲刺", "原地复活", "亡灵冲刺", "金币礼包", "", "", "超值礼包" };
	public static final String[] PAY_NUM = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" };
	public static final String[] PAY_PRICE = { "800", "600", "600", "200", "400", "600", "800", "600", "800", "200", "200", "200", "200", "800", "0", "0", "1000" };

	public static final int[] PAY_PRICE_INT = { 800, 600, 600, 200, 400, 600, 800, 600, 800, 200, 200, 200, 200, 800, 0, 0, 1000};
	

	
	static
	{
		System.loadLibrary("cocos2dcpp");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		GamePay.setActivity(this);
		
		GamePay.rankInstance = new yct.game.pay.c();
	    GamePay.rankInstance.a(this);
	}

}
