# FlexboxItemCenter
### Overview Main Activity
In this Activity we shows recyclerview item.
<img src="https://github.com/sainivik/FlexboxItemCenter/blob/master/screenshots/flexbox_home_screen.png" width="300px" height="632px"/>
#### Usage

Add the following code to your view

```xml
       <androidx.recyclerview.widget.RecyclerView
             android:id="@+id/recyclerview"
             android:layout_width="match_parent"
             android:layout_height="match_parent" />
```

 the following code you can set adapter

```java
 val flexboxLayoutManager = FlexboxLayoutManager(this)
         flexboxLayoutManager.justifyContent = JustifyContent.CENTER
         binding.recyclerview.layoutManager = flexboxLayoutManager
         adapter = FlexItemAdapter(this, object : FlexItemClickListener {
             override fun onClick(v: View, pos: Int) {
                 Toast.makeText(this@MainActivity, "click", Toast.LENGTH_SHORT).show()
             }
         })
         binding.recyclerview.adapter = adapter
```


## Author

Vikas Saini

email: vikaskumarsaini0001@gmail.com

github: https://github.com/sainivik
