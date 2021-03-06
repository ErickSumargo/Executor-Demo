package com.bael.executor.demo.lib.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bael.executor.demo.lib.threading.contract.Threading
import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Concurrent
import com.bael.executor.demo.lib.threading.util.Util.DefaultThread
import com.bael.executor.demo.lib.threading.util.Util.MainThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by ErickSumargo on 01/09/20.
 */

@ExperimentalCoroutinesApi
abstract class BaseViewModel<S>(
    initState: S,
    private val savedStateHandle: SavedStateHandle,
    private val executor: Executor,
    override val coroutineContext: CoroutineContext = DefaultThread
) : ViewModel(),
    Threading {
    private val restoredState: S = savedStateHandle.get(KEY_SAVED_STATE) ?: initState

    private val mutableStateFlow: MutableStateFlow<S> = MutableStateFlow(restoredState)
    private val mutableStateData: MutableLiveData<Pair<S?, S>> = MutableLiveData()

    internal val states: LiveData<Pair<S?, S>> get() = mutableStateData

    protected val state: S get() = mutableStateFlow.value

    init {
        observeState()
    }

    override fun <T> launch(
        thread: CoroutineContext,
        schema: ExecutorSchema,
        block: suspend CoroutineScope.() -> T
    ) {
        viewModelScope.launch(context = thread) {
            executor.execute(schema) { block() }
        }
    }

    private fun observeState() = launch(
        thread = MainThread,
        schema = Concurrent
    ) {
        mutableStateFlow.scan(null as S?) { previousState, newState ->
            mutableStateData.value = previousState to newState
            newState
        }.collect()
    }

    private fun saveState(newState: S) = launch(
        thread = MainThread,
        schema = Concurrent
    ) {
        savedStateHandle.set(KEY_SAVED_STATE, newState)
    }

    protected fun render(newState: S) {
        saveState(newState)
        mutableStateFlow.value = newState
    }

    companion object {
        private const val KEY_SAVED_STATE: String = "saved_state"
    }
}
