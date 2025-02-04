package biblioteca;

import java.io.Serializable;

/**
 * Classe que representa um Aluno no sistema da biblioteca.
 * Alunos podem ter até 2 empréstimos simultâneos.
 */
public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String matricula;
    private String curso;
    private static final int LIMITE_EMPRESTIMOS = 2;

    /**
     * Construtor da classe Aluno.
     * 
     * @param nome      Nome do aluno
     * @param email     Email do aluno
     * @param senha     Senha do aluno
     * @param matricula Matrícula do aluno
     * @param curso     Curso ao qual o aluno está vinculado
     */
    public Aluno(String nome, String email, String senha, String matricula, String curso) {
        super(nome, email, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public int getLimiteEmprestimos() {
        return LIMITE_EMPRESTIMOS;
    }

    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + matricula + " | Curso: " + curso + " | Limite de empréstimos: " + LIMITE_EMPRESTIMOS;
    }
}
