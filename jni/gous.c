/**
 * Created at 2020/7/21 13:54.
 * 
 * @author Liangcheng Juves
 */
#include "tcha.h"
#include "ansicol.h"

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_black(JNIEnv *env, jobject thiz, jobject jobj)
{
    blackprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_red(JNIEnv *env, jobject thiz, jobject jobj)
{
    redprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_green(JNIEnv *env, jobject thiz, jobject jobj)
{
    greenprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_yellow(JNIEnv *env, jobject thiz, jobject jobj)
{
    yellowprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_blue(JNIEnv *env, jobject thiz, jobject jobj)
{
    blueprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_fuchsia(JNIEnv *env, jobject thiz, jobject jobj)
{
    fuchsiaprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_cyan(JNIEnv *env, jobject thiz, jobject jobj)
{
    cyanprnt(jobj_tcstr(env, jobj));
}

JNIEXPORT void JNICALL
Java_com_meyoustu_amuse_gous_lang_System_white(JNIEnv *env, jobject thiz, jobject jobj)
{
    whiteprnt(jobj_tcstr(env, jobj));
}
