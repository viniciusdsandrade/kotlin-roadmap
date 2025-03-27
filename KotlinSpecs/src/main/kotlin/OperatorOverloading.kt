/**
 * Exemplo detalhado de Sobrecarga de Operadores em Kotlin
 *
 * Em Kotlin, é possível fornecer implementações personalizadas para um conjunto
 * predefinido de operadores. Esses operadores possuem representações simbólicas
 * (como +, -, [] etc.) e precedência definida. Para sobrecarregar um operador,
 * basta definir uma função membro ou uma função de extensão com um nome específico
 * e marcar essa função com o modificador "operator".
 *
 * Exemplos abordados:
 * 1. Sobrecarga de operadores unários (ex.: -a, +a, !a)
 * 2. Sobrecarga de operadores binários (ex.: a + b, a - b, a * b, a / b, a % b)
 * 3. Sobrecarga do operador "in" (a in b, a !in b)
 * 4. Sobrecarga de operadores de acesso por índice (a[i], a[i] = b)
 * 5. Sobrecarga do operador invoke (a(), a(i))
 * 6. Sobrecarga de operadores de comparação (>, <, ≥, ≤) usando compareTo()
 * 7. Sobrecarga de operadores de atribuição aumentada (+=, -=, etc.)
 */

//////////////////////////////
// 1. Sobrecarga de operadores unários
//////////////////////////////

/**
 * Classe Point representa um ponto no plano com coordenadas x e y.
 * Sobrecaregamos o operador unário '-' para inverter as coordenadas.
 */
data class Point(val x: Int, val y: Int) {
    /**
     * Sobrecarga do operador unário '-' que cria um novo ponto
     * com as coordenadas invertidas.
     *
     * Exemplo: -point
     */
    operator fun unaryMinus() = Point(-x, -y)
}

//////////////////////////////
// 2. Sobrecarga de operadores binários
//////////////////////////////

/**
 * Classe Counter representa um contador com um valor inicial.
 * Sobrecaregamos o operador '+' para permitir a soma de um valor inteiro ao contador.
 */
class Counter(val valor: Int) {
    /**
     * Sobrecarga do operador '+' que retorna um novo Counter com o valor atualizado.
     *
     * Exemplo: counter + 10
     *
     * @param incremento valor inteiro a ser somado.
     * @return novo objeto Counter com o valor atualizado.
     */
    operator fun plus(incremento: Int): Counter {
        return Counter(valor + incremento)
    }
}

//////////////////////////////
// 3. Sobrecarga do operador de acesso por índice e "in"
//////////////////////////////

/**
 * Classe MinhaLista é uma estrutura simples que encapsula uma lista mutável.
 * Ela demonstra a sobrecarga dos operadores:
 *   - get: para acesso por índice, usando a notação [].
 *   - set: para atribuição por índice, também usando [].
 *   - contains: para verificar se um elemento está presente, permitindo o uso de "in".
 */
class MinhaLista<T>(private val elementos: MutableList<T> = mutableListOf()) {
    /**
     * Sobrecarga do operador get para acesso por índice.
     *
     * @param indice Posição do elemento.
     * @return Elemento na posição especificada.
     */
    operator fun get(indice: Int): T = elementos[indice]

    /**
     * Sobrecarga do operador set para atribuição por índice.
     *
     * @param indice Posição do elemento.
     * @param valor Novo valor a ser atribuído.
     */
    operator fun set(indice: Int, valor: T) {
        elementos[indice] = valor
    }

    /**
     * Sobrecarga do operador "in" para verificar a contenção.
     *
     * @param item Elemento a ser verificado.
     * @return True se o item estiver presente; false caso contrário.
     */
    operator fun contains(item: T): Boolean = elementos.contains(item)

    /**
     * Método para adicionar um elemento à lista.
     */
    fun add(item: T) {
        elementos.add(item)
    }

    override fun toString(): String = elementos.toString()
}

//////////////////////////////
// 4. Sobrecarga do operador invoke
//////////////////////////////

/**
 * Classe Invocador demonstra a sobrecarga do operador invoke,
 * permitindo que uma instância da classe seja chamada como se fosse uma função.
 */
class Invocador {
    /**
     * Sobrecarga do operador invoke que aceita uma mensagem e a imprime.
     *
     * Exemplo: invocador("Olá!")
     *
     * @param mensagem A mensagem a ser impressa.
     */
    operator fun invoke(mensagem: String) {
        println("Invocador chamado com a mensagem: $mensagem")
    }
}

//////////////////////////////
// 5. Sobrecarga de operadores de comparação
//////////////////////////////

/**
 * Classe Numero implementa Comparable para sobrecarregar os operadores
 * de comparação (>, <, >=, <=) usando a função compareTo.
 */
data class Numero(val valor: Int): Comparable<Numero> {
    override fun compareTo(other: Numero): Int = this.valor - other.valor
}

//////////////////////////////
// 6. Sobrecarga de operadores de atribuição aumentada
//////////////////////////////

/**
 * Classe Valor demonstra a sobrecarga dos operadores de atribuição aumentada.
 * Aqui, sobrecarregamos o operador '+' e o operador 'plusAssign'.
 */
data class Valor(var numero: Int) {
    /**
     * Sobrecarga do operador '+' que retorna um novo objeto Valor com o número atualizado.
     */
    operator fun plus(outro: Int): Valor = Valor(numero + outro)

    /**
     * Sobrecarga do operador '+=' que atualiza o valor do objeto sem criar um novo.
     *
     * Nota: Esse operador deve retornar Unit.
     */
    operator fun plusAssign(outro: Int) {
        numero += outro
    }
}

//////////////////////////////
// Função Main: Demonstração dos exemplos
//////////////////////////////

fun main() {
    // Exemplo 1: Operador unário (-)
    val ponto = Point(10, 20)
    println("Ponto original: $ponto")
    println("Ponto invertido (usando -ponto): ${-ponto}")

    // Exemplo 2: Operador binário (+) com a classe Counter
    val contador = Counter(5)
    val novoContador = contador + 10
    println("Contador original: ${contador.valor}, Novo contador: ${novoContador.valor}")

    // Exemplo 3: Operador de acesso por índice e "in" com a classe MinhaLista
    val minhaLista = MinhaLista<String>()
    minhaLista.add("Kotlin")
    minhaLista.add("Java")
    minhaLista.add("Python")
    println("Minha Lista: $minhaLista")
    println("Elemento no índice 1: ${minhaLista[1]}")
    minhaLista[1] = "C++"
    println("Lista após alteração: $minhaLista")
    println("Contém 'Kotlin'? ${"Kotlin" in minhaLista}")
    println("Contém 'Java'? ${"Java" in minhaLista}")

    // Exemplo 4: Operador invoke
    val invocador = Invocador()
    invocador("Olá, operadores!")

    // Exemplo 5: Operadores de comparação usando compareTo
    val num1 = Numero(10)
    val num2 = Numero(20)
    println("num1 < num2: ${num1 < num2}")
    println("num1 >= num2: ${num1 >= num2}")

    // Exemplo 6: Operadores de atribuição aumentada (+=)
    val valor = Valor(100)
    valor += 50
    println("Valor após += 50: ${valor.numero}")
}
