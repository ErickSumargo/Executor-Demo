package com.bael.executor.demo.lib.base.dispatcher

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bael.executor.demo.lib.base.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/09/20.
 */

@ExperimentalCoroutinesApi
abstract class BaseDispatcher<S>(private val viewModel: BaseViewModel<S>) {
    private val statesObserver: Observer<Pair<S?, S>> get() = dispatchStates()

    fun observe(lifecycleOwner: LifecycleOwner) {
        viewModel.states.observe(lifecycleOwner, statesObserver)
    }

    protected abstract fun dispatchStates(): Observer<Pair<S?, S>>

    open fun clear(): Unit = Unit
}
