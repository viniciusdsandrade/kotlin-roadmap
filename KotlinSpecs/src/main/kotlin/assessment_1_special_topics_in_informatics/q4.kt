package assessment_1_special_topics_in_informatics/*

    Faça um programa em Kotlin que converta graus Fahrenheit para Celsius.
    Usar a função Celsius recebendo o grau Fahrenheit como parâmetro.
    Fórmula para cálculo: C=(F-32)/1.8.

 */

// Função que converte Fahrenheit para Celsius
fun converterFahrenheitParaCelsius(fahrenheit: Double): Double {
    return (fahrenheit - 32) / 1.8
}

fun main() {

    // Solicita a temperatura em Fahrenheit
    print("Informe a temperatura em Fahrenheit: ")
    val fahrenheit = readlnOrNull()?.toDoubleOrNull()

    if (fahrenheit == null) {
        println("Valor inválido!")
        return
    }

    // Converte para Celsius
    val celsius = converterFahrenheitParaCelsius(fahrenheit)

    // Exibe o resultado
    println("$fahrenheit°F equivalem a $celsius°C")
}
