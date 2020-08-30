package com.bael.executor.demo.lib.base.ext

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bael.executor.demo.lib.base.delegate.FragmentViewBindingDelegate

/**
 * Created by ErickSumargo on 01/09/20.
 */

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory)
