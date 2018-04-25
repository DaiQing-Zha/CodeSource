package com.example.adbshell;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_getSalt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(MainActivity.this, "HHH", Toast.LENGTH_SHORT).show();
				getSalt();
			}
		});
    }
    
	private void getSalt(){
		try {
			Class<?> clazz1 = Class.forName("com.android.internal.widget.LockPatternUtils");
			Object lockUtilsObject = clazz1.getConstructor(Context.class).newInstance(this);
			Class<?> lockUtilsClazz = lockUtilsObject.getClass();
			Method getSaltM = lockUtilsClazz.getDeclaredMethod("getSalt", int.class);
			getSaltM.setAccessible(true);
			Object saltObj = getSaltM.invoke(lockUtilsObject, 0);
			Log.e("mainHHH", "slat = " + saltObj);
			System.out.println("slat = " + saltObj);
			Toast.makeText(this, saltObj.toString(), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("mainHHH", "err = " + Log.getStackTraceString(e));
			System.out.println("err = " + Log.getStackTraceString(e));
			Toast.makeText(this, Log.getStackTraceString(e), Toast.LENGTH_LONG).show();
		} 
		
	}
	
}
