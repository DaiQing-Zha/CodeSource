package abc.abc.abc;

import android.app.Activity;
import android.content.Context;

public abstract class PayInterface {

	public interface PayListener {
		public void payCallback(int payIndex, boolean success);
	}

	public abstract void pay(int p1, int p2, String p3,
			PayInterface.PayListener p4);

	public void onAppAttachContext(Context context) {
	}

	public void onAppCreate(Context context) {
	}

	public void onActivityCreate(Activity activity) {
	}

	public void onActivityPause(Activity activity) {
	}

	public void onActivityResume(Activity activity) {
	}

	public void onActivityDestroy(Activity activity) {
	}

}
