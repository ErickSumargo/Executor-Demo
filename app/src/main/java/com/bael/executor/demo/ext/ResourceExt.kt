package com.bael.executor.demo.ext

import android.content.Context

/**
 * Created by ericksumargo on 01/09/20.
 */

fun Context.textOf(
    resId: Int,
    vararg params: Any
): String {
    return getString(resId, *params)
}