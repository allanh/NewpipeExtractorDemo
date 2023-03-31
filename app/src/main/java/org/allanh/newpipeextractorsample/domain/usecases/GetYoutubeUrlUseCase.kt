package org.allanh.newpipeextractorsample.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import org.allanh.newpipeextractorsample.data.UseCaseResult
import org.allanh.newpipeextractorsample.data.repository.YoutubeRepository
import org.allanh.newpipeextractorsample.di.IODispatcher
import org.allanh.newpipeextractorsample.domain.UseCase
import javax.inject.Inject

class GetYoutubeUrlUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository,
    @IODispatcher defaultDispatcher: CoroutineDispatcher
) : UseCase<GetYoutubeUrlUseCase.Params, UseCaseResult<String, Exception>>(defaultDispatcher) {

    override suspend fun runInternal(params: Params): UseCaseResult<String, Exception> {
        return try {
            val url = youtubeRepository.getYoutubeUrl(params.videoUrl)
            if (url != null) {
                UseCaseResult.Success(url)
            } else {
                UseCaseResult.Failure(Exception("Unable to get Youtube URL"))
            }
        } catch (e: Exception) {
            UseCaseResult.Failure(e)
        }
    }

    data class Params(
        val videoUrl: String
    )
}

