package com.bael.executor.demo.lib.threading.executor.queue.contract

/**
 * Created by ErickSumargo on 01/09/20.
 */

interface QueueExecutor {

    suspend fun <T> enqueue(block: suspend () -> T): T
}
