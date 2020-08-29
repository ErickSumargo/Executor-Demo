package com.bael.executor.demo.lib.threading.executor.queue

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class QueueExecutor @Inject constructor() {
    private val mutex: Mutex = Mutex()

    suspend fun <T> enqueue(block: suspend () -> T): T {
        mutex.withLock {
            return block()
        }
    }
}
