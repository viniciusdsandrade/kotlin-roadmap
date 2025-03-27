/**
 * Explicação detalhada sobre funções inline em Kotlin
 *
 * Funções inline são uma característica poderosa do Kotlin que melhora a performance
 * ao eliminar a sobrecarga de chamadas de função e a criação de objetos para lambdas.
 * Ao marcar uma função como inline, o compilador copia o seu corpo – e o corpo dos
 * lambdas passados a ela – diretamente para o local de chamada.
 *
 * Vantagens:
 *   - Redução de alocação de memória (especialmente para lambdas que não capturam variáveis).
 *   - Possibilidade de "return" não local: permite que um lambda retorne da função
 *     que o chamou.
 *   - Parâmetros genéricos reificados: mantém a informação de tipo em tempo de execução,
 *     superando a perda de informação (type erasure).
 *
 * Modificadores adicionais:
 *   - noinline: impede que um parâmetro lambda seja inlinado, evitando a cópia do seu
 *     corpo no local de chamada.
 *   - crossinline: garante que o lambda não possa usar "return" não local, ou seja,
 *     qualquer "return" dentro do lambda sairá apenas do lambda e não da função chamadora.
 *
 * A seguir, são apresentados exemplos práticos que ilustram esses conceitos.
 */

fun main() {
    println("=== Exemplos de funções inline em Kotlin ===\n")

    // 1. Exemplo simples de função inline
    println("1. Exemplo simples de função inline:")
    saudacaoInline("Maria") {
        println("Dentro do lambda inline: Seja bem-vinda!")
    }
    println()

    // 2. Exemplo de retorno não local (non-local return)
    println("2. Exemplo de retorno não local:")
    println("A lista contém número par? " + verificaNumeroPar(listOf(1, 3, 5, 7, 8)))
    println()

    // 3. Exemplo com crossinline
    println("3. Exemplo com crossinline:")
    exemploCrossinline {
        println("Dentro do lambda marcado como crossinline")
        // O "return" sem rótulo aqui causaria erro de compilação.
        // return@exemploCrossinline // Descomente para ver o erro.
    }
    println()

    // 4. Exemplo com noinline
    println("4. Exemplo com noinline:")
    exemploNoinline(
        lambdaInline = { println("Lambda inline executado") },
        lambdaNaoInline = { println("Lambda noinline executado") }
    )
    println()

    // 5. Exemplo com tipo reificado
    println("5. Exemplo com tipo reificado:")
    println("É 'Olá' uma String? " + ehDoTipo<String>("Olá"))
    println("É 123 um Int? " + ehDoTipo<Int>(123))
    println("É 123 uma String? " + ehDoTipo<String>(123))
}

/**
 * 1. Função Inline Simples: saudacaoInline
 *
 * Essa função imprime uma saudação e executa uma ação adicional definida pelo lambda.
 * Por ser marcada como inline, tanto o corpo da função quanto o lambda passado são
 * copiados para o local de chamada, eliminando chamadas de função extras.
 *
 * @param nome O nome da pessoa a ser saudada.
 * @param acao Lambda que executa uma ação adicional.
 */
inline fun saudacaoInline(nome: String, acao: () -> Unit) {
    println("Olá, $nome!")
    acao() // O lambda é inlinado aqui.
}

/**
 * 2. Exemplo de Retorno Não Local:
 *
 * Essa função percorre uma lista de inteiros e retorna true se encontrar um número par.
 * O lambda passado para o método forEach é inlinado, permitindo um retorno não local
 * (a execução sai da função que chamou verificaNumeroPar).
 *
 * @param numeros Lista de inteiros.
 * @return True se houver um número par; caso contrário, false.
 */
inline fun verificaNumeroPar(numeros: List<Int>): Boolean {
    numeros.forEach { numero ->
        if (numero % 2 == 0) {
            // Retorno não local: sai imediatamente da função verificaNumeroPar.
            return true
        }
    }
    return false
}

/**
 * 3. Exemplo com crossinline:
 *
 * A função abaixo usa o modificador crossinline para impedir que o lambda passado
 * utilize "return" não local, garantindo que qualquer return dentro do lambda só
 * saia do próprio lambda e não da função chamadora.
 *
 * @param acao Lambda marcado com crossinline.
 */
inline fun exemploCrossinline(crossinline acao: () -> Unit) {
    println("Antes do lambda crossinline")
    acao() // Qualquer "return" aqui será local e não afetará a função que chamou exemploCrossinline.
    println("Depois do lambda crossinline")
}

/**
 * 4. Exemplo com noinline:
 *
 * Em funções inline, todos os lambdas são inlinados por padrão.
 * Para impedir que um lambda seja inlinado – por exemplo, para evitar aumento no tamanho
 * do código – usamos o modificador noinline.
 *
 * @param lambdaInline Lambda que será inlinado.
 * @param lambdaNaoInline Lambda marcado com noinline, que não será inlinado.
 */
inline fun exemploNoinline(lambdaInline: () -> Unit, noinline lambdaNaoInline: () -> Unit) {
    println("Executando lambda inlinado:")
    lambdaInline()  // Este lambda é inlinado, sem criação de objeto.
    println("Executando lambda noinline:")
    lambdaNaoInline() // Este lambda NÃO é inlinado; um objeto será criado.
}

/**
 * 5. Exemplo com Tipo Reificado:
 *
 * Essa função verifica se um valor é do tipo T. O parâmetro genérico T é reificado,
 * o que permite que sua informação de tipo seja preservada em tempo de execução.
 *
 * @param valor Valor a ser verificado.
 * @return True se o valor for do tipo T, false caso contrário.
 */
inline fun <reified T> ehDoTipo(valor: Any): Boolean {
    return valor is T
}
