package abc.abc.abc;

import com.egeg.aaa.MainActivity;
import android.app.Activity;
import android.widget.Toast;

public class SuccessPay extends PayInterface {

	MainActivity act;

	public void onActivityCreate(Activity activity) {
		super.onActivityCreate(activity);
		act = (MainActivity) activity;
	}

	public void pay(int payIndex, int money
			, String product, PayInterface.PayListener listener) {
		if (listener != null) {
			listener.payCallback(payIndex, true);
		}
		act.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(act, "购买成功", 0).show();
			}
		});
	}
}