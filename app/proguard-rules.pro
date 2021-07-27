

-keepattributes SourceFile,LineNumberTable

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-ignorewarnings

-dontskipnonpubliclibraryclassmembers
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keepattributes Signature
-keepattributes *Annotation*
-keep class org.apache.commons.**
-keep class org.dom4j.**
-keep class com.google.gdata.**
-keep class com.hp.hpl.sparta.**
-keep class demo.**
-keep class net.sourceforge.pinyin4j.**{ *; }
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class * extends android.view.View

-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**

-keepclasseswithmembers class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public void onClick(android.view.View);
}

-keepclassmembers class ** {
    public void onEvent*(**);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class com.jingwei.card.BaseWebViewActivity$JavascriptInterface{
    <methods>;
}

-keep class android.support.v4.**
-keep class android.support.v7.**
-dontwarn org.apache.commons.net.**
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-dontshrink
-dontoptimize

-keepclassmembers class * {
public <methods>;
}

#Tencent
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.tauth.**  {* ;}
-keep class com.tencent.open.**  {* ;}
-keep class com.tencent.**  {* ;}
-keep class MTT.ThirdAppInfoNew {
	*;
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.jwgson.stream.** { *; }
-keep class com.google.gson.** { *; }

#信鸽
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {* ;}
-keep class com.tencent.mid.**  {* ;}

#Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#Umeng

-keep class com.umeng.** {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class com.hywq.tomato.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#Rongyun
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
 public *;
}

-keepattributes Exceptions,InnerClasses

-keep class io.rong.** {*;}

-keep class * implements io.rong.imlib.model.MessageContent{*;}

-keep class com.google.gson.examples.android.model.** { *; }

-keepclassmembers class * extends com.sea_monster.dao.AbstractDao {
 public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.eclipse.jdt.annotation.**

-keep class com.ultrapower.** {*;}

-dontwarn io.rong.push.**
-keep class io.rong.push.** {*;}


#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.** {*;}


#okhttp
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**


#okio
-dontwarn okio.**
-keep class okio.** {*;}

#EventBus
-dontwarn org.greenrobot.eventbus.**
-keep class org.greenrobot.eventbus.** {*;}

#entity

#rx==========================================
-dontwarn com.trello.rxlifecycle.**
-keep class com.trello.rxlifecycle.** {*;}

-dontwarn com.trello.rxlifecycle.components.**
-keep class com.trello.rxlifecycle.components.** {*;}

-dontwarn rx.**
-keep class rx.** {*;}

-dontwarn com.jakewharton.rxbinding.**
-keep class com.jakewharton.rxbinding.** {*;}

-dontwarn retrofit2.**
-keep class retrofit2.** {*;}

-dontwarn android.support.multidex.**
-keep class android.support.multidex.** {*;}

-dontwarn junit.**
-keep class junit.**{*;}

-dontwarn org.junit.**
-keep class org.junit.**{*;}

-dontwarn com.github.lzyzsd.**
-keep class com.github.lzyzsd.**{*;}

-dontwarn org.hamcrest.**
-keep class org.hamcrest.**{*;}

-dontwarn com.google.android.flexbox.**
-keep class com.google.android.flexbox.**{*;}

-dontwarn com.jakewharton.disklrucache.**
-keep class com.jakewharton.disklrucache.**{*;}

-dontwarn android.support.design.**
-keep class android.support.design.**{*;}

-dontwarn android.support.graphics.drawable.**
-keep class android.support.graphics.drawable.**{*;}

-dontwarn com.litesuits.orm.**
-keep class com.litesuits.orm.**{*;}

-keepclassmembers class android.support.v4.widget.SwipeRefreshLayout{
  private void setRefreshing(boolean,boolean);
}

#-keep class com.taobao.** {*;}
#-keep class com.alibaba.** {*;}
#-keep class com.alipay.** {*;}
#-dontwarn com.taobao.**
#-dontwarn com.alibaba.**
#-dontwarn com.alipay.**
#-keep class com.ut.** {*;}
#-dontwarn com.ut.**
#-keep class com.ta.** {*;}
#-dontwarn com.ta.**
#-keep class com.alibaba.sdk.android.feedback.** {*;}

# Ali Feedback
-keep class com.alibaba.sdk.android.feedback.impl.FeedbackServiceImpl {*;}
-keep class com.alibaba.sdk.android.feedback.impl.FeedbackAPI {*;}
-keep class com.alibaba.sdk.android.feedback.util.IWxCallback {*;}
-keep class com.alibaba.sdk.android.feedback.util.IUnreadCountCallback{*;}
-keep class com.alibaba.sdk.android.feedback.FeedbackService{*;}
-keep public class com.alibaba.mtl.log.model.LogField {public *;}
-keep class com.taobao.securityjni.**{*;}
-keep class com.taobao.wireless.security.**{*;}
-keep class com.ut.secbody.**{*;}
-keep class com.taobao.dp.**{*;}
-keep class com.alibaba.wireless.security.**{*;}
-keep class com.ta.utdid2.device.**{*;}

# GT
-dontwarn com.igexin.**
-keep class com.igexin.** { *; }
-keep class org.json.** { *; }

# Alipay
#-libraryjars libs/alipaySingle-20160825.jar
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

# Glide OkHttp
-keep class com.bumptech.glide.integration.okhttp3.OkHttpGlideModule

# Mars
-keep class com.tencent.mars.** {
  *;
}

# 华为
-dontwarn com.huawei.hms.**
-keep class com.huawei.hms.** { *; }
-keep class com.huawei.android.** { *; }
-dontwarn com.huawei.android.**
-keep class com.hianalytics.android.** { *; }
-dontwarn com.hianalytics.android.**
-keep class com.huawei.updatesdk.** { *; }
-dontwarn com.huawei.updatesdk.**
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

# 小米
-keep class com.xiaomi.** { *; }
-dontwarn com.xiaomi.push.**
-keep class org.apache.thrift.** { *; }
-dontwarn com.xiaomi.push.**
-keep class com.xiaomi.push.** { *; }

# 魅族
-keep class com.meizu.** { *; }
-dontwarn com.meizu.**
-dontwarn com.meizu.cloud.**
-keep class com.meizu.cloud.** { *; }

#vivo
-dontwarn com.vivo.push.**
-keep class com.vivo.push.**{*; }
-keep class com.vivo.vms.**{*; }

#oppo
-keep class com.coloros.mcssdk.** { *; }
 -dontwarn com.coloros.mcssdk.**
-dontwarn com.coloros.mcsdk.**
-keep class com.coloros.mcsdk.** { *; }

-dontwarn com.heytap.**
-keep class com.heytap.** { *; }

-dontwarn com.mcs.**
-keep class com.mcs.** { *; }

#微博混淆配置
-keep class com.sina.weibo.sdk.** { *; }