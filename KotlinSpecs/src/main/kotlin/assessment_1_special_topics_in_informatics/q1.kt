package assessment_1_special_topics_in_informatics/*

    Um posto está vendendo combustíveis com a seguinte tabela de descontos:
    Álcool: até 20 litros, desconto de 3% por litro e acima de 20 litros, desconto de 5% por litro;
    Gasolina: até 20 litros, desconto de 4% por litro e acima de 20 litros, desconto de 6% por litro.

    Escreva um programa em Kotlin que leia o número de litros vendidos,
    o tipo de combustível (A-álcool, G-gasolina) e imprima o valor a ser pago pelo cliente.

    Considere que o preço do litro da gasolina é R$ 5,99 e o preço do litro do álcool é R$ 3,99.

 */

fun main() {
    // Solicita ao usuário o número de litros vendidos
    print("Informe o número de litros vendidos: ")
    val litrosInput = readlnOrNull()
    val litros = litrosInput?.toDoubleOrNull()
    if (litros == null || litros <= 0) {
        println("Valor de litros inválido!")
        return
    }

    // Solicita o tipo de combustível: A (álcool) ou G (gasolina)
    print("Informe o tipo de combustível (A para Álcool e G para Gasolina): ")
    val tipoInput = readlnOrNull()?.trim()?.uppercase()
    if (tipoInput != "A" && tipoInput != "G") {
        println("Tipo de combustível inválido!")
        return
    }

    // Define os valores do litro e o percentual de desconto conforme combustível e quantidade
    val (valorPorLitro, descontoPercentual) = when (tipoInput) {
        "A" -> {
            // Preço do álcool: R$ 3,99
            // Até 20 litros, desconto de 3%; Acima de 20 litros, desconto de 5%
            val desconto = if (litros <= 20) 0.03 else 0.05
            Pair(3.99, desconto)
        }

        "G" -> {
            // Preço da gasolina: R$ 5,99
            // Até 20 litros, desconto de 4%; Acima de 20 litros, desconto de 6%
            val desconto = if (litros <= 20) 0.04 else 0.06
            Pair(5.99, desconto)
        }

        else -> Pair(0.0, 0.0) // Esse caso não ocorrerá, pois já foi verificado o tipo
    }

    // Calcula o valor total sem o desconto
    val valorTotal = litros * valorPorLitro

    // Calcula o valor do desconto
    val desconto = valorTotal * descontoPercentual

    // Valor a ser pago após o desconto
    val valorAPagar = valorTotal - desconto

    // Exibe o resultado com duas casas decimais
    println("Valor a ser pago: R$ %.2f".format(valorAPagar))
}
