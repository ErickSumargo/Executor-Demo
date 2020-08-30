package com.bael.executor.demo.lib.threading.executor.queue.implementation

import com.bael.executor.demo.lib.threading.executor.queue.contract.QueueExecutor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class QueueExecutorImpl @Inject constructor() : QueueExecutor {
    private val mutex: Mutex = Mutex()

    override suspend fun <T> enqueue(block: suspend () -> T): T {
        mutex.withLock {
            return block()
        }
    }
}
