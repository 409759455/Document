
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
#LOCAL_MODULE��ʾ���ɵĿ�����֣�ǰ���lib�ͺ�׺������д 
LOCAL_MODULE    := NDK_01
LOCAL_SRC_FILES := \HelloWorld.c

include $(BUILD_SHARED_LIBRARY)

#һ��Android.mk file���ȱ��붨���LOCAL_PATH������
#�������ڿ������в���Դ�ļ�������������У��꺯����my-dir��, �ɱ���ϵͳ�ṩ�����ڷ��ص�ǰ·����������Android.mk file�ļ���Ŀ¼����
 
#2��include $( CLEAR_VARS)
#CLEAR_VARS �ɱ���ϵͳ�ṩ��ָ����GNU MAKEFILEΪ��������LOCAL_XXX����
#������ LOCAL_MODULE, LOCAL_SRC_FILES, LOCAL_STATIC_LIBRARIES, �ȵ�...),��LOCAL_PATH ��
#���Ǳ�Ҫ�ģ���Ϊ���еı�������ļ�����ͬһ��GNU MAKEִ�л����У����еı�������ȫ�ֵġ�
 
#3��LOCAL_MODULE :=  HcSyncml
#LOCAL_MODULE�������붨�壬�Ա�ʶ����Android.mk�ļ���������ÿ��ģ�顣
#���Ʊ�����Ψһ�ģ����Ҳ��� ���κοո�ע�����ϵͳ���Զ��������ʵ�ǰ׺�ͺ�׺��
#���仰˵��һ��������Ϊ'HcSyncml'�Ĺ����ģ�飬��������'libHcSyncml.so'�ļ���
 
#4��LOCAL_C_INCLUDES := $(LOCAL_PATH)/extra_inc$(LOCAL_PATH)/main_inc
#LOCAL_C_INCLUDES �м�������Ҫ������ͷ�ļ�·��
 
#5��LOCAL_SRC_FILES
#LOCAL_SRC_FILES�м���Դ�ļ�·��(��Ҫ������ļ�),����ļ��� ��\�� ����
 
#6��LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib �Cllog
#��ʾ�����ӡLog
