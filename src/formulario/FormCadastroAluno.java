package formulario;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import dao.AlunoDao;
import modelo.Aluno;
import modelo.Pessoa;
import modelo.Turma;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormCadastroAluno extends JFrame {
    JTable tblAluno;
    JPanel painelFundo;
    JScrollPane barraRolagem;
    private JTextField txtNome;
    private JTextField txtMatricula;
    private JComboBox<String> comboTurmas;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private DefaultTableModel modelo = new DefaultTableModel();
    AlunoDao alunoDao = new AlunoDao();

    private void carregarTabela() {
        try {
            modelo.setRowCount(0);
            for (Aluno aluno : alunoDao.listarTodos()) {
                modelo.addRow(new Object[]{ 
                    aluno.getMatricula(),
                    aluno.getPessoa().getNome(),
                });
            }
            tblAluno.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FormCadastroAluno(List<Turma> turmas) {
        setTitle("Cadastro de Aluno");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        painelFundo = new JPanel();
        painelFundo.setSize(450, 100);
        painelFundo.setLayout(new BorderLayout());
        tblAluno = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Matricula");
        modelo.addColumn("Nome");
        tblAluno.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblAluno.getColumnModel().getColumn(1).setPreferredWidth(120);
        carregarTabela();
        barraRolagem = new JScrollPane(tblAluno);
        painelFundo.add(BorderLayout.CENTER, barraRolagem);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(25);
        JLabel lblMatricula = new JLabel("Matricula:");
        lblMatricula.setSize(200, 100);
        txtMatricula = new JTextField(25);
        btnCadastrar = new JButton("Cadastrar");
        btnEditar = new JButton("Editar");
        JLabel lblTurma = new JLabel("Turma:");
        comboTurmas = new JComboBox<>();
        comboTurmas.setSize(200, 50);
        for (Turma turma : turmas) {
            comboTurmas.addItem(turma.getNome());
        }

        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int linhaSelecionada = tblAluno.getSelectedRow();
                System.out.println(linhaSelecionada);
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String nome = txtNome.getText();
                Aluno aluno = new Aluno(0, matricula, new Pessoa(0, nome, "", "", "", "", "", "", ""));
                alunoDao.update(matricula, aluno);
                carregarTabela();
                JOptionPane.showMessageDialog(null, "O Aluno " + aluno.getPessoa().getNome() + " foi cadastrado com sucesso!");
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String nome = txtNome.getText();
                Aluno aluno = new Aluno(0, matricula, new Pessoa(0, nome, "", "", "", "", "", "", ""));
                alunoDao.inserir(aluno);
                carregarTabela();
                JOptionPane.showMessageDialog(null, "O Aluno " + aluno.getPessoa().getNome() + " foi cadastrado com sucesso!");
            }
        });

        add(lblMatricula);
        add(txtMatricula);
        add(lblNome);
        add(txtNome);
        add(lblTurma);
        add(comboTurmas);
        add(btnCadastrar);
        add(btnEditar);
        add(painelFundo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormCadastroDisciplina().setVisible(true);
            }
        });
    }
}
