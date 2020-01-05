package com.tokoy.tosa.tarakain.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentStoreOfTheDayBinding
import com.tokoy.tosa.tarakain.utils.Constants
import java.util.Random

class StoreOfTheDayFragment : Fragment() {
    private lateinit var binding: FragmentStoreOfTheDayBinding
    private var stores = arrayOf<String>()
    private var handler: Handler?  = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_store_of_the_day,
            container,
            false
        )
        getStores()
        handler = Handler()

        binding.btnRandomize.setOnClickListener {
            onRandomizeClick()
        }
        binding.btnChoose.setOnClickListener {
            onChooseClick()
        }
        binding.textStore.text = stores.random()
        return binding.root
    }

    private fun getStores() {
        val pinoy = resources.getStringArray(R.array.pinoy_resto) ?: arrayOf()
        val korean = resources.getStringArray(R.array.korean) ?: arrayOf()
        val japanese = resources.getStringArray(R.array.japanese_resto) ?: arrayOf()
        val american = resources.getStringArray(R.array.american) ?: arrayOf()
        val cafe = resources.getStringArray(R.array.cafe) ?: arrayOf()

        stores = stores.plus(pinoy)
        stores = stores.plus(korean)
        stores = stores.plus(japanese)
        stores = stores.plus(american)
        stores = stores.plus(cafe)
    }

    private fun onRandomizeClick() {
        Thread(Runnable {
            var i = 0
            while (i < 20) {
                val store = stores.random()
                val img = arrayOf(
                    R.drawable.ic_dice_1,
                    R.drawable.ic_dice_2,
                    R.drawable.ic_dice_3,
                    R.drawable.ic_dice_4,
                    R.drawable.ic_dice_5
                )
                Thread.sleep(Constants.Duration.randomize)
                handler?.post {
                    binding.textStore.text = store
                    binding.imgStore.setImageResource(img.random())
                    val rnd = Random()
                    val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                    binding.imgStore.setColorFilter(color)
                }
                i++
            }
        }).start()
    }

    private fun onChooseClick() {
        // TODO
    }
}