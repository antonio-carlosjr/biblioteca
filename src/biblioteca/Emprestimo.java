package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que representa um empréstimo de uma obra no sistema da biblioteca.
 * Implementa Serializable para persistência de dados.
 */
public class Emprestimo implements Serializable {
    private String emailUsuario;
    private int idObra;
    private LocalDate dataEmprestimo;
    private boolean devolvido;

    /**
     * Construtor da classe Emprestimo.
     * 
     * @param emailUsuario Email do usuário que realizou o empréstimo.
     * @param idObra       ID da obra emprestada.
     */
    public Emprestimo(String emailUsuario, int idObra) {
        this.emailUsuario = emailUsuario;
        this.idObra = idObra;
        this.dataEmprestimo = LocalDate.now();
        this.devolvido = false;
    }

    /**
     * Obtém o email do usuário que pegou a obra emprestada.
     * 
     * @return Email do usuário.
     */
    public String getEmailUsuario() {
        return emailUsuario;
    }

    /**
     * Obtém o ID da obra emprestada.
     * 
     * @return ID da obra.
     */
    public int getIdObra() {
        return idObra;
    }

    /**
     * Obtém a data do empréstimo.
     * 
     * @return Data do empréstimo.
     */
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * Obtém o status do empréstimo (se foi devolvido ou não).
     * 
     * @return true se foi devolvido, false caso contrário.
     */
    public boolean isDevolvido() {
        return devolvido;
    }

    /**
     * Marca o empréstimo como devolvido.
     */
    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    /**
     * Verifica se o empréstimo está atrasado.
     * O prazo máximo para devolução é de 14 dias.
     * 
     * @return true se o empréstimo está atrasado, false caso contrário.
     */
    public boolean isAtrasado() {
        return !devolvido && ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now()) > 14;
    }

    /**
     * Representação textual do empréstimo.
     * 
     * @return String formatada com informações do empréstimo.
     */
    @Override
    public String toString() {
        return "📚 Empréstimo - Usuário: " + emailUsuario + " | Livro ID: " + idObra +
               " | Data: " + dataEmprestimo + " | 🔄 Devolvido: " + (devolvido ? "Sim" : "Não") +
               " | ⏳ Atrasado: " + (isAtrasado() ? "Sim" : "Não");
    }
}
