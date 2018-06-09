package abc.abc.abc;

public class HidePay extends PayInterface {

	public HidePay() {

	}

	public void pay(int payIndex, int money
			, String product, PayInterface.PayListener listener) {
		if (listener != null) {
			listener.payCallback(payIndex, true);
		}
	}
}