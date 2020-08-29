package com.bael.executor.demo.lib.base.di.assisted

/**
 * Created by ErickSumargo on 01/09/20.
 */

interface DispatcherFactoryAssisted<in VM, in R, in A, out D> {

    fun create(viewModel: VM, renderer: R, action: A): D
}
