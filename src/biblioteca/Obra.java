package biblioteca;

import java.io.Serializable;

/**
 * Classe que representa uma obra (livro) no sistema da biblioteca.
 * Implementa Serializable para permitir persistência de dados.
 */
public class Obra implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String titulo;
    private String autor;
    private int quantidade;

    /**
     * Construtor da classe Obra.
     * 
     * @param id         Identificador único da obra.
     * @param titulo     Título da obra.
     * @param autor      Autor da obra.
     * @param quantidade Quantidade disponível para empréstimo.
     */
    public Obra(int id, String titulo, String autor, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidade = Math.max(quantidade, 0); // Evita quantidade negativa
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = Math.max(quantidade, 0); // Evita quantidade negativa
    }

    /**
     * Realiza o empréstimo de um exemplar da obra.
     * 
     * @return true se o empréstimo for bem-sucedido, false se não houver exemplares disponíveis.
     */
    public boolean emprestar() {
        if (quantidade > 0) {
            quantidade--;
            return true;
        }
        return false;
    }

    /**
     * Registra a devolução de um exemplar da obra, aumentando a quantidade disponível.
     */
    public void devolver() {
        quantidade++;
    }

    /**
     * Representação textual da obra.
     * 
     * @return String formatada com informações da obra.
     */
    @Override
    public String toString() {
        return "📖 ID: " + id + " | Título: " + titulo + " | Autor: " + autor + " | 📦 Disponível: " + quantidade;
    }
}