import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaGerenciamentoEstoque {
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    public static void main(String[] args) {
        List<Produto> produtos = Produto.lerArquivoCSV("estoque.csv");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Produto.salvarProdutosNoBanco(produtos, conn);
            System.out.println("Produtos salvos no banco de dados.");

            // Relatórios
            System.out.println("Quantidade de categorias de produtos: " + Produto.contarCategorias(conn));
            System.out.println("Quantidade de produtos por categoria:");
            Map<String, Integer> produtosPorCategoria = Produto.contarProdutosPorCategoria(conn);
            for (Map.Entry<String, Integer> entry : produtosPorCategoria.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Valor médio dos produtos: " + Produto.calcularValorMedio(conn));
            System.out.println("Produtos em estoque baixo:");
            List<Produto> estoqueBaixo = Produto.listarProdutosEstoqueBaixo(conn);
            for (Produto produto : estoqueBaixo) {
                System.out.println(produto.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
