package br.com.dio.coinconverter.data.model

import java.util.*

/*
 Essa classe é uma espécie de interface, que representa as moedas, onde
 cada constante declarada é um objeto. As moedas declaradas no corpo dessa classe
 são usadas para preencher os DropDownMenus da MainActivity
 */

enum class Coin(val locale: Locale) {
    USD(Locale.US),
    CAD(Locale.CANADA),
    BRL(Locale("pt", "BR")),
    ARS(Locale("es", "AR"))
    ;

    companion object {
        fun getByName(name: String) = values().find { it.name == name } ?: BRL
    }
}