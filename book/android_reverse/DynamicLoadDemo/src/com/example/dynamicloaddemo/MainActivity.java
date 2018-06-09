package com.example.dynamicloaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.dynamicloaddemo.dex.DexUtil;
import com.example.fatjartest.CallApi;
import com.example.fatjartest.MakeApi;



public class MainActivity extends Activity {

	private Button mBtnInitSDK,mBtnPay,mBtnRhb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnInitSDK = (Button) findViewById(R.id.btn_initSdk);
        mBtnPay = (Button) findViewById(R.id.btn_pay);
        mBtnRhb = (Button) findViewById(R.id.btn_rhb);
        
        mBtnInitSDK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DexUtil.getInstance().init(MainActivity.this, "hello");
			}
		});
        mBtnPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DexUtil.getInstance().doPay(MainActivity.this, "2000");
			}
		});
        mBtnRhb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CallApi callApi = new CallApi();
				MakeApi makeApi = new MakeApi();
				callApi.main(new String[]{"A","S","D","F","G"});
				makeApi.printStaticApi();
			}
		});
    }
}
