package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar os usuários do sistema. Permite o cadastro,
 * busca, carregamento e salvamento de usuários.
 */
public class GerenciadorDeUsuarios {

    private List<Usuario> usuarios;
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    /**
     * Construtor que inicializa a lista de usuários e carrega os dados do
     * arquivo.
     */
    public GerenciadorDeUsuarios() {
        this.usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public Usuario validarLogin(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public void carregarUsuarios() {
        File arquivo = new File(ARQUIVO_USUARIOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de usuários não encontrado: " + ARQUIVO_USUARIOS);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 4) {
                    String tipo = dados[0];
                    String nome = dados[1];
                    String email = dados[2];
                    String senha = dados[3];
                    switch (tipo) {
                        case "B" -> {
                            String telefone = dados[4];
                            int totalDevolucoes = Integer.parseInt(dados[5]);
                            usuarios.add(new Bibliotecario(nome, email, senha, telefone, totalDevolucoes));
                        }
                        case "P" -> {
                            String departamento = dados[4];
                            usuarios.add(new Professor(nome, email, senha, departamento));
                        }
                        case "A" -> {
                            String matricula = dados[4];
                            String curso = dados[5];
                            usuarios.add(new Aluno(nome, email, senha, matricula, curso));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public void salvarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Bibliotecario biblio) {
                    bw.write("B," + biblio.getNome() + "," + biblio.getEmail() + "," + biblio.getSenha() + "," + biblio.getTelefone() + "," + biblio.getTotalDevolucoes());
                } else if (usuario instanceof Professor prof) {
                    bw.write("P," + prof.getNome() + "," + prof.getEmail() + "," + prof.getSenha() + "," + prof.getDepartamento());
                } else if (usuario instanceof Aluno aluno) {
                    bw.write("A," + aluno.getNome() + "," + aluno.getEmail() + "," + aluno.getSenha() + "," + aluno.getMatricula() + "," + aluno.getCurso());
                }
                bw.newLine();
            }
            System.out.println("Usuários salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}
