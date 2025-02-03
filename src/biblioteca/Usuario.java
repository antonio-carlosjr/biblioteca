package biblioteca;

import java.io.Serializable;

/**
 * Classe base para representar um usuário no sistema da biblioteca.
 * Implementa Serializable para persistência de dados.
 */
public abstract class Usuario implements Serializable {
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
        this.bloqueado = false; // Usuário começa sem bloqueio
    }

    /**
     * Obtém o nome do usuário.
     * 
     * @return Nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define um novo nome para o usuário.
     * 
     * @param nome Novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do usuário.
     * 
     * @return Email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define um novo email para o usuário.
     * 
     * @param email Novo email do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém a senha do usuário.
     * 
     * @return Senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define uma nova senha para o usuário.
     * 
     * @param senha Nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Verifica se o usuário está bloqueado.
     * 
     * @return true se estiver bloqueado, false caso contrário.
     */
    public boolean isBloqueado() {
        return bloqueado;
    }

    /**
     * Define se o usuário está bloqueado ou não.
     * 
     * @param bloqueado Estado de bloqueio do usuário.
     */
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * Método abstrato para obter o limite máximo de empréstimos de cada tipo de usuário.
     * Esse método deve ser implementado pelas classes filhas (Aluno, Professor).
     * 
     * @return O limite máximo de empréstimos permitido para esse usuário.
     */
    public abstract int getLimiteEmprestimos();

    /**
     * Representação textual do usuário.
     * 
     * @return String formatada com informações básicas do usuário.
     */
    @Override
    public String toString() {
        return "👤 Usuário: " + nome + " | ✉️ Email: " + email + " | 🔒 Bloqueado: " + (bloqueado ? "Sim" : "Não");
    }
}
