package br.com.dio.coinconverter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Define o retorno do serviço nesta variável
typealias ExchangeResponse = HashMap<String, ExchangeResponseValue>

//Esta classe define o objeto de resposta da API
@Entity(tableName = "tb_exchange")
data class ExchangeResponseValue(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val code: String,
    val codein: String,
    val name: String,
    val bid: Double
)