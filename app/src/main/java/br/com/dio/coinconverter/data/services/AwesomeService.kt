package br.com.dio.coinconverter.data.services

import br.com.dio.coinconverter.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

/*Declaração de interface de serviços, define como o endpoint vai ser retornado
 ao ser chamado em DataModules
*/
interface AwesomeService {

    @GET("/json/last/{coins}")
    suspend fun exchangeValue(@Path("coins") coins: String): ExchangeResponse
}