/**
 * Exemplo de Higher-Order Functions e Lambdas em Kotlin
 *
 * Kotlin trata funções como cidadãos de primeira classe, permitindo que sejam
 * armazenadas em variáveis, passadas como argumentos para outras funções e até mesmo
 * retornadas por outras funções. Este arquivo demonstra os conceitos teóricos e
 * práticos relacionados a funções de ordem superior e lambda expressions.
 *
 * Conceitos abordados:
 * 1. Funções de ordem superior: funções que recebem outras funções como parâmetros
 *    ou retornam funções.
 * 2. Tipos de função: declaração de tipos de funções, incluindo funções com receiver.
 * 3. Lambda expressions: sintaxe e exemplos práticos.
 * 4. Funções anônimas: declaração de funções sem nome.
 * 5. Uso de trailing lambda: passagem de lambda fora dos parênteses.
 * 6. Uso do 'it': nome implícito de parâmetro único.
 * 7. Destructuring e underscore para parâmetros não utilizados.
 * 8. Funções literais com receiver (ex.: para DSLs).
 */

fun main() {
    println("Exemplos de Higher-Order Functions e Lambdas:\n")

    // 1. Função de Ordem Superior: 'operate'
    // A função 'operate' recebe dois números e uma operação, e retorna o resultado.
    val sumResult = operate(5, 3) { a, b -> a + b }
    println("Resultado da soma: $sumResult")

    // 2. Função de ordem superior usando função anônima explícita.
    val productResult = operate(5, 3, fun(a: Int, b: Int): Int {
        return a * b
    })
    println("Resultado da multiplicação: $productResult")

    // 3. Uso de trailing lambda.
    // Se o último parâmetro for uma função, o lambda pode ser passado fora dos parênteses.
    val subtractionResult = operate(5, 3) { a, b -> a - b }
    println("Resultado da subtração: $subtractionResult")

    // 4. Uso do 'it' quando há um único parâmetro.
    // 'filter' recebe uma lambda que filtra os elementos positivos de uma lista.
    val numbers = listOf(-1, 2, -3, 4, 0)
    val positives = numbers.filter { it > 0 }
    println("Números positivos: $positives")

    // 5. Destructuring em lambdas.
    // A função forEach pode usar destructuring para acessar chave e valor de um Map.
    val map = mapOf("A" to 1, "B" to 2)
    map.forEach { (key, value) ->
        println("Chave: $key, Valor: $value")
    }

    // 6. Função literal com receiver utilizando buildString.
    // O lambda com receiver permite acessar o objeto StringBuilder como 'this'.
    val builderResult = buildString {
        append("Hello")
        append(", ")
        append("World!")
    }
    println("Resultado do builder: $builderResult")

    // 7. Uso de underscore para parâmetros não utilizados.
    // No forEachIndexed, ignoramos o índice usando '_'.
    numbers.forEachIndexed { _, value ->
        println("Valor: $value")
    }

    // 8. Função que retorna outra função.
    // 'makeIncrementer' retorna uma função que incrementa seu argumento pelo valor passado.
    val incrementer = makeIncrementer(10)
    println("Incrementando 5 com incremento de 10: ${incrementer(5)}")

    // 9. Exemplo de DSL simples com funções literais com receiver.
    // Utilizando a função 'html' para construir um documento HTML.
    val resultDSL = html {
        head {
            title("Exemplo DSL")
        }
        body {
            p("Parágrafo 1")
            p("Parágrafo 2")
        }
    }
    println("HTML gerado:\n$resultDSL")
}

/**
 * 1. Função de Ordem Superior 'operate'
 *
 * Recebe dois inteiros e uma função 'operation' que define a operação a ser executada.
 *
 * @param a Primeiro operando.
 * @param b Segundo operando.
 * @param operation Função que recebe dois inteiros e retorna um inteiro.
 * @return Resultado da operação aplicada a 'a' e 'b'.
 */
fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

/**
 * 2. Função que Retorna Outra Função
 *
 * Retorna uma função que incrementa seu argumento pelo valor 'increment'.
 *
 * @param increment Valor a ser somado.
 * @return Uma função que recebe um Int e retorna um Int.
 */
fun makeIncrementer(increment: Int): (Int) -> Int {
    return { x: Int -> x + increment }
}

/**
 * 3. Função Literal com Receiver usando buildString.
 *
 * Permite construir uma string de forma personalizada utilizando um lambda com receiver.
 *
 * @param builderAction Lambda com receiver que opera sobre StringBuilder.
 * @return A string construída.
 */
fun buildString(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.builderAction()  // Invoca o lambda com 'sb' como receiver.
    return sb.toString()
}

/**
 * 4. Exemplo de DSL para Construção de HTML
 *
 * Demonstra o uso de funções literais com receiver para criar uma mini-DSL.
 */

/**
 * Classe que representa um documento HTML.
 */
class HTML {
    private val elements = mutableListOf<String>()

    /**
     * Adiciona um elemento HTML ao documento.
     */
    fun addElement(element: String) {
        elements.add(element)
    }

    /**
     * Retorna o HTML como uma string.
     */
    override fun toString(): String {
        return elements.joinToString(separator = "\n")
    }
}

/**
 * Função para construir um documento HTML utilizando uma função literal com receiver.
 *
 * @param init Lambda que configura o objeto HTML.
 * @return O documento HTML como string.
 */
fun html(init: HTML.() -> Unit): String {
    val html = HTML()  // Cria o objeto HTML.
    html.init()        // Configura o objeto com o lambda.
    return html.toString()
}

/**
 * Configura a seção <head> do HTML.
 */
fun HTML.head(init: Head.() -> Unit) {
    val head = Head().apply(init)
    addElement(head.toString())
}

/**
 * Configura a seção <body> do HTML.
 */
fun HTML.body(init: Body.() -> Unit) {
    val body = Body().apply(init)
    addElement(body.toString())
}

/**
 * Classe que representa a tag <head>.
 */
class Head {
    private var titleText: String = ""

    /**
     * Define o título do HTML.
     */
    fun title(text: String) {
        titleText = text
    }

    override fun toString(): String {
        return "<head><title>$titleText</title></head>"
    }
}

/**
 * Classe que representa a tag <body>.
 */
class Body {
    private val paragraphs = mutableListOf<String>()

    /**
     * Adiciona um parágrafo ao corpo do HTML.
     */
    fun p(text: String) {
        paragraphs.add("<p>$text</p>")
    }

    override fun toString(): String {
        return "<body>\n${paragraphs.joinToString(separator = "\n")}\n</body>"
    }
}
