package oop

import oop.Utilidades.Companion.saudacao

/**
 * Exemplo detalhado de Classes em Kotlin
 *
 * Este arquivo demonstra diversos conceitos sobre classes em Kotlin:
 * 1. Declaração de classes simples.
 * 2. Construtor primário e declaração de propriedades no cabeçalho.
 * 3. Blocos de inicialização (init) e ordem de execução.
 * 4. Construtores secundários com delegação ao construtor primário.
 * 5. Uso de objeto companheiro (companion object) para simular membros estáticos.
 *
 * Em Kotlin, classes são declaradas utilizando a palavra-chave "class". Se a classe não tiver corpo,
 * as chaves podem ser omitidas. Propriedades podem ser declaradas diretamente no construtor primário.
 *
 * Exemplo:
 *   class Pessoa(val primeiroNome: String, val ultimoNome: String, var idade: Int)
 *
 * Durante a criação de uma instância, os blocos init são executados na ordem em que aparecem,
 * intercalados com as inicializações de propriedades.
 */

// 1. Classe Vazia: Uma classe sem corpo nem construtor explícito.
class Vazia

// 2. Classe com construtor primário e propriedades declaradas no cabeçalho.
class Pessoa(val primeiroNome: String, val ultimoNome: String, var idade: Int)

// 3. Classe com blocos de inicialização (init)
// Exibe a ordem de inicialização: as propriedades e os blocos init são executados na ordem de declaração.
class ExemploInit(nome: String) {
    // Propriedade inicializada usando o parâmetro do construtor primário.
    val primeiraPropriedade = "Primeira propriedade: $nome".also { println(it) }

    // Primeiro bloco init
    init {
        println("Primeiro bloco init que imprime o nome: $nome")
    }

    // Outra propriedade que depende do parâmetro.
    val segundaPropriedade = "Segunda propriedade: ${nome.length}".also { println(it) }

    // Segundo bloco init
    init {
        println("Segundo bloco init que imprime o tamanho do nome: ${nome.length}")
    }
}

// 4. Classe com construtor secundário
// O construtor secundário delega ao construtor primário usando o "this".
class PessoaComPai(val nome: String) {
    // Lista para armazenar os filhos deste objeto.
    val filhos: MutableList<PessoaComPai> = mutableListOf()

    // Construtor secundário que recebe o nome e um pai, delegando ao construtor primário.
    constructor(nome: String, pai: PessoaComPai) : this(nome) {
        pai.filhos.add(this) // Adiciona este objeto à lista de filhos do pai.
    }
}

// 5. Classe com objeto companheiro (companion object)
// Permite declarar funções e propriedades que podem ser acessadas sem instanciar a classe,
// similar a membros estáticos em outras linguagens.
class Utilidades {
    companion object {
        /**
         * Retorna uma saudação personalizada.
         *
         * @param nome O nome da pessoa a ser saudada.
         * @return Uma string com a saudação.
         */
        fun saudacao(nome: String): String {
            return "Olá, $nome! Seja bem-vindo(a) ao Kotlin."
        }
    }
}

//////////////////////////////
// Função main para demonstrar os exemplos
//////////////////////////////
fun main() {
    println("=== Demonstração de Classes em Kotlin ===\n")

    // Exemplo 1: Instanciando uma classe vazia.
    val objetoVazio = Vazia()
    println("Instância de classe Vazia criada: $objetoVazio\n")

    // Exemplo 2: Criando uma pessoa usando o construtor primário.
    val pessoa = Pessoa("João", "Silva", 30)
    println("Pessoa: ${pessoa.primeiroNome} ${pessoa.ultimoNome}, Idade: ${pessoa.idade}\n")

    // Exemplo 3: Demonstração dos blocos de inicialização (init).
    val exemploInit = ExemploInit("Kotlin")
    println("Instância de ExemploInit criada.\n")

    // Exemplo 4: Utilizando construtor secundário com delegação.
    val pai = PessoaComPai("Carlos")
    val filho = PessoaComPai("Pedro", pai)
    println("Pai: ${pai.nome}, Filhos: ${pai.filhos.map { it.nome }}\n")

    // Exemplo 5: Acessando o companion object para chamar função estática.
    val saudacao = saudacao("Ana")
    println("Utilizando companion object: $saudacao")
}
