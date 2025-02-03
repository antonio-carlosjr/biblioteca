package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar as obras (livros) da biblioteca.
 * Carrega, salva e permite buscas no acervo.
 */
public class GerenciadorDeObras {
    private List<Obra> obras;
    private static final String ARQUIVO_ACERVO = "acervo.csv";

    /**
     * Construtor que inicializa a lista de obras e carrega os dados do arquivo.
     */
    public GerenciadorDeObras() {
        this.obras = new ArrayList<>();
        carregarAcervo();
    }

    /**
     * Obtém a lista de obras cadastradas.
     * 
     * @return Lista de obras.
     */
    public List<Obra> getObras() {
        return obras;
    }

    /**
     * Exibe todas as obras disponíveis no acervo.
     */
    public void exibirAcervo() {
        if (obras.isEmpty()) {
            System.out.println("📚 Nenhuma obra encontrada no acervo.");
        } else {
            for (Obra obra : obras) {
                System.out.println(obra);
            }
        }
    }

    /**
     * Busca uma obra pelo título e exibe suas informações.
     * 
     * @param titulo Título da obra a ser pesquisada.
     */
    public void buscarObraPorTitulo(String titulo) {
        for (Obra obra : obras) {
            if (obra.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("🔍 Obra encontrada: " + obra);
                return;
            }
        }
        System.out.println("⚠️ Nenhuma obra encontrada com esse título.");
    }

    /**
     * Carrega as obras do arquivo `acervo.csv`.
     */
    public void carregarAcervo() {
        File arquivo = new File(ARQUIVO_ACERVO);
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ACERVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    int id = Integer.parseInt(dados[0]);
                    String titulo = dados[1];
                    String autor = dados[2];
                    int quantidade = Integer.parseInt(dados[3]);
                    obras.add(new Obra(id, titulo, autor, quantidade));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Erro ao carregar o acervo: " + e.getMessage());
        }
    }

    /**
     * Salva as obras no arquivo `acervo.csv`.
     */
    public void salvarAcervo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_ACERVO))) {
            for (Obra obra : obras) {
                bw.write(obra.getId() + "," + obra.getTitulo() + "," + obra.getAutor() + "," + obra.getQuantidade());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Erro ao salvar o acervo: " + e.getMessage());
        }
    }
}
