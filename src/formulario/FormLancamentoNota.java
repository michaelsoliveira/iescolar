package formulario;
import javax.swing.*;

import modelo.Turma;

import java.awt.*;
import java.awt.event.*;

public class FormLancamentoNota extends JFrame {
    private JTextField txtNomeTurma;
    private JButton btnCadastrar;

    public FormLancamentoNota() {
        setTitle("Lançamento de Nota");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lblNomeTurma = new JLabel("Disciplina:");
        txtNomeTurma = new JTextField(25);
        btnCadastrar = new JButton("Lançar Nota");

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeTurma = txtNomeTurma.getText();
                Turma turma = new Turma(nomeTurma);
                JOptionPane.showMessageDialog(null, "Turma " + 
                turma.getNome() + " cadastrada com sucesso!");
            }
        });

        add(lblNomeTurma);
        add(txtNomeTurma);
        add(btnCadastrar);
    }
}

