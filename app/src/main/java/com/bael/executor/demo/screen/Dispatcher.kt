package com.bael.executor.demo.screen

import androidx.lifecycle.Observer
import com.bael.executor.demo.lib.base.di.assisted.DispatcherFactoryAssisted
import com.bael.executor.demo.lib.base.dispatcher.BaseDispatcher
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/09/20.
 */

@FragmentScoped
@ExperimentalCoroutinesApi
class Dispatcher @AssistedInject constructor(
    @Assisted arg0: ViewModel,
    @Assisted arg1: Renderer
) : BaseDispatcher<State>(arg0),
    Renderer by arg1 {

    @AssistedInject.Factory
    interface Factory : DispatcherFactoryAssisted<ViewModel, Renderer, Dispatcher>

    override fun dispatchStates(): Observer<Pair<State?, State>> {
        return Observer { (previousState, newState) ->
            if (previousState?.type != newState.type ||
                previousState.task != newState.task) {
                renderCTA(newState.type, newState.task)
            }

            if (previousState?.task != newState.task) {
                renderSummary(newState.task)
            }

            if (previousState?.progress != newState.progress) {
                renderProgress(newState.progress)
            }
        }
    }
}
