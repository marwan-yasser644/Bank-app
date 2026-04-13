# ProGuard/R8 Configuration for SmartBank

# Keep all classes in the app package
-keep class com.marwan.smartbank.** { *; }

# Keep Firebase classes
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Keep Retrofit classes
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }

# Keep Hilt classes
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.HiltAndroidApp class *

# Keep Room database classes
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class *

# Keep LiveData and ViewModel
-keep class androidx.lifecycle.** { *; }

# Keep Gson classes
-keep class com.google.gson.** { *; }

# Keep RxJava classes
-keep class io.reactivex.rxjava3.** { *; }

# Keep serialized objects
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve line numbers for crash reporting
-keepattributes SourceFile,LineNumberTable

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
