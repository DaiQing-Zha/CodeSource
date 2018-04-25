LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NDKHello
LOCAL_SRC_FILES := NDKHello.cpp
LOCAL_SRC_FILES := com_example_ndkhello_JniTest.c
include $(BUILD_SHARED_LIBRARY)


