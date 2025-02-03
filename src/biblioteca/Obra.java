package biblioteca; 

import java.io.Serializable;

/**
 * Classe que representa uma obra (livro) no sistema da biblioteca.
 * Implementa Serializable para persistência de dados.
 */
public class Obra implements Serializable {
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
        this.quantidade = quantidade;
    }

    /**
     * Obtém o ID da obra.
     * 
     * @return ID da obra.
     */
    public int getId() {
        return id;
    }

    /**
     * Define um novo ID para a obra.
     * 
     * @param id Novo ID da obra.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o título da obra.
     * 
     * @return Título da obra.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define um novo título para a obra.
     * 
     * @param titulo Novo título da obra.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o autor da obra.
     * 
     * @return Autor da obra.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define um novo autor para a obra.
     * 
     * @param autor Novo autor da obra.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém a quantidade disponível da obra.
     * 
     * @return Quantidade disponível.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade disponível da obra.
     * 
     * @param quantidade Nova quantidade disponível.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Reduz a quantidade disponível da obra ao realizar um empréstimo.
     * 
     * @return true se o empréstimo for bem-sucedido, false se não houver exemplares disponíveis.
     */
    public boolean emprestar() {
        if (quantidade > 0) {
            quantidade--;
            return true;
        } else {
            System.out.println("❌ Empréstimo não realizado! Não há exemplares disponíveis.");
            return false;
        }
    }

    /**
     * Aumenta a quantidade disponível da obra ao realizar uma devolução.
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
