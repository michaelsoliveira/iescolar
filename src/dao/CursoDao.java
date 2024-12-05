package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Statement;

import conexao.ConexaoJDBC;
import modelo.Aluno;
import modelo.Curso;
import modelo.Pessoa;

public class CursoDao extends ConexaoJDBC {
    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<Curso>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * from curso");
            while(rs.next()) { 
                Curso curso = new Curso(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
                cursos.add(curso);
            }
            return cursos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(String matricula, Aluno aluno) {
        try {
            String sql = "update aluno SET matricula = "+ aluno.getMatricula()  +
                            ", nome = " + aluno.getPessoa().getNome() + 
                            " where matricula = " + matricula;
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserir(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno(matricula, nome) VALUES(?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, aluno.getMatricula());
            preparedStatement.setString(
                2, aluno.getPessoa().getNome()
            );
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM aluno WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
            "Erro ao excluir o aluno: " + e.getMessage());
        }
    }

    public Curso getCursoById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Curso curso = new Curso();
        String sql = 
        "SELECT * from curso WHERE id = ?";
        try {
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                curso.setNome(rs.getString("nome"));
            }
        } catch (Exception e) {

        }

        return curso;
    }
}
