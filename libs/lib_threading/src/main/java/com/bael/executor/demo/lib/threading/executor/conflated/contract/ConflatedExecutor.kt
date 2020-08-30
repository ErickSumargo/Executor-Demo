package com.bael.executor.demo.lib.threading.executor.conflated.contract

/**
 * Created by ErickSumargo on 01/09/20.
 */

interface ConflatedExecutor {

    suspend fun <T> conflate(block: suspend () -> T): T
}
