package com.example.flexboxitemcenter.activity

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flexboxitemcenter.R
import com.example.flexboxitemcenter.adapter.FlexItemAdapter
import com.example.flexboxitemcenter.databinding.ActivityMainBinding
import com.example.flexboxitemcenter.flexbox.FlexboxLayoutManager
import com.example.flexboxitemcenter.flexbox.JustifyContent
import com.google.android.flexbox.FlexItemClickListener


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FlexItemAdapter
    var height = 0
    var width = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getDeviceWidth()
        setRecyclerView()
    }

    private fun getDeviceWidth() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
    }

    private fun setRecyclerView() {

        val flexboxLayoutManager = FlexboxLayoutManager(this)
        flexboxLayoutManager.justifyContent = JustifyContent.CENTER
        binding.recyclerview.layoutManager = flexboxLayoutManager
        adapter = FlexItemAdapter(this, object : FlexItemClickListener {
            override fun onClick(v: View, pos: Int) {
                Toast.makeText(this@MainActivity, "click", Toast.LENGTH_SHORT).show()
            }
        })
        binding.recyclerview.adapter = adapter


        binding.addFab.setOnClickListener {
            //want only three item in one row
            var itemWidth = (width / 3)
            itemWidth -= 10
            var lp = FlexboxLayoutManager.LayoutParams(
                itemWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            lp.setMargins(0, 0, 10, 0)
            adapter.addItem(lp)
        }
        binding.removeFab.setOnClickListener(View.OnClickListener {
            if (adapter.itemCount == 0) {
                return@OnClickListener
            }
            adapter.removeItem(adapter.itemCount - 1)
        })
    }
}