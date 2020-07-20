# Common
-keepattributes Signature, InnerClasses
-keepattributes *Annotation*
-keepattributes Exceptions
-dontwarn javax.annotation.**

# Remove logs
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
}
-assumenosideeffects class timber.log.Timber {
    public static int tag(...);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
}

# Kotlin
-keep class kotlin.Metadata { *; }
-keep class kotlin.internal.annotations.AvoidUninitializedObjectCopyingCheck { *; }
-dontwarn kotlin.internal.annotations.AvoidUninitializedObjectCopyingCheck

## Dagger
-dontwarn com.google.errorprone.annotations.**

# Gson
-dontwarn sun.misc.**
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}