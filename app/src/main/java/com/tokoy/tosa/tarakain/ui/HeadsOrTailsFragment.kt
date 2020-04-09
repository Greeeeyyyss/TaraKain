package com.tokoy.tosa.tarakain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentHeadsOrTailsBinding
import com.tokoy.tosa.tarakain.di.Injectable
import com.tokoy.tosa.tarakain.utils.EventObserver
import com.tokoy.tosa.tarakain.viewmodels.HeadsOrTailsViewModel
import javax.inject.Inject

class HeadsOrTailsFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentHeadsOrTailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HeadsOrTailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_heads_or_tails,
            container,
            false
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HeadsOrTailsViewModel::class.java)
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.stores.observe(viewLifecycleOwner, Observer { stores ->
            if (stores.count() > 1) {
                viewModel.storeNames = stores.map { it.name }
                binding.showEmptyState = false
            } else {
                binding.showEmptyState = true
            }
            binding.viewModel = viewModel
        })

        viewModel.coinFlipEvent.observe(viewLifecycleOwner, EventObserver { coinFlipped ->
            if (coinFlipped) {
                onFlip()
            }
        })

        viewModel.addStoreEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                findNavController().navigate(R.id.action_headsOrTails_to_addStore)
            }
        })
    }

    private fun onFlip() {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 1000
        fadeOut.fillAfter = true
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                viewModel.randomize()

                val fadeIn = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator()
                fadeIn.duration = 3000
                fadeIn.fillAfter = true

                binding.btnFlip.alpha = 1f
                binding.imgCoin.startAnimation(fadeIn)
            }

            override fun onAnimationStart(animation: Animation?) {
                binding.btnFlip.alpha = 0.7f
            }
        })

        binding.imgCoin.startAnimation(fadeOut)
    }
}