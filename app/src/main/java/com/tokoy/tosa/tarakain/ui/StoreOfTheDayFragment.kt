package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentStoreOfTheDayBinding
import com.tokoy.tosa.tarakain.db.repo.StoreRepo
import com.tokoy.tosa.tarakain.di.Injectable
import com.tokoy.tosa.tarakain.utils.Constants
import com.tokoy.tosa.tarakain.utils.EventObserver
import com.tokoy.tosa.tarakain.utils.getRandomColor
import com.tokoy.tosa.tarakain.viewmodels.StoreOfTheDayViewModel
import javax.inject.Inject

class StoreOfTheDayFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentStoreOfTheDayBinding
    private var handler: Handler?  = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StoreOfTheDayViewModel
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
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(StoreOfTheDayViewModel::class.java)

        viewModel.isFavorites = args.isFavorites
        binding.viewModel = viewModel

        handler = Handler()

        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.getStores().observe(viewLifecycleOwner, Observer { storeList ->
            viewModel.storeList = storeList
            binding.showEmptyState = storeList.count() < 2

            if (storeList.isNotEmpty()) {
                val store = viewModel.getRandomStore()
                binding.textStore.text = store.name
                binding.textCategory.text = store.category?.name
            }
        })

        viewModel.onRandomizedEvent.observe(viewLifecycleOwner, EventObserver {
            if (it && viewModel.storeList.count() > 1) {
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

        viewModel.onAddStoreEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                val bundle = Bundle()
                bundle.putBoolean(Constants.Key.isFavorites, args.isFavorites)
                findNavController().navigate(R.id.action_storeOfTheDay_to_addStore, bundle)
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