package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoJDBC;
import modelo.Curso;
import modelo.EstruturaCurricular;

public class EstruturaCurricularDao extends ConexaoJDBC {

    public void inserir(EstruturaCurricular estruturaCurricular) {
        try {
            String sql = "INSERT INTO estrutura_curricular(id_curso, semestre, disciplina) VALUES(?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, estruturaCurricular.getCurso().getId());
            stmt.setString(2, estruturaCurricular.getSemestre());
            stmt.setString(3, estruturaCurricular.getDisciplina());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EstruturaCurricular> listarPorCurso(int idCurso) {
        List<EstruturaCurricular> estruturas = new ArrayList<>();
        try {
            String sql = "SELECT ec.id, ec.semestre, ec.disciplina, c.nome AS curso_nome " +
                         "FROM estrutura_curricular ec " +
                         "JOIN curso c ON ec.id_curso = c.id " +
                         "WHERE ec.id_curso = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(rs.getInt("id_curso"), rs.getString("curso_nome"), null);
                EstruturaCurricular estruturaCurricular = new EstruturaCurricular(
                    rs.getInt("id"),
                    curso,
                    rs.getString("semestre"),
                    rs.getString("disciplina")
                );
                estruturas.add(estruturaCurricular);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estruturas;
    }

    public void atualizar(EstruturaCurricular estruturaCurricular) {
        try {
            String sql = "UPDATE estrutura_curricular SET semestre = ?, disciplina = ? WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, estruturaCurricular.getSemestre());
            stmt.setString(2, estruturaCurricular.getDisciplina());
            stmt.setInt(3, estruturaCurricular.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try {
            String sql = "DELETE FROM estrutura_curricular WHERE id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
