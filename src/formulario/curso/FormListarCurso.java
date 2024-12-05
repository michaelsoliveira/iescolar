package formulario.curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormListarCurso extends JFrame {
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btnInserir;
    private JButton btnExcluir;
    private JButton btnEditar;
    private DefaultTableModel modelo = new DefaultTableModel();
    CursoDao dao = new CursoDao();

    public FormCadastroCurso() {
        super("Cadastro de Cursos");
        createTable();
        createWindow();
    }

    public void createWindow() {
        btnInserir = new JButton("Inserir");
        btnExcluir = new JButton("Excluir");
        btnEditar = new JButton("Editar");

        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btnInserir);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 320);
        btnInserir.addActionListener(new BtnInserirListener());
        btnEditar.addActionListener(new BtnEditarListener());
        btnExcluir.addActionListener(new BtnExcluirListener());
    }

    public void createTable(){
        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        carregarTabela(modelo);
    }

    public static void carregarTabela(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        
        for (Curso curso : dao.getCursos()) {
            modelo.addRow(new Object[]{ curso.getId(), curso.getNome() });
        }
    }

    private class BtnInserirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormInserirCurso ic = new FormInserirCurso(modelo);
            ic.setVisible(true);
        }
    }

    private class BtnEditarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idCurso = (int) tabela.getValueAt(linhaSelecionada, 0);
                FormAtualizarCurso ac = new FormAtualizarCurso(modelo, idCurso, linhaSelecionada);
                ac.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necessário selecionar uma linha");
            }
        }

        private class BtnExcluirListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = -1;
                linhaSelecionada = tabela.getSelectedRow();
                if (linhaSelecionada >= 0) {
                    int idCurso = (int) tabela.getValueAt(linhaSelecionada, 0);
                    dao.remover(idCurso);
                    modelo.removeRow(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, 
                    "É necessário selecionar uma linha");
                }
            }
    }
}
