package formulario.curso;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class FormInserirCurso extends JFrame {
    private DefaultTableModel modelo = new DefaultTableModel();
    
    public FormInserirCurso(DefaultTableModel md) {
        super("Cadastrar Curso");
        modelo = md;
    }
}
