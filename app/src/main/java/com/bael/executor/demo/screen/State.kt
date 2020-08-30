package com.bael.executor.demo.screen

import com.bael.executor.demo.lib.base.state.BaseState
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class State @Inject constructor() : BaseState() {
    internal var type: String = ""

    internal var task: Int = 0

    internal var progress: String = ""
}
