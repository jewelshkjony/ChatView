# Repackages optimized classes into %packageName%.repacked package
# in resulting AIX. Repackaging is necessary to avoid clashes with
# the other extensions that might be using same libraries as you.
-repackageclasses %packageName%.repacked

-android
-optimizationpasses 5
-allowaccessmodification
-mergeinterfacesaggressively
-overloadaggressively
-useuniqueclassmembernames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmember

# Custom Rules
-dontwarn androidx.**
-dontwarn androidx.recyclerview.widget.RecyclerView$ViewHolder
-dontwarn androidx.recyclerview.widget.RecyclerView
-dontwarn androidx.recyclerview.widget.RecyclerView$State
-dontnote androidx.**
-dontnote android.support.v4.**