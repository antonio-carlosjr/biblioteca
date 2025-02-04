package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar os empréstimos e devoluções de obras na
 * biblioteca.
 */
public class GerenciadorDeEmprestimos {

    private List<Emprestimo> emprestimosAtivos;
    private GerenciadorDeObras gerenciadorObras;
    private static final String ARQUIVO_EMPRESTIMOS = "emprestimos.txt";

    /**
     * Construtor que inicializa a lista de empréstimos e carrega os dados do
     * arquivo.
     */
    public GerenciadorDeEmprestimos(GerenciadorDeObras gerenciadorObras) {
        this.emprestimosAtivos = new ArrayList<>();
        this.gerenciadorObras = gerenciadorObras;
        carregarEmprestimos();
    }

    public boolean realizarEmprestimo(Usuario usuario, int idObra) {
        if (usuario instanceof Bibliotecario) {
            System.out.println("Bibliotecários não podem realizar empréstimos.");
            return false;
        }

        long emprestimosAtuais = emprestimosAtivos.stream()
                .filter(e -> e.getEmailUsuario().equals(usuario.getEmail()) && !e.isDevolvido())
                .count();

        if (emprestimosAtuais >= usuario.getLimiteEmprestimos()) {
            System.out.println("Limite de empréstimos atingido!");
            return false;
        }

        if (usuario.isBloqueado()) {
            System.out.println("Usuário bloqueado devido a atrasos.");
            return false;
        }

        Obra obra = gerenciadorObras.buscarObraPorId(idObra);
        if (obra == null || obra.getQuantidade() <= 0) {
            System.out.println("Obra indisponível para empréstimo.");
            return false;
        }

        if (obra.emprestar()) {
            emprestimosAtivos.add(new Emprestimo(usuario.getEmail(), idObra));
            salvarEmprestimos();
            gerenciadorObras.salvarAcervo();
            System.out.println("Empréstimo realizado com sucesso!");
            return true;
        }
        return false;
    }

    public void registrarDevolucao(String emailUsuario, int idObra) {
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getEmailUsuario().equals(emailUsuario) && e.getIdObra() == idObra && !e.isDevolvido()) {
                e.setDevolvido(true);
                Obra obra = gerenciadorObras.buscarObraPorId(idObra);
                if (obra != null) {
                    obra.devolver();
                }
                salvarEmprestimos();
                gerenciadorObras.salvarAcervo();
                System.out.println("Devolução registrada com sucesso!");
                return;
            }
        }
        System.out.println("Empréstimo não encontrado.");
    }

    public void carregarEmprestimos() {
        File arquivo = new File(ARQUIVO_EMPRESTIMOS);
        if (!arquivo.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            emprestimosAtivos = (List<Emprestimo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar empréstimos: " + e.getMessage());
        }
    }

    public void salvarEmprestimos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_EMPRESTIMOS))) {
            oos.writeObject(emprestimosAtivos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar empréstimos: " + e.getMessage());
        }
    }

    public void bloquearUsuariosComAtraso(GerenciadorDeUsuarios gerenciadorUsuarios) {
        for (Emprestimo e : emprestimosAtivos) {
            if (e.isAtrasado()) {
                Usuario usuario = gerenciadorUsuarios.getUsuarios().stream()
                        .filter(u -> u.getEmail().equals(e.getEmailUsuario()))
                        .findFirst()
                        .orElse(null);
                if (usuario != null) {
                    usuario.setBloqueado(true);
                }
            }
        }
    }

    public void listarObrasEmprestadas() {
        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhuma obra emprestada no momento.");
            return;
        }

        System.out.println("\n=== Obras Emprestadas ===");
        for (Emprestimo e : emprestimosAtivos) {
            if (!e.isDevolvido()) {
                System.out.println("Usuário: " + e.getEmailUsuario() + " | Obra ID: " + e.getIdObra());
            }
        }
    }

    public void listarUsuariosAtrasados() {
        boolean encontrouAtrasados = false;

        System.out.println("\n=== Usuários com Empréstimos Atrasados ===");
        for (Emprestimo e : emprestimosAtivos) {
            if (e.isAtrasado()) {
                System.out.println("Usuário: " + e.getEmailUsuario() + " | Obra ID: " + e.getIdObra());
                encontrouAtrasados = true;
            }
        }

        if (!encontrouAtrasados) {
            System.out.println("Nenhum usuário com empréstimo atrasado.");
        }
    }

}
