package biblioteca; 

/**
 * Classe que representa um Aluno no sistema da biblioteca.
 * Alunos têm um limite de até 2 empréstimos simultâneos.
 */
public class Aluno extends Usuario {
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

    /**
     * Obtém a matrícula do aluno.
     * 
     * @return Matrícula do aluno.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define uma nova matrícula para o aluno.
     * 
     * @param matricula Nova matrícula do aluno.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtém o curso do aluno.
     * 
     * @return Curso do aluno.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Define um novo curso para o aluno.
     * 
     * @param curso Novo curso do aluno.
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Retorna o limite de empréstimos para alunos.
     * 
     * @return O limite de empréstimos (2).
     */
    @Override
    public int getLimiteEmprestimos() {
        return LIMITE_EMPRESTIMOS;
    }

    /**
     * Representação textual do aluno.
     * 
     * @return String formatada com informações do aluno.
     */
    @Override
    public String toString() {
        return super.toString() + " | 🎓 Matrícula: " + matricula + " | 📚 Curso: " + curso;
    }
}
