package com.example.ndkhello;


public class JniDemo {
	
	//����һ�����ط���
	public native void sayHello();
	
	public static void main(String[] args){
		//���ö�̬���ӿ�
		System.loadLibrary("AndroidDell");//LibraryDll-----JniDemo
		JniDemo jniDemo = new JniDemo();
		jniDemo.sayHello();
		
	}
	
//	public void testNative(){
//		try {
//			//���ö�̬���ӿ�
//			System.loadLibrary("JniDemo");//LibraryDll-----JniDemo
//			JniDemo jniDemo = new JniDemo();
//			jniDemo.sayHello();
//		} catch (Exception e) {
//			Log.e("mainHHHH", "error = " + e.getMessage());
//		}
//	}
}

