package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import conexao.ConexaoJDBC;
import modelo.Aluno;

public class AlunoDao extends ConexaoJDBC {
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM aluno;");
            while(rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getString("matricula"), 
                    rs.getString("nome")
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
                            ", nome = " + aluno.getNome() + 
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
            preparedStatement.setString(2, aluno.getNome());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
