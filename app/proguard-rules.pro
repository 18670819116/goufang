# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidSdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#百度ocr
-keep class com.baidu.ocr.sdk.**{*;}
-dontwarn com.baidu.ocr.**
#权限
-dontwarn com.yanzhenjie.permission.**
#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder

#阿里推送
     -keepclasseswithmembernames class ** {
         native <methods>;
     }
     -keepattributes Signature
     -keep class sun.misc.Unsafe { *; }
     -keep class com.taobao.** {*;}
     -keep class com.alibaba.** {*;}
     -keep class com.alipay.** {*;}
     -keep class com.ut.** {*;}
     -keep class com.ta.** {*;}
     -keep class anet.**{*;}
     -keep class anetwork.**{*;}
     -keep class org.android.spdy.**{*;}
     -keep class org.android.agoo.**{*;}
     -keep class android.os.**{*;}
     -dontwarn com.taobao.**
     -dontwarn com.alibaba.**
     -dontwarn com.alipay.**
     -dontwarn anet.**
     -dontwarn org.android.spdy.**
     -dontwarn org.android.agoo.**
     -dontwarn anetwork.**
     -dontwarn com.ut.**
     -dontwarn com.ta.**
#友盟推送
    -keep class com.umeng.** {*;}
    -keepclassmembers class * {
       public <init> (org.json.JSONObject);
    }
    -keepclassmembers enum * {
        public static **[] values();
        public static ** valueOf(java.lang.String);
    }
        -keep public class com.ljcs.cxwl.R$*{
        public static final int *;
        }
#万能适配器
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}