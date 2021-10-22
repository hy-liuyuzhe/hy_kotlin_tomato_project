#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_hywq_tomato_fragment_HomeFragment_stringFromJNI(JNIEnv *env, jobject thiz) {
    int * p = NULL;
    *p = 100;
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}