package biblioteca;

import java.util.Scanner;

/**
 * Classe principal do sistema de biblioteca.
 * Gerencia o menu, login, logout e ações dos usuários.
 */
public class SistemaBiblioteca {

    // Gerenciadores responsáveis por manipular usuários, obras e empréstimos
    private final GerenciadorDeUsuarios gerenciadorUsuarios = new GerenciadorDeUsuarios();
    private final GerenciadorDeObras gerenciadorObras = new GerenciadorDeObras();
    private final GerenciadorDeEmprestimos gerenciadorEmprestimos = new GerenciadorDeEmprestimos(gerenciadorObras);
    
    private Usuario usuarioLogado; // Armazena o usuário autenticado no momento
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia a execução do sistema.
     */
    public void iniciar() {
        gerenciadorEmprestimos.carregarEmprestimos(); // Carrega histórico de empréstimos

        while (true) {
            System.out.println("\n=== BIBLIOTECA MUNICIPAL ===");
            System.out.println("1 - Login");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome quebra de linha para evitar problemas

            switch (opcao) {
                case 1 -> fazerLogin();
                case 2 -> {
                    System.out.println("Saindo do sistema. Até mais!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Realiza o login do usuário.
     */
    private void fazerLogin() {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = gerenciadorUsuarios.validarLogin(email, senha);

        if (usuarioLogado != null) {
            System.out.println("Login realizado com sucesso!");
            exibirMenuUsuario();
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    /**
     * Exibe o menu correspondente ao tipo de usuário logado.
     */
    private void exibirMenuUsuario() {
        while (usuarioLogado != null) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Consultar obras");
            System.out.println("2 - Realizar empréstimo");
            System.out.println("3 - Devolver obra");
            System.out.println("4 - Buscar obra por título");
            System.out.println("5 - Logout");

            // Opções exclusivas para bibliotecários
            if (usuarioLogado instanceof Bibliotecario) {
                System.out.println("6 - Cadastrar novo usuário");
                System.out.println("7 - Relatório de obras emprestadas");
                System.out.println("8 - Relatório de usuários com atraso");
            }

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Evita problemas com a leitura de entrada

            switch (opcao) {
                case 1 -> gerenciadorObras.exibirAcervo();
                case 2 -> realizarEmprestimo();
                case 3 -> registrarDevolucao();
                case 4 -> buscarObraPorTitulo();
                case 5 -> logout();
                case 6 -> {
                    if (usuarioLogado instanceof Bibliotecario) {
                        cadastrarUsuarioBibliotecario();
                    } else {
                        System.out.println("Apenas bibliotecários podem cadastrar usuários.");
                    }
                }
                case 7 -> {
                    if (usuarioLogado instanceof Bibliotecario) {
                        listarObrasEmprestadas();
                    } else {
                        System.out.println("Apenas bibliotecários podem acessar este relatório.");
                    }
                }
                case 8 -> {
                    if (usuarioLogado instanceof Bibliotecario) {
                        listarUsuariosAtrasados();
                    } else {
                        System.out.println("Apenas bibliotecários podem acessar este relatório.");
                    }
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Realiza o empréstimo de uma obra para o usuário logado.
     */
    private void realizarEmprestimo() {
        System.out.print("\nDigite o ID da obra desejada: ");
        int idObra = scanner.nextInt();
        scanner.nextLine();
        
        boolean sucesso = gerenciadorEmprestimos.realizarEmprestimo(usuarioLogado, idObra);
        
        if (sucesso) {
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o empréstimo.");
        }
    }

    /**
     * Registra a devolução de uma obra emprestada.
     */
    private void registrarDevolucao() {
        System.out.print("\nDigite o ID da obra a ser devolvida: ");
        int idObra = scanner.nextInt();
        scanner.nextLine();

        gerenciadorEmprestimos.registrarDevolucao(usuarioLogado.getEmail(), idObra);
    }

    /**
     * Busca uma obra pelo título.
     */
    private void buscarObraPorTitulo() {
        System.out.print("\nDigite o título da obra: ");
        String titulo = scanner.nextLine();
        gerenciadorObras.buscarObraPorTitulo(titulo);
    }

    /**
     * Cadastra um novo usuário no sistema (apenas para bibliotecários).
     */
    private void cadastrarUsuarioBibliotecario() {
        if (!(usuarioLogado instanceof Bibliotecario)) {
            System.out.println("Apenas bibliotecários podem cadastrar usuários.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        gerenciadorUsuarios.cadastrarUsuario(new Bibliotecario(nome, email, senha, "Telefone não informado"));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    /**
     * Lista todas as obras que estão emprestadas no momento (apenas para bibliotecários).
     */
    private void listarObrasEmprestadas() {
        gerenciadorEmprestimos.listarObrasEmprestadas();
    }

    /**
     * Lista os usuários que possuem empréstimos em atraso (apenas para bibliotecários).
     */
    private void listarUsuariosAtrasados() {
        gerenciadorEmprestimos.listarUsuariosAtrasados();
    }

    /**
     * Realiza o logout do usuário logado.
     */
    private void logout() {
        System.out.println("Logout realizado com sucesso.");
        usuarioLogado = null;
    }
}
