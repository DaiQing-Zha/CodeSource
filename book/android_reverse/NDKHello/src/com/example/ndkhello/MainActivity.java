package com.example.ndkhello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {


	private TextView tv_showContent;
	private Button btn_showNativeResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_showContent = (TextView) findViewById(R.id.tv_showContent);
		btn_showNativeResult = (Button) findViewById(R.id.btn_showNativeResult);
		
		btn_showNativeResult.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				JniTest jniTest = new JniTest();
				tv_showContent.setText(String.valueOf(jniTest.add(2, 3)));
				Log.e("mainHHH", "-----------------------------");
			}
		});
	}

}
