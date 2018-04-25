package com.example.ndkhello;


public class JniDemo {
	
	//定义一个本地方法
	public native void sayHello();
	
	public static void main(String[] args){
		//调用动态链接库
		System.loadLibrary("AndroidDell");//LibraryDll-----JniDemo
		JniDemo jniDemo = new JniDemo();
		jniDemo.sayHello();
		
	}
	
//	public void testNative(){
//		try {
//			//调用动态链接库
//			System.loadLibrary("JniDemo");//LibraryDll-----JniDemo
//			JniDemo jniDemo = new JniDemo();
//			jniDemo.sayHello();
//		} catch (Exception e) {
//			Log.e("mainHHHH", "error = " + e.getMessage());
//		}
//	}
}

