package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
                "SELECT a.id as id_aluno, a.matricula, p.* from aluno a JOIN pessoa p ON p.id = a.id_pessoa");
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
            String sql = "UPDATE aluno SET matricula = ?, nome = ? WHERE matricula = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, aluno.getMatricula());
            stmt.setString(2, aluno.getPessoa().getNome());
            stmt.setString(3, matricula);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserir(Aluno aluno) {
        try {
            String sqlPessoa = "INSERT INTO pessoa(nome, rg, cpf, logradouro, numero, bairro, municipio, uf) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtPessoa = getConnection().prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            stmtPessoa.setString(1, aluno.getPessoa().getNome());
            stmtPessoa.setString(2, aluno.getPessoa().getRg());
            stmtPessoa.setString(3, aluno.getPessoa().getCpf());
            stmtPessoa.setString(4, aluno.getPessoa().getLogradouro());
            stmtPessoa.setString(5, aluno.getPessoa().getNumero());
            stmtPessoa.setString(6, aluno.getPessoa().getBairro());
            stmtPessoa.setString(7, aluno.getPessoa().getMunicipio());
            stmtPessoa.setString(8, aluno.getPessoa().getUf());
            stmtPessoa.executeUpdate();

            ResultSet rsPessoa = stmtPessoa.getGeneratedKeys();
            if (rsPessoa.next()) {
                int idPessoa = rsPessoa.getInt(1);

                String sqlAluno = "INSERT INTO aluno(matricula, id_pessoa) VALUES(?, ?)";
                PreparedStatement stmtAluno = getConnection().prepareStatement(sqlAluno);
                stmtAluno.setString(1, aluno.getMatricula());
                stmtAluno.setInt(2, idPessoa);
                stmtAluno.executeUpdate();
            }
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
            e.printStackTrace();
        }
    }

    public Aluno getAlunoById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aluno aluno = null;
        String sql = 
        "SELECT a.id, a.matricula, p.* from aluno a JOIN pessoa p ON p.id = a.id_pessoa WHERE a.id = ?";
        try {
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Pessoa pessoa = new Pessoa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("rg"),
                    rs.getString("cpf"),
                    rs.getString("logradouro"),
                    rs.getString("numero"),
                    rs.getString("bairro"),
                    rs.getString("municipio"),
                    rs.getString("uf")
                );
                aluno = new Aluno(
                    rs.getInt("id"),
                    rs.getString("matricula"),
                    pessoa
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;
    }
}
