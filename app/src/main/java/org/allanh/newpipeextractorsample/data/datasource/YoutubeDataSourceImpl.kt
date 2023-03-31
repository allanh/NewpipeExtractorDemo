package org.allanh.newpipeextractorsample.data.datasource

import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor

class YoutubeDataSourceImpl : YoutubeDataSource {
    override suspend fun fetchYoutubeStream(url: String): String {
        var streamUrl: String = ""
        try {
            val extractor =
                YoutubeService(0).getStreamExtractor(url) as YoutubeStreamExtractor
            extractor.fetchPage()

            if (extractor.videoStreams.isNotEmpty()) {
                streamUrl = extractor.videoStreams.first().url ?: ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return streamUrl
    }
}