package biblioteca;

import java.io.Serializable;

/**
 * Classe que representa um Professor no sistema da biblioteca.
 * Professores podem ter até 10 empréstimos simultâneos.
 */
public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String departamento;
    private static final int LIMITE_EMPRESTIMOS = 10;

    /**
     * Construtor da classe Professor.
     * 
     * @param nome         Nome do professor
     * @param email        Email do professor
     * @param senha        Senha do professor
     * @param departamento Departamento ao qual o professor está vinculado
     */
    public Professor(String nome, String email, String senha, String departamento) {
        super(nome, email, senha);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public int getLimiteEmprestimos() {
        return LIMITE_EMPRESTIMOS;
    }

    @Override
    public String toString() {
        return super.toString() + " | Departamento: " + departamento + " | Limite de empréstimos: " + LIMITE_EMPRESTIMOS;
    }
}