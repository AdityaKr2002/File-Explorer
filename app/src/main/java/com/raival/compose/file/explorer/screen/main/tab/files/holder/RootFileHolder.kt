package com.raival.compose.file.explorer.screen.main.tab.files.holder

import com.raival.compose.file.explorer.App.Companion.globalClass
import com.raival.compose.file.explorer.R
import com.raival.compose.file.explorer.common.extension.emptyString
import com.raival.compose.file.explorer.screen.main.tab.files.misc.ContentCount
import java.io.File

class RootFileHolder : ContentHolder() {
    companion object {
        const val rootDir = "/"
    }

    val virtualRootContent = listOf(
        "/acct", "/apex", "/bin", "/cache", "/config", "/data", "/dev", "/etc",
        "/mnt", "/odm", "/oem", "/proc", "/product", "/sbin", "/sdcard",
        "/storage", "/system", "/vendor"
    )

    var contentsCount = ContentCount()
    val content = arrayListOf<LocalFileHolder>()

    override val uniquePath = rootDir
    override val displayName = globalClass.getString(R.string.root_dir)
    override val details = emptyString
    override val icon = R.drawable.baseline_folder_24
    override val iconPlaceholder = R.drawable.baseline_folder_24
    override val isFolder = true
    override val lastModified = 0L
    override val size = 0L
    override val extension = emptyString
    override val canAddNewContent = false
    override val canRead = true
    override val canWrite = false

    override suspend fun listContent(): ArrayList<out ContentHolder> {
        val content = ArrayList<LocalFileHolder>()

        virtualRootContent.forEach { item ->
            File(item).let { file ->
                if (file.exists()) {
                    content.add(LocalFileHolder(file))
                }
            }
        }

        contentsCount = ContentCount(folders = content.size)

        return content
    }

    override fun getParent() = null

    override fun getContentCount() = contentsCount

    override fun findFile(name: String) = content.find { it.displayName == name }

    override fun isValid() = true
}