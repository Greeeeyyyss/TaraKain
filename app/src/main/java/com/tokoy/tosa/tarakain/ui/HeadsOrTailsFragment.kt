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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tokoy.tosa.tarakain.R
import com.tokoy.tosa.tarakain.databinding.FragmentHeadsOrTailsBinding
import com.tokoy.tosa.tarakain.utils.InjectorUtils
import com.tokoy.tosa.tarakain.utils.getRandomColor
import com.tokoy.tosa.tarakain.viewmodels.StoreViewModel
import java.util.*

class HeadsOrTailsFragment : Fragment() {
    private lateinit var binding: FragmentHeadsOrTailsBinding
    private val viewModel: StoreViewModel by viewModels {
        InjectorUtils.provideStoreViewModelFactory(requireContext())
    }

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
        getAllStores()
        binding.btnFlip.setOnClickListener {
            onFlipClick()
        }
        return binding.root
    }

    private fun getAllStores() {
        viewModel.stores.observe(viewLifecycleOwner, Observer { stores ->
            if (stores.isNotEmpty()) {
                viewModel.storeNames = stores.map { it.name }
                viewModel.headIndex.set(0)
                viewModel.tailIndex.set(1)
                binding.viewModel = viewModel
            }
        })
    }

    private fun onFlipClick() {
        val heads = viewModel.storeNames[viewModel.headIndex.get() ?: 0]
        val tails = viewModel.storeNames[viewModel.tailIndex.get() ?: 0]

        if (heads.isNotEmpty() && tails.isNotEmpty()) {
            val fadeOut = AlphaAnimation(1f, 0f)
            fadeOut.duration = 1000
            fadeOut.fillAfter = true
            fadeOut.setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {

                    val bool = Random().nextBoolean()
                    if (bool) {
                        binding.imgStore.setImageResource(R.drawable.ic_dice_5)
                        binding.textStore.text = heads
                    } else {
                        binding.imgStore.setImageResource(R.drawable.ic_dice_1)
                        binding.textStore.text = tails
                    }

                    binding.imgStore.setColorFilter(getRandomColor())

                    val fadeIn = AlphaAnimation(0f, 1f)
                    fadeIn.interpolator = DecelerateInterpolator()
                    fadeIn.duration = 3000
                    fadeIn.fillAfter = true

                    binding.textStore.visibility = View.VISIBLE
                    binding.imgStore.startAnimation(fadeIn)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })

            binding.imgStore.startAnimation(fadeOut)
        }
    }
}