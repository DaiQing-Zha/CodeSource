package abc.abc.abc;

import android.content.Context;

import com.pay.sdk.alan.application.AlanApplication;

public class MyApplication extends AlanApplication {

	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		GamePay.onAppAttachContext(base);
	}

	public void onCreate() {
		super.onCreate();
		GamePay.onAppCreate(this);
	}
}
