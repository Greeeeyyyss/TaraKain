package com.tokoy.tosa.tarakain.ui

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
import com.tokoy.tosa.tarakain.utils.Constants
import com.tokoy.tosa.tarakain.utils.EventObserver
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.utils.getRandomColor
import com.tokoy.tosa.tarakain.viewmodels.StoreOfTheDayViewModel

class StoreOfTheDayFragment : Fragment() {
    private lateinit var binding: FragmentStoreOfTheDayBinding
    private var handler: Handler?  = null
    private val viewModel: StoreOfTheDayViewModel by viewModels {
        InjectorUtils.provideStoreOfTheDayViewModelFactory(requireContext())
    }
    private val args: StoreOfTheDayFragmentArgs by navArgs()

    private val dices = arrayOf(
        R.drawable.ic_dice_1,
        R.drawable.ic_dice_2,
        R.drawable.ic_dice_3,
        R.drawable.ic_dice_4,
        R.drawable.ic_dice_5
    )

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

        viewModel.isFavorites = args.isFavorites
        binding.viewModel = viewModel

        handler = Handler()

        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.getStores().observe(viewLifecycleOwner, Observer { storeList ->
            viewModel.storeList = storeList

            if (storeList.isEmpty()) {
                // TODO empty state
            } else {
                val store = viewModel.getRandomStore()
                binding.textStore.text = store.name
                binding.textCategory.text = store.category?.name
            }
        })

        viewModel.onRandomizedEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                onRandomizeClick()
            }
        })

        viewModel.onCheckStoresEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                val bundle = Bundle()
                bundle.putBoolean(Constants.Key.isFavorites, args.isFavorites)
                findNavController().navigate(R.id.action_storeOfTheDay_to_storeList, bundle)
            }
        })
    }

    private fun onRandomizeClick() {
        Thread(Runnable {
            var i = 0
            while (i < 20) {
                val store = viewModel.getRandomStore()
                Thread.sleep(Constants.Duration.randomize)
                handler?.post {
                    binding.textStore.text = store.name
                    binding.textCategory.text = store.category?.name
                    binding.imgDice.setImageResource(dices.random())
                    binding.imgDice.setColorFilter(getRandomColor())
                }
                i++
            }
        }).start()
    }
}