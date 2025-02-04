package biblioteca;

import java.io.Serializable;

/**
 * Classe que representa um Bibliotecário no sistema da biblioteca.
 * O bibliotecário é responsável pelo cadastro de usuários e devoluções.
 */
public class Bibliotecario extends Usuario {
    private static final long serialVersionUID = 1L;
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
        this(nome, email, senha, telefone, 0);
    }

    /**
     * Construtor usado ao carregar dados do arquivo.
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTotalDevolucoes() {
        return totalDevolucoes;
    }

    public void incrementarDevolucoes() {
        this.totalDevolucoes++;
    }

    @Override
    public String toString() {
        return super.toString() + " | Telefone: " + telefone + " | Devoluções: " + totalDevolucoes;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 0;
    }
}
