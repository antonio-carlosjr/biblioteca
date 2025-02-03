package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar os empréstimos e devoluções de obras na biblioteca.
 */
public class GerenciadorDeEmprestimos {
    private List<Emprestimo> emprestimosAtivos;
    private GerenciadorDeObras gerenciadorObras;
    private static final String ARQUIVO_EMPRESTIMOS = "emprestimos.txt";

    /**
     * Construtor que inicializa a lista de empréstimos e carrega os dados do arquivo.
     */
    public GerenciadorDeEmprestimos(GerenciadorDeObras gerenciadorObras) {
        this.emprestimosAtivos = new ArrayList<>();
        this.gerenciadorObras = gerenciadorObras;
        carregarEmprestimos();
    }

    /**
     * Registra um novo empréstimo para um usuário.
     */
    public void registrarEmprestimo(String emailUsuario, int idObra) {
        for (Obra obra : gerenciadorObras.getObras()) {
            if (obra.getId() == idObra && obra.emprestar()) {
                emprestimosAtivos.add(new Emprestimo(emailUsuario, idObra));
                salvarEmprestimos();
                System.out.println("✅ Empréstimo realizado com sucesso!");
                return;
            }
        }
        System.out.println("❌ Empréstimo falhou! Obra não encontrada ou indisponível.");
    }

    /**
     * Registra a devolução de uma obra.
     */
    public void registrarDevolucao(String emailUsuario, int idObra) {
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getEmailUsuario().equals(emailUsuario) && e.getIdObra() == idObra && !e.isDevolvido()) {
                e.setDevolvido(true);
                
                for (Obra obra : gerenciadorObras.getObras()) {
                    if (obra.getId() == idObra) {
                        obra.devolver();
                        break;
                    }
                }
                
                salvarEmprestimos();
                gerenciadorObras.salvarAcervo();
                System.out.println("✅ Devolução registrada com sucesso!");
                return;
            }
        }
        System.out.println("❌ Empréstimo não encontrado.");
    }

    /**
     * Bloqueia automaticamente usuários que possuem empréstimos atrasados.
     */
    public void bloquearUsuariosComAtraso(GerenciadorDeUsuarios gerenciadorUsuarios) {
        for (Emprestimo e : emprestimosAtivos) {
            if (e.isAtrasado() && !e.isDevolvido()) {
                for (Usuario u : gerenciadorUsuarios.getUsuarios()) {
                    if (u.getEmail().equals(e.getEmailUsuario())) {
                        u.setBloqueado(true);
                    }
                }
            }
        }
    }

    /**
     * Lista todas as obras atualmente emprestadas.
     */
    public void listarObrasEmprestadas() {
        System.out.println("\n📜 === Obras Atualmente Emprestadas ===");
        for (Emprestimo e : emprestimosAtivos) {
            if (!e.isDevolvido()) {
                System.out.println("📘 Obra ID: " + e.getIdObra() + " | Usuário: " + e.getEmailUsuario());
            }
        }
    }

    /**
     * Lista usuários que possuem empréstimos atrasados.
     */
    public void listarUsuariosAtrasados() {
        System.out.println("\n🚨 === Usuários com Empréstimos Atrasados ===");
        for (Emprestimo e : emprestimosAtivos) {
            if (e.isAtrasado() && !e.isDevolvido()) {
                System.out.println("📧 Usuário: " + e.getEmailUsuario() + " | Obra ID: " + e.getIdObra());
            }
        }
    }

    /**
     * Carrega os empréstimos do arquivo `emprestimos.txt`.
     */
    public void carregarEmprestimos() {
        File arquivo = new File(ARQUIVO_EMPRESTIMOS);
        if (!arquivo.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_EMPRESTIMOS))) {
            emprestimosAtivos = (List<Emprestimo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Erro ao carregar empréstimos: " + e.getMessage());
        }
    }

    /**
     * Salva os empréstimos no arquivo `emprestimos.txt`.
     */
    public void salvarEmprestimos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_EMPRESTIMOS))) {
            oos.writeObject(emprestimosAtivos);
        } catch (IOException e) {
            System.out.println("⚠️ Erro ao salvar empréstimos: " + e.getMessage());
        }
    }
}
