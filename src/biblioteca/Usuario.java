package biblioteca;

import java.io.Serializable;

/**
 * Classe base para representar um usuário no sistema da biblioteca.
 * Implementa Serializable para persistência de dados.
 */
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String email;
    private String senha;
    private boolean bloqueado;

    /**
     * Construtor da classe Usuario.
     * 
     * @param nome  Nome do usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     */
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.bloqueado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * Método abstrato para obter o limite máximo de empréstimos de cada tipo de usuário.
     * 
     * @return O limite máximo de empréstimos permitido para esse usuário.
     */
    public abstract int getLimiteEmprestimos();

    @Override
    public String toString() {
        return "Usuário: " + nome + " | Email: " + email + " | Bloqueado: " + (bloqueado ? "Sim" : "Não");
    }
}
