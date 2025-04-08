package assessment_1_special_topics_in_informatics
/*

    Faça um programa em Kotlin que apresente função para calcular a área de uma circunferência.
    A função deve calcular a área e realizar o retorno para a função principal. Area = 3.14*(R*R).

 */

// Função que calcula e retorna a área da circunferência
fun calcularAreaCircunferencia(raio: Double): Double {
    return 3.14 * raio * raio
}

fun main() {

    // Solicita o valor do raio e converte para Double
    print("Informe o raio da circunferência: ")
    val raio = readlnOrNull()?.toDoubleOrNull()

    if (raio == null || raio <= 0) {
        println("Valor inválido para o raio!")
        return
    }

    // Calcula a área usando a função definida
    val area = calcularAreaCircunferencia(raio)

    // Exibe o resultado
    println("A área da circunferência é: $area")
}
