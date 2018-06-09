package com.pay.sdk.alan.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.os.Bundle;

/*
 * 系统辅助函数
 * */
public class HttpUtils {
	/**
	 * Default encoding for POST or PUT parameters. See
	 * {@link #getParamsEncoding()}.
	 */
	private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

	/**
	 * Supported request methods.
	 */
	public static final int HTTP_DEPRECATED_GET_OR_POST = -1;
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	// public static final int PUT = 2;
	// public static final int DELETE = 3;
	// public static final int HEAD = 4;
	// public static final int OPTIONS = 5;
	// public static final int TRACE = 6;
	// public static final int PATCH = 7;

	/** The default socket timeout in milliseconds */
	public static final int DEFAULT_TIMEOUT_MS = 15000;

	/** The default times of retrying */
	private static final int DEFAULT_RETRY_TIMES = 3;

	private static int mConnectTimeout = DEFAULT_TIMEOUT_MS;

	private static int mRetryTimes = DEFAULT_RETRY_TIMES;

	// Url Encode 將參數編碼
	public static String EncodeUrl(Bundle params) {
		if (params == null) {
			return "";
		}
		StringBuilder sBuilder = new StringBuilder();
		boolean first = true;
		for (String key : params.keySet()) {
			if (first) {
				sBuilder.append("?");
				first = false;
			} else {
				sBuilder.append("&");
			}
			String strKeyValue = params.getString(key);
			if (strKeyValue == null) {
				sBuilder.append(key + "=" + params.getInt(key));
			} else {
				sBuilder.append(key + "=" + params.getString(key));
			}
		}
		return sBuilder.toString();
	}

	// HTTP GET
	public static HttpResponse ExecuteHttpGet(String strUrl) {

		return performHttpRequest(strUrl, HTTP_GET, null);
	}

	// ///////////////////////////////////////
	// HTTP POST
	static HttpResponse ExecuteHttpPost(String strUrl, StringEntity entity) {
		HttpPost post = new HttpPost(strUrl);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			if (entity != null) {
				post.setEntity(entity);
			}
			return httpClient.execute(post);
		} catch (Exception e) {
		}
		return null;
	}

	// HTTP POST
	public static HttpResponse ExecuteHttpPost(String strUrl, String entity) {
		return performHttpRequest(strUrl, HTTP_POST, entity);
	}

	/**
	 * Execute the HTTP request and handle some errors in the HTTP request.
	 * 
	 * @param context
	 *            application context.
	 * @param strUrl
	 *            url address.
	 * @param requestMode
	 *            the mode of HTTP request.
	 * @param entity
	 *            the data will be posted to the server.
	 * @return HTTP response.
	 */
	public static HttpResponse performHttpRequest(String strUrl,
			int requestMode, String entity) {
		int retry = mRetryTimes;
		HttpResponse httpResponse = null;
		Map<String, String> responseHeaders = new HashMap<String, String>();
		while (retry > 0) {
			retry--;
			try {
				httpResponse = performRequest(requestMode, strUrl, entity);
				StatusLine statusLine = httpResponse.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				responseHeaders = convertHeaders(httpResponse.getAllHeaders());
				// Handle moved resources
				if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
						|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
					strUrl = responseHeaders.get("Location");
				}
				return httpResponse;
			} catch (ClientProtocolException e1) {
				e1.printStackTrace();
			} catch (SocketTimeoutException e) {
				e.printStackTrace();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			} catch (ConnectTimeoutException e) {
				e.printStackTrace();

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			} catch (IOException e1) {
				e1.printStackTrace();

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof UnknownHostException) {
				}

			}
		}
		return httpResponse;
	}

	/**
	 * Execute the HTTP request.
	 * 
	 * @param strUrl
	 *            url address.
	 * @param requestMode
	 *            the mode of HTTP request.
	 * @param entity
	 *            the data will be posted to the server.
	 * @return HTTP response.
	 */
	public static HttpResponse performRequest(int requestMode
			, String strUrl, String entity) throws ClientProtocolException, IOException,
			UnknownHostException {
		HttpUriRequest httpRequest = createHttpRequest(requestMode, strUrl,entity);
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpRequest.getParams();
		// TODO: Reevaluate this connection timeout based on more wide-scale
		// data collection and possibly different for wifi vs. 3G.
		HttpConnectionParams.setConnectionTimeout(httpParams, mConnectTimeout);
		HttpConnectionParams.setSoTimeout(httpParams, mConnectTimeout);
		return httpClient.execute(httpRequest);

	}

	/**
	 * Creates the appropriate subclass of HttpUriRequest for passed in request.
	 */
	@SuppressWarnings("deprecation")
	static HttpUriRequest createHttpRequest(int requestMode, String strUrl,
			String entity) {
		switch (requestMode) {
		case HTTP_DEPRECATED_GET_OR_POST:
			if (entity != null) {
				HttpPost postRequest = new HttpPost(strUrl);
				setEntityIfNonEmptyBody(postRequest, entity);
				return postRequest;
			} else {
				return new HttpGet(strUrl);
			}
		case HTTP_GET:
			return new HttpGet(strUrl);
		case HTTP_POST: {
			HttpPost postRequest = new HttpPost(strUrl);
			setEntityIfNonEmptyBody(postRequest, entity);
			return postRequest;
		}
		default:
			throw new IllegalStateException("Unknown request method.");
		}

	}

	private static void setEntityIfNonEmptyBody(
			HttpEntityEnclosingRequestBase httpRequest, String entity) {
		byte[] body = null;
		try {
			body = entity.getBytes(DEFAULT_PARAMS_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (body != null) {
			HttpEntity httpEntity = new ByteArrayEntity(body);
			httpRequest.setEntity(httpEntity);
		}
	}

	/**
	 * Converts Headers[] to Map<String, String>.
	 */
	private static Map<String, String> convertHeaders(Header[] headers) {
		Map<String, String> result = new HashMap<String, String>();
		for (int i = 0; i < headers.length; i++) {
			result.put(headers[i].getName(), headers[i].getValue());
		}
		return result;
	}

	/**
	 * 数据流转换String
	 * @param is
	 * @return
	 */
	static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null)
				sb.append(line + "\n");
		} catch (IOException e) {
			try {
				is.close();
			} catch (IOException e1) {
			}
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		return sb.toString();
	}

	/**
	 * 获取HttpResponse中的字符数据
	 * @param response
	 * @return
	 */
	public static String getResposeResult(HttpResponse response) {
		if (response == null) {
			return null;
		}
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			return null;
		}
		try {
			InputStream instream = entity.getContent();
			return convertStreamToString(instream);
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 解析逗号分割的字符串
	 * @param sentence
	 * @param separator
	 * @return
	 */
	static String[] parseCSVSentence(String sentence, char separator) {
		if (sentence == null) {
			return null;
		}
		String[] data = sentence.split(String.valueOf(separator));
		return data;
	}

	// 解析逗号分割的字符串
	/**
	 * Separate the string with the separative sign specified.
	 * 
	 * @param sentence
	 *            the string will be separated.
	 * @param separator
	 *            the separative sign.
	 * @return string array
	 */
	public static String[] parseCSVSentence(String sentence, String separator) {
		if (sentence == null) {
			return null;
		}
		if (!sentence.contains(separator)) {
			String filterword = extractString(sentence, "\"");
			return new String[] { filterword };
		}
		String[] data = sentence.split(separator);
		for (int i = 0; i < data.length; i++) {
			data[i] = extractString(data[i], "\"");
		}
		return data;
	}

	/**
	 * Extract the string between the first index and the last index of the sub
	 * in the parent string.
	 * 
	 * @param parentString
	 *            the main string.
	 * @param sub
	 *            the sub string.
	 * @return the string between the first index and the last index of the sub
	 *         in the parent string.
	 */
	public static String extractString(String parentString, String sub) {
		if (parentString == null) {
			return null;
		}
		if (sub == null) {
			return parentString;
		}
		int startIndex = parentString.indexOf(sub);
		int endIndex = parentString.lastIndexOf(sub);
		String subString = null;
		if (endIndex > startIndex && startIndex >= 0) {
			subString = parentString.substring(startIndex + 1, endIndex);
		} else {
			subString = parentString;
		}
		return subString;
	}

	/**
	 * 处理数据
	 * @param _json
	 * @param data
	 * @return
	 */
	public static String MakeToString(JSONObject _json, String data) {
		String _str = null;
		if (!_json.isNull(data))
			try {
				_str = _json.getString(data);
			} catch (Exception e) {
				return "";
			}
		if (_str == null)
			_str = "";
		return _str;
	}

	public static int MakeToInt(JSONObject _json, String data) {
		int _int = 0;
		if (!_json.isNull(data))
			try {
				_int = _json.getInt(data);
			} catch (Exception e) {
				return 0;
			}
		return _int;
	}

	// ///////////////////////////////////////////////////////////
	// END

	/**
	 * Set the timeout of network connecting associate with network request.
	 * 
	 * @param timeout
	 *            timeout in milliseconds.
	 */
	public static void setConnectTimeout(int timeout) {
		mConnectTimeout = timeout > 0 ? timeout : DEFAULT_TIMEOUT_MS;
	}

	/**
	 * Set the times of retrying while the network exception.
	 * 
	 * @param times
	 *            times of retrying.
	 */
	public static void setRetryTimes(int times) {
		mRetryTimes = times > 0 ? times : DEFAULT_RETRY_TIMES;

	}

}
