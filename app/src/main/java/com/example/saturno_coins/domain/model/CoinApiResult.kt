package com.example.saturno_coins.domain.model

sealed class CoinApiResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : CoinApiResult<T>(data)

    class Error<T>(message: String?, data: T? = null) : CoinApiResult<T>(data, message)

    class Loading<T> : CoinApiResult<T>()

}
// data class Failure(val throwable: Throwable) : CoinApiResult<Nothing>()

/*sealed class ApiResult <out T> {
    data class Success<out T>(val data: R?): ApiResult<R>()
    data class Error(val message: String): ApiResult<Nothing>()
    object Loading: ApiResult<Nothing>()
}*/
// https://gist.github.com/WenLonG12345/ebd07d7eb8ceb39e1921ca438215e57e
// /https://gist.github.com/crocsandcoffee/b933f994cc6cf40467dec9cca4ad408a
// https://gist.github.com/VladBytsyuk/9fceb4761b2f690ddcceb13160d02567
// https://gist.github.com/crocsandcoffee/b933f994cc6cf40467dec9cca4ad408a
