package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa um empréstimo no sistema da biblioteca.
 * Controla informações como usuário, obra e datas de empréstimo e devolução.
 */
public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String emailUsuario;
    private int idObra;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    /**
     * Construtor da classe Emprestimo.
     *
     * @param emailUsuario Email do usuário que realizou o empréstimo.
     * @param idObra ID da obra emprestada.
     */
    public Emprestimo(String emailUsuario, int idObra) {
        this.emailUsuario = emailUsuario;
        this.idObra = idObra;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(14); // Padrão: 14 dias para devolução
        this.devolvido = false;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public int getIdObra() {
        return idObra;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public boolean isAtrasado() {
        return !devolvido && LocalDate.now().isAfter(dataDevolucao);
    }

    @Override
    public String toString() {
        return "Usuário: " + emailUsuario + " | Obra ID: " + idObra + " | Empréstimo: " + dataEmprestimo + " | Devolução: " + dataDevolucao + " | Devolvido: " + (devolvido ? "Sim" : "Não");
    }
}