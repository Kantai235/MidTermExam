# 期中考作業，有點懶得寫說明，所以期中考那天再回來寫 README.md

# 題目要求
![Demo](images/1.png)

# 成品畫面
![Demo](images/2.png)

# 移除 ActionBar
為了客製化 ToolBar，讓我們可以自定義 ToolBar 的樣式及功能，所以我們必須定義全部 Layout 不要 ActionBar，因此要優改 res/values/styles.xml：

```xml
<resources>
    <!-- Base application theme. -->
    <!-- 設定應用程式不要顯示出 Toolbar -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
</resources>
```

# 引入參考
以及因為個人習慣性的引入一些好用的參考，請在 build.gradle (Module:app) 當中加入：

```
dependencies {

    // Code ...

    // Bootstrap
    // https://github.com/Bearded-Hen/Android-Bootstrap
    compile 'com.beardedhen:androidbootstrap:2.3.0'

    // HTextView 動畫
    // https://github.com/hanks-zyh/HTextView
    compile 'hanks.xyz:htextview-library:0.1.5'

    // Navigation Bar UI
    // https://github.com/roughike/BottomBar
    compile 'com.roughike:bottom-bar:2.0.2'
}
```

# 製作載入畫面
請多拉一個 Empty Activity，並在 AndroidManifest.xml 指定它為起始畫面，然後就開始排版，比較需要注意的是 ImageView 滿版的方法及引用 HTextView 的使用方法：

```xml
<ImageView
    ...
    指定顯示方式為滿版
    android:scaleType="centerCrop" />

<com.hanks.htextview.HTextView
    ...
    指定顯示動畫的方式
    htext:animateType="typer" />
```

再來新增點擊事件，如果畫面被點擊了，就刷新顯示的內容，如果內容已經顯示完了，那就跳轉到主頁面去。

```Java
public void splashScreenClick(View view) {
    mCounter++;
    if (mCounter < sentences.length) {
        hTextView.animateText(sentences[mCounter]);
    } else {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        this.mediaPlayer.stop();
    }
}
```

不過顯示的內容可以從 res/values/strings.xml 當中讀取，無論是字串還是陣列，我們首先要到 xml 去定義資料：

```xml
<!-- 定義字串 -->
<string name="string-name">...</string>

<!-- 定義陣列 -->
<string-array name="string-array-name">
    <item>...</item>
</string-array>
```

然後在 Java 當中直接利用 getResources() 的方法，即可取得 strings.xml 當中的值了：

```Java
// 取得字串
getResources().getString(R.string.string-name);
// 取得陣列
getResources().getStringArray(R.array.string-array-name);
```

# 主要畫面

根據題型的指示，有非常多種做法，像是用 GrilLayout 來達成目的，但我比較偏向於較複雜的做法，因為可客製化的內容也較寬廣，利用 LinearLayout 的方式來切版，再利用 RelativeLayout 來做方格內較為特殊的排版方式，整理完後的 xml 大概如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout ... >

    <android.support.v7.widget.Toolbar> ... </android.support.v7.widget.Toolbar>

    <LinearLayout ...>

        <LinearLayout ... >

            <RelativeLayout ... >
                <ImageView ... />
                <TextView ... />
            </RelativeLayout>

            <LinearLayout ... >

                <RelativeLayout ... >
                    <ImageView ... />
                    <TextView ... />
                </RelativeLayout>

                <RelativeLayout ... >
                    <ImageView ... />
                    <TextView ... />
                </RelativeLayout>
                
            </LinearLayout>
        </LinearLayout>

        <LinearLayout ...>

            <RelativeLayout>
                <ImageView ... />
                <TextView ... />
            </RelativeLayout>

            <RelativeLayout>
                <ImageView ... />
                <TextView ... />
            </RelativeLayout>

            <RelativeLayout>
                <ImageView ... />
                <TextView ... />
            </RelativeLayout>

        </LinearLayout>

        <com.roughike.bottombar.BottomBar ... />

    </LinearLayout>
</LinearLayout>
```