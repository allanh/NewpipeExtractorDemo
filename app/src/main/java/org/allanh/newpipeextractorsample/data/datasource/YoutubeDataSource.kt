package org.allanh.newpipeextractorsample.data.datasource

interface YoutubeDataSource {
    suspend fun fetchYoutubeStream(url: String): String
}