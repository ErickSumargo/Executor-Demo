package com.bael.executor.demo.lib.threading.executor.implementation

import com.bael.executor.demo.lib.threading.executor.concurrent.contract.ConcurrentExecutor
import com.bael.executor.demo.lib.threading.executor.conflated.contract.ConflatedExecutor
import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.queue.contract.QueueExecutor
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Concurrent
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Conflated
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Queue
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class ExecutorImpl @Inject constructor(
    conflatedExecutor: ConflatedExecutor,
    queueExecutor: QueueExecutor,
    concurrentExecutor: ConcurrentExecutor
) : Executor,
    ConflatedExecutor by conflatedExecutor,
    QueueExecutor by queueExecutor,
    ConcurrentExecutor by concurrentExecutor {

    override suspend fun <T> execute(
        schema: ExecutorSchema,
        block: suspend () -> T
    ): T {
        return when (schema) {
            is Conflated -> conflate(block)
            is Queue -> enqueue(block)
            is Concurrent -> run(block)
        }
    }
}
