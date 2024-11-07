package formulario;
import javax.swing.*;

import modelo.Turma;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormPrincipal extends JFrame {
    private JButton btnCadAluno;
    private JButton btnCadTurma;
    private JButton btnCadDisciplina;
    private JButton btnCadProfessor;
    private List<Turma> turmas;

    public FormPrincipal() {
        setTitle("Sistema de Gerenciamento Escolar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        btnCadDisciplina = new JButton("Cadastrar Disciplina");
        btnCadAluno = new JButton("Cadastrar Aluno");
        btnCadTurma = new JButton("Cadastrar Turma");
        btnCadProfessor = new JButton("Cadastrar Professor");
        turmas = new ArrayList<Turma>();

        add(btnCadDisciplina);
        add(btnCadAluno);
        add(btnCadTurma);
        add(btnCadProfessor);

        btnCadDisciplina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroDisciplina frmCadDisciplina = new FormCadastroDisciplina();
                frmCadDisciplina.setVisible(true);
            }
        });

        btnCadTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroTurma frmCadTurma = new FormCadastroTurma(turmas);
                frmCadTurma.setVisible(true);
            }
        });

        btnCadAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroAluno frmCadAluno = new FormCadastroAluno(turmas);
                frmCadAluno.setVisible(true);
            }
        });
    }
}
