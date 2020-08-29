package com.bael.executor.demo.lib.threading.executor.implementation

import com.bael.executor.demo.lib.threading.executor.concurrent.ConcurrentExecutor
import com.bael.executor.demo.lib.threading.executor.conflated.ConflatedExecutor
import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.queue.QueueExecutor
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Concurrent
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Conflated
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema.Queue
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class DefaultExecutor @Inject constructor(
    private val conflatedExecutor: ConflatedExecutor,
    private val queueExecutor: QueueExecutor,
    private val concurrentExecutor: ConcurrentExecutor
) : Executor {

    override suspend fun execute(
        schema: ExecutorSchema,
        block: suspend () -> Unit
    ) {
        when (schema) {
            is Conflated -> {
                conflatedExecutor.conflate(block)
            }
            is Queue -> {
                queueExecutor.enqueue(block)
            }
            is Concurrent -> {
                concurrentExecutor.run(block)
            }
        }
    }
}
