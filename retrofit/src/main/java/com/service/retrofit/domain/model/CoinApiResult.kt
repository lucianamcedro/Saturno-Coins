package com.service.retrofit.domain.model

sealed class CoinApiResult<out T : Any> {
    class Loading : CoinApiResult<Nothing>()
    class Success<out T : Any>(val data: List<CoinItem>) : CoinApiResult<T>()
    class Error400(val throwable: String) : CoinApiResult<Nothing>()
    class Error401(val throwable: String) : CoinApiResult<Nothing>()
    class Error403(val throwable: String) : CoinApiResult<Nothing>()
    class Error429(val throwable: String) : CoinApiResult<Nothing>()
    class Error500(val throwable: String) : CoinApiResult<Nothing>()
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
