package com.tokoy.tosa.tarakain.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.models.Category
import com.tokoy.tosa.tarakain.models.Store

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setCategoryList() {
//        val categoryList = arrayListOf<Category>(
//            Category(1, getString(R.string.all)),
//            Category(2, getString(R.string.pinoy)),
//            Category(3, getString(R.string.american)),
//            Category(4, getString(R.string.japanese)),
//            Category(5, getString(R.string.korean)),
//            Category(6, getString(R.string.chinese)),
//            Category(7, getString(R.string.italian)),
//            Category(8, getString(R.string.mexican)),
//            Category(9, getString(R.string.thai)),
//            Category(10, getString(R.string.latest)),
//            Category(11, getString(R.string.recommended)),
//            Category(12, getString(R.string.five_star)),
//            Category(13, getString(R.string.buffet)),
//            Category(14, getString(R.string.cafe)),
//            Category(15, getString(R.string.fast_food))
//        )

//        val store = Store(
//            1,
//            "Pinoy",
//            arrayListOf(categoryList(1)),
//
//        )
    }
}
