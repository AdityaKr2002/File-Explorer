package com.raival.compose.file.explorer.screen.main.tab.files.coil

import com.raival.compose.file.explorer.screen.main.tab.files.holder.DocumentHolder

fun canUseCoil(documentHolder: DocumentHolder): Boolean {
    return (documentHolder.isFile && documentHolder.isImage || documentHolder.isVideo || documentHolder.isApk || documentHolder.isPdf)
}