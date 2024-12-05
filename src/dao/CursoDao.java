package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoJDBC;
import modelo.Curso;

public class CursoDao extends ConexaoJDBC {

    public void inserir(Curso curso) {
        try {
            String sql = "INSERT INTO curso(nome, descricao) VALUES(?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM curso";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao")
                );
                cursos.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public void atualizar(Curso curso) {
        try {
            String sql = "UPDATE curso SET nome = ?, descricao = ? WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try {
            String sql = "DELETE FROM curso WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
