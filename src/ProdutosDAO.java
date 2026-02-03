    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */

    /**
     *
     * @author Adm
     */

    import java.sql.PreparedStatement;
    import java.sql.Connection;
    import javax.swing.JOptionPane;
    import java.sql.ResultSet;
    import java.util.ArrayList;


    public class ProdutosDAO {

        Connection conn;
        PreparedStatement prep;
        ResultSet resultset;
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        public void cadastrarProduto (ProdutosDTO produto){

            // Commit 5 - filtro para apenas itens vendidos
            //conn = new conectaDAO().connectDB();
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    conn = new conectaDAO().connectDB();

    try {
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, produto.getNome());
        pst.setDouble(2, produto.getValor());
        pst.setString(3, produto.getStatus());

        pst.execute();
        pst.close();

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    }    

        }

        public ArrayList<ProdutosDTO> listarProdutos() {

    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos";

    conn = new conectaDAO().connectDB();

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getDouble("valor"));
            produto.setStatus(resultset.getString("status"));

            lista.add(produto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos");
    }

    return lista;
}
        
        public ArrayList<ProdutosDTO> listarProdutosVendidos() {

    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    conn = new conectaDAO().connectDB();

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO dto = new ProdutosDTO();
            dto.setId(resultset.getInt("id"));
            dto.setNome(resultset.getString("nome"));
            dto.setValor(resultset.getDouble("valor"));
            dto.setStatus(resultset.getString("status"));

            lista.add(dto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendidos");
    }

    return lista;
}




        public void venderProduto(int id) {

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    conn = new conectaDAO().connectDB();

    try {
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
}
    }
