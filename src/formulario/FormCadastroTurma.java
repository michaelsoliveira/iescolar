package formulario;
import javax.swing.*;

import modelo.Turma;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormCadastroTurma extends JFrame {
    private JTextField txtNomeTurma;
    private JButton btnCadastrar;
    public Turma turma;

    public FormCadastroTurma(List<Turma> turmas) {
        setTitle("Cadastro de Turma");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lblNomeTurma = new JLabel("Nome da Turma:");
        txtNomeTurma = new JTextField(25);
        btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeTurma = txtNomeTurma.getText();
                turma = new Turma(nomeTurma);
                turmas.add(turma);
                JOptionPane.showMessageDialog(null, "Turma " + 
                turma.getNome() + " cadastrada com sucesso!");
            }
        });

        add(lblNomeTurma);
        add(txtNomeTurma);
        add(btnCadastrar);
    }
}
