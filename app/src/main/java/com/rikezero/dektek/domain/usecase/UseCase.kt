package com.rikezero.dektek.domain.usecase

/**
 * A base UseCase that encapsulates a unit of business logic.
 *
 * @param P The type of input parameter.
 * @param R The type of the result produced by this use case.
 */
abstract class UseCase<in P, out R> {

    /**
     * Execute the use case logic with the given parameters.
     *
     * @param params Parameters required to execute the use case.
     * @return The result of executing this use case.
     *
     * This function is `operator` so it can be invoked using the `useCase(params)` syntax.
     *
     * It's marked `suspend` to support asynchronous operations, which is common in use cases.
     */
    suspend operator fun invoke(params: P): R {
        return execute(params)
    }

    /**
     * Implement this method in subclasses to define the actual business logic.
     *
     * @param params Parameters for executing the use case.
     * @return The business logic result.
     */
    protected abstract suspend fun execute(params: P): R
}
