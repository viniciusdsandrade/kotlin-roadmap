package assessment_1_special_topics_in_informatics/*

    Faça um programa em Kotlin o para ler:
    número da conta do cliente, saldo, débito e crédito.
    Após, calcular e escrever o saldo atual (saldo atual = saldo - débito + crédito).
    Também testar se saldo atual for maior ou igual a zero escrever a mensagem 'Saldo Positivo',
    senão escrever a mensagem 'Saldo Negativo'.

 */

fun main() {
    // Lê o número da conta do cliente
    print("Informe o número da conta: ")
    val conta = readlnOrNull() ?: ""

    // Lê o saldo inicial e converte para Double
    print("Informe o saldo inicial: ")
    val saldoInicial = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    // Lê o valor do débito
    print("Informe o débito: ")
    val debito = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    // Lê o valor do crédito
    print("Informe o crédito: ")
    val credito = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    // Calcula o saldo atual
    val saldoAtual = saldoInicial - debito + credito

    // Exibe o resultado formatado com duas casas decimais
    println("Saldo atual da conta $conta: R$ %.2f".format(saldoAtual))

    // Verifica se o saldo é positivo ou negativo
    if (saldoAtual >= 0)
        println("Saldo Positivo")
    else
        println("Saldo Negativo")
}