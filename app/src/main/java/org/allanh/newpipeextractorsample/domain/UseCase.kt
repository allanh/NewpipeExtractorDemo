package org.allanh.newpipeextractorsample.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Encapsulate operation as a re-usable use case to run in provided dispatcher.
 *
 * @property defaultDispatcher The dispatcher that will be used to run the operation.
 */
abstract class UseCase<in Param, out Return>(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    /**
     * A pre-defined operation that will be run in provided dispatcher when invoked.
     *
     * @param param Parameters that the operation requires.
     */
    protected abstract suspend fun runInternal(param: Param): Return

    /**
     * Invoke pre-defined operation in provided dispatcher.
     *
     * @param param Parameters that the operation requires.
     */
    suspend fun run(param: Param): Return = withContext(defaultDispatcher) {
        return@withContext runInternal(param)
    }
}
