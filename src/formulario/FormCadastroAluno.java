package formulario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.AlunoDao;
import modelo.Aluno;
import modelo.Turma;

import java.awt.*;
import java.awt.event.*;
import java.util.List;;

public class FormCadastroAluno extends JFrame{
    JTable tblAluno;
    JPanel painelFundo;
    JScrollPane barraRolagem;
    private JTextField txtNome;
    private JTextField txtMatricula;
    private JComboBox<String> comboTurmas;
    private JButton btnCadastrar;
    private DefaultTableModel modelo = new DefaultTableModel();

    public FormCadastroAluno(List<Turma> turmas) {
        setTitle("Cadastro de Aluno");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        painelFundo = new JPanel();
        painelFundo.setSize(450, 100);
        painelFundo.setLayout(new BorderLayout());
        tblAluno = new JTable(modelo);
        modelo.addColumn("Matricula");
        modelo.addColumn("Nome");
        tblAluno.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblAluno.getColumnModel().getColumn(1).setPreferredWidth(120);
        
        AlunoDao alunoDao = new AlunoDao();
        int tamanho = alunoDao.listarTodos().size();
        String[] colunas = { "Matricula", "Nome" };
        String[][] dados = new String[tamanho][2];
        
        for (int i = 0; i < tamanho; i++) {
            Aluno aluno = alunoDao.listarTodos().get(i);
            dados[i][0] = aluno.getMatricula();
            dados[i][1] = aluno.getNome();
        }
        modelo = new DefaultTableModel(dados, colunas);
        tblAluno.setModel(modelo);

        for (Aluno aluno : alunoDao.listarTodos()) {
            modelo.addRow(new Object[]{ 
                aluno.getMatricula(),
                aluno.getNome()
             });
        }
        barraRolagem = new JScrollPane(tblAluno);
        painelFundo.add(BorderLayout.CENTER, barraRolagem);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(25);
        JLabel lblMatricula = new JLabel("Matricula:");
        lblMatricula.setSize(200, 100);
        txtMatricula = new JTextField(25);
        btnCadastrar = new JButton("Cadastrar");

        JLabel lblTurma = new JLabel("Turma:");
        comboTurmas = new JComboBox<>();
        comboTurmas.setSize(200, 50);
        for(Turma turma : turmas) {
            comboTurmas.addItem(turma.getNome());
        }

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                String nome = txtNome.getText();
                String turma = (String) comboTurmas.getSelectedItem();
                Aluno aluno = new Aluno(matricula, nome);
                
                JOptionPane.showMessageDialog(null, "O Aluno " + 
                aluno.getNome() + " foi cadastrado com sucesso!");
            }
        });

        add(lblMatricula);
        add(txtMatricula);
        add(lblNome);
        add(txtNome);
        add(lblTurma);
        add(comboTurmas);
        add(btnCadastrar);
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
