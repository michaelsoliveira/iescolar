package formulario.curso;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import dao.CursoDao;
import modelo.Curso;

public class FormAtualizarCurso extends JFrame {
    private DefaultTableModel modelo = new DefaultTableModel();
    Curso curso;
    private int linhaSelecionada;
    CursoDao dao = new CursoDao();
    
    public FormAtualizarCurso(DefaultTableModel md, int id, int linha) {
        super("Atualizar Curso");
        modelo = md;
        curso = dao.getCursoById(id);
    }
}
