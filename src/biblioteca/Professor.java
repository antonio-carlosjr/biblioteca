package biblioteca; 

/**
 * Classe que representa um Professor no sistema da biblioteca.
 * Professores têm um limite de até 10 empréstimos simultâneos.
 */
public class Professor extends Usuario {
    private String departamento;
    private static final int LIMITE_EMPRESTIMOS = 10;

    /**
     * Construtor da classe Professor.
     * 
     * @param nome        Nome do professor
     * @param email       Email do professor
     * @param senha       Senha do professor
     * @param departamento Departamento ao qual o professor está vinculado
     */
    public Professor(String nome, String email, String senha, String departamento) {
        super(nome, email, senha);
        this.departamento = departamento;
    }

    /**
     * Obtém o departamento do professor.
     * 
     * @return Departamento do professor.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define um novo departamento para o professor.
     * 
     * @param departamento Novo departamento do professor.
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Retorna o limite de empréstimos para professores.
     * 
     * @return O limite de empréstimos (10).
     */
    @Override
    public int getLimiteEmprestimos() {
        return LIMITE_EMPRESTIMOS;
    }

    /**
     * Representação textual do professor.
     * 
     * @return String formatada com informações do professor.
     */
    @Override
    public String toString() {
        return super.toString() + " | 🏫 Departamento: " + departamento;
    }
}
