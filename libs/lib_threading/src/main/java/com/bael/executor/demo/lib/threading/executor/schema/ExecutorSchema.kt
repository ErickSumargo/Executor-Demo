package com.bael.executor.demo.lib.threading.executor.schema

/**
 * Created by ErickSumargo on 01/09/20.
 */

sealed class ExecutorSchema {

    object Conflated : ExecutorSchema()

    object Queue : ExecutorSchema()

    object Concurrent : ExecutorSchema()
}
