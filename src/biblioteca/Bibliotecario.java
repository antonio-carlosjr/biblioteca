package biblioteca;

/**
 * Classe que representa um Bibliotecário no sistema da biblioteca.
 * O bibliotecário é responsável pelo cadastro de usuários e devoluções.
 */
public class Bibliotecario extends Usuario {

    private String telefone;
    private int totalDevolucoes;

    /**
     * Construtor da classe Bibliotecario usado para novos cadastros.
     *
     * @param nome Nome do bibliotecário
     * @param email Email do bibliotecário
     * @param senha Senha do bibliotecário
     * @param telefone Telefone do bibliotecário
     */
    public Bibliotecario(String nome, String email, String senha, String telefone) {
        this(nome, email, senha, telefone, 0); // Chama o segundo construtor com totalDevolucoes = 0
    }

    /**
     * Construtor usado ao carregar dados do arquivo (permite definir `totalDevolucoes`).
     *
     * @param nome Nome do bibliotecário
     * @param email Email do bibliotecário
     * @param senha Senha do bibliotecário
     * @param telefone Telefone do bibliotecário
     * @param totalDevolucoes Número total de devoluções realizadas
     */
    public Bibliotecario(String nome, String email, String senha, String telefone, int totalDevolucoes) {
        super(nome, email, senha);
        this.telefone = telefone;
        this.totalDevolucoes = totalDevolucoes;
    }

    /**
     * Obtém o telefone do bibliotecário.
     *
     * @return Telefone do bibliotecário.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define um novo telefone para o bibliotecário.
     *
     * @param telefone Novo telefone do bibliotecário.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o número total de devoluções feitas pelo bibliotecário.
     *
     * @return Total de devoluções.
     */
    public int getTotalDevolucoes() {
        return totalDevolucoes;
    }

    /**
     * Incrementa a quantidade de devoluções feitas pelo bibliotecário.
     */
    public void incrementarDevolucoes() {
        this.totalDevolucoes++;
    }

    /**
     * Representação textual do bibliotecário.
     *
     * @return String formatada com informações do bibliotecário.
     */
    @Override
    public String toString() {
        return super.toString() + " | 📞 Telefone: " + telefone + " | 📖 Devoluções: " + totalDevolucoes;
    }

    /**
     * Retorna o limite de empréstimos para bibliotecários.
     * Como bibliotecários não pegam livros emprestados, retorna 0.
     *
     * @return 0 (Bibliotecários não realizam empréstimos).
     */
    @Override
    public int getLimiteEmprestimos() {
        return 0;
    }
}