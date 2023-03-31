package org.allanh.newpipeextractorsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.allanh.newpipeextractorsample.utils.PipeDownloader
import org.schabi.newpipe.extractor.NewPipe
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var pipeDownloader: PipeDownloader

    override fun onCreate() {
        super.onCreate()
        NewPipe.init(pipeDownloader)
    }
}