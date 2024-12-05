package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoJDBC;
import modelo.Aluno;
import modelo.Disciplina;
import modelo.Nota;

public class NotaDao extends ConexaoJDBC {

    public void lancarNota(Nota nota) {
        try {
            String sql = "INSERT INTO nota(id_aluno, id_disciplina, valor) VALUES(?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, nota.getAluno().getId());  // ID do aluno
            stmt.setInt(2, nota.getDisciplina().getId());  // ID da disciplina
            stmt.setFloat(3, nota.getValorNota());  // Valor da nota
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Nota> listarNotasPorAluno(int idAluno) {
        List<Nota> notas = new ArrayList<>();
        try {
            String sql = "SELECT n.id, n.valor, a.matricula, d.nome FROM nota n " +
                         "JOIN aluno a ON n.id_aluno = a.id " +
                         "JOIN disciplina d ON n.id_disciplina = d.id " +
                         "WHERE n.id_aluno = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt("id_aluno"), rs.getString("matricula"), null);
                Disciplina disciplina = new Disciplina(rs.getInt("id_disciplina"), rs.getString("nome"));
                Nota nota = new Nota(rs.getInt("id"), aluno, disciplina, rs.getFloat("valor"));
                notas.add(nota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notas;
    }

    public void atualizarNota(Nota nota) {
        try {
            String sql = "UPDATE nota SET valor = ? WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setFloat(1, nota.getValorNota());
            stmt.setInt(2, nota.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirNota(int idNota) {
        try {
            String sql = "DELETE FROM nota WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, idNota);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
