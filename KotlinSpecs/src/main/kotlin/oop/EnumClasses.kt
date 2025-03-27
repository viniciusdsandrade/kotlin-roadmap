package oop

import kotlin.enums.enumEntries

/**
 * Exemplo detalhado de Enum Classes em Kotlin
 *
 * Em Kotlin, enum classes são utilizadas para criar enums com segurança de tipo.
 * Cada constante de um enum é um objeto, podendo ter seu próprio estado (através
 * de parâmetros) e comportamentos (através da sobrecarga de métodos ou implementação
 * de interfaces).
 *
 * Conceitos demonstrados neste exemplo:
 * 1. Enum simples (ex.: direções).
 * 2. Enum com construtor e parâmetro (ex.: cores com valor RGB).
 * 3. Enum com classes anônimas para definir comportamentos distintos para cada constante
 *    (ex.: estados de um protocolo).
 * 4. Enum implementando uma interface para operações específicas (ex.: operações aritméticas).
 * 5. Uso de propriedades e funções sintéticas: acesso aos valores, nome, ordinal e
 *    métodos como valueOf, enumEntries e enumValueOf.
 */

//////////////////////////////
// 1. Enum simples: Direcao
//////////////////////////////

enum class Direcao {
    NORTE, SUL, LESTE, OESTE
}

//////////////////////////////
// 2. Enum com construtor e parâmetro: Cor
//////////////////////////////

enum class Cor(val rgb: Int) {
    VERMELHO(0xFF0000),
    VERDE(0x00FF00),
    AZUL(0x0000FF)
}

//////////////////////////////
// 3. Enum com classes anônimas: EstadoProtocolo
//////////////////////////////

enum class EstadoProtocolo {
    ESPERANDO {
        // Sobrescreve o metodo abstrato sinal para retornar o estado TALKING
        override fun sinal() = CONVERSANDO
    },
    CONVERSANDO {
        // Sobrescreve o metodo abstrato sinal para retornar o estado ESPERANDO
        override fun sinal() = ESPERANDO
    };

    // Cada constante de EstadoProtocolo deve implementar este metodo abstrato.
    abstract fun sinal(): EstadoProtocolo
}

//////////////////////////////
// 4. Enum implementando interface: Aritmetica
//////////////////////////////

/**
 * Interface que define uma operação binária sobre inteiros.
 */
interface OperacaoBinaria {
    fun aplicar(a: Int, b: Int): Int
}

/**
 * Enum que implementa a interface OperacaoBinaria.
 * Cada constante fornece sua própria implementação de aplicar().
 */
enum class Aritmetica : OperacaoBinaria {
    SOMA {
        override fun aplicar(a: Int, b: Int): Int = a + b
    },
    MULTIPLICACAO {
        override fun aplicar(a: Int, b: Int): Int = a * b
    }
}

//////////////////////////////
// 5. Demonstração do uso de métodos sintéticos em enums
//////////////////////////////

enum class RGB {
    VERMELHO, VERDE, AZUL
}

//////////////////////////////
// Função Main: Demonstração de enum classes
//////////////////////////////

fun main() {
    // Exemplo 1: Uso do enum Direcao
    println("Direções:")
    // .values() retorna um array com todas as constantes do enum
    for (direcao in Direcao.entries) {
        // Propriedades syntéticas: name (nome da constante) e ordinal (posição)
        println("Nome: ${direcao.name}, Ordinal: ${direcao.ordinal}")
    }
    println()

    // Exemplo 2: Uso do enum Cor com parâmetro
    println("Cores com valor RGB:")
    for (cor in Cor.entries) {
        println("Cor: ${cor.name}, RGB: ${cor.rgb}")
    }
    println()

    // Exemplo 3: Enum com classes anônimas (EstadoProtocolo)
    println("Estados de protocolo:")
    var estado = EstadoProtocolo.ESPERANDO
    println("Estado inicial: $estado")
    // Chama o metodo sinal, que foi sobrescrito para alternar os estados
    estado = estado.sinal()
    println("Após sinal: $estado")
    estado = estado.sinal()
    println("Após outro sinal: $estado")
    println()

    // Exemplo 4: Enum implementando interface (Operações aritméticas)
    println("Operações aritméticas:")
    val a = 10
    val b = 5
    for (operacao in Aritmetica.entries) {
        println("Operação: ${operacao.name}, Resultado: ${operacao.aplicar(a, b)}")
    }
    println()

    // Exemplo 5: Trabalhando com constantes de enum de forma genérica
    // A partir do Kotlin 1.9, a propriedade "entries" retorna uma lista imutável com todas as constantes.
    println("Cores no enum RGB usando enumEntries:")
    for (cor in enumEntries<RGB>()) {
        println(cor)
    }
    // Também é possível obter uma constante pelo nome usando enumValueOf
    val corEncontrada = enumValueOf<RGB>("VERDE")
    println("EnumValueOf('VERDE'): $corEncontrada")
}
