package org.allanh.newpipeextractorsample.data.repository

import org.allanh.newpipeextractorsample.data.datasource.YoutubeDataSource
import javax.inject.Singleton

@Singleton
class YoutubeRepositoryImpl(private val youtubeDataSource: YoutubeDataSource) : YoutubeRepository {
    override suspend fun getYoutubeUrl(url: String): String? {
        val streamUrl = youtubeDataSource.fetchYoutubeStream(url)
        return streamUrl.ifEmpty {
            null
        }
    }
}