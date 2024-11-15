package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Statement;

import conexao.ConexaoJDBC;
import modelo.Aluno;
import modelo.Pessoa;

public class AlunoDao extends ConexaoJDBC {
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT a.id as id_aluno, a.matricula, p.* from aluno a, pessoa p "+
        "WHERE p.id = a.id_pessoa");
            while(rs.next()) {
                Pessoa pessoa = new Pessoa(
                    rs.getInt("id"), 
                    rs.getString("nome"), 
                    rs.getString("rg"), 
                    rs.getString("cpf"), 
                    rs.getString("logradouro"), 
                    rs.getString("numero"), 
                    rs.getString("bairro"), 
                    rs.getString("municipio"), 
                    rs.getString("uf"));
                Aluno aluno = new Aluno(
                    rs.getInt("id_aluno"),
                    rs.getString("matricula"), 
                    pessoa
                );
                alunos.add(aluno);
            }
            return alunos;
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

    public Aluno getAlunoById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aluno aluno = new Aluno();
        String sql = 
        "SELECT a.id, a.matricula, p.* from aluno a, pessoa p "+
        "WHERE p.id = a.id_pessoa AND a.id = ?";
        try {
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                aluno.setMatricula(rs.getString("matricula"));
            }
        } catch (Exception e) {

        }

        return aluno;
    }
}
