package com.bael.executor.demo.screen

/**
 * Created by ErickSumargo on 01/09/20.
 */

interface Renderer {

    fun renderSummary(task: Int)

    fun renderCTA(type: String, task: Int)

    fun renderProgress(progress: String)
}
