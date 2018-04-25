package com.example.ndkhello;

public class JniTest {
	
	static {
	     System.loadLibrary("NDKHello");
	}
	
	public native int add(int a,int b);
}
