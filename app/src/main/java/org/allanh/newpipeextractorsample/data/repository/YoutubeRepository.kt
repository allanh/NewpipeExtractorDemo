package org.allanh.newpipeextractorsample.data.repository

interface YoutubeRepository {
    suspend fun getYoutubeUrl(url: String): String?
}