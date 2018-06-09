package yct.game.pay;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fktxpoiuyt.config.GameConfig;
import com.fktxpoiuyt.fktx.R;

public class PayDialog extends Dialog
{
	private ImageView dialog_bg;
	private ImageView dialog_title;
	private ImageView dialog_desc;
	private ImageView dialog_desc_pay;
	private ImageButton btn_confirm;
	private ImageButton btn_cancel;
	private TextView tv_payMoney;
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public PayDialog(Context context, int payId)
	{
		super(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);		
		
		this.setCancelable(false);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.layout_dialog, null);
		this.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		dialog_bg = (ImageView)layout.findViewById(R.id.dialog_bg);
		dialog_title = (ImageView)layout.findViewById(R.id.dialog_title);
		dialog_desc = (ImageView)layout.findViewById(R.id.dialog_desc);
		dialog_desc_pay = (ImageView)layout.findViewById(R.id.dialog_desc_pay);
		btn_confirm = (ImageButton)layout.findViewById(R.id.dialog_btn_confirm);
		btn_cancel = (ImageButton)layout.findViewById(R.id.dialog_btn_cancel);
		tv_payMoney = (TextView)layout.findViewById(R.id.tv_payMoney);
		Bitmap bitmap = drawableToBitmap(dialog_desc.getBackground());
		SetPosition(dialog_bg, 0, 0, 0, 0, 0.6f, 0.6f);
		SetPosition(dialog_title, 0, 0, 0, (int)(bitmap.getHeight() * 0.65f * 0.6f), 0.35f, 0.6f);
		SetPosition(dialog_desc, 0, 0, 0, 0, 0.6f, 0.6f);
		SetPosition(dialog_desc_pay, 0, (int)(bitmap.getHeight() * 0.44f * 0.6f), 0, 0, 0.5f, 0.5f);
		SetPosition(btn_confirm, 0, (int)(bitmap.getHeight() * 0.7f * 0.6f), 0, 0, 0.6f, 0.6f);
		SetPosition(btn_cancel, (int)(bitmap.getWidth() * 0.6f * 0.6f), 0, 0, (int)(bitmap.getHeight() * 0.85f * 0.6f), 0.6f, 0.6f);
		
		setPayID(payId);
		this.setContentView(layout);
	}
	
	public void setConfirmOnClickListener(View.OnClickListener listener)
	{
		btn_confirm.setOnClickListener(listener);
	}
	
	public void setCancelOnClickListener(View.OnClickListener listener)
	{
		btn_cancel.setOnClickListener(listener);
	}
	
	public void setPayID(int payId)
	{
		System.out.println("pay id = " + payId);
		payId += 1;
		if(payId == 2)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_2);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_2);
		}
		else if(payId == 3)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_3);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_3);
		}
		else if(payId == 4)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_4);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_4);
		}
		else if(payId == 5)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_5);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_5);
		}
		else if(payId == 6)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_6);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_6);
		}
		else if(payId == 7)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_7);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_7);
		}
		else if(payId == 8)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_8);
			dialog_title.setBackgroundResource(R.drawable.letu_tip_goods_8);
		}
		else if(payId == 9)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_9);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_9);
		}
		else if(payId == 10)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_10);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_10);
		}
		else if(payId == 11)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_11);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_11);
		}
		else if(payId == 12)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_12);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_12);
		}
		else if(payId == 13)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_13);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_13);
		}
		else if(payId == 14)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_5);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_5);
		}
		else if(payId == 17)
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_17);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_17);
		}
		else
		{
			dialog_title.setBackgroundResource(R.drawable.letu_title_1);
			dialog_desc.setBackgroundResource(R.drawable.letu_tip_goods_1);
		}
		Log.e("mainGGGGGG", "------------------------ssh1 = " + GameConfig.open_sh);
        if (GameConfig.open_sh == 0) { //审核
        	tv_payMoney.setVisibility(View.VISIBLE);
		}else {	//不审核
			tv_payMoney.setVisibility(View.GONE);
		}
	}

	private Bitmap drawableToBitmap(Drawable drawable) 
    {
        Bitmap bitmap = Bitmap.createBitmap(
        drawable.getIntrinsicWidth(),
        drawable.getIntrinsicHeight(),
        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        return bitmap;
    }
    
    private void SetPosition(View view, int left, int top, int right, int bottom, float widthSize, float heightSize)
    {
		Bitmap bitmap = drawableToBitmap(view.getBackground());			//�ο�ͼƬ
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
		params.setMargins(left, top, right, bottom);
		params.width = (int)(bitmap.getWidth() * widthSize);
		params.height = (int)(bitmap.getHeight() * heightSize);
		view.setLayoutParams(params);
    }
}
