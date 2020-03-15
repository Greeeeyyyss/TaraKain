package com.tokoy.tosa.tarakain.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentStoreOfTheDayBinding
import com.tokoy.tosa.tarakain.db.models.Store
import com.tokoy.tosa.tarakain.utils.Constants
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModel
import java.util.Random

class StoreOfTheDayFragment : Fragment() {
    private lateinit var binding: FragmentStoreOfTheDayBinding
    private var stores = arrayOf<String>()
    private var handler: Handler?  = null

    private val viewModel: StoreViewModel by viewModels {
        InjectorUtils.provideStoreViewModelFactory(requireContext())
    }

    private val args: StoreOfTheDayFragmentArgs by navArgs()

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

        if (args.isFavorites) {
            getFavoriteStores()
        } else {
            getAllStores()
        }
        handler = Handler()

        binding.btnRandomize.setOnClickListener {
            onRandomizeClick()
        }
        return binding.root
    }

    private fun getAllStores() {
        viewModel.stores.observe(viewLifecycleOwner, Observer { allStore ->
            allStore?.let { stores ->
                setStores(stores)
            }
        })
    }

    private fun getFavoriteStores() {
        viewModel.favorites.observe(viewLifecycleOwner, Observer { favoriteStores ->
            favoriteStores?.let { stores ->
                setStores(stores)
            }
        })
    }

    private fun setStores(storeList: List<Store>) {
        val storeNames = storeList.map { store ->
            store.name
        }
        stores = storeNames.toTypedArray()
        if (stores.isNotEmpty()) {
            binding.textStore.text = stores.random()
        }
    }

    private fun onRandomizeClick() {
        if (stores.isNotEmpty()) {
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
    }
}