package conexao;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConexaoJDBC {
    private static Connection minhaConexao;

    public static Connection abrirConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   
            minhaConexao = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/escola", "root", "secret"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return minhaConexao;
    }

    public static void fecharConexao() {
        try {
            if (!minhaConexao.isClosed()) {
                minhaConexao.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (minhaConexao == null || minhaConexao.isClosed()) {
                abrirConexao();
            } 
            return minhaConexao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
