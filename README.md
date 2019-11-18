# RadioGroupMultipleSelection
RadioGroupMultipleSelection

可单选和多选，可以取消选择的的RadioGroup 实时返回选择结果 选择按钮宽度自适应

![截图](https://raw.githubusercontent.com/9lala/RadioGroupMultipleSelection/master/screenshot/GIF.gif)

# 使用方法

```
 引入依赖
 
 implementation 'com.github.9lala:RadioGroupMultipleSelection:1.1'
```
```
  <cn.photon.multiple.RadioGroupForMarketLayout xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/rg_1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        app:layoutContent="选择一,选择二,选择三,选择四,选择一百一十一"
        app:layoutTitle="单选" />
  <cn.photon.multiple.RadioGroupForMarketLayout xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/rg_2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        app:layoutContent="选择一,选择二,选择三,选择四,选择五,选择六,选择一百一十一"
        app:layoutTitle="多选"
        app:layoutType="multiple_selection" />
```

