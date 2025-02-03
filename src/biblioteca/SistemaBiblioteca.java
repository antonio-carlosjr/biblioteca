package biblioteca;

import java.util.Scanner;

/**
 * Classe principal do sistema de biblioteca. Gerencia o menu, login, logout e
 * ações dos usuários.
 */
public class SistemaBiblioteca {

    private final GerenciadorDeUsuarios gerenciadorUsuarios = new GerenciadorDeUsuarios();
    private final GerenciadorDeObras gerenciadorObras = new GerenciadorDeObras();
    private final GerenciadorDeEmprestimos gerenciadorEmprestimos = new GerenciadorDeEmprestimos(gerenciadorObras);
    private Usuario usuarioLogado;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia a execução do sistema.
     */
    public void iniciar() {
        gerenciadorUsuarios.carregarUsuarios();
        gerenciadorObras.carregarAcervo();
        gerenciadorEmprestimos.carregarEmprestimos();
        gerenciadorEmprestimos.bloquearUsuariosComAtraso(gerenciadorUsuarios);

        while (true) {
            System.out.println("\n📚 === BIBLIOTECA MUNICIPAL ===");
            System.out.println("1️⃣  Login");
            System.out.println("2️⃣  Sair");
            System.out.print("👉 Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 ->
                    fazerLogin();
                case 2 -> {
                    System.out.println("👋 Saindo do sistema. Até mais!");
                    return;
                }
                default ->
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    /**
     * Realiza o login do usuário.
     */
    private void fazerLogin() {
        System.out.print("\n✉️  Email: ");
        String email = scanner.nextLine();
        System.out.print("🔑 Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = gerenciadorUsuarios.validarLogin(email, senha);

        if (usuarioLogado != null) {
            System.out.println("✅ Login realizado com sucesso!");
            exibirMenuUsuario();
        } else {
            System.out.println("❌ Email ou senha incorretos.");
        }
    }

    /**
     * Exibe o menu correspondente ao tipo de usuário logado.
     */
    private void exibirMenuUsuario() {
        while (usuarioLogado != null) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1️⃣  Consultar obras");
            System.out.println("2️⃣  Realizar empréstimo");
            System.out.println("3️⃣  Devolver obra");
            System.out.println("4️⃣  Buscar obra por título");
            System.out.println("5️⃣  Logout");

            if (usuarioLogado instanceof Bibliotecario) {
                System.out.println("6️⃣  Cadastrar novo usuário");
                System.out.println("7️⃣  Relatório: Obras emprestadas");
                System.out.println("8️⃣  Relatório: Usuários com atraso");
            }

            System.out.print("👉 Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 ->
                    gerenciadorObras.exibirAcervo();
                case 2 ->
                    realizarEmprestimo();
                case 3 ->
                    registrarDevolucao();
                case 4 ->
                    buscarObraPorTitulo();
                case 5 ->
                    logout();
                case 6 -> {
                    if (usuarioLogado instanceof Bibliotecario) {
                        cadastrarUsuarioBibliotecario();
                    } else {
                        System.out.println("🚫 Apenas bibliotecários podem cadastrar usuários.");
                    }
                }
                case 7 -> {
                    if (usuarioLogado instanceof Bibliotecario) {
                        gerenciadorEmprestimos.listarObrasEmprestadas();
                    } else {
                        System.out.println("🚫 Apenas bibliotecários podem acessar este relatório.");
                    }
                }
                case 8 -> {
                    if (usuarioLogado instanceof Bibliotecario) {
                        gerenciadorEmprestimos.listarUsuariosAtrasados();
                    } else {
                        System.out.println("🚫 Apenas bibliotecários podem acessar este relatório.");
                    }
                }
                default ->
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    /**
     * Realiza o empréstimo de uma obra.
     */
    private void realizarEmprestimo() {
        System.out.println("\n📖 === OBRAS DISPONÍVEIS PARA EMPRÉSTIMO ===");
        gerenciadorObras.exibirAcervo();

        System.out.print("\n📌 Digite o ID da obra desejada: ");
        int idObra = scanner.nextInt();
        scanner.nextLine();

        gerenciadorEmprestimos.registrarEmprestimo(usuarioLogado.getEmail(), idObra);
    }

    /**
     * Registra a devolução de uma obra emprestada.
     */
    private void registrarDevolucao() {
        System.out.print("\n📌 Digite o ID da obra a ser devolvida: ");
        int idObra = scanner.nextInt();
        scanner.nextLine();

        gerenciadorEmprestimos.registrarDevolucao(usuarioLogado.getEmail(), idObra);
    }

    /**
     * Busca uma obra pelo título.
     */
    private void buscarObraPorTitulo() {
        System.out.print("\n🔎 Digite o título da obra: ");
        String titulo = scanner.nextLine();
        gerenciadorObras.buscarObraPorTitulo(titulo);
    }

    /**
     * Apenas bibliotecários podem cadastrar novos usuários.
     */
    private void cadastrarUsuarioBibliotecario() {
        if (!(usuarioLogado instanceof Bibliotecario)) {
            System.out.println("🚫 Apenas bibliotecários podem cadastrar usuários.");
            return;
        }

        System.out.println("\n=== NOVO USUÁRIO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Tipo (1-Aluno, 2-Professor): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        switch (tipo) {
            case 1 -> {
                System.out.print("Matrícula: ");
                String matricula = scanner.nextLine();
                System.out.print("Curso: ");
                String curso = scanner.nextLine();
                gerenciadorUsuarios.getUsuarios().add(new Aluno(nome, email, senha, matricula, curso));
            }
            case 2 -> {
                System.out.print("Departamento: ");
                String departamento = scanner.nextLine();
                gerenciadorUsuarios.getUsuarios().add(new Professor(nome, email, senha, departamento));
            }
            default -> {
                System.out.println("❌ Tipo inválido! Cadastro cancelado.");
                return;
            }
        }

        gerenciadorUsuarios.salvarUsuarios();
        System.out.println("✅ Usuário cadastrado com sucesso!");
    }

    /**
     * Realiza o logout do usuário.
     */
    private void logout() {
        System.out.println("👋 Logout realizado com sucesso.");
        usuarioLogado = null;
    }
}
