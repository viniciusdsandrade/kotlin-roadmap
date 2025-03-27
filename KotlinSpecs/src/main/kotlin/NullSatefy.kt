// Classe para demonstrar nullable receiver
data class Person(val name: String)

fun nonNullVsNullableExample() {
    // Exibe uma mensagem inicial explicando o que será demonstrado:
    // a diferença entre variáveis não-nulas e variáveis que podem ser nulas.
    println("Exemplo: Variáveis não-nulas vs. variáveis nulas")

    // Variável não-nula: Declarada sem o símbolo '?' e, portanto, NÃO pode conter null.
    var a: String = "abc"
    println("1. Variável não-nula 'a': $a")

    // Tente atribuir null a 'a'. Essa operação não é permitida, pois 'a' foi declarada como não-nula.
    // A linha abaixo, se descomentada, causará um erro de compilação.
    // a = null // Erro: Null can not be a value of a non-null type String

    // Variável nullable: Declarada com '?' e, portanto, pode conter um valor ou null.
    var b: String? = "abc"
    println("2. Variável nullable 'b' inicialmente: $b")

    // É permitido atribuir null à variável 'b', pois ela é do tipo String? (aceita null).
    b = null
    println("3. Após atribuir null, 'b': $b")
    println("--------------------------------------\n")
}


fun ifNullCheckExample() {
    // Exemplo: Checagem de null utilizando a estrutura condicional 'if'
    println("Exemplo: Checagem de null com 'if'")

    // Declara uma variável 'b' do tipo String? (pode conter null) e a inicializa com null.
    val b: String? = null

    // Verifica se 'b' não é null:
    // - Se 'b' não for null, o bloco if é executado e retorna b.length (comprimento da string).
    // - Se 'b' for null, o bloco else é executado e retorna -1, que é um valor padrão.
    // Essa abordagem evita tentar acessar 'length' em um valor null, prevenindo erros.
    val length = if (b != null) {
        // Neste bloco, 'b' é tratado como não-null, permitindo o acesso seguro a b.length.
        b.length
    } else {
        // Se 'b' for null, atribuímos -1 à variável 'length' para indicar ausência de um valor válido.
        -1
    }

    // Exibe o resultado: se 'b' fosse uma string válida, mostraria seu comprimento;
    // mas como 'b' é null, o valor padrão -1 é exibido.
    println("Comprimento (if-null check): $length (usa if para evitar NPE)")
    println("--------------------------------------\n")
}



fun safeCallExample() {
    // Demonstra o uso do operador de safe call (?.), que permite acessar propriedades de uma variável
    // que pode ser nula sem causar exceções.
    println("Exemplo: Operador de safe call (?.)")

    // Declara a variável 'a' como String? (pode ser nula) e a inicializa com "Kotlin".
    // Aqui, 'a' possui um valor, logo não é nula.
    val a: String? = "Kotlin"

    // Declara a variável 'b' como String? e a inicializa com null.
    // Nesse caso, 'b' é nula.
    val b: String? = null

    // Ao acessar 'a?.length', o operador safe call (?.) verifica se 'a' não é nula.
    // Como 'a' contém "Kotlin", retorna o comprimento da string, que é 6.
    println("Para 'a' (não nulo): ${a?.length} (acessa length com segurança)")

    // Ao acessar 'b?.length', o operador safe call (?.) verifica se 'b' não é nula.
    // Como 'b' é nula, a expressão retorna null, evitando uma exceção.
    println("Para 'b' (nulo): ${b?.length} (retorna null sem erro)")
    println("--------------------------------------\n")
}



fun elvisOperatorExample() {
    // Exibe uma mensagem explicando o propósito do exemplo:
    // Demonstrar como o Elvis Operator (?:) fornece um valor padrão quando uma expressão é nula.
    println("Exemplo: Elvis Operator (?:)")

    // Declara uma variável 'b' do tipo String? (pode ser nula) e a inicializa com null.
    val b: String? = null

    // A expressão 'b?.length' tenta acessar o comprimento da string 'b'.
    // Como 'b' é null, 'b?.length' retorna null.
    // O Elvis Operator (?:) verifica se a expressão à esquerda é null;
    // se for, ele retorna o valor à direita (neste caso, 0).
    val length = b?.length ?: 0

    // Imprime o resultado: como 'b' é null, o comprimento é 0.
    println("Comprimento usando Elvis: $length (valor padrão de 0 se null)")
    println("--------------------------------------\n")
}


fun notNullAssertionExample() {
    println("Exemplo: Not-null assertion (!!)")

    // Declaração de uma variável 'b' do tipo String? que pode ser nula, mas aqui é inicializada com "Kotlin".
    val b: String? = "Kotlin"

    // O operador !! força a variável 'b' a ser tratada como não-nula.
    // Como 'b' contém "Kotlin", 'b!!.length' retorna o comprimento da string, que é 6.
    println("Usando !! em 'b': ${b!!.length} (confia que 'b' não é null)")

    // Abaixo, um exemplo de como o uso do !! pode causar uma exceção:
    // Se a variável for null, !! fará com que uma NullPointerException seja lançada.
    // Descomente o bloco abaixo para testar o comportamento com valor nulo.
    /*
    val c: String? = null
    // Aqui, c é null, então c!! lança uma NullPointerException ao tentar acessar c!!.length.
    println(c!!.length)
    */
    println("--------------------------------------\n")
}


fun nullableReceiverExample() {
    // Demonstrar como métodos podem ser chamados de forma segura em objetos que podem ser nulos.
    println("Exemplo: Nullable Receiver com safe call em método")

    // Cria uma variável do tipo Person? que pode ser nula.
    // Aqui, 'person1' é inicializada com null.
    val person1: Person? = null

    // Cria outra variável do tipo Person? com um objeto válido.
    // 'person2' é inicializada com uma instância de Person com o nome "Alice".
    val person2: Person? = Person("Alice")

    // Utiliza o operador de safe call (?.) para chamar o método toString().
    // Se a variável for null (como person1), o resultado da chamada é null, sem lançar exceção.
    println("person1?.toString(): ${person1?.toString()} (retorna null)")

    // Para person2, que contém um objeto válido, o safe call retorna a representação em string do objeto.
    println("person2?.toString(): ${person2?.toString()} (retorna representação da pessoa)")
    println("--------------------------------------\n")
}


fun letFunctionExample() {
    // Exibe uma mensagem inicial explicando o objetivo do exemplo:
    // Demonstrar como a função let pode ser usada para executar um bloco de código
    // somente se o valor for não nulo.
    println("Exemplo: Uso da função let para executar código se não nulo")

    // Cria uma lista que contém elementos do tipo String? (podem ser nulos).
    // A lista inclui "Kotlin", null e "Null Safety".
    val listWithNulls: List<String?> = listOf("Kotlin", null, "Null Safety")

    println("Itens não nulos na lista:")

    // Itera sobre cada elemento da lista usando forEach.
    listWithNulls.forEach { item ->
        // Se 'item' não for nulo, o operador safe call (?.) invoca a função let.
        // Dentro do bloco let, 'it' representa o valor não nulo de 'item'.
        // Assim, o valor é impresso.
        item?.let { println("- $it") }
    }
    println("--------------------------------------\n")
}


fun safeCastExample() {
    // Demonstrar como utilizar o operador de safe cast ('as?') para converter tipos de forma segura.
    println("Exemplo: Safe Cast com 'as?'")

    // Declara uma variável 'a' do tipo Any, que pode conter qualquer tipo de valor.
    // Neste exemplo, 'a' é inicializada com a string "Hello, Kotlin!".
    val a: Any = "Hello, Kotlin!"

    // Tenta converter 'a' para o tipo Int utilizando 'as?'.
    // Como 'a' não é um Int, o safe cast retorna null ao invés de lançar uma exceção.
    val aInt: Int? = a as? Int

    // Tenta converter 'a' para o tipo String utilizando 'as?'.
    // Como 'a' é realmente uma String, a conversão é bem-sucedida e retorna "Hello, Kotlin!".
    val aString: String? = a as? String

    // Imprime o resultado da conversão para Int: retorna null porque a conversão falhou.
    println("Safe cast para Int: $aInt (null, pois 'a' não é Int)")

    // Imprime o resultado da conversão para String: retorna "Hello, Kotlin!" pois a conversão foi bem-sucedida.
    println("Safe cast para String: $aString (sucesso na conversão)")
    println("--------------------------------------\n")
}


fun filterNotNullExample() {
    // Demonstrar como utilizar a função filterNotNull() para remover valores nulos de uma coleção.
    println("Exemplo: Filtrar valores nulos em uma coleção")

    // Cria uma lista de inteiros que pode conter valores nulos (tipo List<Int?>).
    // A lista contém os elementos: 1, 2, null e 4.
    val nullableList: List<Int?> = listOf(1, 2, null, 4)

    // Utiliza a função filterNotNull() para filtrar a lista,
    // retornando uma nova lista (tipo List<Int>) que contém apenas os valores não nulos.
    val intList: List<Int> = nullableList.filterNotNull()

    // Imprime a lista original, que inclui o valor null.
    println("Lista original: $nullableList")

    // Imprime a lista filtrada, mostrando somente os números inteiros não nulos.
    println("Lista filtrada (sem nulls): $intList")
    println("--------------------------------------\n")
}


fun main() {
    println("=== Demonstração dos Conceitos de Null Safety em Kotlin ===\n")

    nonNullVsNullableExample()
    ifNullCheckExample()
    safeCallExample()
    elvisOperatorExample()
    notNullAssertionExample()
    nullableReceiverExample()
    letFunctionExample()
    safeCastExample()
    filterNotNullExample()

    println("Fim da demonstração de Null Safety!")
}
