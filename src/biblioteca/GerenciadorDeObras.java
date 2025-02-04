package biblioteca;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar as obras da biblioteca. Essa classe permite
 * carregar, exibir, buscar e salvar obras no acervo.
 */
public class GerenciadorDeObras {

    private List<Obra> obras;
    private static final String ARQUIVO_ACERVO = "acervo.csv";

    /**
     * Construtor da classe. Inicializa a lista de obras e carrega os dados do acervo.
     */
    public GerenciadorDeObras() {
        this.obras = new ArrayList<>();
        carregarAcervo();
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void exibirAcervo() {
        if (obras.isEmpty()) {
            System.out.println("Nenhuma obra encontrada no acervo.");
        } else {
            obras.forEach(System.out::println);
        }
    }

    public Obra buscarObraPorId(int id) {
        return obras.stream()
                .filter(obra -> obra.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void buscarObraPorTitulo(String titulo) {
        obras.stream()
            .filter(obra -> obra.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .ifPresentOrElse(
                obra -> System.out.println("Obra encontrada: " + obra),
                () -> System.out.println("Nenhuma obra encontrada com esse título."));
    }

    public void carregarAcervo() {
        File arquivo = new File(ARQUIVO_ACERVO);
        if (!arquivo.exists()) {
            System.out.println("O arquivo `acervo.csv` não foi encontrado!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            br.lines()
              .skip(1) // Pular cabeçalho
              .map(linha -> linha.split(","))
              .filter(dados -> dados.length == 3)
              .forEach(dados -> {
                  try {
                      int id = Integer.parseInt(dados[0].trim());
                      String titulo = dados[1].trim();
                      int quantidade = Integer.parseInt(dados[2].trim());
                      obras.add(new Obra(id, titulo, "Autor Desconhecido", quantidade));
                  } catch (NumberFormatException e) {
                      System.out.println("Erro ao processar linha inválida: " + String.join(",", dados));
                  }
              });
        } catch (IOException e) {
            System.out.println("Erro ao carregar o acervo: " + e.getMessage());
        }
    }

    public void salvarAcervo() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARQUIVO_ACERVO), StandardCharsets.UTF_8))) {
            bw.write("ID,Título,Quantidade");
            bw.newLine();
            for (Obra obra : obras) {
                bw.write(obra.getId() + "," + obra.getTitulo() + "," + obra.getQuantidade());
                bw.newLine();
            }
            System.out.println("Acervo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o acervo: " + e.getMessage());
        }
    }
}