import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private String categoria;
    private double preco;

    public Produto(int id, String nome, int quantidade, String categoria, double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public static List<Produto> lerArquivoCSV(String arquivo) {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                int quantidade = Integer.parseInt(campos[2]);
                String categoria = campos[3];
                double preco = Double.parseDouble(campos[4]);
                produtos.add(new Produto(id, nome, quantidade, categoria, preco));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public static void salvarProdutosNoBanco(List<Produto> produtos, Connection conn) throws SQLException {
        String sql = "INSERT INTO produtos (id, nome, quantidade, categoria, preco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Produto produto : produtos) {
                stmt.setInt(1, produto.getId());
                stmt.setString(2, produto.getNome());
                stmt.setInt(3, produto.getQuantidade());
                stmt.setString(4, produto.getCategoria());
                stmt.setDouble(5, produto.getPreco());
                stmt.executeUpdate();
            }
        }
    }

    public static int contarCategorias(Connection conn) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT categoria) FROM produtos";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public static Map<String, Integer> contarProdutosPorCategoria(Connection conn) throws SQLException {
        Map<String, Integer> produtosPorCategoria = new HashMap<>();
        String sql = "SELECT categoria, SUM(quantidade) FROM produtos GROUP BY categoria";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String categoria = rs.getString(1);
                int quantidade = rs.getInt(2);
                produtosPorCategoria.put(categoria, quantidade);
            }
        }
        return produtosPorCategoria;
    }

    public static double calcularValorMedio(Connection conn) throws SQLException {
        String sql = "SELECT AVG(preco) FROM produtos";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getDouble(1);
        }
    }

    public static List<Produto> listarProdutosEstoqueBaixo(Connection conn) throws SQLException {
        List<Produto> estoqueBaixo = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE quantidade < 3";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                String categoria = rs.getString("categoria");
                double preco = rs.getDouble("preco");
                estoqueBaixo.add(new Produto(id, nome, quantidade, categoria, preco));
            }
        }
        return estoqueBaixo;
    }
}
