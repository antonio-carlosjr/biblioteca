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

    /**
     * Obtém a lista de usuários cadastrados.
     *
     * @return Lista de usuários.
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param usuario Usuário a ser cadastrado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    /**
     * Valida o login do usuário.
     *
     * @param email Email fornecido pelo usuário.
     * @param senha Senha fornecida pelo usuário.
     * @return Objeto `Usuario` se as credenciais estiverem corretas, `null`
     * caso contrário.
     */
    public Usuario validarLogin(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Carrega os usuários do arquivo `usuarios.txt`.
     */
    public void carregarUsuarios() {
        File arquivo = new File(ARQUIVO_USUARIOS);
        if (!arquivo.exists()) {
            System.out.println("⚠ Arquivo de usuários não encontrado: " + ARQUIVO_USUARIOS);
            return;
        }

        System.out.println("✅ Arquivo de usuários encontrado! Lendo...");

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                if (linha.isEmpty()) { // Ignorar linhas vazias
                    continue;
                }

                System.out.println("🔍 Usuário carregado: " + linha);
                String[] dados = linha.split(",");
                if (dados.length >= 4) {
                    String tipo = dados[0];
                    String nome = dados[1];
                    String email = dados[2];
                    String senha = dados[3];

                    switch (tipo) {
                        case "B" -> {
                            if (dados.length == 6) {
                                String telefone = dados[4];
                                int totalDevolucoes = Integer.parseInt(dados[5]);
                                usuarios.add(new Bibliotecario(nome, email, senha, telefone, totalDevolucoes));
                            }
                        }
                        case "P" -> {
                            if (dados.length == 5) {
                                String departamento = dados[4];
                                usuarios.add(new Professor(nome, email, senha, departamento));
                            }
                        }
                        case "A" -> {
                            if (dados.length == 6) {
                                String matricula = dados[4];
                                String curso = dados[5];
                                usuarios.add(new Aluno(nome, email, senha, matricula, curso));
                            }
                        }
                        default ->
                            System.out.println("⚠ Tipo de usuário desconhecido: " + tipo);
                    }
                } else {
                    System.out.println("⚠ Linha mal formatada: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠ Erro ao carregar usuários: " + e.getMessage());
        }
    }

    /**
     * Salva os usuários no arquivo `usuarios.txt`.
     */
    public void salvarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Bibliotecario bibliotecario) {
                    bw.write("B," + bibliotecario.getNome() + "," + bibliotecario.getEmail() + ","
                            + bibliotecario.getSenha() + "," + bibliotecario.getTelefone() + ","
                            + bibliotecario.getTotalDevolucoes());
                } else if (usuario instanceof Professor professor) {
                    bw.write("P," + professor.getNome() + "," + professor.getEmail() + ","
                            + professor.getSenha() + "," + professor.getDepartamento());
                } else if (usuario instanceof Aluno aluno) {
                    bw.write("A," + aluno.getNome() + "," + aluno.getEmail() + ","
                            + aluno.getSenha() + "," + aluno.getMatricula() + ","
                            + aluno.getCurso());
                }
                bw.newLine(); // Adiciona uma nova linha para cada usuário
            }
            System.out.println("✅ Usuários salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("⚠ Erro ao salvar usuários: " + e.getMessage());
        }
    }

}
