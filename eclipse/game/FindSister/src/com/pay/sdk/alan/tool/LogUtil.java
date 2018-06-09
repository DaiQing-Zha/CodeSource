package com.pay.sdk.alan.tool;

import android.text.TextUtils;
import android.util.Log;

public class LogUtil {
	private static LogUtil instance;
	private static String TAG = "-----LogUtil-----";
	public static final int LOG_LEVEL_NONE = 0;
	public static final int LOG_LEVEL_DEBUG = 1;
	public static final int LOG_LEVEL_INFO = 2;
	public static final int LOG_LEVEL_WARN = 3;
	public static final int LOG_LEVEL_ERROR = 4;
	public static final int LOG_LEVEL_ALL = 5;
	private static int mLogLevel = 5;

	public static synchronized LogUtil getInstance(String Tag) {
		if (instance == null) {
			instance = new LogUtil(Tag);
		}
		if (!TextUtils.isEmpty(TAG)) {
			TAG = "";
		}
		TAG = "---YXF---" + Tag;
		return instance;
	}

	public LogUtil(String Tag) {
		init(Tag);
	}

	private void init(String Tag) {
		if (!TextUtils.isEmpty(TAG)) {
			TAG = "";
		}
		TAG = Tag;
	}

	public int getLogLevel() {
		return mLogLevel;
	}

	public static void setLogLevel(int level) {
		mLogLevel = level;
	}

	public void d(String msg) {
		if (getLogLevel() >= 1) {
			Log.d(TAG, msg);
		}
	}

	public void i(String msg) {
		if (getLogLevel() >= 2) {
			Log.i(TAG, msg);
		}
	}

	public void w(String msg) {
		if (getLogLevel() >= 3) {
			Log.w(TAG, msg);
		}
	}

	public void e(String msg) {
		if (getLogLevel() >= 4) {
			Log.e(TAG, msg);
		}
	}

	public void v(String msg) {
		if (getLogLevel() >= 5) {
			Log.v(TAG, msg);
		}
	}
}