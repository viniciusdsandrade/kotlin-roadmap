import kotlin.math.abs
import kotlin.math.cos

/**
 * Exemplo de Funções em Kotlin
 *
 * Este arquivo demonstra diversos conceitos teóricos e práticos sobre funções em Kotlin,
 * incluindo:
 * 1. Declaração básica de funções.
 * 2. Parâmetros, argumentos nomeados e valores padrão.
 * 3. Funções de expressão única.
 * 4. Funções que retornam Unit.
 * 5. Funções com número variável de argumentos (vararg).
 * 6. Funções infixas.
 * 7. Funções locais.
 * 8. Funções membros.
 * 9. Funções genéricas.
 * 10. Funções tail-recursive.
 *
 * A seguir, cada conceito é exemplificado com comentários explicativos utilizando
 * o padrão de comentários do Kotlin (/** ... */ e //).
 */

fun main() {
    println("Exemplos de funções em Kotlin:\n")

    // 1. Função Básica
    // Função que retorna o dobro de um número inteiro.
    println("double(4) = ${double(4)}")

    // 2. Função com parâmetros padrão e argumentos nomeados
    // Chamadas com argumentos nomeados e com omissão dos parâmetros que possuem valores padrão.
    reformat("ExemploDeString", upperCaseFirstLetter = false, wordSeparator = '_')
    reformat("OutraExemplo")

    // 3. Função com vararg
    // Converte uma sequência de argumentos em uma lista.
    val list = asList(1, 2, 3, 4)
    println("Lista com vararg: $list")

    // 4. Função Infixa
    // Utilizando a função infixa 'myShl' para realizar um deslocamento de bits.
    println("1 myShl 2 = ${1 myShl 2}")

    // 5. Função Local
    // Demonstra o uso de uma função declarada localmente dentro de outra função.
    localFunctionExample()

    // 6. Função Membro
    // Cria uma instância da classe Sample e chama sua função membro.
    val sample = Sample()
    sample.memberFunction()

    // 7. Função Genérica
    // Retorna uma lista contendo um único elemento.
    val singleton = singletonList("Kotlin")
    println("Singleton list: $singleton")

    // 8. Função Tail-Recursive
    // Calcula o ponto fixo da função cosseno utilizando otimização tailrec.
    val fixPoint = findFixPoint()
    println("FixPoint de cos(x): $fixPoint")
}

/**
 * 1. Declaração Básica de Função
 *
 * Declaração de uma função que retorna o dobro de um número inteiro.
 *
 * @param x Número inteiro que será dobrado.
 * @return O dobro de x.
 */
fun double(x: Int): Int {
    return 2 * x
}

/**
 * 2. Função com Parâmetros Padrão e Argumentos Nomeados
 *
 * A função reformat() recebe uma string e a formata conforme os parâmetros.
 * Parâmetros com valores padrão permitem que o chamador omita argumentos,
 * podendo utilizar argumentos nomeados para especificar somente os desejados.
 *
 * @param str String a ser reformatada.
 * @param normalizeCase Se true, converte a string para minúsculas (default: true).
 * @param upperCaseFirstLetter Se true, coloca a primeira letra em maiúscula (default: true).
 * @param divideByCamelHumps Se true, insere um separador entre transições de camelCase (default: false).
 * @param wordSeparator Caractere usado como separador de palavras (default: ' ').
 */
fun reformat(
    str: String,
    normalizeCase: Boolean = true,
    upperCaseFirstLetter: Boolean = true,
    divideByCamelHumps: Boolean = false,
    wordSeparator: Char = ' '
) {
    var result = str

    // Normaliza o caso da string, se solicitado.
    if (normalizeCase) {
        result = result.lowercase()
    }

    // Coloca a primeira letra em maiúscula, se solicitado.
    if (upperCaseFirstLetter && result.isNotEmpty()) {
        result = result.replaceFirstChar { it.uppercase() }
    }

    // Se divideByCamelHumps for true, insere o separador entre letras minúsculas seguidas de maiúsculas.
    if (divideByCamelHumps) {
        result = result.replace(Regex("(?<=[a-z])(?=[A-Z])"), wordSeparator.toString())
    }
    println("Reformatado: $result")
}

/**
 * 3. Função com Número Variável de Argumentos (vararg)
 *
 * Esta função genérica recebe um número variável de argumentos e os coloca em uma lista.
 *
 * @param ts Sequência de elementos do tipo T.
 * @return Lista contendo os elementos passados.
 */
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

/**
 * 4. Função Infixa
 *
 * Define uma função infixa 'myShl' que realiza o deslocamento de bits para a esquerda.
 * A notação infixa permite chamar a função sem usar o ponto ou os parênteses.
 *
 * Exemplo:
 *   1 myShl 2 é equivalente a 1.myShl(2)
 *
 * @param x Número de posições para deslocar os bits.
 * @return O resultado do deslocamento de bits.
 */
infix fun Int.myShl(x: Int): Int = this.shl(x)

/**
 * 5. Função Local
 *
 * Demonstra o uso de uma função local, definida dentro de outra função.
 * Funções locais podem acessar variáveis do escopo da função externa (closure).
 */
fun localFunctionExample() {
    println("\nExemplo de função local:")
    // Função local para somar dois números.
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
    val resultado = sum(3, 7)
    println("Soma local de 3 e 7: $resultado")
}

/**
 * 6. Função Membro
 *
 * A classe Sample contém uma função membro que imprime uma mensagem.
 */
class Sample {
    /**
     * Função membro que imprime uma mensagem.
     */
    fun memberFunction() {
        println("Função membro chamada a partir de um objeto da classe Sample")
    }
}

/**
 * 7. Função Genérica
 *
 * Retorna uma lista contendo apenas o elemento fornecido.
 *
 * @param item Elemento de qualquer tipo.
 * @return Uma lista contendo o item.
 */
fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

/**
 * 8. Função Tail-Recursive
 *
 * Calcula o ponto fixo da função cosseno utilizando recursão otimizada (tailrec).
 * O ponto fixo é encontrado quando a diferença entre x e cos(x) é menor que eps.
 *
 * @param x Valor inicial para a iteração (default: 1.0).
 * @return O ponto fixo de cos(x).
 */
tailrec fun findFixPoint(x: Double = 1.0): Double {
    val eps = 1E-10
    val next = cos(x)
    return if (abs(x - next) < eps) x else findFixPoint(next)
}
