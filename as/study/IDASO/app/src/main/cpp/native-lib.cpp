#include <jni.h>
#include <iostream>
#include <string>
#include <cstring>
#include <malloc.h>
#include  <android/log.h>
#define TAG "CCC"
#include <sstream>

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

//https://www.jianshu.com/p/cb3064450688支持c++11
using namespace std;

const int is_number(const char *str);
const char *get_encrypt_str(const char *str);

template <typename T>
std::string to_string(T value)
{
    std::ostringstream os ;
    os << value ;
    return os.str() ;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_zha_jxnu_idaso_MainActivity_isEquals(JNIEnv *env, jobject instance, jstring str_) {

    const char *strAry = env->GetStringUTFChars(str_, 0);

    int len = strlen(strAry);
    char* dest = (char*)malloc(len);
    strcpy(dest,strAry);
    LOGI("########## strAry = %s", strAry);
    int number = is_number(strAry);
    LOGI("########## number = %d", number);
    if (number == 0){
        return 0;
    }
    std::cout << "Value1 of str is : " << strAry << endl;
//    LOGI(strAry);
    const char *encry_str = get_encrypt_str(strAry);
//    LOGI(strAry);

    const char *pas = "ssBCqpBssP000";
    int result = strcmp(pas,encry_str);

    env->ReleaseStringUTFChars(str_, strAry);

    if (result == 0){
        return 1;
    } else{
        return 0;
    }
}



const int is_number(const char *str) {
    LOGI("########## strlen(str) = %d", strlen(str));
    if (strlen(str) > 5){
        return 1;
    } else{
        return 0;
    }
}
const char *get_encrypt_str(const char *str){

    char* pc = new char[100];//足够长
    strcpy(pc,str);
    std::string value = pc;
    value += "000";
    return value.data();
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zha_jxnu_idaso_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
