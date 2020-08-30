package com.bael.executor.demo.screen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.bael.executor.demo.lib.base.viewmodel.BaseViewModel
import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Concurrent
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Conflated
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Queue
import com.bael.executor.demo.lib.threading.util.Util.DefaultThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import androidx.hilt.Assisted as HiltAssisted

/**
 * Created by ErickSumargo on 01/09/20.
 */

@ExperimentalCoroutinesApi
class ViewModel @ViewModelInject constructor(
    initState: State,
    executor: Executor,
    @HiltAssisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<State>(initState, savedStateHandle, executor) {

    fun setType(type: String) {
        val newState = State().apply {
            this.type = type
            this.task = state.task
        }
        render(newState)
    }

    fun assignTask(task: Int) {
        val newState = State().apply {
            this.type = state.type
            this.task = task
        }
        render(newState)
    }

    fun launchTask(type: String, task: Int) = launch(
        thread = DefaultThread,
        schema = when (type) {
            "Conflated" -> Conflated
            "Queue" -> Queue
            else -> Concurrent
        }
    ) {
        (5 downTo 0).forEach { turn ->
            val progress = "Task-$task completes in $turn".takeIf {
                turn > 0
            } ?: "Task-$task completed"

            val newState = State().apply {
                this.type = state.type
                this.task = state.task
                this.progress = progress
            }
            render(newState)

            delay(timeMillis = 1000L)
        }
    }
}
