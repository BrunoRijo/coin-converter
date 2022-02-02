package br.com.dio.coinconverter.data.repository

import br.com.dio.coinconverter.core.exceptions.RemoteException
import br.com.dio.coinconverter.data.database.AppDatabase
import br.com.dio.coinconverter.data.model.ErrorResponse
import br.com.dio.coinconverter.data.model.ExchangeResponseValue
import br.com.dio.coinconverter.data.services.AwesomeService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

//Esta classe define como o CoinRepository tem que buscar essas informações na API
class CoinRepositoryImpl(
    appDatabase: AppDatabase,
    private val service: AwesomeService
) : CoinRepository {

    private val dao = appDatabase.exchangeDao()

    //função que dá a resposta
    override suspend fun getExchangeValue(coins: String) = flow {
        try {
            val exchangeValue = service.exchangeValue(coins) //consulta o valor na exchange
            val exchange = exchangeValue.values.first() //vê o primeiro valor retornado
            emit(exchange) //informa para quem está ouvindo
        } catch (e: HttpException) {
            // {"status":404,"code":"CoinNotExists","message":"moeda nao encontrada USD-USD"}
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)
        }
    }

    override suspend fun save(exchange: ExchangeResponseValue) {
        dao.save(exchange)
    }

    override fun list(): Flow<List<ExchangeResponseValue>> {
        return dao.findAll()
    }
}