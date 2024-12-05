package formulario;

import dao.CursoDao;
import dao.EstruturaCurricularDao;
import modelo.Curso;
import modelo.EstruturaCurricular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadastroCursoEstrutura extends JFrame {
    private JTextField txtNomeCurso;
    private JTextArea txtDescricaoCurso;
    private JTextField txtSemestre;
    private JTextField txtDisciplina;
    private JButton btnSalvarCurso;
    private JButton btnSalvarEstrutura;
    private JComboBox<Curso> comboCursos;
    private DefaultComboBoxModel<Curso> modeloCursos;

    private CursoDao cursoDao;
    private EstruturaCurricularDao estruturaCurricularDao;

    public FormCadastroCursoEstrutura() {
        setTitle("Cadastro de Curso e Estrutura Curricular");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        cursoDao = new CursoDao();
        estruturaCurricularDao = new EstruturaCurricularDao();

        JLabel lblNomeCurso = new JLabel("Nome do Curso:");
        txtNomeCurso = new JTextField(25);

        JLabel lblDescricaoCurso = new JLabel("Descrição do Curso:");
        txtDescricaoCurso = new JTextArea(5, 25);
        txtDescricaoCurso.setWrapStyleWord(true);
        txtDescricaoCurso.setLineWrap(true);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricaoCurso);

        btnSalvarCurso = new JButton("Salvar Curso");

        JLabel lblCurso = new JLabel("Selecione o Curso:");
        modeloCursos = new DefaultComboBoxModel<>();
        for (Curso curso : cursoDao.listarTodos()) {
            modeloCursos.addElement(curso);
        }
        comboCursos = new JComboBox<>(modeloCursos);

        JLabel lblSemestre = new JLabel("Semestre:");
        txtSemestre = new JTextField(10);

        JLabel lblDisciplina = new JLabel("Disciplina:");
        txtDisciplina = new JTextField(20);

        btnSalvarEstrutura = new JButton("Salvar Estrutura Curricular");

        btnSalvarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCurso = txtNomeCurso.getText();
                String descricaoCurso = txtDescricaoCurso.getText();

                if (nomeCurso.isEmpty() || descricaoCurso.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos do Curso!");
                } else {
                    Curso curso = new Curso(0, nomeCurso, descricaoCurso);
                    cursoDao.inserir(curso);
                    modeloCursos.addElement(curso);
                    JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!");
                }
            }
        });

        btnSalvarEstrutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso cursoSelecionado = (Curso) comboCursos.getSelectedItem();
                String semestre = txtSemestre.getText();
                String disciplina = txtDisciplina.getText();

                if (cursoSelecionado == null || semestre.isEmpty() || disciplina.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos da Estrutura Curricular!");
                } else {
                    EstruturaCurricular estruturaCurricular = new EstruturaCurricular(0, cursoSelecionado, semestre, disciplina);
                    estruturaCurricularDao.inserir(estruturaCurricular);
                    JOptionPane.showMessageDialog(null, "Estrutura Curricular cadastrada com sucesso!");
                }
            }
        });

        add(lblNomeCurso);
        add(txtNomeCurso);
        add(lblDescricaoCurso);
        add(scrollDescricao);
        add(btnSalvarCurso);

        add(lblCurso);
        add(comboCursos);
        add(lblSemestre);
        add(txtSemestre);
        add(lblDisciplina);
        add(txtDisciplina);
        add(btnSalvarEstrutura);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormCadastroCursoEstrutura().setVisible(true);
            }
        });
    }
}
