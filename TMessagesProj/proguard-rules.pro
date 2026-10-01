# AyuGram ProGuard Rules
# No obfuscation per R6 - but keep for optimization

# ============ GENERAL ============
-dontoptimize
-dontobfuscate
-dontpreverify
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

# Keep all public classes and members
-keep public class * {
    public protected *;
}

# Keep all classes with native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep setters in Views so that animations can still work.
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}

# Keep Parcelable implementations
-keep class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Keep Enum values
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# ============ GOOGLE PLAY SERVICES ============
-keep class com.google.android.gms.** { *; }
-keep class com.google.firebase.** { *; }

# ============ ROOM ============
-keep class androidx.room.** { *; }
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *

# ============ GSON ============
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# ============ OKHTTP ============
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# ============ EXOPLAYER ============
-keep class com.google.android.exoplayer2.** { *; }
-dontwarn com.google.android.exoplayer2.**

# ============ MAPLIBRE ============
-keep class org.maplibre.** { *; }
-dontwarn org.maplibre.**

# ============ NANOHTTPD ============
-keep class fi.iki.elonen.** { *; }

# ============ KOTLIN ============
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }

# ============ AYUGRAM SPECIFIC ============
-keep class com.tech.ayugram.** { *; }
-keep class org.telegram.messenger.** { *; }
-keep class org.telegram.tgnet.** { *; }
-keep class org.telegram.ui.** { *; }

# TLRPC classes (generated)
-keep class org.telegram.tgnet.TLRPC** { *; }

# ============ STUB CLASSES (Google Play Services replacements) ============
-keep class com.google.android.gms.tasks.** { *; }
-keep class com.google.android.gms.cast.** { *; }
-keep class com.google.android.gms.cast.framework.** { *; }
-keep class com.google.android.gms.wallet.** { *; }
-keep class com.google.android.gms.wearable.** { *; }
-keep class com.google.android.gms.auth.api.signin.** { *; }
-keep class com.google.android.gms.common.api.** { *; }
-keep class com.google.android.play.core.integrity.** { *; }
-keep class com.google.firebase.messaging.** { *; }
-keep class com.stripe.android.** { *; }
-keep class com.android.billingclient.api.** { *; }
-keep class com.tech.ayugram.play.stub.** { *; }

# ============ WORKMANAGER ============
-keep class androidx.work.** { *; }

# ============ LIFECYCLE ============
-keep class androidx.lifecycle.** { *; }

# ============ SUPPORT LIBRARY ============
-keep class androidx.** { *; }

# ============ LINE NUMBERS ============
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# ============ ANNOTATIONS ============
-keepattributes *Annotation*,EnclosingMethod,InnerClasses,Signature