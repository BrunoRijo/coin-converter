package br.com.dio.coinconverter.data.repository

import br.com.dio.coinconverter.data.model.ExchangeResponseValue
import kotlinx.coroutines.flow.Flow

//Repositório de
interface CoinRepository {
    suspend fun getExchangeValue(coins: String): Flow<ExchangeResponseValue>

    suspend fun save(exchange: ExchangeResponseValue)
    fun list(): Flow<List<ExchangeResponseValue>>
}