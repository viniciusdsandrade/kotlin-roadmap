package assessment_1_special_topics_in_informatics

/**
 * Classe base Pessoa
 * - Construtor principal
 * - Atributos armazenados em propriedades var, com getters e setters (quando necessário)
 * - Métodos cadastrarConta() e visualizarConta() como exemplo de implementação
 */
open class Pessoa(
    private var nome: String,
    private var rua: String,
    private var telefone: String,
    private var numero: Int,
    private var bairro: String,
    private var cidade: String,
    private var agencia: Int,
    private var conta: Int,
    salario: Float
) {
    // Propriedade com getter e setter personalizados (exemplo)
    var salario: Float = salario
        get() {
            // Você pode inserir lógicas extras no getter, se precisar
            return field
        }
        set(value) {
            // Você pode inserir lógicas extras no setter, se precisar
            if (value >= 0) {
                field = value
            } else {
                println("Salário não pode ser negativo. O valor não foi atualizado.")
            }
        }

    /**
     * Metodo para cadastrar a conta.
     * Dependendo da sua regra de negócio, implemente a lógica adequada.
     */
    open fun cadastrarConta() {
        println("\n=== Cadastrando Conta ===")
        println("Titular: $nome")
        println("Agência: $agencia | Conta: $conta")
        // Aqui poderiam entrar lógicas para gravação em banco de dados, etc.
    }

    /**
     * Metodo para visualizar os dados da conta.
     */
    open fun visualizarConta() {
        println("\n=== Visualizar Conta ===")
        println("Titular: $nome")
        println("Agência: $agencia | Conta: $conta")
        println("Endereço: $rua, $numero, $bairro, $cidade")
        println("Telefone: $telefone")
        println("Salário: R$ %.2f".format(salario))
    }
}

/**
 * Classe PessoaFisica herdando de Pessoa
 * Atributo adicional: cpf
 */
class PessoaFisica(
    nome: String,
    rua: String,
    telefone: String,
    numero: Int,
    bairro: String,
    cidade: String,
    agencia: Int,
    conta: Int,
    salario: Float,
    var cpf: String
) : Pessoa(nome, rua, telefone, numero, bairro, cidade, agencia, conta, salario) {

    // Sobrescrita de metodo (exemplo)
    override fun cadastrarConta() {
        super.cadastrarConta()
        println("CPF: $cpf")
    }

    override fun visualizarConta() {
        super.visualizarConta()
        println("CPF: $cpf")
    }
}

/**
 * Classe PessoaJuridica herdando de Pessoa
 * Atributo adicional: cnpj
 */
class PessoaJuridica(
    nome: String,
    rua: String,
    telefone: String,
    numero: Int,
    bairro: String,
    cidade: String,
    agencia: Int,
    conta: Int,
    salario: Float,
    var cnpj: String
) : Pessoa(nome, rua, telefone, numero, bairro, cidade, agencia, conta, salario) {

    // Sobrescrita de metodo (exemplo)
    override fun cadastrarConta() {
        super.cadastrarConta()
        println("CNPJ: $cnpj")
    }

    override fun visualizarConta() {
        super.visualizarConta()
        println("CNPJ: $cnpj")
    }
}

/**
 * Função main para demonstrar o uso das classes
 */
fun main() {
    // Criando um objeto PessoaFisica
    val pf = PessoaFisica(
        nome = "João da Silva",
        rua = "Av. das Flores",
        telefone = "11 99999-1234",
        numero = 123,
        bairro = "Centro",
        cidade = "São Paulo",
        agencia = 1111,
        conta = 222222,
        salario = 3000.0f,
        cpf = "123.456.789-00"
    )

    // Invocando métodos
    pf.cadastrarConta()
    pf.visualizarConta()

    // Tentativa de ajustar salário para um valor negativo (demonstra setter personalizado)
    pf.salario = -500.0f
    println("Salário após tentativa de atribuição negativa: R$ ${pf.salario}\n")

    // Criando um objeto PessoaJuridica
    val pj = PessoaJuridica(
        nome = "Empresa XYZ",
        rua = "Rua das Palmeiras",
        telefone = "21 98888-5678",
        numero = 456,
        bairro = "Bairro Industrial",
        cidade = "Rio de Janeiro",
        agencia = 3333,
        conta = 444444,
        salario = 10000.0f,
        cnpj = "12.345.678/0001-99"
    )

    // Invocando métodos
    pj.cadastrarConta()
    pj.visualizarConta()
}
