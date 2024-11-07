package dao;

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
}
