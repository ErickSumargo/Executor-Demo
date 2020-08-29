package com.bael.executor.demo.lib.threading.executor.conflated

import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.yield
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class ConflatedExecutor @Inject constructor() {
    private val activeTask: AtomicReference<Job?> = AtomicReference(null)

    suspend fun <T> conflate(block: suspend () -> T): T {
        return coroutineScope {
            val newTask = async(start = LAZY) { block() }.also { task ->
                task.invokeOnCompletion {
                    activeTask.compareAndSet(task, null)
                }
            }

            val result: T
            while (true) {
                if (!activeTask.compareAndSet(null, newTask)) {
                    activeTask.get()?.cancelAndJoin()
                    yield()
                } else {
                    result = newTask.await()
                    break
                }
            }
            result
        }
    }
}
